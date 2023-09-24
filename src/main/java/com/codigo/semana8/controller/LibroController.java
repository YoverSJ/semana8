package com.codigo.semana8.controller;

import com.codigo.semana8.model.Libro;
import com.codigo.semana8.service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<Libro>> listarLibros(){
        return new ResponseEntity<>(libroService.obtenerTodosLibros(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro){
        return new ResponseEntity<>(libroService.crearLibro(libro), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id){
        Libro libro = libroService.obtenerLibroPorId(id);
        return new ResponseEntity<>(libro, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro){
        return new ResponseEntity<>(libroService.actualizarLibro(id, libro), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> eliminarLibroPorId(@PathVariable Long id){
        if (libroService.eliminarLibroPorId(id)){
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
    }

}
