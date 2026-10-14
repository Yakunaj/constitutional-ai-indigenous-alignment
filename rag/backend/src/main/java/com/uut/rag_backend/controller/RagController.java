package com.uut.rag_backend.controller;

import com.uut.rag_backend.dto.DocumentoRagIngestRequest;
import com.uut.rag_backend.dto.DocumentoRagIngestResponse;
import com.uut.rag_backend.dto.RagHealthResponse;
import com.uut.rag_backend.dto.ChatRequestDTO;
import com.uut.rag_backend.dto.ChatResponseDTO;
import com.uut.rag_backend.repository.DocumentoRagRepository;
import com.uut.rag_backend.service.ChatService;
import com.uut.rag_backend.service.OllamaApiClient;
import com.uut.rag_backend.service.RagDocumentService;
import com.uut.rag_backend.service.RagIngestService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/chat")
public class RagController {

    private final RagIngestService ragIngestService;
    private final ChatService chatService;
    private final RagDocumentService ragDocumentService;
    private final OllamaApiClient ollamaApiClient;
    private final DocumentoRagRepository documentoRagRepository;

    public RagController(RagIngestService ragIngestService,
                         ChatService chatService,
                         RagDocumentService ragDocumentService,
                         OllamaApiClient ollamaApiClient,
                         DocumentoRagRepository documentoRagRepository) {
        this.ragIngestService = ragIngestService;
        this.chatService = chatService;
        this.ragDocumentService = ragDocumentService;
        this.ollamaApiClient = ollamaApiClient;
        this.documentoRagRepository = documentoRagRepository;
    }

    @PostMapping(value = "/ingest", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DocumentoRagIngestResponse> ingest(
            @RequestPart("archivo") MultipartFile archivo,
            @RequestPart("metadata") DocumentoRagIngestRequest metadata
    ) {
        return ResponseEntity.ok(ragIngestService.ingest(archivo, metadata));
    }

    @PostMapping("/")
    public ResponseEntity<ChatResponseDTO> query(@RequestBody ChatRequestDTO request) {
        return ResponseEntity.ok(chatService.answerQuestion(request));
    }

    @GetMapping("/documentos/{id}/pdf")
    public ResponseEntity<Resource> downloadPdfById(@PathVariable Long id) {
        RagDocumentService.PdfDownloadResource pdf = ragDocumentService.resolvePdfById(id);
        Resource resource = new FileSystemResource(pdf.filePath());

        ContentDisposition contentDisposition = ContentDisposition.attachment()
                .filename(pdf.filename(), StandardCharsets.UTF_8)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
                .contentType(MediaType.APPLICATION_PDF)
            .contentLength(pdf.fileSize())
                .body(resource);
    }

    @GetMapping("/health")
    public ResponseEntity<RagHealthResponse> health() {
        boolean ollamaReachable;
        try {
            ollamaApiClient.getTags();
            ollamaReachable = true;
        } catch (IOException exception) {
            ollamaReachable = false;
        }

        String status = ollamaReachable ? "UP" : "DEGRADED";

        return ResponseEntity.ok(new RagHealthResponse(
                status,
                ollamaReachable,
            documentoRagRepository.count()
        ));
    }
}
