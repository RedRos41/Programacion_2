package org.example.Clase;

import java.util.ArrayList;
import java.util.List;

public class GestionClase {
    private final List <Clase> clases;


    public GestionClase() {this.clases = new ArrayList<>();
    }

    public boolean agregarClase (long idClase, boolean estadoClase, short capacidad, int fechaInicioClase, int fechaFinClase, int horarioClase, String tipoClase, String nombreClase) {
        if (buscarClaseId(idClase) == null) {
            Clase clase = new Clase(idClase, estadoClase, capacidad, fechaInicioClase, fechaFinClase, horarioClase, tipoClase, nombreClase);
            clases.add(clase);
            return true;
        }
        return false;
    }
    public Clase buscarClaseId(long idClase) {
        for (Clase clase : clases) {
            if (clase.getIdClase() == idClase) {
                return clase;
            }
        }
        return null;
    }
    public Clase buscarClaseHorario(int horarioClase){
        for (Clase clase: clases){
            if(clase.getHorarioClase() == horarioClase){
                return clase;
            }

        }
        return null;
    }
    public Clase buscarClaseTipo(String tipoClase){
        for (Clase clase: clases){
            if(clase.getTipoClase().equalsIgnoreCase(tipoClase)){
                return clase;
            }
        }
    // falta el metodo con el entrenador
        return null;
    }



    public void cancelarClase(){

    }

    public void consultarDisponibilidadClase(){

    }

    public void imprimirClases() {
        for (Clase clase : clases) {
            System.out.println(clase);
        }
    }

}

