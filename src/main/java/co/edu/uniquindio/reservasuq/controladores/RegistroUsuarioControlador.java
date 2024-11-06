package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import co.edu.uniquindio.reservasuq.controladores.ControladorPrincipal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistroUsuarioControlador {

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtEmail;

    @FXML
    private ComboBox<TipoPersona> comboTipoPersona;

    @FXML
    private PasswordField txtPassword;

    private final ControladorPrincipal controladorPrincipal;

    public RegistroUsuarioControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    public void initialize() {
        comboTipoPersona.getItems().addAll(TipoPersona.values());
    }

    @FXML
    public void registrarUsuario(ActionEvent event) {
        try {
            // Validar que todos los campos estén llenos
            if (txtCedula.getText().isEmpty() || txtNombre.getText().isEmpty() || txtEmail.getText().isEmpty()
                    || txtPassword.getText().isEmpty() || comboTipoPersona.getValue() == null) {
                mostrarAlerta("Error", "Por favor, complete todos los campos.", Alert.AlertType.ERROR);
                return;
            }

            controladorPrincipal.registrarPersona(
                    txtCedula.getText(),
                    txtNombre.getText(),
                    comboTipoPersona.getValue(),
                    txtEmail.getText(),
                    txtPassword.getText()
            );
            mostrarAlerta("Éxito", "Usuario registrado correctamente", Alert.AlertType.INFORMATION);

            limpiarFormulario();

        } catch (Exception e) {
            mostrarAlerta("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarFormulario() {
        txtCedula.clear();
        txtNombre.clear();
        txtEmail.clear();
        txtPassword.clear();
        comboTipoPersona.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}