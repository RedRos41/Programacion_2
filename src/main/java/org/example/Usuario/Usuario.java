package org.example.Usuario;

import org.example.Entrenamiento.Entrenamiento;
import org.example.Persona;

public class Usuario extends Persona {
    private String direccion, correo, contrasena;
    private long telefono;

    public Usuario(String nombre, long id, String direccion, String correo, String contrasena, long telefono) {
        super(nombre, id);
        this.direccion = direccion;
        this.correo = correo;
        this.contrasena = contrasena;
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", direccion='" + direccion + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}
