package co.edu.uniquindio.proyectofinal.controladores;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class InicioControlador {

    // falta la lista aleatoria de alojamiententos
    // los botones para una sección para la búsqueda de alojamientos
    // y una sección de ofertas.

    private final ControladorPrincipal controladorPrincipal;

    public InicioControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    public void irIniciarSesion(ActionEvent actionEvent) {
        controladorPrincipal.navegarVentana("/IniciarSesion.fxml", "Iniciar Sesión");
    }

    public void irRegistroCliente(ActionEvent actionEvent) {
        controladorPrincipal.navegarVentana("/RegistrarUsuario.fxml", "Registro Persona");
    }


}
