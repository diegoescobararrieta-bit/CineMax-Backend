package com.cinemax.backend.dto;

import jakarta.validation.constraints.*;

public class CompraRequest {

    @NotNull
    private Long funcionId;
    @NotBlank
    private String nombreCliente;
    @NotNull
    @Positive
    private Integer cantidadBoletos;
    @Pattern(regexp = "GENERAL|GENERAL_PLUS")
    private String ubicacion = "GENERAL";

    public Long getFuncionId() { return funcionId; }
    public void setFuncionId(Long funcionId) { this.funcionId = funcionId; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public Integer getCantidadBoletos() { return cantidadBoletos; }
    public void setCantidadBoletos(Integer cantidadBoletos) { this.cantidadBoletos = cantidadBoletos; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
}