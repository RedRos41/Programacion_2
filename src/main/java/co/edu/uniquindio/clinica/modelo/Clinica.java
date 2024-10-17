package co.edu.uniquindio.clinica.modelo;

import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionBasica;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionPremium;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Clinica {

    private List<Paciente> pacientes;
    private List<Cita> citas;
    private List<Servicio> servicios;

    public Clinica() {
        pacientes = new ArrayList<>();
        citas = new ArrayList<>();
        servicios = new ArrayList<>();
    }

    // Método para registrar un paciente
    public void registrarPaciente(String telefono, String nombre, String cedula, String email) throws Exception {
        if (nombre.isEmpty() || cedula.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
            throw new Exception("Todos los campos son obligatorios.");
        }

        Paciente paciente = Paciente.builder()
                .id(UUID.randomUUID().toString())
                .telefono(telefono)
                .nombre(nombre)
                .cedula(cedula)
                .email(email)
                .build();
        pacientes.add(paciente);
    }

    // Método para listar los pacientes registrados
    public List<Paciente> listarPacientes() {
        return pacientes;
    }

    // Método para registrar un servicio
    public void registrarServicio(double precio, String nombre) {
        Servicio servicio = Servicio.builder()
                .id(UUID.randomUUID().toString())
                .precio(precio)
                .nombre(nombre)
                .build();
        servicios.add(servicio);
    }

    // Método para obtener los servicios disponibles
    public List<Servicio> getServiciosDisponibles() {
        return servicios;
    }

    // Método para registrar una cita (validando si no hay un cruce de horario)
    public void registrarCita(Paciente paciente, Servicio servicio, LocalDate fecha) throws Exception {
        if (paciente == null) {
            throw new Exception("El paciente no puede ser nulo.");
        }

        if (servicio == null) {
            throw new Exception("El servicio no puede ser nulo.");
        }

        for (Cita c : citas) {
            if (c.getFecha().equals(fecha) && c.getPaciente().equals(paciente)) {
                throw new Exception("Ya existe una cita registrada para ese paciente en el mismo horario.");
            }
        }

        Factura factura = generarFactura(paciente, servicio);
        Cita nuevaCita = Cita.builder()
                .id(UUID.randomUUID().toString())
                .paciente(paciente)
                .servicio(servicio)
                .fecha(fecha)
                .factura(factura)
                .build();
        citas.add(nuevaCita);
    }

    // Método para listar las citas
    public List<Cita> listarCitas() {
        return citas;
    }

    // Método para generar una factura
    public Factura generarFactura(Paciente paciente, Servicio servicio) {
        double total = paciente.getSuscripcion().calcularPrecio(servicio);
        return Factura.builder()
                .id(UUID.randomUUID().toString())
                .paciente(paciente)
                .servicio(servicio)
                .total(total)
                .build();
    }
}
