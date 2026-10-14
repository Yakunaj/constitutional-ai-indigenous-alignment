package com.uut.rag_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OllamaEmbeddingService {

    private final OllamaApiClient ollamaApiClient;

    @Value("${ollama.embedding-model}")
    private String embeddingModel;

    @Value("${ollama.embedding-dimension}")
    private int expectedDimension;

    public OllamaEmbeddingService(OllamaApiClient ollamaApiClient) {
        this.ollamaApiClient = ollamaApiClient;
    }

    public float[] embed(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("text must not be null or blank");
        }

        try {
            double[] embedding = ollamaApiClient.embeddings(text);
            validateEmbedding(embedding, text);

            float[] result = new float[embedding.length];
            for (int index = 0; index < embedding.length; index++) {
                result[index] = (float) embedding[index];
            }
            return result;
        } catch (IOException exception) {
            throw new IllegalStateException("Error generating embedding with Ollama", exception);
        }
    }

    public List<float[]> embedBatch(List<String> texts) {
        if (texts == null) {
            throw new IllegalArgumentException("texts must not be null");
        }

        List<float[]> embeddings = new ArrayList<>(texts.size());
        for (String text : texts) {
            embeddings.add(embed(text));
        }
        return embeddings;
    }

    public int getDimension() {
        return expectedDimension;
    }

    public String getEmbeddingModel() {
        return embeddingModel;
    }

    private void validateEmbedding(double[] embedding, String text) {
        if (embedding == null || embedding.length == 0) {
            throw new IllegalStateException("Ollama returned an empty embedding for the provided text");
        }

        if (expectedDimension > 0 && embedding.length != expectedDimension) {
            throw new IllegalStateException(
                    "Embedding dimension mismatch for model " + embeddingModel
                            + ": expected " + expectedDimension
                            + " but got " + embedding.length
                            + " for text length " + text.length());
        }
    }
}
