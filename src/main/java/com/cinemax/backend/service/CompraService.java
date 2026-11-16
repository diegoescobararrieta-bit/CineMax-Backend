package com.cinemax.backend.service;

import com.cinemax.backend.dto.CompraRequest;
import com.cinemax.backend.exception.ConflictoException;
import com.cinemax.backend.exception.RecursoNoEncontradoException;
import com.cinemax.backend.model.Compra;
import com.cinemax.backend.model.Funcion;
import com.cinemax.backend.repository.CompraRepository;
import com.cinemax.backend.repository.FuncionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class CompraService {

    private static final BigDecimal CARGO_SERVICIO = new BigDecimal("1500.00");

    private final CompraRepository compraRepository;
    private final FuncionRepository funcionRepository;

    public CompraService(CompraRepository compraRepository, FuncionRepository funcionRepository) {
        this.compraRepository = compraRepository;
        this.funcionRepository = funcionRepository;
    }

    // Sin fechas: todo el historial. Solo "desde": ese día. "desde" y "hasta": rango.
    public List<Compra> listar(LocalDate desde, LocalDate hasta) {
        if (desde == null) {
            return compraRepository.findAllByOrderByFechaCompraDesc();
        }
        LocalDate fin = (hasta != null) ? hasta : desde;
        if (fin.isBefore(desde)) {
            throw new IllegalArgumentException("La fecha 'hasta' no puede ser anterior a 'desde'.");
        }
        return compraRepository.findByFechaCompraBetweenOrderByFechaCompraDesc(
                desde.atStartOfDay(), fin.atTime(LocalTime.MAX));
    }

    @Transactional
    public Compra crear(CompraRequest datos) {
        Funcion funcion = funcionRepository.buscarParaCompra(datos.getFuncionId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe la función con id " + datos.getFuncionId()));

        int cantidad = datos.getCantidadBoletos();

        // Regla 1: cupos insuficientes -> 409 y no se modifica nada
        if (cantidad > funcion.getCuposDisponibles()) {
            throw new ConflictoException("Cupos insuficientes: se solicitaron " + cantidad
                    + " boletas y solo quedan " + funcion.getCuposDisponibles() + ".");
        }

        // Regla 2: compra exitosa -> total = precio x cantidad (+ cargo) y se descuentan cupos
        BigDecimal precioUnitario = "GENERAL_PLUS".equals(datos.getUbicacion())
                ? funcion.getPrecioGeneralPlus()
                : funcion.getPrecio();
        BigDecimal total = precioUnitario.multiply(BigDecimal.valueOf(cantidad)).add(CARGO_SERVICIO);

        funcion.setCuposDisponibles(funcion.getCuposDisponibles() - cantidad);
        funcionRepository.save(funcion);

        Compra compra = new Compra();
        compra.setFuncion(funcion);
        compra.setNombreCliente(datos.getNombreCliente());
        compra.setCantidadBoletos(cantidad);
        compra.setUbicacion(datos.getUbicacion());
        compra.setPrecioUnitario(precioUnitario);
        compra.setCargoServicio(CARGO_SERVICIO);
        compra.setValorTotal(total);
        compra.setFechaCompra(LocalDateTime.now());
        return compraRepository.save(compra);
    }
}