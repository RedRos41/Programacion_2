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
import javafx.util.StringConverter;

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
            // Inicializar la lista de pacientes
            if (getClinica() != null) {
                ObservableList<Paciente> listaPacientes = getClinica().getPacientes();
                if (listaPacientes != null && !listaPacientes.isEmpty()) {
                    comboPaciente.setItems(listaPacientes);
                    comboPaciente.setConverter(new StringConverter<Paciente>() {
                        @Override
                        public String toString(Paciente paciente) {
                            return paciente.getNombre();  // Mostrar solo el nombre en el ComboBox
                        }

                        @Override
                        public Paciente fromString(String string) {
                            return null;  // No necesitamos implementar esto
                        }
                    });
                } else {
                    System.out.println("La lista de pacientes está vacía.");
                }

                // Inicializar la lista de servicios
                ObservableList<Servicio> listaServicios = getClinica().listarServicios();
                if (listaServicios != null && !listaServicios.isEmpty()) {
                    comboServicio.setItems(listaServicios);
                    comboServicio.setConverter(new StringConverter<Servicio>() {
                        @Override
                        public String toString(Servicio servicio) {
                            return servicio.getNombre();  // Mostrar solo el nombre del servicio en el ComboBox
                        }

                        @Override
                        public Servicio fromString(String string) {
                            return null;  // No necesitamos implementar esto
                        }
                    });
                } else {
                    System.out.println("La lista de servicios está vacía.");
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

            // Crear una nueva cita con el paciente, servicio y fecha seleccionados
            Cita nuevaCita = Cita.builder()
                    .idCita(UUID.randomUUID().toString())
                    .paciente(comboPaciente.getValue())
                    .servicio(comboServicio.getValue())
                    .fecha(fechaCita.getValue())
                    .build();

            // Registrar la cita en la clínica
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
