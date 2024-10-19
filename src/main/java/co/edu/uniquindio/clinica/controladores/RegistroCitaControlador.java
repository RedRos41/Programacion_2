package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Cita;
import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.modelo.Servicio;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.util.UUID;

public class RegistroCitaControlador extends AbstractControlador {

    @FXML
    private ComboBox<Paciente> comboPaciente;

    @FXML
    private ComboBox<Servicio> comboServicio;

    @FXML
    private DatePicker fechaCita;

    @FXML
    public void initialize() {
        comboPaciente.getItems().setAll(getClinica().getPacientes());
        comboServicio.getItems().setAll(getClinica().listarServicios());
    }

    @FXML
    public void registrarCita() {
        try {
            if (comboPaciente.getValue() == null || comboServicio.getValue() == null || fechaCita.getValue() == null) {
                mostrarAlerta("Error", "Por favor, complete todos los campos.", Alert.AlertType.ERROR);
                return;
            }
            Cita nuevaCita = Cita.builder()
                    .idCita(UUID.randomUUID().toString())
                    .paciente(comboPaciente.getValue())
                    .servicio(comboServicio.getValue())
                    .fecha(fechaCita.getValue())
                    .build();

            getClinica().registrarCita(nuevaCita);
            mostrarAlerta("Éxito", "Cita registrada correctamente", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            mostrarAlerta("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
