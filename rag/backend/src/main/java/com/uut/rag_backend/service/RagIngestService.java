package com.uut.rag_backend.service;

import com.uut.rag_backend.dto.DocumentoRagIngestRequest;
import com.uut.rag_backend.dto.DocumentoRagIngestResponse;
import com.uut.rag_backend.model.DocumentoRag;
import com.uut.rag_backend.model.DocumentoRagEstado;
import com.uut.rag_backend.model.FragmentoRag;
import com.uut.rag_backend.repository.DocumentoRagRepository;
import com.uut.rag_backend.repository.FragmentoRagRepository;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class RagIngestService {

    private final DocumentoRagRepository documentoRagRepository;
    private final FragmentoRagRepository fragmentoRagRepository;
    private final OllamaEmbeddingService ollamaEmbeddingService;
    private final ChunkingService chunkingService;

    @Value("${rag.storage-directory:${user.home}/rag-backend/rag-files}")
    private String storageDirectory;

    public RagIngestService(DocumentoRagRepository documentoRagRepository,
                            FragmentoRagRepository fragmentoRagRepository,
                            OllamaEmbeddingService ollamaEmbeddingService,
                            ChunkingService chunkingService) {
        this.documentoRagRepository = documentoRagRepository;
        this.fragmentoRagRepository = fragmentoRagRepository;
        this.ollamaEmbeddingService = ollamaEmbeddingService;
        this.chunkingService = chunkingService;
    }

    public DocumentoRagIngestResponse ingest(MultipartFile archivoPdf, DocumentoRagIngestRequest metadata) {
        validateRequest(archivoPdf, metadata);

        Path storageRoot = Paths.get(storageDirectory).toAbsolutePath().normalize();
        try {
            Files.createDirectories(storageRoot);
        } catch (IOException exception) {
            throw new IllegalStateException("No se pudo crear la ruta de almacenamiento del PDF", exception);
        }

        String originalFilename = resolveOriginalFilename(archivoPdf);
        Path storedPath = storageRoot.resolve(UUID.randomUUID() + "-" + sanitizeFilename(originalFilename)).normalize();

        DocumentoRag documento = new DocumentoRag();
        documento.setTitulo(metadata.titulo().trim());
        documento.setDescripcion(trimToNull(metadata.descripcion()));
        documento.setNombreArchivoOriginal(originalFilename);
        documento.setRutaAlmacenamiento(storedPath.toString());
        documento.setFechaIngesta(LocalDateTime.now());
        documento.setEstado(DocumentoRagEstado.PROCESANDO);
        documento = documentoRagRepository.save(documento);

        try {
            archivoPdf.transferTo(storedPath);

            String extractedText = extractPdfText(storedPath);
            List<String> chunks = chunkingService.chunk(extractedText);

            if (chunks.isEmpty()) {
                throw new IllegalStateException("No se generaron fragmentos a partir del PDF");
            }

            for (int index = 0; index < chunks.size(); index++) {
                String chunk = chunks.get(index);
                float[] embedding = ollamaEmbeddingService.embed(chunk);

                FragmentoRag fragmento = new FragmentoRag();
                fragmento.setDocumento(documento);
                fragmento.setContenido(chunk);
                fragmento.setOrdenFragmento(index);
                fragmento.setEmbeddingVector(floatArrayToBytes(embedding));
                fragmento.setEmbeddingModel(ollamaEmbeddingService.getEmbeddingModel());
                fragmento.setEmbeddingDimension(embedding.length);
                fragmento.setVectorNorm(vectorNorm(embedding));

                fragmentoRagRepository.save(fragmento);
            }

            documento.setEstado(DocumentoRagEstado.COMPLETADO);
            documento = documentoRagRepository.save(documento);

            return new DocumentoRagIngestResponse(
                    documento.getId(),
                    documento.getTitulo(),
                    documento.getNombreArchivoOriginal(),
                    documento.getRutaAlmacenamiento(),
                    documento.getEstado(),
                    chunks.size(),
                    documento.getFechaIngesta()
            );
        } catch (Exception exception) {
            documento.setEstado(DocumentoRagEstado.ERROR);
            documentoRagRepository.save(documento);
            throw new IllegalStateException("Error al ingerir el documento PDF", exception);
        }
    }

    private void validateRequest(MultipartFile archivoPdf, DocumentoRagIngestRequest metadata) {
        if (archivoPdf == null || archivoPdf.isEmpty()) {
            throw new IllegalArgumentException("archivoPdf must not be null or empty");
        }

        if (metadata == null) {
            throw new IllegalArgumentException("metadata must not be null");
        }

        if (metadata.titulo() == null || metadata.titulo().isBlank()) {
            throw new IllegalArgumentException("metadata.titulo must not be null or blank");
        }
    }

    private String extractPdfText(Path pdfPath) throws IOException {
        try (var document = Loader.loadPDF(pdfPath.toFile())) {
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(true);
            String text = stripper.getText(document);
            String normalized = normalizeExtractedText(text);

            if (normalized.isBlank()) {
                throw new IllegalStateException("El PDF no contiene texto extraíble");
            }

            return normalized;
        }
    }

    private String normalizeExtractedText(String text) {
        if (text == null) {
            return "";
        }

        return text.replace("\r\n", "\n")
                .replace('\r', '\n')
                .replaceAll("[\\t\\x0B\\f ]+", " ")
                .replaceAll("\\n{3,}", "\n\n")
                .trim();
    }

    private String resolveOriginalFilename(MultipartFile archivoPdf) {
        String originalFilename = archivoPdf.getOriginalFilename();
        if (originalFilename == null || originalFilename.isBlank()) {
            return "documento.pdf";
        }

        return originalFilename.trim();
    }

    private String sanitizeFilename(String filename) {
        String candidate = filename.trim();
        int lastSeparator = Math.max(candidate.lastIndexOf('/'), candidate.lastIndexOf('\\'));
        if (lastSeparator >= 0 && lastSeparator < candidate.length() - 1) {
            candidate = candidate.substring(lastSeparator + 1);
        }

        candidate = candidate.replaceAll("[^a-zA-Z0-9._-]", "_");
        if (candidate.isBlank()) {
            return "documento.pdf";
        }

        return candidate.toLowerCase(Locale.ROOT);
    }

    private String trimToNull(String value) {
        if (value == null) {
            return null;
        }

        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    private byte[] floatArrayToBytes(float[] vector) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(vector.length * 4);
        for (float value : vector) {
            byteBuffer.putFloat(value);
        }
        return byteBuffer.array();
    }

    private double vectorNorm(float[] vector) {
        double sum = 0.0;
        for (float value : vector) {
            sum += value * value;
        }
        return Math.sqrt(sum);
    }
}