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
    private ObservableList<Servicio> servicios;


    private Clinica() {
        this.pacientes = FXCollections.observableArrayList();
        this.citas = new ArrayList<>();
        this.servicios = FXCollections.observableArrayList();

        serviciosBasicos();
    }

    public static Clinica getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new Clinica();
        }
        return INSTANCIA;
    }

    private void serviciosBasicos() {

        Servicio nuevoServicio1 = Servicio.builder()
                .idServicio(UUID.randomUUID().toString())
                .nombre("Consulta General")
                .precio(40000)
                .build();

        Servicio nuevoServicio2 = Servicio.builder()
                .idServicio(UUID.randomUUID().toString())
                .nombre("Examen de Laboratorio")
                .precio(75000)
                .build();

        servicios.add(nuevoServicio1);
        servicios.add(nuevoServicio2);
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
    }

    public ObservableList<Paciente>  getPacientes() {
        return pacientes;
    }


    public void registrarCita(Cita nuevaCita) throws Exception {
        for (Cita citaExistente : citas) {
            if (citaExistente.getFechaHora().equals(nuevaCita.getFechaHora()) &&
                    citaExistente.getPaciente().equals(nuevaCita.getPaciente())) {
                throw new Exception("El paciente ya tiene una cita programada en esta fecha y hora.");
            }
        }

        double total = nuevaCita.getPaciente().getSuscripcion().calcularPrecio(nuevaCita.getServicio().getPrecio());

        Factura factura = new Factura(nuevaCita.getPaciente(), nuevaCita.getServicio(), total);
        nuevaCita.setFactura(factura);

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

    public ObservableList<Servicio> listarServicios() {
        return servicios;
    }

    public void registrarServicio(Servicio servicio) {
        servicios.add(servicio);
    }


}

