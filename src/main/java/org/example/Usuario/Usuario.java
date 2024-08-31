package org.example.Usuario;

import org.example.Entrenamiento.Entrenamiento;
import org.example.Persona;

public class Usuario extends Persona {
    private String direccion, correo, contrasena;
    private long telefono;
    private Entrenamiento entrenamiento;

    public Usuario(String nombre, long id, String direccion, String correo, String contrasena, long telefono, Entrenamiento entrenamiento) {
        super(nombre, id);
        this.direccion = direccion;
        this.correo = correo;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.entrenamiento=entrenamiento;
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

    public  Entrenamiento getEntrenamiento(){return  entrenamiento;}

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
                ", entrenamiento=" + entrenamiento +
                '}';
    }
}
