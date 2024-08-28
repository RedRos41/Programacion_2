package org.example.Clase;

import org.example.Entrenador.Entrenador;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GestionClase {
    private final List <Clase> clases;


    public GestionClase() {
        this.clases = new ArrayList<>();
    }

    public void agregarClase(String nombreClase, TipoClase tipoClase, Entrenador entrenador, LocalTime horarioClase, LocalDate fechaInicioClase, LocalDate fechaFinClase, short capacidad, String idClase) {
        if (verificarClaseId(idClase) == null) {
            Clase clase = new Clase(nombreClase, tipoClase, entrenador, horarioClase, fechaInicioClase, fechaFinClase, capacidad, idClase, new ArrayList<>());
            clases.add(clase);
        }
    }

    public Clase verificarClaseId(String idClase) {
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
            if(clase.getHorarioClase().equals(horarioClase)){
                clasesEncontradasHorario.add(clase);
            }
        }
        return clasesEncontradasHorario;
    }
    public List<Clase> buscarClaseTipo(TipoClase tipoClase){
        List<Clase> clasesEncontradasTipo = new ArrayList<>();
        for (Clase clase: clases){
            if(clase.getTipoClase().equals(tipoClase)){
                clasesEncontradasTipo.add(clase);
            }
        }
        return clasesEncontradasTipo;
    }


    public List<Clase> buscarClaseEntrenador(Entrenador entrenador){
        List<Clase> clasesEncontradasEntrenador = new ArrayList<>();
        for(Clase clase : clases){
            if (clase.getEntrenador().equals(entrenador)){
                clasesEncontradasEntrenador.add(clase);
            }
        }
        return  clasesEncontradasEntrenador;
    }

    public boolean verificarDisponibilidadClase(Clase clase){
        boolean verificarDisponibilidad = true;
        return clase.getRegistroReserva().size() > clase.getCapacidad();
    }

    public boolean agregarReserva(Reserva reserva){
        boolean agregarReserva = true;
        Clase clase = reserva.getClase();
        if (clases.contains(clase) && verificarDisponibilidadClase(clase)) {
            clase.getRegistroReserva().add(reserva);
                clase.actualizarEstadoClase();
            return true;
        }
        else {return false;
        }
    }

    public boolean cancelarReserva(Reserva reserva){
        boolean cancelarReserva =true;
        Clase clase = reserva.getClase();
        if (clases.contains(clase) && clase.getRegistroReserva().remove(reserva)){
             clase.actualizarEstadoClase();
            return true;
        }
        else{
           return false;
        }
    }

    public void imprimirClases() {
        for (Clase clase : clases) {
            System.out.println(clase);
        }
    }

}

