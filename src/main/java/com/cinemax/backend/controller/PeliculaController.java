package com.cinemax.backend.controller;

import com.cinemax.backend.model.Pelicula;
import com.cinemax.backend.service.PeliculaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {

    private final PeliculaService service;

    public PeliculaController(PeliculaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Pelicula>> listar(@RequestParam(required = false) String titulo) {
        return ResponseEntity.ok(service.listar(titulo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<Pelicula> crear(@Valid @RequestBody Pelicula pelicula) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(pelicula));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizar(@PathVariable Long id, @Valid @RequestBody Pelicula pelicula) {
        return ResponseEntity.ok(service.actualizar(id, pelicula));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}