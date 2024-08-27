package org.example.Clase;

import org.example.Usuario.Usuario;

import java.time.LocalDateTime;

public class Reserva {

    private LocalDateTime fechaReserva;
    private Usuario usuario;
    private Clase clase;

    public Reserva(Usuario usuario, LocalDateTime fechaReserva, Clase clase) {
        this.usuario = usuario;
        this.fechaReserva = fechaReserva;
        this.clase = clase;
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
                '}';
    }
}
