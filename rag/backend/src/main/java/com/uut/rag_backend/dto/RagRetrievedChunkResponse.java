package com.uut.rag_backend.dto;

public record RagRetrievedChunkResponse(
        Long fragmentId,
        Long documentoId,
        String tituloDocumento,
        String nombreArchivoOriginal,
        Integer ordenFragmento,
        String text,
        Double similarity
) {
}
