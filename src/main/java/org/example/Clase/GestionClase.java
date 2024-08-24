package org.example.Clase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GestionClase {
    private final List <Clase> clases;


    public GestionClase() {this.clases = new ArrayList<>();
    }
    int plazas = 0;
    public boolean agregarClase (String idClase, boolean estadoClase, short capacidad, LocalDate fechaInicioClase, LocalDate fechaFinClase, LocalTime horarioClase, TipoClase tipoClase, String nombreClase) {
        if (buscarClaseId(idClase) == null) {
            Clase clase = new Clase(idClase, estadoClase, capacidad, fechaInicioClase, fechaFinClase, horarioClase, tipoClase, nombreClase);
            clases.add(clase);
            plazas+=1;
            return true;
        }
        return false;
    }
    public Clase buscarClaseId(String idClase) {
        for (Clase clase : clases) {
            if (clase.getIdClase().equals(idClase)) {
                return clase;
            }
        }
        return null;
    }
    public Clase buscarClaseHorario(LocalTime horarioClase){
        for (Clase clase: clases){
            if(clase.getHorarioClase().equals(horarioClase)){
                return clase;
            }

        }
        return null;
    }
    public Clase buscarClaseTipo(TipoClase tipoClase){
        for (Clase clase: clases){
            if(clase.getTipoClase().equals(tipoClase)){
                return clase;
            }
        }
    // falta el metodo con el entrenador
        return null;
    }

    public void consultarDisponibilidadClase(){

    }

    public void imprimirClases() {
        for (Clase clase : clases) {
            System.out.println(clase);
        }
    }

}

