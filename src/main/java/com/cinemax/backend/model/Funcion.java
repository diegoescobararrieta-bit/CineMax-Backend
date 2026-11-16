package com.cinemax.backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "funciones")
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pelicula_id", nullable = false)
    private Pelicula pelicula;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora", nullable = false)
    private LocalTime hora;

    @Column(name = "sala", nullable = false)
    private String sala;

    @Column(name = "formato", nullable = false)
    private String formato = "2D";

    @Column(name = "idioma", nullable = false)
    private String idioma = "DOBLADA";

    @Column(name = "precio", nullable = false)
    private BigDecimal precio;

    @Column(name = "precio_general_plus", nullable = false)
    private BigDecimal precioGeneralPlus;

    @Column(name = "cupos_disponibles", nullable = false)
    private Integer cuposDisponibles;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Pelicula getPelicula() { return pelicula; }
    public void setPelicula(Pelicula pelicula) { this.pelicula = pelicula; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }
    public String getSala() { return sala; }
    public void setSala(String sala) { this.sala = sala; }
    public String getFormato() { return formato; }
    public void setFormato(String formato) { this.formato = formato; }
    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public BigDecimal getPrecioGeneralPlus() { return precioGeneralPlus; }
    public void setPrecioGeneralPlus(BigDecimal precioGeneralPlus) { this.precioGeneralPlus = precioGeneralPlus; }
    public Integer getCuposDisponibles() { return cuposDisponibles; }
    public void setCuposDisponibles(Integer cuposDisponibles) { this.cuposDisponibles = cuposDisponibles; }
}