package org.example.Clase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GestionClase {
    private final List <Clase> clases;


    public GestionClase() {this.clases = new ArrayList<>();
    }

    public boolean agregarClase (String idClase, boolean estadoClase, short capacidad, LocalDate fechaInicioClase, LocalDate fechaFinClase, LocalTime horarioClase, TipoClase tipoClase, String nombreClase) {
        if (buscarClaseId(idClase) == null) {
            Clase clase = new Clase(idClase, estadoClase, capacidad, fechaInicioClase, fechaFinClase, horarioClase, tipoClase, nombreClase);
            clases.add(clase);

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
    public List<Clase> buscarClaseHorario(LocalTime horarioClase){
        List<Clase> clasesEncontradasHorario = new ArrayList<>();
        for (Clase clase: clases){
            if(clase.getHorarioClase()== horarioClase){
                clasesEncontradasHorario.add(clase);
            }
        }
        return clasesEncontradasHorario;
    }
    public List<Clase> buscarClaseTipo(TipoClase tipoClase){
        List<Clase> clasesEncontradasTipo = new ArrayList<>();
        for (Clase clase: clases){
            if(clase.getTipoClase()== tipoClase){
                clasesEncontradasTipo.add(clase);
            }
        }
        return clasesEncontradasTipo;
    }
    // falta el metodo con el entrenador

    public void consultarDisponibilidadClase(){

    }

    public void imprimirClases() {
        for (Clase clase : clases) {
            System.out.println(clase);
        }
    }

}

