package co.edu.uniquindio.proyectofinal.controladores;

import co.edu.uniquindio.proyectofinal.modelos.Cliente;
import co.edu.uniquindio.proyectofinal.modelos.Sesion;
import co.edu.uniquindio.proyectofinal.controladores.ControladorPrincipal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.Node;

public class EditarPerfilClienteControlador {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtTelefono;

    private final ControladorPrincipal controladorPrincipal;

    public EditarPerfilClienteControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    public void initialize() {
        // Cargar la información del cliente actual en los campos de texto
        Cliente cliente = (Cliente) Sesion.getInstancia().getUsuario();
        if (cliente != null) {
            txtNombre.setText(cliente.getNombreUsuario());
            txtEmail.setText(cliente.getEmailUsuario());
            txtTelefono.setText(String.valueOf(cliente.getTelefonoUsuario()));
        }
    }

    @FXML
    private void guardarCambios(ActionEvent event) {
        try {
            // Obtener los datos actualizados desde los campos de texto
            String nombre = txtNombre.getText();
            String email = txtEmail.getText();
            long telefono = Long.parseLong(txtTelefono.getText());

            Cliente cliente = (Cliente) Sesion.getInstancia().getUsuario();

            // Actualizar la información del cliente en la clase `Empresa`
            controladorPrincipal.editarUsuario(cliente.getCedulaUsuario(), nombre, email, telefono);

            controladorPrincipal.mostrarAlerta("Perfil actualizado exitosamente", "Éxito", Alert.AlertType.INFORMATION);
            cerrarVentana(event);
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta(e.getMessage(), "Error al actualizar perfil", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void eliminarCuenta(ActionEvent event) {
        try {
            Cliente cliente = (Cliente) Sesion.getInstancia().getUsuario();

            controladorPrincipal.eliminarUsuario(cliente.getCedulaUsuario());

            controladorPrincipal.mostrarAlerta("Cuenta eliminada exitosamente", "Éxito", Alert.AlertType.INFORMATION);

            // Cerrar sesión y ventana después de eliminar la cuenta
            Sesion.getInstancia().cerrarSesion();
            cerrarVentana(event);

            // Regresar al inicio de sesión o ventana principal después de la eliminación
            controladorPrincipal.navegarVentana("/iniciarSesion.fxml", "Iniciar Sesión");
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta(e.getMessage(), "Error al eliminar cuenta", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void cancelarEdicion(ActionEvent event) {
        cerrarVentana(event);
    }

    private void cerrarVentana(ActionEvent event) {
        Node source = (Node) event.getSource();
        controladorPrincipal.cerrarVentana(source);
    }
}
