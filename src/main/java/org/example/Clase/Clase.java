package org.example.Clase;

public class Clase {
    private String nombreClase, tipoClase;
    private int horarioClase, fechaInicioClase, fechaFinClase;
    private short capacidad;
    private boolean estadoClase;
    private final long idClase;

    // entrenador (nombre, numId, especialidad)
    public Clase(long idClase, boolean estadoClase, short capacidad, int fechaInicioClase, int fechaFinClase, int horarioClase, String tipoClase, String nombreClase) {
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

    public String getTipoClase() {return tipoClase;
    }

    public int getHorarioClase() {return horarioClase;
    }

    public int getFechaInicioClase() {return fechaInicioClase;
    }

    public int getFechaFinClase() {return fechaFinClase;
    }

    public short getCapacidad() {return capacidad;
    }

    public boolean isEstadoClase() {return estadoClase;
    }

    public long getIdClase() {return idClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public void setTipoClase(String tipoClase) {
        this.tipoClase = tipoClase;
    }

    public void setHorarioClase(int horarioClase) {
        this.horarioClase = horarioClase;
    }

    public void setFechaInicioClase(int fechaInicioClase) {
        this.fechaInicioClase = fechaInicioClase;
    }

    public void setFechaFinClase(int fechaFinClase) {
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
                ", idClase=" + idClase +
                "nombreClase='" + nombreClase + '\'' +
                ", tipoClase='" + tipoClase + '\'' +
                ", horarioClase=" + horarioClase +
                ", fechaInicioClase=" + fechaInicioClase +
                ", fechaFinClase=" + fechaFinClase +
                ", capacidad=" + capacidad +
                ", estadoClase=" + estadoClase +
                '}';
    }
}
