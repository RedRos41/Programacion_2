package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Cita;
import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.modelo.Servicio;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;

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
        Platform.runLater(() -> {
            if (getClinica() != null) {
                ObservableList<Paciente> listaPacientes = getClinica().getPacientes();
                if (listaPacientes != null && !listaPacientes.isEmpty()) {
                    comboPaciente.setItems(listaPacientes);

                    // Configurar el ComboBox para mostrar solo el nombre del paciente
                    comboPaciente.setCellFactory(lv -> new ListCell<Paciente>() {
                        @Override
                        protected void updateItem(Paciente paciente, boolean empty) {
                            super.updateItem(paciente, empty);
                            setText(empty ? null : paciente.getNombre());  // Mostrar solo el nombre
                        }
                    });

                    // Mostrar el nombre del paciente seleccionado
                    comboPaciente.setButtonCell(new ListCell<Paciente>() {
                        @Override
                        protected void updateItem(Paciente paciente, boolean empty) {
                            super.updateItem(paciente, empty);
                            setText(empty ? null : paciente.getNombre());  // Mostrar solo el nombre
                        }
                    });

                } else {
                    System.out.println("La lista de pacientes está vacía.");
                }
            } else {
                System.out.println("Error: No se pudo inicializar la instancia de la clínica.");
            }
        });
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
