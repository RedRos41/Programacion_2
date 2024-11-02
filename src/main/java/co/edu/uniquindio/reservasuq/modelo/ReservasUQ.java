package co.edu.uniquindio.reservasuq.modelo;

import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ReservasUQ implements ServiciosReservasUQ {

    private final List<Persona> personas;
    private final List<Instalacion> instalaciones;
    private final List<Reserva> reservas;

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
                .orElseThrow(() -> new Exception("Credenciales incorrectas"));
    }

    @Override
    public void registrarPersona(String cedula, String nombre, TipoPersona tipoPersona, String email, String password) throws Exception {
        Optional<Persona> existente = personas.stream()
                .filter(p -> p.getCedula().equals(cedula))
                .findFirst();

        if (existente.isPresent()) {
            throw new Exception("Ya existe una persona registrada con esta cédula");
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
        Instalacion nuevaInstalacion = Instalacion.builder()
                .id(UUID.randomUUID().toString())
                .nombre(nombre)
                .aforo(aforo)
                .costo(costo)
                .build();

        instalaciones.add(nuevaInstalacion);
    }

    @Override
    public Reserva crearReserva(String idInstalacion, String cedulaPersona, LocalDate diaReserva, String horaReserva) throws Exception {
        Instalacion instalacion = buscarInstalacionPorId(idInstalacion);
        Persona persona = buscarPersonaPorCedula(cedulaPersona);

        LocalTime hora = LocalTime.parse(horaReserva);
        LocalDateTime fechaHoraReserva = LocalDateTime.of(diaReserva, hora);

        for (Reserva reserva : reservas) {
            if (reserva.getInstalacion().getId().equals(idInstalacion) &&
                    reserva.getFechaHoraReserva().isEqual(fechaHoraReserva)) {
                throw new Exception("El horario ya está ocupado para esta instalación.");
            }
        }

        Reserva nuevaReserva = Reserva.builder()
                .idReserva(UUID.randomUUID().toString())
                .instalacion(instalacion)
                .persona(persona)
                .fechaHoraReserva(fechaHoraReserva)
                .fechaCreacion(LocalDate.now())
                .costo(calcularCosto(instalacion, persona))
                .build();

        reservas.add(nuevaReserva);
        return nuevaReserva;
    }

    @Override
    public List<Reserva> listarTodasReservas() {
        return reservas;
    }

    @Override
    public List<Reserva> listarReservasPorPersona(String cedulaPersona) {
        return reservas.stream()
                .filter(r -> r.getPersona().getCedula().equals(cedulaPersona))
                .collect(Collectors.toList());
    }

    @Override
    public void cancelarReserva(String idReserva) throws Exception {
        Reserva reserva = reservas.stream()
                .filter(r -> r.getIdReserva().equals(idReserva))
                .findFirst()
                .orElseThrow(() -> new Exception("Reserva no encontrada"));

        reservas.remove(reserva);
    }

    @Override
    public List<Instalacion> listarInstalaciones() {
        return instalaciones;
    }

    @Override
    public Instalacion buscarInstalacionPorId(String idInstalacion) throws Exception {
        return instalaciones.stream()
                .filter(i -> i.getId().equals(idInstalacion))
                .findFirst()
                .orElseThrow(() -> new Exception("Instalación no encontrada"));
    }

    @Override
    public void gestionarCapacidadInstalacion(String idInstalacion, int nuevaCapacidad) throws Exception {
        Instalacion instalacion = buscarInstalacionPorId(idInstalacion);
        instalacion.setAforo(nuevaCapacidad);
    }

    @Override
    public void asignarHorarioInstalacion(String idInstalacion, List<Horario> nuevosHorarios) throws Exception {
        Instalacion instalacion = buscarInstalacionPorId(idInstalacion);
        instalacion.setHorarios(nuevosHorarios);
    }

    @Override
    public List<Horario> listarHorariosDisponibles(String idInstalacion, LocalDate fecha) {
        List<LocalDateTime> horariosOcupados = reservas.stream()
                .filter(reserva -> reserva.getInstalacion().getId().equals(idInstalacion) && reserva.getFechaHoraReserva().toLocalDate().equals(fecha))
                .map(Reserva::getFechaHoraReserva)
                .collect(Collectors.toList());

        List<Horario> horariosDisponibles = new ArrayList<>();
        LocalTime horaInicio = LocalTime.of(8, 0); // Inicio jornada
        LocalTime horaFin = LocalTime.of(18, 0);   // Fin jornada

        while (horaInicio.isBefore(horaFin)) {
            final LocalTime inicio = horaInicio;
            if (horariosOcupados.stream().noneMatch(horaOcupada -> horaOcupada.toLocalTime().equals(inicio))) {
                horariosDisponibles.add(new Horario(null, inicio, inicio.plusMinutes(30)));
            }
            horaInicio = horaInicio.plusMinutes(30);
        }
        return horariosDisponibles;
    }

    private float calcularCosto(Instalacion instalacion, Persona persona) {
        if (persona.getTipoPersona() == TipoPersona.EXTERNO) {
            return instalacion.getCosto();
        }
        return 0;
    }

    private Persona buscarPersonaPorCedula(String cedula) throws Exception {
        return personas.stream()
                .filter(p -> p.getCedula().equals(cedula))
                .findFirst()
                .orElseThrow(() -> new Exception("Persona no encontrada."));
    }

    public Instalacion buscarInstalacionPorNombre(String nombre) throws Exception {
        return instalaciones.stream()
                .filter(instalacion -> instalacion.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElseThrow(() -> new Exception("Instalación no encontrada."));
    }
}
