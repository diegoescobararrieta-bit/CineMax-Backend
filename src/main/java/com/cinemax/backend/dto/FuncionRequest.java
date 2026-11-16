package com.cinemax.backend.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class FuncionRequest {

    @NotNull
    private Long peliculaId;
    @NotNull
    private LocalDate fecha;
    @NotNull
    private LocalTime hora;
    @NotBlank
    private String sala;
    @Pattern(regexp = "2D|3D|IMAX")
    private String formato = "2D";
    @Pattern(regexp = "DOBLADA|SUBTITULADA")
    private String idioma = "DOBLADA";
    @NotNull
    @PositiveOrZero
    private BigDecimal precio;
    @NotNull
    @PositiveOrZero
    private BigDecimal precioGeneralPlus;
    @NotNull
    @PositiveOrZero
    private Integer cuposDisponibles;

    public Long getPeliculaId() { return peliculaId; }
    public void setPeliculaId(Long peliculaId) { this.peliculaId = peliculaId; }
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