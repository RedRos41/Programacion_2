package org.example;

public class Persona {
    protected String nombre;
    protected final long id;

    public Persona(String nombre, long id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public long getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
