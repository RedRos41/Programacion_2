package org.example.Clase;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Clase {
    private String nombreClase;
    private TipoClase tipoClase;
    //private Entrenador entrenador;
    private LocalTime horarioClase;
    private LocalDate fechaInicioClase, fechaFinClase;
    private short capacidad; //seria la capacidad de clases que hay segun el tipo de clase
    private boolean estadoClase; // este indica si hay o no disponibilidad de plazas de la clase
    private final String idClase;
    private List<Reserva> registroReserva;

    // entrenador (nombre, numId, especialidad)
    public Clase(String idClase, boolean estadoClase, short capacidad, LocalDate fechaInicioClase, LocalDate fechaFinClase, LocalTime horarioClase, TipoClase tipoClase, String nombreClase,List<Reserva> registroReserva ) {
        this.idClase = idClase;
        this.estadoClase = estadoClase;
        this.capacidad = capacidad;
        this.fechaInicioClase = fechaInicioClase;
        this.fechaFinClase = fechaFinClase;
        this.horarioClase = horarioClase;
        this.tipoClase = tipoClase;
        this.nombreClase = nombreClase;
        this.registroReserva = new ArrayList<>();
    }

    public String getNombreClase() {return nombreClase;
    }

    public TipoClase getTipoClase() {return tipoClase;
    }

    public LocalTime getHorarioClase() {return horarioClase;
    }

    public LocalDate getFechaInicioClase() {return fechaInicioClase;
    }

    public LocalDate getFechaFinClase() {return fechaFinClase;
    }

    public short getCapacidad() {return capacidad;
    }

    public boolean isEstadoClase() {return estadoClase;
    }

    public String getIdClase() {return idClase;
    }

    public List<Reserva> getRegistroReserva(){return registroReserva;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public void setTipoClase(TipoClase tipoClase) {
        this.tipoClase = tipoClase;
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

    public void setEstadoClase(boolean estadoClase) {
        this.estadoClase = estadoClase;
    }

    @Override
    public String toString() {
        return "Clase{" +
                "idClase=" + idClase +
                ", nombreClase='" + nombreClase + '\'' +
                ", tipoClase='" + tipoClase + '\'' +
                ", horarioClase=" + horarioClase +
                ", fechaInicioClase=" + fechaInicioClase +
                ", fechaFinClase=" + fechaFinClase +
                ", capacidad=" + capacidad +
                ", estadoClase=" + estadoClase +
                '}';
    }
}
