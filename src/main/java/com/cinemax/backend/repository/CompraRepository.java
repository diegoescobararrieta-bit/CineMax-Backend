package com.cinemax.backend.repository;

import com.cinemax.backend.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Long> {

    List<Compra> findAllByOrderByFechaCompraDesc();

    List<Compra> findByFechaCompraBetweenOrderByFechaCompraDesc(LocalDateTime inicio, LocalDateTime fin);
}