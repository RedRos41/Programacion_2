package co.edu.uniquindio.seguimiento2.controladores;


import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class ContactoControlador {
    @FXML
    private Label añadirText;

    @FXML
    protected void crearButton() {
        añadirText.setText("Acción de añadir contacto");
    }

    @FXML
    private Label editarText;

    @FXML
    protected void actualizarButton() {
        editarText.setText("Acción de editar contacto");
    }

    @FXML
    private Label eliminarText;

    @FXML
    protected void eliminarButton() {
        eliminarText.setText("Acción de eliminar contacto");
    }

}
