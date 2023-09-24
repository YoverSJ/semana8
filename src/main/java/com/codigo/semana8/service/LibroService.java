package com.codigo.semana8.service;

import com.codigo.semana8.model.Editor;
import com.codigo.semana8.model.Libro;
import com.codigo.semana8.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    
    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> obtenerTodosLibros(){
        List<Libro> libros = libroRepository.findAll();
        List<Libro> librosNoEliminadas = new ArrayList<>();

        libros.forEach(libro -> {
            if (libro.getEstado() == 1){
                librosNoEliminadas.add(libro);
            }
        });

        return librosNoEliminadas;
    }

    public Libro obtenerLibroPorId(Long id){
        Optional<Libro> libro = libroRepository.findById(id);

        if (libro.isPresent()){
            return libro.get();
        }else {
            throw new RuntimeException("Libro no encontrado");
        }

    }

    public Libro crearLibro(Libro libro){
        return libroRepository.save(libro);
    }

    public Libro actualizarLibro(Long id, Libro libro){
        Libro libroExistente = obtenerLibroPorId(id);

        libroExistente.setTitulo(libro.getTitulo());
        libroExistente.setEstado(libro.getEstado());
        libroExistente.setEditor(libro.getEditor());
        libroExistente.setAutor(libro.getAutor());
        libroExistente.setCategorias(libro.getCategorias());

        return libroRepository.save(libroExistente);
    }

    public Boolean eliminarLibroPorId(Long id){
        Libro libroExistente = obtenerLibroPorId(id);

        if (libroExistente.getEstado() == 1){
            libroExistente.setEstado(0);
            libroRepository.save(libroExistente);
            return true;
        }else {
            return false;
        }

    }

}
