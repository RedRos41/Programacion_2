package co.edu.uniquindio.reservasuq.modelo;

import co.edu.uniquindio.reservasuq.modelo.enums.DiaSemana;
import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class ReservasUQ implements ServiciosReservasUQ {

    private final List<Persona> personas;
    private final List<Instalacion> instalaciones;
    private final List<Reserva> reservas;

    public ReservasUQ() {
        this.personas = new ArrayList<>();
        this.instalaciones = new ArrayList<>();
        this.reservas = new ArrayList<>();

        // Crear instalaciones de ejemplo
        crearInstalacionesEjemplo();
    }

    private void crearInstalacionesEjemplo() {
        List<Horario> horariosGimnasio = new ArrayList<>();
        horariosGimnasio.add(new Horario(DiaSemana.LUNES, LocalTime.of(8, 0), LocalTime.of(15, 0)));
        horariosGimnasio.add(new Horario(DiaSemana.MARTES, LocalTime.of(8, 0), LocalTime.of(18, 0)));

        List<Horario> horariosPiscina = new ArrayList<>();
        horariosPiscina.add(new Horario(DiaSemana.JUEVES, LocalTime.of(8, 0), LocalTime.of(16, 0)));
        horariosPiscina.add(new Horario(DiaSemana.VIERNES, LocalTime.of(8, 0), LocalTime.of(15, 0)));

        List<Horario> horariosCanchaFutbol = new ArrayList<>();
        horariosCanchaFutbol.add(new Horario(DiaSemana.MIERCOLES, LocalTime.of(8, 0), LocalTime.of(20, 0)));
        horariosCanchaFutbol.add(new Horario(DiaSemana.JUEVES, LocalTime.of(10, 0), LocalTime.of(20, 0)));
        horariosCanchaFutbol.add(new Horario(DiaSemana.SABADO, LocalTime.of(7, 0), LocalTime.of(14, 0)));

        Instalacion gimnasio = Instalacion.builder()
                .id(UUID.randomUUID().toString())
                .nombre("Gimnasio")
                .aforo(20)
                .costo(45000)
                .horarios(new HashMap<>())
                .build();
        gimnasio.agregarHorario(DiaSemana.LUNES, LocalTime.of(8, 0), LocalTime.of(18, 0));
        gimnasio.agregarHorario(DiaSemana.MARTES, LocalTime.of(8, 0), LocalTime.of(18, 0));

        Instalacion piscina = Instalacion.builder()
                .id(UUID.randomUUID().toString())
                .nombre("Piscina")
                .aforo(15)
                .costo(18000)
                .horarios(new HashMap<>())
                .build();
        piscina.agregarHorario(DiaSemana.JUEVES, LocalTime.of(8, 0), LocalTime.of(16, 0));
        piscina.agregarHorario(DiaSemana.VIERNES, LocalTime.of(8, 0), LocalTime.of(16, 0));

        Instalacion canchaFutbol = Instalacion.builder()
                .id(UUID.randomUUID().toString())
                .nombre("Cancha de Futbol")
                .aforo(25)
                .costo(55000)
                .horarios(new HashMap<>())
                .build();
        canchaFutbol.agregarHorario(DiaSemana.MIERCOLES, LocalTime.of(8, 0), LocalTime.of(20, 0));
        canchaFutbol.agregarHorario(DiaSemana.JUEVES, LocalTime.of(10, 0), LocalTime.of(20, 0));
        canchaFutbol.agregarHorario(DiaSemana.SABADO, LocalTime.of(7, 0), LocalTime.of(14, 0));

        instalaciones.add(gimnasio);
        instalaciones.add(piscina);
        instalaciones.add(canchaFutbol);
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
                .horarios(new HashMap<>())
                .build();

        for (Horario horario : horarios) {
            nuevaInstalacion.agregarHorario(horario.getDiaSemana(), horario.getHoraInicio(), horario.getHoraFin());
        }

        instalaciones.add(nuevaInstalacion);
    }

    @Override
    public Reserva crearReserva(String idInstalacion, String cedulaPersona, LocalDate diaReserva, String horaReserva) throws Exception {
        Instalacion instalacion = buscarInstalacionPorId(idInstalacion);
        Persona persona = buscarPersonaPorCedula(cedulaPersona);

        LocalTime hora = LocalTime.parse(horaReserva);
        LocalDateTime fechaHoraReserva = LocalDateTime.of(diaReserva, hora);

        if (reservas.stream().anyMatch(reserva ->
                reserva.getInstalacion().getId().equals(idInstalacion) &&
                        reserva.getFechaHoraReserva().equals(fechaHoraReserva))) {
            throw new Exception("El horario ya está ocupado para esta instalación.");
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
        for (Horario horario : nuevosHorarios) {
            instalacion.agregarHorario(horario.getDiaSemana(), horario.getHoraInicio(), horario.getHoraFin());
        }
    }

    @Override
    public List<Horario> listarHorariosDisponibles(String idInstalacion, LocalDate fecha) {
        Instalacion instalacion = null;
        try {
            instalacion = buscarInstalacionPorId(idInstalacion);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<LocalDateTime> horariosOcupados = reservas.stream()
                .filter(reserva -> reserva.getInstalacion().getId().equals(idInstalacion) && reserva.getFechaHoraReserva().toLocalDate().equals(fecha))
                .map(Reserva::getFechaHoraReserva)
                .collect(Collectors.toList());

        List<Horario> horariosDisponibles = new ArrayList<>();
        DiaSemana diaSemana = DiaSemana.fromLocalDate(fecha);

        for (Horario horario : instalacion.obtenerHorariosPorDia(diaSemana)) {
            LocalTime horaInicio = horario.getHoraInicio();
            LocalTime horaFin = horario.getHoraFin();

            while (horaInicio.isBefore(horaFin)) {
                LocalDateTime horarioPropuesto = LocalDateTime.of(fecha, horaInicio);
                if (horariosOcupados.stream().noneMatch(hora -> hora.equals(horarioPropuesto))) {
                    horariosDisponibles.add(new Horario(diaSemana, horaInicio, horaInicio.plusMinutes(30)));
                }
                horaInicio = horaInicio.plusMinutes(30);
            }
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

    @Override
    public void actualizarInstalacion(Instalacion instalacion) throws Exception {
        Instalacion original = buscarInstalacionPorId(instalacion.getId());
        original.setNombre(instalacion.getNombre());
        original.setAforo(instalacion.getAforo());
        original.setCosto(instalacion.getCosto());
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
    public boolean verificarDisponibilidadConDuracion(String idInstalacion, LocalDateTime fechaHoraReserva, int duracionHoras) {
        LocalDateTime finReserva = fechaHoraReserva.plusHours(duracionHoras);
        return reservas.stream()
                .filter(reserva -> reserva.getInstalacion().getId().equals(idInstalacion))
                .noneMatch(reserva ->
                        (reserva.getFechaHoraReserva().isBefore(finReserva) && reserva.getFechaHoraReserva().plusHours(duracionHoras).isAfter(fechaHoraReserva))
                );
    }
}
