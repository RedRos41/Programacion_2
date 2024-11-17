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
            List<Alojamiento> alojamientosPopulares = controladorPrincipal.alojamientosPopulares();
            ObservableList<Alojamiento> listaPopulares = FXCollections.observableArrayList(alojamientosPopulares);
            tablaAlojamientos.setItems(listaPopulares);
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta("Error al cargar alojamientos populares: " + e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
    }

    //@FXML
    //private void mostrarAlojamientosRentables(ActionEvent event) {
        //try {
            //List<PieChart.Data> alojamientosRentables = controladorPrincipal.alojamientosRentables();
            //ObservableList<PieChart.Data> listaRentables = FXCollections.observableArrayList(alojamientosRentables);
            //tablaAlojamientos.setItems(listaRentables);
        //} catch (Exception e) {
            //controladorPrincipal.mostrarAlerta("Error al cargar alojamientos rentables: " + e.getMessage(), "Error", Alert.AlertType.ERROR);
        //}
    //}

    @FXML
    private void volver(ActionEvent event) {
        controladorPrincipal.cerrarVentana(btnVolver);
    }

    @Override
    public void notificar() {
        cargarAlojamientos();
    }
}
