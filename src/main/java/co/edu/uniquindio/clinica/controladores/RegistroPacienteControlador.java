package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.modelo.Clinica;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

public class RegistroPacienteControlador {

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

    private final Clinica clinica;
    private ListaPacientesControlador listaPacientesControlador;

    public RegistroPacienteControlador(Clinica clinica, ListaPacientesControlador listaPacientesControlador) {
        this.clinica = clinica;
        this.listaPacientesControlador = listaPacientesControlador;
    }

    @FXML
    public void initialize() {
        comboSuscripcion.getItems().addAll("Basica", "Premium");
    }

    @FXML
    public void registrarPaciente(ActionEvent event) {
        try {
            if (txtNombre.getText().isEmpty() || txtCedula.getText().isEmpty() || txtTelefono.getText().isEmpty() || txtEmail.getText().isEmpty() || comboSuscripcion.getValue() == null) {
                mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
                return;
            }

            clinica.registrarPaciente(
                    txtTelefono.getText(),
                    txtNombre.getText(),
                    txtCedula.getText(),
                    txtEmail.getText()
            );

            listaPacientesControlador.actualizarListaPacientes();
            mostrarAlerta("Éxito", "Paciente registrado correctamente", Alert.AlertType.INFORMATION);
            limpiarFormulario();
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

    private void limpiarFormulario() {
        txtNombre.clear();
        txtCedula.clear();
        txtTelefono.clear();
        txtEmail.clear();
        comboSuscripcion.setValue(null);
    }
}
