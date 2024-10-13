package co.edu.uniquindio.proyectofinal.controladores;


import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class UsuarioController {

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

}
