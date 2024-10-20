package co.edu.uniquindio.clinica.modelo;

import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionBasica;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionFactory;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionPremium;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Clinica {

    private static Clinica INSTANCIA;

    private ObservableList<Paciente> pacientes;
    private List<Cita> citas;
    private List<Servicio> servicios;

    private Clinica() {
        this.pacientes = FXCollections.observableArrayList();
        this.citas = new ArrayList<>();
        this.servicios = new ArrayList<>();
    }

    public static Clinica getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new Clinica();
        }
        return INSTANCIA;
    }

    public void registrarPaciente(String telefono, String nombre, String cedula, String email, String tipoSuscripcion) throws Exception {
        if (nombre.isEmpty() || cedula.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
            throw new Exception("Todos los campos son obligatorios.");
        }

        for (Paciente p : pacientes) {
            if (p.getCedula().equals(cedula)) {
                throw new Exception("Ya existe un paciente registrado con esta cédula.");
            }
        }

        Paciente paciente = Paciente.builder()
                .id(UUID.randomUUID().toString())
                .telefono(telefono)
                .nombre(nombre)
                .cedula(cedula)
                .email(email)
                .suscripcion(SuscripcionFactory.crearSuscripcion(tipoSuscripcion))
                .build();

        pacientes.add(paciente);
        System.out.println("Paciente registrado: " + paciente.getNombre());
    }

    public ObservableList<Paciente>  getPacientes() {
        return pacientes;
    }

    public void registrarCita(Cita nuevaCita) throws Exception {
        // Validar si ya hay una cita en la misma fecha para el paciente
        for (Cita citaExistente : citas) {
            if (citaExistente.getFecha().equals(nuevaCita.getFecha()) &&
                    citaExistente.getPaciente().equals(nuevaCita.getPaciente())) {
                throw new Exception("El paciente ya tiene una cita programada en esta fecha.");
            }
        }
        citas.add(nuevaCita);
    }

    public List<Cita> listarCitas() {
        return citas;
    }

    public boolean cancelarCita(String idCita) {
        for (Cita cita : citas) {
            if (cita.getIdCita().equals(idCita)) {
                citas.remove(cita);
                return true;
            }
        }
        return false;
    }

    public void registrarServicio(Servicio servicio) {
        servicios.add(servicio);
    }

    public List<Servicio> listarServicios() {
        return servicios;
    }


}

