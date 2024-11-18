package co.edu.uniquindio.proyectofinal.controladores;

import co.edu.uniquindio.proyectofinal.modelos.Alojamiento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AlojamientosPopularesControlador {

    @FXML
    private TableView<Alojamiento> tablaAlojamientosPopulares;

    @FXML
    private TableColumn<Alojamiento, String> colNombre;

    @FXML
    private TableColumn<Alojamiento, String> colTipo;

    @FXML
    private TableColumn<Alojamiento, String> colCiudad;

    @FXML
    private TableColumn<Alojamiento, Double> colPrecio;

    private final ControladorPrincipal controladorPrincipal;

    public AlojamientosPopularesControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreAlojamiento"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoAlojamiento"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudadAlojamiento"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioPorNocheAlojamiento"));

        cargarAlojamientosPopulares();
    }

    private void cargarAlojamientosPopulares() {
        try {
            List<Alojamiento> alojamientosPopulares = controladorPrincipal.alojamientosPopulares();
            ObservableList<Alojamiento> listaPopulares = FXCollections.observableArrayList(alojamientosPopulares);
            tablaAlojamientosPopulares.setItems(listaPopulares);
        } catch (Exception e) {
            mostrarAlerta("Error al cargar alojamientos populares: " + e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String mensaje, String titulo, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
