package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Cita;
import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.modelo.Servicio;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class RegistroCitaControlador extends AbstractControlador {

    @FXML
    private ComboBox<Paciente> comboPaciente;

    @FXML
    private ComboBox<Servicio> comboServicio;

    @FXML
    private DatePicker fechaCita;

    @FXML
    private TextField horaCita;

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
                            return paciente.getNombre();
                        }

                        @Override
                        public Paciente fromString(String string) {
                            return null;
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
                            return servicio.getNombre();
                        }

                        @Override
                        public Servicio fromString(String string) {
                            return null;
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
            if (comboPaciente.getValue() == null || comboServicio.getValue() == null || fechaCita.getValue() == null || horaCita.getText().isEmpty()) {
                mostrarAlerta("Error", "Por favor, complete todos los campos.", Alert.AlertType.ERROR);
                return;
            }

            LocalDate fecha = fechaCita.getValue();
            LocalTime hora = LocalTime.parse(horaCita.getText());
            LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);

            Cita nuevaCita = Cita.builder()
                    .idCita(UUID.randomUUID().toString())
                    .paciente(comboPaciente.getValue())
                    .servicio(comboServicio.getValue())
                    .fechaHora(fechaHora)
                    .build();

            // Registrar la cita en la clínica
            getClinica().registrarCita(nuevaCita);
            mostrarAlerta("Éxito", "Cita registrada correctamente", Alert.AlertType.INFORMATION);

            // Limpiar los campos del formulario
            limpiarFormularioCita();

        } catch (Exception e) {
            mostrarAlerta("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarFormularioCita() {
        comboPaciente.setValue(null);
        comboServicio.setValue(null);
        fechaCita.setValue(null);
        horaCita.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
