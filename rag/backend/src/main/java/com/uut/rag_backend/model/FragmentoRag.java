package com.uut.rag_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "fragmentos_rag")
public class FragmentoRag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "documento_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_fragmento_documento_rag")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DocumentoRag documento;

    @Lob
    @Column(name = "contenido", columnDefinition = "LONGTEXT", nullable = false)
    private String contenido;

    @Column(name = "orden_fragmento", nullable = false)
    private Integer ordenFragmento;

    @Lob
    @Column(name = "embedding_vector", columnDefinition = "LONGBLOB", nullable = false)
    private byte[] embeddingVector;

    @Column(name = "embedding_model", nullable = false, length = 255)
    private String embeddingModel;

    @Column(name = "embedding_dimension", nullable = false)
    private Integer embeddingDimension;

    @Column(name = "vector_norm", nullable = false)
    private Double vectorNorm;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DocumentoRag getDocumento() {
        return documento;
    }

    public void setDocumento(DocumentoRag documento) {
        this.documento = documento;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Integer getOrdenFragmento() {
        return ordenFragmento;
    }

    public void setOrdenFragmento(Integer ordenFragmento) {
        this.ordenFragmento = ordenFragmento;
    }

    public byte[] getEmbeddingVector() {
        return embeddingVector;
    }

    public void setEmbeddingVector(byte[] embeddingVector) {
        this.embeddingVector = embeddingVector;
    }

    public String getEmbeddingModel() {
        return embeddingModel;
    }

    public void setEmbeddingModel(String embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    public Integer getEmbeddingDimension() {
        return embeddingDimension;
    }

    public void setEmbeddingDimension(Integer embeddingDimension) {
        this.embeddingDimension = embeddingDimension;
    }

    public Double getVectorNorm() {
        return vectorNorm;
    }

    public void setVectorNorm(Double vectorNorm) {
        this.vectorNorm = vectorNorm;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}