package co.edu.uniquindio.proyectofinal.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import co.edu.uniquindio.proyectofinal.controladores.ControladorPrincipal;

public class IniciarSesionControlador {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtContraseña;

    @FXML
    private TextField txtCodigoActivacion;

    private final ControladorPrincipal controladorPrincipal;

    public IniciarSesionControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    private void iniciarSesion(ActionEvent event) {
        try {
            String email = txtEmail.getText();
            String contraseña = txtContraseña.getText();
            String codigoActivacion = txtCodigoActivacion.getText();

            controladorPrincipal.iniciarSesion(email, codigoActivacion, contraseña);

            controladorPrincipal.mostrarAlerta("Inicio de sesión exitoso", "Éxito", Alert.AlertType.INFORMATION);
            controladorPrincipal.cerrarVentana(txtEmail);

        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta(e.getMessage(), "Error de Inicio de Sesión", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void cancelarInicioSesion(ActionEvent event) {
        controladorPrincipal.cerrarVentana(txtEmail);
    }
}
