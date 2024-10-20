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
        Parent node = cargarPanel("/registroCita.fxml");
        if (node != null) {
            panelPrincipal.getChildren().setAll(node);
        } else {
            System.out.println("Error al cargar el panel de registro de cita.");
        }


    }

    public void mostrarRegistroServicio(ActionEvent actionEvent) {
        Parent node = cargarPanel("/registroServicio.fxml");
        if (node != null) {
            panelPrincipal.getChildren().setAll(node);
        }
    }



    public void mostrarListaCitas(ActionEvent actionEvent) {
        cargarPanel("/listaCitas.fxml");
    }


    private Parent cargarPanel(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent node = loader.load();
            AbstractControlador controlador = loader.getController();

            if (controlador != null) {
                controlador.inicializarClinica(clinica);

                if (controlador instanceof ListaPacientesControlador) {
                    ((ListaPacientesControlador) controlador).inicializarListaPacientes();
                }
            }

            panelPrincipal.getChildren().setAll(node);

            return node;
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo FXML: " + fxmlFile);
            e.printStackTrace();
            return null;
        }
    }

}

