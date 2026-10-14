package com.uut.rag_backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OllamaApiClient {

    private static final MediaType JSON_MEDIA_TYPE = MediaType.get("application/json; charset=utf-8");

    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;

    @Value("${ollama.base-url:http://localhost:11434}")
    private String baseUrl;

    @Value("${ollama.chat-model:qwen2.5:3B}")
    private String chatModel;

    @Value("${ollama.embedding-model:qwen2.5:3B}")
    private String embeddingModel;

    public OllamaApiClient(OkHttpClient okHttpClient,
                           @Qualifier("ollamaObjectMapper") ObjectMapper objectMapper) {
        this.okHttpClient = okHttpClient;
        this.objectMapper = objectMapper;
    }

    public JsonNode getTags() throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl + "/api/tags")
                .get()
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            return readJsonResponse(response, "GET /api/tags");
        }
    }

    public String generate(String prompt) throws IOException {
        String payload = objectMapper.createObjectNode()
                .put("model", chatModel)
                .put("prompt", prompt)
                .put("stream", false)
                .toString();

        Request request = new Request.Builder()
                .url(baseUrl + "/api/generate")
                .post(RequestBody.create(payload, JSON_MEDIA_TYPE))
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            JsonNode jsonNode = readJsonResponse(response, "POST /api/generate");
            JsonNode generated = jsonNode.get("response");
            return generated != null ? generated.asText() : "";
        }
    }

    public double[] embeddings(String text) throws IOException {
        String payload = objectMapper.createObjectNode()
                .put("model", embeddingModel)
                .put("prompt", text)
                .toString();

        Request request = new Request.Builder()
                .url(baseUrl + "/api/embeddings")
                .post(RequestBody.create(payload, JSON_MEDIA_TYPE))
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            JsonNode jsonNode = readJsonResponse(response, "POST /api/embeddings");
            JsonNode embeddingNode = jsonNode.get("embedding");

            if (embeddingNode == null || !embeddingNode.isArray()) {
                return new double[0];
            }

            double[] embedding = new double[embeddingNode.size()];
            for (int i = 0; i < embeddingNode.size(); i++) {
                embedding[i] = embeddingNode.get(i).asDouble();
            }

            return embedding;
        }
    }

    public String getChatModel() {
        return chatModel;
    }

    private JsonNode readJsonResponse(Response response, String operation) throws IOException {
        if (!response.isSuccessful()) {
            String body = response.body() != null ? response.body().string() : "<empty body>";
            throw new IOException(operation + " failed with HTTP " + response.code() + ": " + body);
        }

        if (response.body() == null) {
            throw new IOException(operation + " returned an empty response body");
        }

        return objectMapper.readTree(response.body().string());
    }
}
