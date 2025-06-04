package com.indra.eventossostenibles.gestion;

import com.indra.eventossostenibles.entidades.Organizador;
import com.indra.eventossostenibles.excepciones.ListaVaciaException;
import com.indra.eventossostenibles.excepciones.OrganizadorNotFoundException;

import java.util.ArrayList;
import java.util.Iterator;

public class GestorOrganizadores {
    private final ArrayList<Organizador> listaOrganizadores;

    //Builder
    public GestorOrganizadores() {
        this.listaOrganizadores = new ArrayList<>();
    }

    public Organizador buscarOrganizadorId(int id) throws OrganizadorNotFoundException {
        //Recorremos el array en busca del organizador
        for(Organizador organizador : listaOrganizadores){
            if(organizador.getIdOrganizador() == id){
                return organizador;
            }
        }

        //Excepcion si no lo encontramos
        throw new OrganizadorNotFoundException("No se ha encontrado el organizador con ID "+id);
    }

    public void registrarOrganizador(Organizador organizador){
        //Miramos si el organizador ya está registrado. Si lo está, ya salimos del método
        for(Organizador organizadorActual : listaOrganizadores){
            if(organizadorActual.getIdOrganizador() == organizador.getIdOrganizador()){
                System.out.println("El organizador con id "+organizador.getIdOrganizador()+" ya está registrado");
                return;
            }
        }

        //Lo registramos si no lo estaba
        boolean registrado = listaOrganizadores.add(organizador);

        //Enviamos un mensaje según se ha realizado la operación o no
        if(registrado){
            System.out.println("Se ha añadido el organizador con ID "+organizador.getIdOrganizador());
        }else{
            System.out.println("No se ha podido añadir el organizador con ID "+organizador.getIdOrganizador());
        }
    }

    public ArrayList<Organizador> obtenerListaOrganizadores() throws ListaVaciaException {
        //Si la lista está vacía, lanzamos una excepción
        if(listaOrganizadores.isEmpty()){
            throw new ListaVaciaException("No hay ningún organizador registrado");
        }

        return listaOrganizadores;
    }

    public void eliminarOrganizador(int id) throws OrganizadorNotFoundException{
        //Iterator para recorrer el ArrayList y eliminar a la vez sin modificar índices
        Iterator<Organizador> iterator = listaOrganizadores.iterator();

        while (iterator.hasNext()){
            Organizador organizador = iterator.next();

            //Si lo encontramos, lo eliminamos
            if(organizador.getIdOrganizador() == id){
                iterator.remove();
                System.out.println("Se ha eliminado el organizador con ID "+id);
                return;
            }
        }

        //Si no se llega a eliminar, lanzamos una excepción
        throw new OrganizadorNotFoundException("No se ha encontrado el organizador con ID "+id);
    }

    public void actualizarOrganizador(int id, String nombre, String telefono, String correo) throws OrganizadorNotFoundException {
        //Reutilizamos el método de buscar por id. Si no lo encuentra lanza una excepción
        Organizador organizador = buscarOrganizadorId(id);

        //Si no lanza la exepción, llega a esta parte del código y por lo tanto, actualizamos los datos con los setters
        organizador.setNombre(nombre);
        organizador.setTelefono(telefono);
        organizador.setCorreo(correo);
    }

}
