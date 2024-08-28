package org.example.Clase;
import org.example.Entrenador.Entrenador;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Clase {
    private String nombreClase;
    private TipoClase tipoClase;
    private Entrenador entrenador;
    private LocalTime horarioClase;
    private LocalDate fechaInicioClase, fechaFinClase;
    private short capacidad;
    private boolean estadoClase;
    private final String idClase;
    private List<Reserva> registroReserva;


    public Clase(String nombreClase, TipoClase tipoClase, Entrenador entrenador, LocalTime horarioClase, LocalDate fechaInicioClase, LocalDate fechaFinClase, short capacidad, String idClase, List<Reserva> registroReserva) {
        this.nombreClase = nombreClase;
        this.tipoClase = tipoClase;
        this.entrenador = entrenador;
        this.horarioClase = horarioClase;
        this.fechaInicioClase = fechaInicioClase;
        this.fechaFinClase = fechaFinClase;
        this.capacidad = capacidad;
        this.idClase = idClase;
        this.registroReserva = registroReserva;
        this.estadoClase = registroReserva.size() <= capacidad;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public boolean isEstadoClase() {
        return estadoClase;
    }

    public short getCapacidad() {
        return capacidad;
    }

    public LocalDate getFechaFinClase() {
        return fechaFinClase;
    }

    public LocalDate getFechaInicioClase() {
        return fechaInicioClase;
    }

    public LocalTime getHorarioClase() {
        return horarioClase;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public TipoClase getTipoClase() {
        return tipoClase;
    }

    public List<Reserva> getRegistroReserva() {
        return registroReserva;
    }

    public String getIdClase() {
        return idClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public void setTipoClase(TipoClase tipoClase) {
        this.tipoClase = tipoClase;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public void setHorarioClase(LocalTime horarioClase) {
        this.horarioClase = horarioClase;
    }

    public void setFechaInicioClase(LocalDate fechaInicioClase) {
        this.fechaInicioClase = fechaInicioClase;
    }

    public void setFechaFinClase(LocalDate fechaFinClase) {
        this.fechaFinClase = fechaFinClase;
    }

    public void setCapacidad(short capacidad) {
        this.capacidad = capacidad;
    }

    public void setRegistroReserva(List<Reserva> registroReserva) {
        this.registroReserva = registroReserva;
    }

    public void actualizarEstadoClase() {
        this.estadoClase = registroReserva.size() <= capacidad;
    }
    @Override
    public String toString() {
        return "Clase{" +
                "nombreClase='" + nombreClase + '\'' +
                ", tipoClase=" + tipoClase +
                ", entrenador=" + entrenador +
                ", horarioClase=" + horarioClase +
                ", fechaInicioClase=" + fechaInicioClase +
                ", fechaFinClase=" + fechaFinClase +
                ", capacidad=" + capacidad +
                ", estadoClase=" + estadoClase +
                ", idClase='" + idClase + '\'' +
                ", registroReserva=" + registroReserva +
                '}';
    }
}
