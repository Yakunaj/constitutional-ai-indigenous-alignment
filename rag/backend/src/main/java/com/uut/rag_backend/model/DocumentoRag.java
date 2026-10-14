package com.uut.rag_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "archivos_rag")
public class DocumentoRag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 255)
    private String titulo;

    @Lob
    @Column(name = "descripcion", columnDefinition = "LONGTEXT")
    private String descripcion;

    @Column(name = "nombre_archivo_original", nullable = false, length = 255)
    private String nombreArchivoOriginal;

    @Column(name = "ruta_almacenamiento", nullable = false, length = 1024)
    private String rutaAlmacenamiento;

    @Column(name = "fecha_ingesta", nullable = false)
    private LocalDateTime fechaIngesta;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private DocumentoRagEstado estado;

    @OneToMany(mappedBy = "documento", orphanRemoval = true)
    private List<FragmentoRag> fragmentos = new ArrayList<>();

    @PrePersist
    public void onCreate() {
        if (fechaIngesta == null) {
            fechaIngesta = LocalDateTime.now();
        }

        if (estado == null) {
            estado = DocumentoRagEstado.PROCESANDO;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreArchivoOriginal() {
        return nombreArchivoOriginal;
    }

    public void setNombreArchivoOriginal(String nombreArchivoOriginal) {
        this.nombreArchivoOriginal = nombreArchivoOriginal;
    }

    public String getRutaAlmacenamiento() {
        return rutaAlmacenamiento;
    }

    public void setRutaAlmacenamiento(String rutaAlmacenamiento) {
        this.rutaAlmacenamiento = rutaAlmacenamiento;
    }

    public LocalDateTime getFechaIngesta() {
        return fechaIngesta;
    }

    public void setFechaIngesta(LocalDateTime fechaIngesta) {
        this.fechaIngesta = fechaIngesta;
    }

    public DocumentoRagEstado getEstado() {
        return estado;
    }

    public void setEstado(DocumentoRagEstado estado) {
        this.estado = estado;
    }

    public List<FragmentoRag> getFragmentos() {
        return fragmentos;
    }

    public void setFragmentos(List<FragmentoRag> fragmentos) {
        this.fragmentos = fragmentos;
    }
}