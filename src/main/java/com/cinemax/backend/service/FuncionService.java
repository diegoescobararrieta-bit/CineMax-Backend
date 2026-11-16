package com.cinemax.backend.service;

import com.cinemax.backend.dto.FuncionRequest;
import com.cinemax.backend.exception.RecursoNoEncontradoException;
import com.cinemax.backend.model.Funcion;
import com.cinemax.backend.repository.FuncionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class FuncionService {

    private final FuncionRepository repository;
    private final PeliculaService peliculaService;

    public FuncionService(FuncionRepository repository, PeliculaService peliculaService) {
        this.repository = repository;
        this.peliculaService = peliculaService;
    }

    public List<Funcion> listar(LocalDate fecha) {
        if (fecha == null) {
            return repository.findAllByOrderByFechaAscHoraAsc();
        }
        return repository.findByFechaOrderByHoraAsc(fecha);
    }

    public Funcion obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe la función con id " + id));
    }

    @Transactional
    public Funcion crear(FuncionRequest datos) {
        Funcion funcion = new Funcion();
        aplicar(funcion, datos);
        return repository.save(funcion);
    }

    @Transactional
    public Funcion actualizar(Long id, FuncionRequest datos) {
        Funcion funcion = obtener(id);
        aplicar(funcion, datos);
        return repository.save(funcion);
    }

    @Transactional
    public void eliminar(Long id) {
        Funcion funcion = obtener(id);
        repository.delete(funcion);
        repository.flush();
    }

    private void aplicar(Funcion funcion, FuncionRequest datos) {
        if (datos.getPrecioGeneralPlus().compareTo(datos.getPrecio()) < 0) {
            throw new IllegalArgumentException("El precio General+ no puede ser menor que el precio General.");
        }
        funcion.setPelicula(peliculaService.obtener(datos.getPeliculaId()));
        funcion.setFecha(datos.getFecha());
        funcion.setHora(datos.getHora());
        funcion.setSala(datos.getSala());
        funcion.setFormato(datos.getFormato());
        funcion.setIdioma(datos.getIdioma());
        funcion.setPrecio(datos.getPrecio());
        funcion.setPrecioGeneralPlus(datos.getPrecioGeneralPlus());
        funcion.setCuposDisponibles(datos.getCuposDisponibles());
    }
}