package com.uut.rag_backend.dto;

public record ChatRequestDTO(
        String question,
        Integer limit,
        Double minSimilarity,
        String language
) {
}
