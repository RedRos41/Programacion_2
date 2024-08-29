package org.example.Entrenador;

import org.example.Persona;

public class Entrenador extends Persona {
    private String especialidad;

    public Entrenador(String nombre, long id, String especialidad) {
        super(nombre, id);
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
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}
