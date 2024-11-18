package co.edu.uniquindio.proyectofinal.controladores;

import co.edu.uniquindio.proyectofinal.modelos.Alojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.CiudadAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.TipoAlojamiento;
import co.edu.uniquindio.proyectofinal.observador.Observador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class PanelAdminControlador implements Observador {

    @FXML
    private TableView<Alojamiento> tablaAlojamientos;

    @FXML
    private TableColumn<Alojamiento, String> colNombre;

    @FXML
    private TableColumn<Alojamiento, TipoAlojamiento> colTipo;

    @FXML
    private TableColumn<Alojamiento, CiudadAlojamiento> colCiudad;

    @FXML
    private TableColumn<Alojamiento, Double> colPrecio;

    @FXML
    private Label lblInfoAdmin;

    @FXML
    private Button btnGestionarAlojamientos;

    @FXML
    private Button btnAlojamientosPopulares;

    @FXML
    private Button btnAlojamientosRentables;

    @FXML
    private void abrirEstadisticas(ActionEvent event) {
        controladorPrincipal.abrirVentanaEstadisticas();
    }





    @FXML
    private Button btnVolver;

    private final ControladorPrincipal controladorPrincipal;

    public PanelAdminControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
        controladorPrincipal.registrarVentanaObservable(this);
    }

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreAlojamiento"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoAlojamiento"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudadAlojamiento"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioPorNocheAlojamiento"));

        cargarAlojamientos();
    }

    private void cargarAlojamientos() {
        try {
            List<Alojamiento> alojamientos = controladorPrincipal.getAlojamientos();
            ObservableList<Alojamiento> listaAlojamientos = FXCollections.observableArrayList(alojamientos);
            tablaAlojamientos.setItems(listaAlojamientos);
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta("Error al cargar alojamientos: " + e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void gestionarAlojamientos(ActionEvent event) {
        controladorPrincipal.navegarVentana("/gestionarAlojamientos.fxml", "Gestión de Alojamientos");
    }

    @FXML
    private void mostrarAlojamientosPopulares(ActionEvent event) {
        try {
            List<Alojamiento> alojamientosPopulares = controladorPrincipal.obtenerAlojamientosPopulares();
            ObservableList<Alojamiento> listaPopulares = FXCollections.observableArrayList(alojamientosPopulares);
            tablaAlojamientos.setItems(listaPopulares);
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta("Error al cargar alojamientos populares: " + e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void abrirAlojamientosRentables(ActionEvent event) {
        try {
            // Obtener los datos de los alojamientos más rentables desde el ControladorPrincipal
            List<PieChart.Data> datosRentables = controladorPrincipal.obtenerAlojamientosRentables();

            // Crear el gráfico PieChart para mostrar los datos
            PieChart graficoRentabilidad = new PieChart(FXCollections.observableArrayList(datosRentables));
            graficoRentabilidad.setTitle("Alojamientos Más Rentables");

            // Personalizar los colores del gráfico y sincronizar con la leyenda
            datosRentables.forEach(data -> {
                if (data.getName().equals("Casa")) {
                    data.getNode().setStyle("-fx-pie-color: #77dd77;"); // Verde para Casa
                } else if (data.getName().equals("Apartamento")) {
                    data.getNode().setStyle("-fx-pie-color: #ff6961;"); // Rojo para Apartamento
                } else if (data.getName().equals("Hotel")) {
                    data.getNode().setStyle("-fx-pie-color: #cba6f7;"); // Morado para Hotel
                }
            });

            // Crear un nuevo diálogo para mostrar el gráfico
            Dialog<Void> dialogoGrafico = new Dialog<>();
            dialogoGrafico.setTitle("Alojamientos Más Rentables");
            dialogoGrafico.getDialogPane().setContent(graficoRentabilidad);
            dialogoGrafico.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialogoGrafico.showAndWait();
        } catch (Exception e) {
            // Mostrar alerta si hay un error
            controladorPrincipal.mostrarAlerta(
                    "Error al cargar los alojamientos más rentables: " + e.getMessage(),
                    "Error",
                    Alert.AlertType.ERROR
            );
        }
    }

    @FXML
    private void abrirEstadisticas() {
        try {
            // Navegar a la ventana de estadísticas
            ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
            controladorPrincipal.navegarVentana("/estadisticasAlojamientos.fxml", "Estadísticas de Alojamientos");
        } catch (Exception e) {
            mostrarAlerta("No se pudo abrir la ventana de estadísticas: " + e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String mensaje, String titulo, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }



    @FXML
    private void volver(ActionEvent event) {
        controladorPrincipal.cerrarVentana(btnVolver);
    }

    @Override
    public void notificar() {
        cargarAlojamientos();
    }
}
