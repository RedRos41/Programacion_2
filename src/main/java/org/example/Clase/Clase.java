package org.example.Clase;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Clase {
    private String nombreClase;
    private TipoClase tipoClase;
    //private Entrenador entrenador;
    private LocalTime horarioClase;
    private LocalDate fechaInicioClase, fechaFinClase;
    private short capacidad;
    private boolean estadoClase;
    private final String idClase;

    // entrenador (nombre, numId, especialidad)
    public Clase(String idClase, boolean estadoClase, short capacidad, LocalDate fechaInicioClase, LocalDate fechaFinClase, LocalTime horarioClase, TipoClase tipoClase, String nombreClase) {
        this.idClase = idClase;
        this.estadoClase = estadoClase;
        this.capacidad = capacidad;
        this.fechaInicioClase = fechaInicioClase;
        this.fechaFinClase = fechaFinClase;
        this.horarioClase = horarioClase;
        this.tipoClase = tipoClase;
        this.nombreClase = nombreClase;
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
