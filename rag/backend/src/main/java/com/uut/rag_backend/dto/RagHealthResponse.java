package com.uut.rag_backend.dto;

public record RagHealthResponse(
        String status,
        boolean ollamaReachable,
        long indexedUnits
) {
}
