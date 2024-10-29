package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.modelo.Persona;
import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class LoginControlador {


    @FXML
    public TextField txtCorreo;


    @FXML
    public TextField txtPassword;


    private final ControladorPrincipal controladorPrincipal;


    public LoginControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }


    public void login(ActionEvent actionEvent) {


        try {


            String email = txtCorreo.getText();
            String password = txtPassword.getText();


            Persona persona = controladorPrincipal.login(email, password);


            if(persona.getTipoPersona() != TipoPersona.ADMIN) {
                controladorPrincipal.navegarVentana("/panelCliente.fxml", "Panel Usuario");
            }else{
                controladorPrincipal.navegarVentana("/panelAdmin.fxml", "Panel Administrador");
            }


            controladorPrincipal.cerrarVentana(txtCorreo);


        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta(e.getMessage(), "Error", Alert.AlertType.ERROR);
        }


    }
}

