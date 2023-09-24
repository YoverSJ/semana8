package com.codigo.semana8.service;

import com.codigo.semana8.model.Categoria;
import com.codigo.semana8.model.Editor;
import com.codigo.semana8.repository.EditorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EditorService {
    
    private final EditorRepository editorRepository;

    public EditorService(EditorRepository editorRepository) {
        this.editorRepository = editorRepository;
    }

    public List<Editor> obtenerTodosEditores(){
        List<Editor> editores = editorRepository.findAll();
        List<Editor> editoresNoEliminadas = new ArrayList<>();

        editores.forEach(editor -> {
            if (editor.getEstado() == 1){
                editoresNoEliminadas.add(editor);
            }
        });

        return editoresNoEliminadas;
    }

    public Editor obtenerEditorPorId(Long id){
        Optional<Editor> editor = editorRepository.findById(id);

        if (editor.isPresent()){
            return editor.get();
        }else {
            throw new RuntimeException("Editor no encontrado");
        }

    }

    public Editor crearEditor(Editor editor){
        return editorRepository.save(editor);
    }

    public Editor actualizarEditor(Long id, Editor editor){
        Editor editorExistente = obtenerEditorPorId(id);

        editorExistente.setNombre(editor.getNombre());
        editorExistente.setEstado(editor.getEstado());

        return editorRepository.save(editorExistente);
    }

    public Boolean eliminarEditorPorId(Long id){
        Editor editorExistente = obtenerEditorPorId(id);

        if (editorExistente.getEstado() == 1){
            editorExistente.setEstado(0);
            editorRepository.save(editorExistente);
            return true;
        }else {
            return false;
        }

    }
    
}
