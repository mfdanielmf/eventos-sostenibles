package com.indra.eventossostenibles.gestion;

import com.indra.eventossostenibles.entidades.Categoria;
import com.indra.eventossostenibles.entidades.Evento;
import com.indra.eventossostenibles.excepciones.EventoNotFoundException;
import com.indra.eventossostenibles.excepciones.ListaVaciaException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class GestorEventos {
    private final ArrayList<Evento> listaEventos;

    //Builder
    public GestorEventos() {
        this.listaEventos = new ArrayList<>();
    }

    public Evento buscarEventoId(int id) throws EventoNotFoundException {
        for(Evento evento : listaEventos){
            if(evento.getIdEvento() == id){
                return evento;
            }
        }

        //Excepción si no hemos encontrado el evento en la lista
        throw new EventoNotFoundException("No se ha encontrado el evento con ID "+id);
    }

    public void registrarEvento(Evento evento){
        //Recorremos los eventos
        for(Evento eventoActual : listaEventos){
            //Si ya está añadido, salimos del método
            if(eventoActual.getIdEvento() == evento.getIdEvento()){
                System.out.println("El evento con ID "+evento.getIdEvento()+" ya está resgistrado");
                return;
            }
        }

        //Si no está añadido:
        boolean registrado=listaEventos.add(evento);

        if(registrado){
            System.out.println("Se ha registrado el evento con ID "+evento.getIdEvento());
        }else {
            System.out.println("No se ha podido añadir el evento con ID "+evento.getIdEvento());
        }
    }

    public ArrayList<Evento> obtenerListaEventos() throws ListaVaciaException {
        //Si no tenemos eventos, lanzamos una excepción
        if(listaEventos.isEmpty()){
            throw new ListaVaciaException("No hay eventos registrados");
        }

        return listaEventos;
    }

    public void eliminarEvento(int id) throws EventoNotFoundException{
        //Iterator para recorrer y eliminar
        Iterator<Evento> iterator = listaEventos.iterator();

        while (iterator.hasNext()){
            Evento evento = iterator.next();
            //Si lo encontramos, lo eliminamos y salimos
            if(evento.getIdEvento() == id){
                iterator.remove();
                return;
            }
        }

        throw new EventoNotFoundException("No se ha encontrado el evento con ID "+id);
    }

    public void actualizarEvento(int id, String nombre, String duracion, String lugar, LocalDateTime fechaInicio, String descripcion, Categoria categoria) throws EventoNotFoundException {
        //Reutilizamos el método de buscar por ID. Si lanza excepción buscar por ID, ya no seguiría el flujo por este método
        Evento evento = buscarEventoId(id);

        //Actualizamos los datos del evento mediante los setters
        evento.setNombre(nombre);
        evento.setDuracion(duracion);
        evento.setLugar(lugar);
        evento.setFechaInicio(fechaInicio);
        evento.setDescripcion(descripcion);
        evento.setCategoria(categoria);
    }
}
