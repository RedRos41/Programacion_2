package org.example.Clase;

import java.time.LocalDateTime;

public class Reserva {

    private LocalDateTime fechaReserva;
    //usuario
    private String codigo;

    public Reserva(LocalDateTime fechaReserva, String codigo) {
        this.fechaReserva = fechaReserva;
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
}
