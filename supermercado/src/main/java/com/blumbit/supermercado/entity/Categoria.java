package com.blumbit.supermercado.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column()
    private String descripcion;
    
    // @OneToMany(mappedBy = "categoria")
    // private Set<Producto> productos = new HashSet<>();
    
    // Constructors, getters, setters
}