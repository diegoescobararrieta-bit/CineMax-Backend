package com.cinemax.backend.repository;

import com.cinemax.backend.model.Funcion;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FuncionRepository extends JpaRepository<Funcion, Long> {

    List<Funcion> findAllByOrderByFechaAscHoraAsc();

    List<Funcion> findByFechaOrderByHoraAsc(LocalDate fecha);

    // Bloquea la fila mientras se procesa una compra, para que dos compras
    // simultáneas no vendan los mismos cupos
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select f from Funcion f where f.id = :id")
    Optional<Funcion> buscarParaCompra(@Param("id") Long id);
}