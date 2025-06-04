package com.indra.eventossostenibles.gestion;

import com.indra.eventossostenibles.entidades.Usuario;
import com.indra.eventossostenibles.excepciones.ListaVaciaException;
import com.indra.eventossostenibles.excepciones.UsuarioNotFoundException;

import java.util.ArrayList;
import java.util.Iterator;

public class GestorUsuarios {
    private final ArrayList<Usuario> listaUsuarios;

    //Builder por defecto
    public GestorUsuarios(){
        this.listaUsuarios = new ArrayList<>();
    }

    public Usuario buscarUsuarioId(int id) throws UsuarioNotFoundException {
        //Recorremos la lista con un for-each
        for (Usuario usuario : listaUsuarios){
            if(usuario.getIdUsuario() == id){
                return usuario;
            }
        }

        //Si no encuentra coincidencias, lanzamos una excepción
        throw new UsuarioNotFoundException("No se ha encontrado el usuario con id "+id);
    }

    public void registrarUsuario(Usuario usuario){
        //Buscamos si ya existe el usuario
        for(Usuario usuarioActual : listaUsuarios){
            if(usuarioActual.getIdUsuario() == usuario.getIdUsuario()){
                System.out.println("El usuario ya está registrado");
                return;
            }
        }

        //Si no lo encontramos, lo añadimos a la lista
        boolean registrado = listaUsuarios.add(usuario);

        if(registrado){
            System.out.println("Se ha registrado el usuario con id "+usuario.getIdUsuario());
        }else{
            System.out.println("No se ha podido añadir el usuario con id "+usuario.getIdUsuario());
        }
    }

    public ArrayList<Usuario> obtenerListaUsuarios() throws ListaVaciaException {
        if(listaUsuarios.isEmpty()){
            throw new ListaVaciaException("No hay ningún usuario registrado");
        }
        return listaUsuarios;
    }

    public void eliminarUsuarioId(int id) throws UsuarioNotFoundException {
        //Iterator para borrar sin modificar índices
        Iterator<Usuario> iterator = listaUsuarios.iterator();

        while(iterator.hasNext()){
            Usuario usuario = iterator.next();
            if(usuario.getIdUsuario() == id){
                iterator.remove();
                System.out.println("Se ha eliminado el usuario con id "+id);
                return;
            }
        }

        //Excepción si no lo encontramos
        throw new UsuarioNotFoundException("No se ha encontrado el usuario a eliminar");
    }

    public void actualizarUsuario(int id, String nombre, String correo, String contrasena) throws UsuarioNotFoundException {
        //Reutilizamos el método de buscar por id. Si no lo encuentra, ya se lanza la excepción en buscarPorId
        Usuario usuario = buscarUsuarioId(id);

        //Modificamos los datos con los setters de usuario
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);

        System.out.println("Se han modificado los datos correctamente");
    }
}
