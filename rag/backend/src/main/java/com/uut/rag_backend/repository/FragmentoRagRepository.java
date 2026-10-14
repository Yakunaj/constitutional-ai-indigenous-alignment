package com.uut.rag_backend.repository;

import com.uut.rag_backend.model.DocumentoRagEstado;
import com.uut.rag_backend.model.FragmentoRag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FragmentoRagRepository extends JpaRepository<FragmentoRag, Long> {

    @Query("""
            select f
            from FragmentoRag f
            join fetch f.documento d
            where f.embeddingModel = :embeddingModel
              and f.embeddingVector is not null
              and d.estado = :estado
            order by d.fechaIngesta desc, f.ordenFragmento asc
            """)
    List<FragmentoRag> findIndexableFragments(@Param("embeddingModel") String embeddingModel,
                                              @Param("estado") DocumentoRagEstado estado);
}