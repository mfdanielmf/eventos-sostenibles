package com.indra.eventossostenibles;

import com.indra.eventossostenibles.entidades.Usuario;
import com.indra.eventossostenibles.excepciones.ListaVaciaException;
import com.indra.eventossostenibles.excepciones.UsuarioNotFoundException;
import com.indra.eventossostenibles.gestion.GestorUsuarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GestorUsuariosTest {
    private GestorUsuarios geu;
    private Usuario usuario1;
    private Usuario usuario2;

    //Para cada test, creamos un gestor de usuarios y definimos usuarios (con 2 es suficiente)
    @BeforeEach
    public void setUp() {
        geu = new GestorUsuarios();
        usuario1 = new Usuario(1, "Daniel", "test@test.com", "1234");
        usuario2 = new Usuario(2, "Iván", "iván@test.com", "abc");
    }

    @Test
    public void testRegistrarYBuscar() throws UsuarioNotFoundException {
        geu.registrarUsuario(usuario1);
        Usuario usuario = geu.buscarUsuarioId(1);

        //Comprobamos que coincidan los datos
        assertEquals(1, usuario.getIdUsuario());
        assertEquals("Daniel", usuario.getNombre());
    }

    @Test
    public void testBuscarIdInexistente() {
        //Se espera que lance una excepción (usamos lambda para capturarla)
        assertThrows(UsuarioNotFoundException.class, () -> geu.buscarUsuarioId(1));
    }

    @Test
    public void testRegistrarUsuarioExiste() throws ListaVaciaException {
        geu.registrarUsuario(usuario1);
        int sizeAntes = geu.obtenerListaUsuarios().size();

        geu.registrarUsuario(usuario1);
        int sizeDespues = geu.obtenerListaUsuarios().size();

        //Se espera que el tamaño del ArrayList no cambie después de añadir el mismo usuario
        assertEquals(sizeAntes, sizeDespues);
    }

    @Test
    public void testObtenerListaUsuariosVacia() {
        //Se espera que lance una excepción por lista vacía
        assertThrows(ListaVaciaException.class, () -> geu.obtenerListaUsuarios());
    }

    @Test
    public void testObtenerListaConUsuariosRegistrados() throws ListaVaciaException {
        geu.registrarUsuario(usuario1);
        geu.registrarUsuario(usuario2);

        ArrayList<Usuario> listaUsuarios = geu.obtenerListaUsuarios();

        //Miramos que el tamaño coincida con la cantidad de usuarios que añadimos
        assertEquals(2, listaUsuarios.size());
    }

    @Test
    public void testEliminarUsuarioExistente() throws UsuarioNotFoundException {
        geu.registrarUsuario(usuario1);
        geu.eliminarUsuarioId(usuario1.getIdUsuario());

        //Eliminamos el usuario. Posteriormente, reutilizamos el método de buscar por ID y debería lanzar una excepción
        assertThrows(UsuarioNotFoundException.class, () -> geu.buscarUsuarioId(usuario1.getIdUsuario()));
    }

    @Test
    public void testEliminarUsuarioNoExistente() {
        //Se espera que lance una excepción
        assertThrows(UsuarioNotFoundException.class, () -> geu.eliminarUsuarioId(5));
    }

    @Test
    public void testActualizarUsuario() throws UsuarioNotFoundException {
        geu.registrarUsuario(usuario1);
        geu.actualizarUsuario(1, "Alejandro", "alejandro@test.com", "11111");

        //Se espera que si buscamos por ID, el usuario que nos devuelva coincida con el nombre y correo que actualizamos
        assertEquals("Alejandro", geu.buscarUsuarioId(1).getNombre());
        assertEquals("alejandro@test.com", geu.buscarUsuarioId(1).getCorreo());
    }

    @Test
    public void testActualizarUsuarioNoExistente() {
        //Debería lanzar una excepción, ya que actualizar llama a buscar por ID y buscar por ID lanza la excepción si no lo encuentra
        assertThrows(UsuarioNotFoundException.class, () -> {
            geu.actualizarUsuario(1, "Alejandro", "alejandro@test.com", "1111");
        });
    }

}