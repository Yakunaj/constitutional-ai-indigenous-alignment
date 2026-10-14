package com.uut.rag_backend.dto;

import com.uut.rag_backend.model.DocumentoRagEstado;

import java.time.LocalDateTime;

public record DocumentoRagIngestResponse(
        Long documentoId,
        String titulo,
        String nombreArchivoOriginal,
        String rutaAlmacenamiento,
        DocumentoRagEstado estado,
        int fragmentosProcesados,
        LocalDateTime fechaIngesta
) {
}