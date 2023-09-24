package com.codigo.semana8.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private Integer estado;

    @OneToOne
    @JoinColumn(name = "editor_id")
    private Editor editor;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ManyToMany
    @JoinTable(
            name = "libros_categorias",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<Categoria> categorias = new HashSet<>();
}
