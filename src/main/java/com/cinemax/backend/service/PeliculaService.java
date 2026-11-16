package com.cinemax.backend.service;

import com.cinemax.backend.exception.RecursoNoEncontradoException;
import com.cinemax.backend.model.Pelicula;
import com.cinemax.backend.repository.PeliculaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PeliculaService {

    private final PeliculaRepository repository;

    public PeliculaService(PeliculaRepository repository) {
        this.repository = repository;
    }

    public List<Pelicula> listar(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            return repository.findAll();
        }
        return repository.findByTituloContainingIgnoreCase(titulo);
    }

    public Pelicula obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe la película con id " + id));
    }

    @Transactional
    public Pelicula crear(Pelicula pelicula) {
        pelicula.setId(null);
        return repository.save(pelicula);
    }

    @Transactional
    public Pelicula actualizar(Long id, Pelicula datos) {
        obtener(id);
        datos.setId(id);
        return repository.save(datos);
    }

    @Transactional
    public void eliminar(Long id) {
        Pelicula pelicula = obtener(id);
        repository.delete(pelicula);
        repository.flush();
    }
}