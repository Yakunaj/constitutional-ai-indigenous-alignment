package com.uut.rag_backend.repository;

import com.uut.rag_backend.model.DocumentoRag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRagRepository extends JpaRepository<DocumentoRag, Long> {
}