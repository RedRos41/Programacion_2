package org.example.Entrenador;

import org.example.Usuario.Usuario;

public class Entrenador extends Usuario {
    private String especialidad;

    public Entrenador(String nombre, long id, String direccion, String correo, String contrasena, long telefono, String especialidad) {
        super(nombre, id, direccion, correo, contrasena, telefono);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "especialidad='" + especialidad + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", id=" + getId() +
                ", direccion='" + getDireccion() + '\'' +
                ", correo='" + getCorreo() + '\'' +
                ", telefono=" + getTelefono() +
                '}';
    }
}
