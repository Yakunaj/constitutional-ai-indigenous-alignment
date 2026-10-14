package com.uut.rag_backend.dto;

import java.util.List;

public record ChatResponseDTO(
        String question,
        String answer,
        List<Long> retrievedIds,
        List<Double> similarities,
        List<RagRetrievedChunkResponse> retrievedChunks,
        long latencyMs,
        String model
) {
}
