package org.example.Entrenamiento;

import org.example.Clase.TipoClase;

public class Entrenamiento {
    private long id;
    private long idUsuario;
    private TipoClase tipoClase;
    private TipoEntrenamiento tipoEntrenamiento;
    private String descripcion;

    public Entrenamiento(long id, long idUsuario, TipoClase tipoClase, TipoEntrenamiento tipoEntrenamiento, String descripcion) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.tipoClase = tipoClase;
        this.tipoEntrenamiento = tipoEntrenamiento;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public TipoClase getTipoClase() {
        return tipoClase;
    }

    public TipoEntrenamiento getTipoEntrenamiento() {
        return tipoEntrenamiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "Entrenamiento{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", tipoClase=" + tipoClase +
                ", tipoEntrenamiento=" + tipoEntrenamiento +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
