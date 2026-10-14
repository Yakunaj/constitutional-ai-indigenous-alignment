package com.uut.rag_backend.service;

import java.util.List;

public interface ChunkingService {

    List<String> chunk(String text);
}
