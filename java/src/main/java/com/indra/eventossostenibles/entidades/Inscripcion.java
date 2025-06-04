package com.indra.eventossostenibles.entidades;

import java.time.LocalDate;

public class Inscripcion {
    private Usuario usuario;
    private Evento evento;
    private LocalDate fechaInscripcion;
    private boolean cancelado;

    //Builder
    public Inscripcion(Usuario usuario, Evento evento, LocalDate fechaInscripcion, boolean cancelado) {
        this.usuario = usuario;
        this.evento = evento;
        this.fechaInscripcion = fechaInscripcion;
        this.cancelado = cancelado;
    }

    //Getters y setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    //Override toString
    @Override
    public String toString(){
        return "Usuario: "+usuario+"\nEvento: "+evento+"\nFecha inscripción: "+fechaInscripcion+"\nCancelado: "+cancelado;
    }
}
