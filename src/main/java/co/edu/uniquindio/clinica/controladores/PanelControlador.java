package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Clinica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class PanelControlador {

    @FXML
    private StackPane panelPrincipal;

    private final Clinica clinica;
    private ListaPacientesControlador listaPacientesControlador;  // Controlador compartido para la lista de pacientes

    public PanelControlador() {
        this.clinica = new Clinica();  // Se crea una instancia única de Clinica
        this.listaPacientesControlador = new ListaPacientesControlador(clinica);  // Instancia del controlador de lista de pacientes
    }

    public void mostrarRegistroPaciente(ActionEvent actionEvent) {
        try {
            // Cargar el archivo FXML manualmente
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/clinica/registroPaciente.fxml"));

            // Crear una instancia del controlador
            RegistroPacienteControlador registroPacienteControlador = new RegistroPacienteControlador(clinica, listaPacientesControlador);

            // Establecer el controlador en el FXMLLoader
            loader.setController(registroPacienteControlador);

            // Cargar el nodo del FXML
            Parent node = loader.load();

            // Reemplazar el contenido del panel principal con el nuevo panel
            panelPrincipal.getChildren().setAll(node);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void mostrarListaPacientes(ActionEvent actionEvent) {
        // Cargar el panel de lista de pacientes
        Parent node = cargarPanelConControlador("/co/edu/uniquindio/clinica/listaPacientes.fxml", listaPacientesControlador);
        if (node != null) {
            panelPrincipal.getChildren().setAll(node);
        }
    }

    public void mostrarRegistroCita(ActionEvent actionEvent) {
        // Cargar el panel de registro de citas sin parámetros adicionales
        Parent node = cargarPanel("/views/registroCita.fxml");
        if (node != null) {
            panelPrincipal.getChildren().setAll(node);
        }
    }

    public void mostrarListaCitas(ActionEvent actionEvent) {
        // Cargar el panel de lista de citas sin parámetros adicionales
        Parent node = cargarPanel("/views/listaCitas.fxml");
        if (node != null) {
            panelPrincipal.getChildren().setAll(node);
        }
    }

    /**
     * Método para cargar paneles con controladores que requieren parámetros.
     *
     * @param fxmlFile Ruta del archivo FXML
     * @param controlador Instancia del controlador con parámetros
     * @return El nodo padre del panel cargado
     */
    private Parent cargarPanelConControlador(String fxmlFile, Object controlador) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            loader.setController(controlador);  // Se establece el controlador personalizado
            return loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método para cargar paneles sin necesidad de un controlador con parámetros.
     *
     * @param fxmlFile Ruta del archivo FXML
     * @return El nodo padre del panel cargado
     */
    private Parent cargarPanel(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            return loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
