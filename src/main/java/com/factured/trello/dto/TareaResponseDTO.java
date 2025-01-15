package com.factured.trello.dto;

public class TareaResponseDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private String estado;
    private Long tableroId;
    private String tableroNombre;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Long getTableroId() { return tableroId; }
    public void setTableroId(Long tableroId) { this.tableroId = tableroId; }

    public String getTableroNombre() { return tableroNombre; }
    public void setTableroNombre(String tableroNombre) { this.tableroNombre = tableroNombre; }
}
