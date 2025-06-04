package com.indra.eventossostenibles.entidades;

import java.time.LocalDateTime;

public class Evento {
    private final int idEvento;
    private String nombre;
    private String duracion;
    private String lugar;
    private LocalDateTime fechaInicio;
    private String descripcion;
    private Categoria categoria;

    //Builder
    public Evento(int idEvento, String nombre, String duracion, String lugar, LocalDateTime fechaInicio, String descripcion, Categoria categoria) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.duracion = duracion;
        this.lugar = lugar;
        this.fechaInicio = fechaInicio;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    //Getters y setters (id sin setter para evitar cambios)
    public int getIdEvento() {
        return idEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    //Override toString
    @Override
    public String toString(){
        return "ID evento: "+idEvento+"\nNombre: "+nombre+"\nDuración: "+duracion+"\nLugar: "+lugar+"\nFecha de inicio: "+fechaInicio+"\nDescripción: "+descripcion+"\nCategoria: "+categoria;
    }
}
