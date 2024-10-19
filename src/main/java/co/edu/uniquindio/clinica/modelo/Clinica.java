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

        Paciente paciente = Paciente.builder()
                .id(UUID.randomUUID().toString())
                .telefono(telefono)
                .nombre(nombre)
                .cedula(cedula)
                .email(email)
                .suscripcion(SuscripcionFactory.crearSuscripcion(tipoSuscripcion))
                .build();

        pacientes.add(paciente);
    }

    public ObservableList<Paciente>  getPacientes() {
        return pacientes;
    }

    public void registrarCita(Cita cita) throws Exception {
        for (Cita c : citas) {
            if (c.getFecha().equals(cita.getFecha()) && c.getPaciente().equals(cita.getPaciente())) {
                throw new Exception("Ya existe una cita para el paciente en esa fecha.");
            }
        }
        citas.add(cita);
    }

    public List<Cita> listarCitas() {
        return citas;
    }

    public void registrarServicio(Servicio servicio) {
        servicios.add(servicio);
    }

    public List<Servicio> listarServicios() {
        return servicios;
    }

    public boolean cancelarCita(String idCita) {
        return citas.removeIf(cita -> cita.getIdCita() == idCita);
    }


    public boolean validarDisponibilidadCita(Cita nuevaCita) {
        for (Cita citaExistente : citas) {
            if (citaExistente.getFecha().equals(nuevaCita.getFecha())) {
                return false; // Cita en conflicto
            }
        }
        return true;
    }

}

