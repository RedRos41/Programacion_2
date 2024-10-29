package co.edu.uniquindio.reservasuq.modelo;

import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import  co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ReservasUQ implements ServiciosReservasUQ {

    private List<Persona> personas;
    private List<Instalacion> instalaciones;
    private List<Reserva> reservas;

    public ReservasUQ() {
        this.personas = new ArrayList<>();
        this.instalaciones = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }


    @Override
    public Persona login(String correo, String contrasena) throws Exception {
        return personas.stream()
                .filter(p -> p.getEmail().equalsIgnoreCase(correo) && p.getPassword().equals(contrasena))
                .findFirst()
                .orElseThrow(() -> new Exception("Correo o contraseña incorrectos"));
    }

    @Override
    public void registrarPersona(String cedula, String nombre, TipoPersona tipoPersona, String email, String password) throws Exception {
        Optional<Persona> personaExistente = personas.stream()
                .filter(p -> p.getCedula().equals(cedula))
                .findFirst();
        if (personaExistente.isPresent()) {
            throw new Exception("La persona con esta cédula ya está registrada.");
        }
        Persona nuevaPersona = Persona.builder()
                .cedula(cedula)
                .nombre(nombre)
                .tipoPersona(tipoPersona)
                .email(email)
                .password(password)
                .build();
        personas.add(nuevaPersona);
    }

    @Override
    public void crearInstalacion(String nombre, int aforo, float costo, List<Horario> horarios) {

        Instalacion canchaFutbol = Instalacion.builder()
                .id(UUID.randomUUID().toString())
                .nombre("Cancha de Futbol")
                .aforo(30)
                .costo(200000)
                .horarios(horarios)
                .build();

        instalaciones.add(canchaFutbol);

        Instalacion nuevaInstalacion = Instalacion.builder()
                .id(UUID.randomUUID().toString())
                .nombre(nombre)
                .aforo(aforo)
                .costo(costo)
                .horarios(horarios)
                .build();
        instalaciones.add(nuevaInstalacion);
    }

    @Override
    public Reserva crearReserva(String idInstalacion, String cedulaPersona, LocalDate diaReserva, String horaReserva) throws Exception {
        return null;
    }

    @Override
    public List<Reserva> listarTodasReservas() {
        return List.of();
    }

    @Override
    public List<Reserva> listarReservasPorPersona(String cedulaPersona) {
        return List.of();
    }
}