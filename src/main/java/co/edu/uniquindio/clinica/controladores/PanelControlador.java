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

            // Obtener el controlador del archivo FXML cargado
            AbstractControlador controlador = loader.getController();

            // Asegurarse de que la instancia de Clinica se inicializa en el controlador
            if (controlador != null) {
                controlador.inicializarClinica(clinica);  // Pasar la instancia de Clinica

                // Si el controlador es ListaPacientesControlador, inicializar la lista de pacientes
                if (controlador instanceof ListaPacientesControlador) {
                    ((ListaPacientesControlador) controlador).inicializarListaPacientes();
                }
            }

            panelPrincipal.getChildren().setAll(node);

            return node;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }





}

