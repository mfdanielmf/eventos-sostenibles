package com.indra.eventossostenibles.entidades;

public class Usuario {
    private final int idUsuario;
    private String nombre;
    private String correo;
    private String contrasena;

    //Builder
    public Usuario(int idUsuario, String nombre, String correo, String contrasena) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    //Getters y setters (id sin setter para no permitir cambios)

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    //toString
    @Override
    public String toString(){
        return "ID usuario: "+idUsuario+"\nNombre: "+nombre+"\nCorreo: "+correo+"\nContraseña: "+contrasena;
    }
}
