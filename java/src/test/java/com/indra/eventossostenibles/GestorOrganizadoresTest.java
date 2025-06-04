package com.indra.eventossostenibles;

import com.indra.eventossostenibles.entidades.Organizador;
import com.indra.eventossostenibles.excepciones.ListaVaciaException;
import com.indra.eventossostenibles.excepciones.OrganizadorNotFoundException;
import com.indra.eventossostenibles.gestion.GestorOrganizadores;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GestorOrganizadoresTest {
    private GestorOrganizadores geo;
    private Organizador organizador1;
    private Organizador organizador2;

    @BeforeEach
    public void setUp(){
        geo = new GestorOrganizadores();
        organizador1 = new Organizador(1, "Dani", "1234", "test@test.com");
        organizador2 = new Organizador(2, "Iván", "123456", "testtt@test.com");
    }

    @Test
    public void testRegistrarYBuscarPorID() throws OrganizadorNotFoundException {
        geo.registrarOrganizador(organizador1);
        Organizador organizador = geo.buscarOrganizadorId(organizador1.getIdOrganizador());

        //Miramos que coincidan los datos del organizador registrado
        assertEquals("Dani", organizador.getNombre());
        assertEquals("1234", organizador.getTelefono());
    }

    @Test
    public void testRegistrarOrganizadorExiste() throws ListaVaciaException {
        geo.registrarOrganizador(organizador1);
        int sizeAntes = geo.obtenerListaOrganizadores().size();

        geo.registrarOrganizador(organizador1);
        int sizeDespues = geo.obtenerListaOrganizadores().size();

        //Miramos que el tamaño del array no se altere si volvemos a introducir el mismo organizador
        assertEquals(sizeAntes, sizeDespues);
    }

    @Test
    public void testBuscarIdNoExistente() {
        //Se espera que lance una excepción por organizador no encontrado
        assertThrows(OrganizadorNotFoundException.class, () -> geo.buscarOrganizadorId(5));
    }

    @Test
    public void testObtenerListaOrganizadoresVacia() {
        assertThrows(ListaVaciaException.class, () -> geo.obtenerListaOrganizadores());
    }

    @Test
    public void testObtenerListaOrganizadores() throws ListaVaciaException {
        geo.registrarOrganizador(organizador1);
        geo.registrarOrganizador(organizador2);

        ArrayList<Organizador> listaOrganizadores = geo.obtenerListaOrganizadores();

        //El tamaño del ArrayList después de añadir 2 organizadores tiene que ser 2
        assertEquals(2, listaOrganizadores.size());
    }

    @Test
    public void testEliminarOrganizador() throws OrganizadorNotFoundException {
        geo.registrarOrganizador(organizador1);
        geo.eliminarOrganizador(organizador1.getIdOrganizador());

        //Reutilizamos el método de buscar por ID. Si se ha eliminado correctamente, el metodo no lo debería localizar y tendría que lanzar una excepción
        assertThrows(OrganizadorNotFoundException.class, () -> {
            geo.buscarOrganizadorId(organizador1.getIdOrganizador());
        });
    }

    @Test
    public void testEliminarOrganizadorInexistente() {
        //Debería lanzar una excepción
        assertThrows(OrganizadorNotFoundException.class, () -> geo.eliminarOrganizador(5));
    }

    @Test
    public void testActualizarOrganizadorExistente() throws OrganizadorNotFoundException {
        geo.registrarOrganizador(organizador1);
        geo.actualizarOrganizador(1, "Pepe", "92392", "pepe@test.com");

        Organizador organizador = geo.buscarOrganizadorId(1);

        //Obtenemos los datos reutilizando el método de buscar por ID y comprobamos que coinciden los cambios en el organizador que nos devuelven
        assertEquals(1, organizador.getIdOrganizador());
        assertEquals("Pepe", organizador.getNombre());
        assertEquals("92392", organizador.getTelefono());
    }

    @Test
    public void testActualizarOrganizadorInexistente() {
        //Lanza una excepción, ya que el método de actualizar reutiliza obtener por ID
        assertThrows(OrganizadorNotFoundException.class, () -> {
            geo.actualizarOrganizador(2, "Raúl", "11", "raul@test.com");
        });
    }
}