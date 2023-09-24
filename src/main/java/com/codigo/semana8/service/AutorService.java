package com.codigo.semana8.service;

import com.codigo.semana8.model.Autor;
import com.codigo.semana8.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> obtenerTodosAutores(){

        List<Autor> autores = autorRepository.findAll();

        List<Autor> autoresNoEliminados = new ArrayList<>();

        autores.forEach(autor -> {
            if (autor.getEstado() == 1){
                autoresNoEliminados.add(autor);
            }
        });

        return autoresNoEliminados;
    }

    public Autor obtenerAutorPorId(Long id){
        Optional<Autor> autor = autorRepository.findById(id);

        if (autor.isPresent()){
            return autor.get();
        }else {
            throw new RuntimeException("Autor no encontrado");
        }

    }

    public Autor crearAutor(Autor autor){
        return autorRepository.save(autor);
    }

    public Autor actualizarAutor(Long id, Autor autor){
        Autor autorExistente = obtenerAutorPorId(id);

        autorExistente.setNombre(autor.getNombre());
        autorExistente.setEstado(autor.getEstado());

        return autorRepository.save(autorExistente);
    }

    public Boolean eliminarAutorPorId(Long id){
        Autor autorExistente = obtenerAutorPorId(id);

        if (autorExistente.getEstado() == 1){
            autorExistente.setEstado(0);
            autorRepository.save(autorExistente);
            return true;
        }else {
            return false;
        }

    }

}
