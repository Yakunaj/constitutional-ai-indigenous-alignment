package com.uut.rag_backend.service.impl;

import com.uut.rag_backend.service.ChunkingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChunkingServiceImpl implements ChunkingService {

    @Value("${rag.chunk-size-chars:1200}")
    private int chunkSizeChars;

    @Value("${rag.chunk-overlap-chars:200}")
    private int chunkOverlapChars;

    @Override
    public List<String> chunk(String text) {
        if (text == null || text.isBlank()) {
            return List.of();
        }

        String normalized = normalizeChunkSource(text);
        List<String> chunks = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (String paragraph : normalized.split("\\n+")) {
            String trimmedParagraph = paragraph.trim();
            if (trimmedParagraph.isEmpty()) {
                continue;
            }

            for (String sentence : trimmedParagraph.split("(?<=[.!?])\\s+")) {
                String trimmedSentence = sentence.trim();
                if (trimmedSentence.isEmpty()) {
                    continue;
                }

                if (trimmedSentence.length() > chunkSizeChars) {
                    if (current.length() > 0) {
                        chunks.add(current.toString().trim());
                        current.setLength(0);
                    }

                    chunks.addAll(splitLongText(trimmedSentence));
                    continue;
                }

                if (current.length() == 0) {
                    current.append(trimmedSentence);
                } else if (current.length() + 1 + trimmedSentence.length() <= chunkSizeChars) {
                    current.append(' ').append(trimmedSentence);
                } else {
                    chunks.add(current.toString().trim());
                    current.setLength(0);
                    current.append(trimmedSentence);
                }
            }
        }

        if (current.length() > 0) {
            chunks.add(current.toString().trim());
        }

        return chunks.stream()
                .map(String::trim)
                .filter(chunk -> !chunk.isEmpty())
                .toList();
    }

    private List<String> splitLongText(String text) {
        List<String> chunks = new ArrayList<>();
        int effectiveChunkSize = Math.max(chunkSizeChars, 1);
        int effectiveOverlap = Math.max(Math.min(chunkOverlapChars, effectiveChunkSize - 1), 0);
        int step = Math.max(effectiveChunkSize - effectiveOverlap, 1);

        for (int start = 0; start < text.length(); start += step) {
            int end = Math.min(start + effectiveChunkSize, text.length());
            String chunk = text.substring(start, end).trim();
            if (!chunk.isEmpty()) {
                chunks.add(chunk);
            }

            if (end >= text.length()) {
                break;
            }
        }

        return chunks;
    }

    private String normalizeChunkSource(String text) {
        return text.replace("\r\n", "\n")
                .replace('\r', '\n')
                .replaceAll("[\\t\\x0B\\f ]+", " ")
                .trim();
    }
}
