package co.edu.uniquindio.clinica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

public class RegistroPacienteControlador extends AbstractControlador {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtEmail;

    @FXML
    private ComboBox<String> comboSuscripcion;

    @FXML
    public void initialize() {
        comboSuscripcion.getItems().addAll("Basica", "Premium", "vip");
    }

    @FXML
    public void registrarPaciente(ActionEvent event) {
        try {
            if (txtCedula.getText().isEmpty() || txtNombre.getText().isEmpty() || txtTelefono.getText().isEmpty() || txtEmail.getText().isEmpty()) {
                mostrarAlerta("Error", "Por favor, complete todos los campos.", Alert.AlertType.ERROR);
                return;
            }

            getClinica().registrarPaciente(
                    txtCedula.getText(),
                    txtNombre.getText(),
                    txtTelefono.getText(),
                    txtEmail.getText(),
                    comboSuscripcion.getValue()
            );
            mostrarAlerta("Éxito", "Paciente registrado correctamente", Alert.AlertType.INFORMATION);

            // Limpiar los campos del formulario
            limpiarFormularioPaciente();
        } catch (Exception e) {
            mostrarAlerta("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarFormularioPaciente() {
        txtCedula.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtEmail.clear();
        comboSuscripcion.setValue(null);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
