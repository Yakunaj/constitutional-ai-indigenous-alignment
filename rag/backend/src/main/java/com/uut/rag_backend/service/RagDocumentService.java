package com.uut.rag_backend.service;

import com.uut.rag_backend.exception.ResourceNotFoundException;
import com.uut.rag_backend.model.DocumentoRag;
import com.uut.rag_backend.repository.DocumentoRagRepository;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class RagDocumentService {

    private final DocumentoRagRepository documentoRagRepository;

    public RagDocumentService(DocumentoRagRepository documentoRagRepository) {
        this.documentoRagRepository = documentoRagRepository;
    }

    public PdfDownloadResource resolvePdfById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }

        DocumentoRag documento = documentoRagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe documento con id=" + id));

        Path pdfPath = Paths.get(documento.getRutaAlmacenamiento()).toAbsolutePath().normalize();
        if (!Files.exists(pdfPath) || !Files.isReadable(pdfPath) || !Files.isRegularFile(pdfPath)) {
            throw new ResourceNotFoundException("No se encontro el PDF para id=" + id);
        }

        long fileSize;
        try {
            fileSize = Files.size(pdfPath);
        } catch (Exception exception) {
            throw new IllegalStateException("No se pudo leer tamano del PDF para id=" + id, exception);
        }

        String filename = documento.getNombreArchivoOriginal();
        if (filename == null || filename.isBlank()) {
            filename = "documento-" + id + ".pdf";
        }

        return new PdfDownloadResource(filename, pdfPath, fileSize);
    }

    public record PdfDownloadResource(String filename, Path filePath, long fileSize) {
    }
}