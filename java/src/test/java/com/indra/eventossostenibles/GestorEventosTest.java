package com.indra.eventossostenibles;

import com.indra.eventossostenibles.entidades.Categoria;
import com.indra.eventossostenibles.entidades.Evento;
import com.indra.eventossostenibles.excepciones.EventoNotFoundException;
import com.indra.eventossostenibles.excepciones.ListaVaciaException;
import com.indra.eventossostenibles.gestion.GestorEventos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GestorEventosTest {
    private GestorEventos gee;
    private Evento evento1;
    private Evento evento2;

    @BeforeEach
    public void setUp(){
        gee = new GestorEventos();
        evento1 = new Evento(1, "Festival ecológico", "3h", "A Coruña", LocalDateTime.of(2025, 6, 10, 18, 0), "Festival ecológico", Categoria.ACTIVIDAD_SOSTENIBLE);
        evento2 = new Evento(2, "Green talks", "2h", "Barcelona", LocalDateTime.of(2025, 7, 1, 10, 0), "Charla sobre sostenibilidad", Categoria.CONFERENCIA);
    }

    @Test
    public void testRegistrarYBuscarEvento() throws EventoNotFoundException {
        gee.registrarEvento(evento1);
        Evento evento = gee.buscarEventoId(evento1.getIdEvento());

        //Comprobamos que los datos son correctos
        assertEquals(1, evento.getIdEvento());
        assertEquals("Festival ecológico", evento.getNombre());
        assertEquals("A Coruña", evento.getLugar());
    }

    @Test
    public void testRegistrarEventoExiste() throws ListaVaciaException {
        gee.registrarEvento(evento1);
        int sizeAntes = gee.obtenerListaEventos().size();

        gee.registrarEvento(evento1);
        int sizeDespues = gee.obtenerListaEventos().size();

        //El tamaño del ArrayList debería ser el mismo si intentamos añadir el mismo evento
        assertEquals(sizeAntes, sizeDespues);
    }

    @Test
    public void testBuscarEventoInexistente(){
        //Se espera que lance una excepción
        assertThrows(EventoNotFoundException.class, () -> gee.buscarEventoId(5));
    }

    @Test
    public void testObtenerListaEventos() throws ListaVaciaException {
        gee.registrarEvento(evento1);
        gee.registrarEvento(evento2);

        ArrayList<Evento> listaEventos = gee.obtenerListaEventos();

        //El tamaño de la lista debería ser 2 cuando tenemos 2 eventos
        assertEquals(2, listaEventos.size());
    }

    @Test
    public void testObtenerListaEventosVacia(){
        //Se espera que lance una excepción
        assertThrows(ListaVaciaException.class, () -> gee.obtenerListaEventos());
    }

    @Test
    public void testEliminarEvento() throws EventoNotFoundException {
        gee.registrarEvento(evento1);
        gee.eliminarEvento(evento1.getIdEvento());

        //Reutilizamos buscar por ID. Si se ha eliminado correctamente, el método de buscar por ID no encontraría el evento y lanzaría una excepción
        assertThrows(EventoNotFoundException.class, () -> gee.buscarEventoId(evento1.getIdEvento()));
    }

    @Test
    public void testEliminarEventoInexistente(){
        //Se espera que lance una excepción
        assertThrows(EventoNotFoundException.class, () -> gee.eliminarEvento(5));
    }

    @Test
    public void testActualizarEvento() throws EventoNotFoundException {
        gee.registrarEvento(evento1);
        gee.actualizarEvento(1, "Test", "10h", "Madrid", LocalDateTime.of(2025, 11, 1, 5, 30), "Test actualizar", Categoria.TALLER);

        //Obtenemos el evento buscando por ID
        Evento eventoActualizado = gee.buscarEventoId(evento1.getIdEvento());

        //Comprobamos que los datos coincidan con la actualización realizada
        assertEquals(1, eventoActualizado.getIdEvento());
        assertEquals("Test", eventoActualizado.getNombre());
        assertEquals("Madrid", eventoActualizado.getLugar());
    }

    @Test
    public void testActualizarEventoInexistente() {
        //Se espera que lance una excepción (reutiliza el método de buscar por ID y si no encuentra el evento, lanza una excepción)
        assertThrows(EventoNotFoundException.class, () -> {
            gee.actualizarEvento(10, "Test", "10h", "Madrid", LocalDateTime.of(2025, 11, 1, 5, 30), "Test actualizar", Categoria.TALLER);
        });
    }
}