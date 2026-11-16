package com.cinemax.backend.controller;

import com.cinemax.backend.dto.FuncionRequest;
import com.cinemax.backend.model.Funcion;
import com.cinemax.backend.service.FuncionService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/funciones")
public class FuncionController {

    private final FuncionService service;

    public FuncionController(FuncionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Funcion>> listar(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(service.listar(fecha));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcion> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<Funcion> crear(@Valid @RequestBody FuncionRequest datos) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(datos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcion> actualizar(@PathVariable Long id, @Valid @RequestBody FuncionRequest datos) {
        return ResponseEntity.ok(service.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}