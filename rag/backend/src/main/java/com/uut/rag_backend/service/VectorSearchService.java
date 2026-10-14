package com.uut.rag_backend.service;

import com.uut.rag_backend.dto.RagRetrievedChunkResponse;
import com.uut.rag_backend.model.DocumentoRagEstado;
import com.uut.rag_backend.model.FragmentoRag;
import com.uut.rag_backend.repository.FragmentoRagRepository;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.Comparator;
import java.util.List;

@Service
public class VectorSearchService {

    private final FragmentoRagRepository fragmentoRagRepository;
    private final OllamaEmbeddingService ollamaEmbeddingService;

    public VectorSearchService(FragmentoRagRepository fragmentoRagRepository,
                               OllamaEmbeddingService ollamaEmbeddingService) {
        this.fragmentoRagRepository = fragmentoRagRepository;
        this.ollamaEmbeddingService = ollamaEmbeddingService;
    }

    public List<RagRetrievedChunkResponse> searchBySimilarity(float[] queryEmbedding, int limit, double minSimilarity) {
        if (queryEmbedding == null || queryEmbedding.length == 0) {
            throw new IllegalArgumentException("queryEmbedding must not be null or empty");
        }
        if (limit <= 0) {
            throw new IllegalArgumentException("limit must be greater than 0");
        }
        if (minSimilarity < 0.0 || minSimilarity > 1.0) {
            throw new IllegalArgumentException("minSimilarity must be between 0.0 and 1.0");
        }

        String modelName = ollamaEmbeddingService.getEmbeddingModel();
        List<FragmentoRag> allFragments = fragmentoRagRepository.findIndexableFragments(
                modelName,
                DocumentoRagEstado.COMPLETADO
        );

        return allFragments.stream()
                .map(fragment -> {
                    float[] storedVector = bytesToFloatArray(fragment.getEmbeddingVector());
                    if (storedVector.length != queryEmbedding.length) {
                        return null;
                    }

                    double similarity = cosineSimilarity(queryEmbedding, storedVector);
                    return new RagRetrievedChunkResponse(
                            fragment.getId(),
                            fragment.getDocumento().getId(),
                            fragment.getDocumento().getTitulo(),
                            fragment.getDocumento().getNombreArchivoOriginal(),
                            fragment.getOrdenFragmento(),
                            fragment.getContenido(),
                            similarity
                    );
                })
                .filter(result -> result != null && result.similarity() >= minSimilarity)
                .sorted(Comparator.comparingDouble(RagRetrievedChunkResponse::similarity).reversed())
                .limit(limit)
                .toList();
    }

    private float[] bytesToFloatArray(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return new float[0];
        }

        float[] floats = new float[bytes.length / 4];
        ByteBuffer.wrap(bytes).asFloatBuffer().get(floats);
        return floats;
    }

    private double cosineSimilarity(float[] vec1, float[] vec2) {
        double dotProduct = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;

        for (int i = 0; i < vec1.length; i++) {
            dotProduct += vec1[i] * vec2[i];
            norm1 += vec1[i] * vec1[i];
            norm2 += vec2[i] * vec2[i];
        }

        if (norm1 == 0.0 || norm2 == 0.0) {
            return 0.0;
        }

        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }
}
