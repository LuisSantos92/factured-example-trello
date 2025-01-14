package com.factured.trello.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Entity
@Document(collection = "tableros") // Anotación para MongoDB
public class Tablero {

    @Id // Para MongoDB
    @jakarta.persistence.Id // Para PostgreSQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PostgreSQL ID

    private String nombre;
    private String descripcion;

    private LocalDate fechaCreacion;

    @OneToMany(mappedBy = "tablero", cascade = CascadeType.ALL) // Relación con JPA
    private List<Tarea> tareas; // Esto no será directo en MongoDB

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }
}
