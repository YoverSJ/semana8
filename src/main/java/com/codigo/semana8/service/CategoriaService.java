package com.codigo.semana8.service;

import com.codigo.semana8.model.Categoria;
import com.codigo.semana8.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> obtenerCategorias(){
        List<Categoria> categorias = categoriaRepository.findAll();
        List<Categoria> categoriasNoEliminadas = new ArrayList<>();

        categorias.forEach(categoria -> {
            if (categoria.getEstado() == 1){
                categoriasNoEliminadas.add(categoria);
            }
        });

        return categoriasNoEliminadas;
    }

    public Categoria buscarCategoriaPorId(Long id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (categoria.isPresent()){
            return categoria.get();
        }else {
            throw new RuntimeException("No existe la Categoria");
        }
    }

    public Categoria crearCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizarCategoria(Long id, Categoria categoria){
        Categoria categoriaExistente = buscarCategoriaPorId(id);

        categoriaExistente.setNombre(categoria.getNombre());
        categoriaExistente.setEstado(categoria.getEstado());

        return categoriaRepository.save(categoriaExistente);

    }

    public Boolean eliminarCategoriaPorId(Long id){
        Categoria categoriaExistente = buscarCategoriaPorId(id);

        if (categoriaExistente.getEstado() == 1){
            categoriaExistente.setEstado(0);
            categoriaRepository.save(categoriaExistente);
            return true;
        }else {
            return false;
        }

    }

}
