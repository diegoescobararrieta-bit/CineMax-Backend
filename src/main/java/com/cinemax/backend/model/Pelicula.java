package com.cinemax.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "peliculas")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "titulo_original")
    private String tituloOriginal;

    @Column(name = "genero", nullable = false)
    private String genero;

    @Column(name = "duracion_min", nullable = false)
    private Integer duracionMin;

    @Column(name = "clasificacion", nullable = false)
    private String clasificacion;

    @Column(name = "sinopsis", columnDefinition = "TEXT")
    private String sinopsis;

    @Column(name = "reparto")
    private String reparto;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(name = "trailer_url")
    private String trailerUrl;

    @Column(name = "fecha_estreno")
    private LocalDate fechaEstreno;

    @Column(name = "estado", nullable = false)
    private String estado = "EN_CARTELERA";

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getTituloOriginal() { return tituloOriginal; }
    public void setTituloOriginal(String tituloOriginal) { this.tituloOriginal = tituloOriginal; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public Integer getDuracionMin() { return duracionMin; }
    public void setDuracionMin(Integer duracionMin) { this.duracionMin = duracionMin; }
    public String getClasificacion() { return clasificacion; }
    public void setClasificacion(String clasificacion) { this.clasificacion = clasificacion; }
    public String getSinopsis() { return sinopsis; }
    public void setSinopsis(String sinopsis) { this.sinopsis = sinopsis; }
    public String getReparto() { return reparto; }
    public void setReparto(String reparto) { this.reparto = reparto; }
    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }
    public String getTrailerUrl() { return trailerUrl; }
    public void setTrailerUrl(String trailerUrl) { this.trailerUrl = trailerUrl; }
    public LocalDate getFechaEstreno() { return fechaEstreno; }
    public void setFechaEstreno(LocalDate fechaEstreno) { this.fechaEstreno = fechaEstreno; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}