package com.indra.eventossostenibles.entidades;

public class Organizador {
    private final int idOrganizador;
    private String nombre;
    private String telefono;
    private String correo;

    //Builder
    public Organizador(int idOrganizador, String nombre, String telefono, String correo) {
        this.idOrganizador = idOrganizador;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    //Getters y setters (id sin setter para no modificar los datos)

    public int getIdOrganizador() {
        return idOrganizador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    //override toString
    @Override
    public String toString(){
        return "ID organizador: "+idOrganizador+"\nNombre: "+nombre+"\nTeléfono: "+telefono+"\nCorreo"+correo;
    }
}
