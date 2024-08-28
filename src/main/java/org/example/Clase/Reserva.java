package org.example.Clase;

import org.example.Usuario.Usuario;

import java.time.LocalDateTime;

public class Reserva {

    private LocalDateTime fechaReserva;
    private Usuario usuario;
    private Clase clase;
    private String codigo;

    public Reserva(Usuario usuario, LocalDateTime fechaReserva, Clase clase, String codigo) {
        this.usuario = usuario;
        this.fechaReserva = fechaReserva;
        this.clase = clase;
        this.codigo = codigo;

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Clase getClase() {
        return clase;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "fechaReserva=" + fechaReserva +
                ", usuario=" + usuario.getNombre() +
                ", clase=" + clase.getNombreClase() +
                ", codigo= " + codigo +
                '}';
    }
}
