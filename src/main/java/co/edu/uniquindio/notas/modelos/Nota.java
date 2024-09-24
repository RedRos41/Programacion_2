package co.edu.uniquindio.notas.modelos;

import java.time.LocalDate;

public class Nota {

    private String titulo;
    private String descripcion;
    private LocalDate fechaCreacion;
    private String categoria;
    private LocalDate recordatorio;

    public Nota(NotaBuilder notaBuilder) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.categoria = categoria;
        this.recordatorio = recordatorio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getRecordatorio() {
        return recordatorio;
    }

    public void setRecordatorio(LocalDate recordatorio) {
        this.recordatorio = recordatorio;
    }
    public static NotaBuilder builder() {
        return new NotaBuilder();
    }

    @Override
    public String toString() {
        return "Nota{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", categoria=" + categoria +
                ", recordatorio=" + recordatorio +
                '}';
    }

    public static class NotaBuilder{
        private String titulo;
        private String descripcion;
        private LocalDate fechaCreacion;
        private String categoria;
        private LocalDate recordatorio;

        public NotaBuilder titulo (String titulo){
            this.titulo = titulo;
            return this;
        }

        public NotaBuilder descripcion (String descripcion){
            this.descripcion = descripcion;
            return this;
        }

        public NotaBuilder fechaCreacion (LocalDate fechaCreacion){
            this.fechaCreacion = fechaCreacion;
            return this;
        }

        public NotaBuilder categoria (String categoria){
            this.categoria = categoria;
            return this;
        }



        public NotaBuilder recordatorio (LocalDate recordatorio){
            this.recordatorio = recordatorio;
            return this;
        }

        public Nota build(){
            return  new Nota(this);
        }
    }

}




