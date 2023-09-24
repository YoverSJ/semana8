package com.codigo.semana8.controller;

import com.codigo.semana8.model.Categoria;
import com.codigo.semana8.model.Libro;
import com.codigo.semana8.service.CategoriaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/categorias")
@Slf4j
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias(){
        return new ResponseEntity<>(categoriaService.obtenerCategorias(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria){
        return new ResponseEntity<>(categoriaService.crearCategoria(categoria), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long id){
        Categoria categoria = categoriaService.buscarCategoriaPorId(id);
        List<Libro> libro = categoria.getLibro();
        int[] cont = {0};
        libro.forEach (lb -> {
            log.info("Libro " + cont[0]);
            log.info(lb.getId() + " " + lb.getTitulo() + " " + lb.getEstado());
            cont[0]++;
        });
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria){
        return new ResponseEntity<>(categoriaService.actualizarCategoria(id, categoria), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> eliminarCategoriaPorId(@PathVariable Long id){
        if (categoriaService.eliminarCategoriaPorId(id)){
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
    }

}
