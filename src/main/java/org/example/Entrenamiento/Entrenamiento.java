package org.example.Entrenamiento;

import org.example.Clase.TipoClase;

public class Entrenamiento {
    private int duracion;
    private int caloriasQuemadas;
    private TipoEntrenamiento tipoEntrenamiento;
    private String descripcion;

    public Entrenamiento(TipoEntrenamiento tipoEntrenamiento, String descripcion, int duracion, int caloriasQuemadas) {
        this.duracion = duracion;
        this.caloriasQuemadas = caloriasQuemadas;
        this.tipoEntrenamiento = tipoEntrenamiento;
        this.descripcion = descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getCaloriasQuemadas() {
        return caloriasQuemadas;
    }

    public TipoEntrenamiento getTipoEntrenamiento() {
        return tipoEntrenamiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setCaloriasQuemadas(int caloriasQuemadas) {
        this.caloriasQuemadas = caloriasQuemadas;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipoEntrenamiento(TipoEntrenamiento tipoEntrenamiento) {
        this.tipoEntrenamiento = tipoEntrenamiento;
    }

    @Override
    public String toString() {
        return "Entrenamiento{" +
                "duracion=" + duracion +
                ", caloriasQuemadas=" + caloriasQuemadas +
                ", tipoEntrenamiento=" + tipoEntrenamiento +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
