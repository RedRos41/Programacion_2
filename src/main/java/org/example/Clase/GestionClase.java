package org.example.Clase;

import org.example.Entrenador.Entrenador;
import org.example.Usuario.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class GestionClase {
    private final List<Clase> clases;
    private final List<Reserva> reservas;


    public GestionClase() {
        this.clases = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public List<Clase> getClases() {
        return clases;
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

    public List<Clase> buscarClaseHorario(LocalTime horarioClase) {
        List<Clase> clasesEncontradasHorario = new ArrayList<>();
        for (Clase clase : clases) {
            if (clase.getHorarioClase().equals(horarioClase)) {
                clasesEncontradasHorario.add(clase);
            }
        }
        return clasesEncontradasHorario;
    }

    public List<Clase> buscarClaseTipo(TipoClase tipoClase) {
        List<Clase> clasesEncontradasTipo = new ArrayList<>();
        for (Clase clase : clases) {
            if (clase.getTipoClase().equals(tipoClase)) {
                clasesEncontradasTipo.add(clase);
            }
        }
        return clasesEncontradasTipo;
    }


    public List<Clase> buscarClaseEntrenador(Entrenador entrenador) {
        List<Clase> clasesEncontradasEntrenador = new ArrayList<>();
        for (Clase clase : clases) {
            if (clase.getEntrenador().equals(entrenador)) {
                clasesEncontradasEntrenador.add(clase);
            }
        }
        return clasesEncontradasEntrenador;
    }

    public boolean verificarDisponibilidadClase(Clase clase) {
        return clase.getRegistroReserva().size() < clase.getCapacidad();
    }

    public void actualizarEstadoClase(Clase clase) {
        boolean estadoActualizado = clase.getRegistroReserva().size() < clase.getCapacidad();
        clase.setEstadoClase(estadoActualizado);
    }

    public Reserva agregarReserva(Usuario usuario, LocalDateTime fechaReserva, Clase clase, String codigo) {

        if (!verificarDisponibilidadClase(clase)) {
            System.out.println("No hay disponibilidad en la clase seleccionada.");
            return null;
        }


        Reserva nuevaReserva = new Reserva(usuario, fechaReserva, clase, codigo);
        clase.getRegistroReserva().add(nuevaReserva);
        actualizarEstadoClase(clase);
        System.out.println("Reserva realizada exitosamente.");
        return nuevaReserva;


    }

    public boolean cancelarReserva(Usuario usuario, String codigo) {
        for (Clase clase : clases) {
            Optional<Reserva> reservaOptional = clase.getRegistroReserva().stream()
                    .filter(r -> r.getCodigo().equals(codigo) && r.getUsuario().equals(usuario))
                    .findFirst();

            if (reservaOptional.isPresent()) {
                Reserva reserva = reservaOptional.get();
                clase.getRegistroReserva().remove(reserva);
                actualizarEstadoClase(clase);
                System.out.println("Reserva cancelada exitosamente.");
                return true;
            }
        }

        System.out.println("Reserva no encontrada.");
        return false;
    }

    public void imprimirClases() {
        for (Clase clase : clases) {
            System.out.println(clase);
        }
    }

    public List<Reserva> imprimirReservas() {
        List<Reserva> todasLasReservas = new ArrayList<>();
        for (Clase clase : clases) {
            todasLasReservas.addAll(clase.getRegistroReserva());
        }
        return todasLasReservas;
    }

}


