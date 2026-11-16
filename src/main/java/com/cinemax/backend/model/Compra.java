package com.cinemax.backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "funcion_id", nullable = false)
    private Funcion funcion;

    @Column(name = "nombre_cliente", nullable = false)
    private String nombreCliente;

    @Column(name = "cantidad_boletos", nullable = false)
    private Integer cantidadBoletos;

    @Column(name = "ubicacion", nullable = false)
    private String ubicacion = "GENERAL";

    @Column(name = "precio_unitario", nullable = false)
    private BigDecimal precioUnitario;

    @Column(name = "cargo_servicio", nullable = false)
    private BigDecimal cargoServicio = BigDecimal.ZERO;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDateTime fechaCompra;

    @PrePersist
    void antesDeGuardar() {
        if (fechaCompra == null) {
            fechaCompra = LocalDateTime.now();
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Funcion getFuncion() { return funcion; }
    public void setFuncion(Funcion funcion) { this.funcion = funcion; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public Integer getCantidadBoletos() { return cantidadBoletos; }
    public void setCantidadBoletos(Integer cantidadBoletos) { this.cantidadBoletos = cantidadBoletos; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
    public BigDecimal getCargoServicio() { return cargoServicio; }
    public void setCargoServicio(BigDecimal cargoServicio) { this.cargoServicio = cargoServicio; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
    public LocalDateTime getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(LocalDateTime fechaCompra) { this.fechaCompra = fechaCompra; }
}