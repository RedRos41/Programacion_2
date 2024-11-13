package co.edu.uniquindio.proyectofinal.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import co.edu.uniquindio.proyectofinal.modelos.Sesion;
import co.edu.uniquindio.proyectofinal.modelos.Usuario;
import co.edu.uniquindio.proyectofinal.modelos.enums.TipoUsuario;

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

            Usuario usuarioAutenticado = controladorPrincipal.iniciarSesion(email, codigoActivacion, contraseña);
            Sesion.getInstancia().setUsuario(usuarioAutenticado);

            controladorPrincipal.mostrarAlerta("Inicio de sesión exitoso", "Éxito", Alert.AlertType.INFORMATION);
            controladorPrincipal.cerrarVentana(txtEmail);

            if (usuarioAutenticado.getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
                controladorPrincipal.navegarVentana("/PanelAdmin.fxml", "Panel de Administrador");
            } else if (usuarioAutenticado.getTipoUsuario() == TipoUsuario.CLIENTE) {
                controladorPrincipal.navegarVentana("/PanelCliente.fxml", "Panel de Cliente");
            }

        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta(e.getMessage(), "Error de Inicio de Sesión", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void cancelarInicioSesion(ActionEvent event) {
        controladorPrincipal.cerrarVentana(txtEmail);
    }
}
