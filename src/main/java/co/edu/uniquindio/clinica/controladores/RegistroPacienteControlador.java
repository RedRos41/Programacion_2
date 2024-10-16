package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Cita;
import co.edu.uniquindio.clinica.modelo.Clinica;
import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.modelo.Servicio;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class RegistroPacienteControlador {

    @FXML
    private TextField txtPacienteId, txtServicioId;
    @FXML
    private DatePicker dateFecha;

    private Clinica clinica;

    public void inicializar(Clinica clinica) {
        this.clinica = clinica;
    }
    public void registrarCita() {
        try {
            String pacienteId = txtPacienteId.getText();
            String servicioId = txtServicioId.getText();
            LocalDate fecha = dateFecha.getValue();

            Paciente paciente = clinica.buscarPacientePorId(pacienteId);
            Servicio servicio = clinica.buscarServicioPorId(servicioId);

            if (paciente != null && servicio != null) {
                Cita cita = new Cita();
                clinica.registrarCita(cita);
            } else {
                System.out.println("Paciente o Servicio no encontrados");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
