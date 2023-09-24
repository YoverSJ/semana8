package com.codigo.semana8.controller;

import com.codigo.semana8.model.Categoria;
import com.codigo.semana8.model.Editor;
import com.codigo.semana8.service.CategoriaService;
import com.codigo.semana8.service.EditorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editores")
public class EditorController {

    private final EditorService editorService;

    public EditorController(EditorService editorService) {
        this.editorService = editorService;
    }

    @GetMapping
    public ResponseEntity<List<Editor>> listarEditores(){
        return new ResponseEntity<>(editorService.obtenerTodosEditores(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Editor> crearEditor(@RequestBody Editor editor){
        return new ResponseEntity<>(editorService.crearEditor(editor), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Editor> obtenerEditorPorId(@PathVariable Long id){
        return new ResponseEntity<>(editorService.obtenerEditorPorId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Editor> actualizarEditor(@PathVariable Long id, @RequestBody Editor editor){
        return new ResponseEntity<>(editorService.actualizarEditor(id, editor), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> eliminarEditorPorId(@PathVariable Long id){
        if (editorService.eliminarEditorPorId(id)){
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
    }

}
