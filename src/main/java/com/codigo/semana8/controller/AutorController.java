package com.codigo.semana8.controller;

import com.codigo.semana8.model.Autor;
import com.codigo.semana8.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public ResponseEntity<List<Autor>> listarAutores(){
        return new ResponseEntity<>(autorService.obtenerTodosAutores(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Autor> crearAutor(@RequestBody Autor autor){
        return new ResponseEntity<>(autorService.crearAutor(autor), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> obtenerAutorPorId(@PathVariable Long id){
        return new ResponseEntity<>(autorService.obtenerAutorPorId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> actualizarAutor(@PathVariable Long id, @RequestBody Autor autor){
        return new ResponseEntity<>(autorService.actualizarAutor(id, autor), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> eliminarAutorPorId(@PathVariable Long id){
        if (autorService.eliminarAutorPorId(id)){
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
    }

}
