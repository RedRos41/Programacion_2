package co.edu.uniquindio.seguimiento2.controladores;


import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class ContactoControlador {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
