package co.edu.uniquindio.clinica.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Clinica {

    private final ObservableList<Paciente> pacientes;
    private final ObservableList<Servicio> servicios;
    private final ObservableList<Cita> citas;

    // Constructor que acepta listas
    public Clinica(ObservableList<Paciente> pacientes, ObservableList<Servicio> servicios, ObservableList<Cita> citas) {
        this.pacientes = pacientes;
        this.servicios = servicios;
        this.citas = citas;
    }

    // Constructor sin argumentos que inicializa listas vacías
    public Clinica() {
        this.pacientes = FXCollections.observableArrayList();
        this.servicios = FXCollections.observableArrayList();
        this.citas = FXCollections.observableArrayList();
    }

    // Método para registrar pacientes
    public void registrarPaciente(String telefono, String nombre, String cedula, String email, String tipoSuscripcion) throws Exception {
        if (nombre.isEmpty() || cedula.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
            throw new Exception("Todos los campos son obligatorios.");
        }

        Suscripcion suscripcion = tipoSuscripcion.equals("Básica") ? new SuscripcionBasica() : new SuscripcionPremium();

        Paciente paciente = Paciente.builder()
                .id(java.util.UUID.randomUUID().toString())
                .telefono(telefono)
                .nombre(nombre)
                .cedula(cedula)
                .email(email)
                .suscripcion(suscripcion)
                .build();

        pacientes.add(paciente);
    }

    // Método para registrar servicios
    public void registrarServicio(double precio, String nombre) {
        Servicio servicio = Servicio.builder()
                .id(java.util.UUID.randomUUID().toString())
                .precio(precio)
                .nombre(nombre)
                .build();

        servicios.add(servicio);
    }

    // Método para registrar una cita
    public void registrarCita(Cita cita) throws Exception {
        for (Cita c : citas) {
            if (c.getFecha().equals(cita.getFecha())) {
                throw new Exception("Ya hay una cita registrada para este horario.");
            }
        }
        citas.add(cita);
    }

    // Métodos de acceso (getters) para obtener las listas
    public ObservableList<Paciente> getPacientes() {
        return pacientes;
    }

    public ObservableList<Servicio> getServicios() {
        return servicios;
    }

    public ObservableList<Cita> getCitas() {
        return citas;
    }

    // Métodos para buscar un paciente o servicio por ID
    public Paciente buscarPacientePorId(String id) {
        for (Paciente p : pacientes) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public Servicio buscarServicioPorId(String id) {
        for (Servicio s : servicios) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }
}
