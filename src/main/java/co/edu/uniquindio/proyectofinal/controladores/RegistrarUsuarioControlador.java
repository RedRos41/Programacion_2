package co.edu.uniquindio.proyectofinal.controladores;

import co.edu.uniquindio.proyectofinal.modelos.enums.TipoUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrarUsuarioControlador {

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtContraseña;

    @FXML
    private TextField txtTelefono;

    @FXML
    private ComboBox<TipoUsuario> comboTipoUsuario;

    private final ControladorPrincipal controladorPrincipal;

    public RegistrarUsuarioControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    public void initialize() {
        comboTipoUsuario.getItems().addAll(TipoUsuario.values());
    }

    @FXML
    private void registrarUsuario(ActionEvent event) {
        try {
            long cedula = Long.parseLong(txtCedula.getText());
            String nombre = txtNombre.getText();
            String email = txtEmail.getText();
            String contraseña = txtContraseña.getText();
            long telefono = Long.parseLong(txtTelefono.getText());
            TipoUsuario tipoUsuario = comboTipoUsuario.getValue();

            controladorPrincipal.registrarUsuario(tipoUsuario, cedula, nombre, email, contraseña, telefono);

            controladorPrincipal.mostrarAlerta("Registro exitoso. Revisa tu correo para el código de activación.", "Éxito", Alert.AlertType.INFORMATION);
            controladorPrincipal.cerrarVentana(txtCedula);

        } catch (NumberFormatException e) {
            controladorPrincipal.mostrarAlerta("Cédula o teléfono inválidos. Deben ser numéricos.", "Error de Formato", Alert.AlertType.ERROR);
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta(e.getMessage(), "Error de Registro", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void cancelarRegistro(ActionEvent event) {
        controladorPrincipal.cerrarVentana(txtCedula);
    }
}
