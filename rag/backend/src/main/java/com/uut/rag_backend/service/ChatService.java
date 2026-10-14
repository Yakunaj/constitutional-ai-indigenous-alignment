package com.uut.rag_backend.service;

import com.uut.rag_backend.dto.ChatRequestDTO;
import com.uut.rag_backend.dto.ChatResponseDTO;
import com.uut.rag_backend.dto.RagRetrievedChunkResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ChatService {

    private static final int DEFAULT_LIMIT = 5;
    private static final double DEFAULT_MIN_SIMILARITY = 0.2;
    private static final String DEFAULT_LANGUAGE = "es";
    private static final String DEFAULT_WORLDVIEW = "No especificada";

    private final OllamaEmbeddingService ollamaEmbeddingService;
    private final VectorSearchService vectorSearchService;
    private final RagPromptService ragPromptService;
    private final OllamaApiClient ollamaApiClient;

    public ChatService(OllamaEmbeddingService ollamaEmbeddingService,
                       VectorSearchService vectorSearchService,
                       RagPromptService ragPromptService,
                       OllamaApiClient ollamaApiClient) {
        this.ollamaEmbeddingService = ollamaEmbeddingService;
        this.vectorSearchService = vectorSearchService;
        this.ragPromptService = ragPromptService;
        this.ollamaApiClient = ollamaApiClient;
    }

    public ChatResponseDTO answerQuestion(ChatRequestDTO request) {
        validateRequest(request);

        long start = System.currentTimeMillis();

        String question = request.question().trim();
        int limit = resolveLimit(request.limit());
        double minSimilarity = resolveMinSimilarity(request.minSimilarity());
        String language = normalizeLanguage(request.language());

        float[] queryEmbedding = ollamaEmbeddingService.embed(question);
        List<RagRetrievedChunkResponse> retrievedChunks = vectorSearchService.searchBySimilarity(
                queryEmbedding,
                limit,
                minSimilarity
        );

        String prompt = ragPromptService.buildRagPrompt(question, retrievedChunks, DEFAULT_WORLDVIEW, language);

        String answer = generateAnswer(prompt);

        long latency = System.currentTimeMillis() - start;

        return new ChatResponseDTO(
                question,
                answer,
                retrievedChunks.stream().map(RagRetrievedChunkResponse::fragmentId).toList(),
                retrievedChunks.stream().map(RagRetrievedChunkResponse::similarity).toList(),
                retrievedChunks,
                latency,
                ollamaApiClient.getChatModel()
        );
    }

    public String generateAnswer(String prompt) {
        try {
            return ollamaApiClient.generate(prompt);
        } catch (IOException exception) {
            throw new IllegalStateException("Error generating response with Ollama", exception);
        }
    }

    private void validateRequest(ChatRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("request must not be null");
        }

        if (request.question() == null || request.question().isBlank()) {
            throw new IllegalArgumentException("request.question must not be null or blank");
        }
    }

    private int resolveLimit(Integer limit) {
        if (limit == null) {
            return DEFAULT_LIMIT;
        }

        if (limit <= 0) {
            throw new IllegalArgumentException("request.limit must be greater than 0");
        }

        return limit;
    }

    private double resolveMinSimilarity(Double minSimilarity) {
        if (minSimilarity == null) {
            return DEFAULT_MIN_SIMILARITY;
        }

        if (minSimilarity < 0.0 || minSimilarity > 1.0) {
            throw new IllegalArgumentException("request.minSimilarity must be between 0.0 and 1.0");
        }

        return minSimilarity;
    }

    private String normalizeLanguage(String language) {
        if (language == null || language.isBlank()) {
            return DEFAULT_LANGUAGE;
        }

        return language.trim();
    }
}
