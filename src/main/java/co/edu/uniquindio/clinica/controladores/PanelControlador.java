package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Clinica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class PanelControlador {

    @FXML
    private StackPane panelPrincipal;

    private final Clinica clinica;

    public PanelControlador() {
        this.clinica = Clinica.getInstancia();
    }

    public void mostrarRegistroPaciente(ActionEvent actionEvent) {
        Parent node = cargarPanel("/registroPaciente.fxml");
        if (node != null) {
            panelPrincipal.getChildren().setAll(node);
        }
    }


    public void mostrarListaPacientes(ActionEvent actionEvent) {
        cargarPanel("/listaPacientes.fxml");
    }

    public void mostrarRegistroCita(ActionEvent actionEvent) {
        cargarPanel("/registroCita.fxml");
    }

    public void mostrarListaCitas(ActionEvent actionEvent) {
        cargarPanel("/listaCitas.fxml");
    }

    private Parent cargarPanel(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent node = loader.load();


            AbstractControlador controlador = loader.getController();
            controlador.inicializarClinica(clinica);


            panelPrincipal.getChildren().setAll(node);

            return node;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }






}