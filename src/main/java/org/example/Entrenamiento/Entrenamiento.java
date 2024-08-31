package org.example.Entrenamiento;

import org.example.Usuario.Usuario; // Importar la clase Usuario

public class Entrenamiento {
    private int duracion;
    private int caloriasQuemadas;
    private TipoEntrenamiento tipoEntrenamiento;
    private String descripcion;
    private long codigo;
    private Usuario usuario; // Añadir este atributo

    public Entrenamiento(TipoEntrenamiento tipoEntrenamiento, String descripcion, int duracion, int caloriasQuemadas, long codigo, Usuario usuario) {
        this.duracion = duracion;
        this.caloriasQuemadas = caloriasQuemadas;
        this.tipoEntrenamiento = tipoEntrenamiento;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.usuario = usuario; // Inicializar el atributo
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

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public Usuario getUsuario() { // Añadir este método
        return usuario;
    }

    @Override
    public String toString() {
        return "Entrenamiento{" +
                "duracion=" + duracion +
                ", caloriasQuemadas=" + caloriasQuemadas +
                ", tipoEntrenamiento=" + tipoEntrenamiento +
                ", descripcion='" + descripcion + '\'' +
                ", codigo='" + codigo + '\'' +
                ", usuario=" + usuario.getNombre() + // Ajustar toString para incluir usuario
                '}';
    }
}
