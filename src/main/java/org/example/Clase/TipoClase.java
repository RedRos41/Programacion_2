package org.example.Clase;

public class TipoClase {

    private String nombreTipo;

    public TipoClase(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }
    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TipoClase tipoClase = (TipoClase) o;
        return nombreTipo.equalsIgnoreCase(tipoClase.nombreTipo);
    }

    @Override
    public int hashCode() {
        return nombreTipo.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return nombreTipo;
    }
}


