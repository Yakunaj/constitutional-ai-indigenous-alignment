package com.uut.rag_backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class OllamaClientConfig {

    @Value("${ollama.timeout-ms:30000}")
    private long timeoutMs;

    @Bean
    public OkHttpClient ollamaOkHttpClient() {
        Duration timeout = Duration.ofMillis(timeoutMs);

        return new OkHttpClient.Builder()
                .connectTimeout(timeout)
                .readTimeout(timeout)
                .writeTimeout(timeout)
                .callTimeout(timeout)
                .build();
    }

    @Bean(name = "ollamaObjectMapper")
    public ObjectMapper ollamaObjectMapper() {
        return new ObjectMapper();
    }
}
