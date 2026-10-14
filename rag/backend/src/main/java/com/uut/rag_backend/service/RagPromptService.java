package com.uut.rag_backend.service;

import com.uut.rag_backend.dto.RagRetrievedChunkResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RagPromptService {

    private static final String DEFAULT_LANGUAGE = "es";
    private static final String DEFAULT_WORLDVIEW = "No especificada";

    public String buildRagPrompt(String question,
                                 List<RagRetrievedChunkResponse> chunks,
                                 String worldview,
                                 String language) {
        List<RagRetrievedChunkResponse> safeChunks = chunks == null ? List.of() : chunks;
        String normalizedWorldview = normalizeOrDefault(worldview, DEFAULT_WORLDVIEW);
        String normalizedLanguage = normalizeOrDefault(language, DEFAULT_LANGUAGE);

        StringBuilder prompt = new StringBuilder();

        prompt.append("[SYSTEM]\n");
        prompt.append("Eres un asistente RAG especializado en transcripciones orales y cosmovisiones indígenas.\n");
        if (safeChunks.isEmpty()) {
            prompt.append("No hay contexto recuperado. Responde con cautela y evita inventar datos específicos.\n\n");
        } else {
            prompt.append("Responde usando el contexto recuperado y señala cuando falte información.\n\n");
        }

        prompt.append("[WORLDVIEW]\n").append(normalizedWorldview).append("\n\n");

        prompt.append("[RETRIEVED_CONTEXT]\n");
        if (safeChunks.isEmpty()) {
            prompt.append("Sin contexto recuperado.\n\n");
        } else {
            for (int i = 0; i < safeChunks.size(); i++) {
                RagRetrievedChunkResponse chunk = safeChunks.get(i);
                prompt.append(i + 1)
                        .append(". [fragmentId=").append(chunk.fragmentId())
                        .append(", documentoId=").append(chunk.documentoId())
                        .append(", titulo=").append(chunk.tituloDocumento())
                        .append(", archivo=").append(chunk.nombreArchivoOriginal())
                        .append(", orden=").append(chunk.ordenFragmento())
                        .append(", similarity=").append(chunk.similarity())
                        .append("]\n")
                        .append(chunk.text())
                        .append("\n\n");
            }
        }

        prompt.append("[QUESTION]\n").append(question.trim()).append("\n\n");
        prompt.append("[INSTRUCTION]\n");
        prompt.append("Responde en ").append(normalizedLanguage).append(".");

        return prompt.toString();
    }

    private String normalizeOrDefault(String value, String defaultValue) {
        if (value == null || value.isBlank()) {
            return defaultValue;
        }

        return value.trim();
    }
}
