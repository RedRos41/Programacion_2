package co.edu.uniquindio.proyectofinal.controladores;

import co.edu.uniquindio.proyectofinal.modelos.Alojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.CiudadAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.TipoAlojamiento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class GestionAlojamientosControlador {

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
    private Label lblMensaje;

    private final ControladorPrincipal controladorPrincipal;

    public GestionAlojamientosControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
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
            lblMensaje.setText("Error al cargar alojamientos: " + e.getMessage());
        }
    }

    @FXML
    private void abrirFormularioRegistro(ActionEvent event) {
        controladorPrincipal.navegarVentana("/RegistrarAlojamiento.fxml", "Registrar Alojamiento");
    }

    @FXML
    private void abrirFormularioEdicion(ActionEvent event) {
        Alojamiento alojamientoSeleccionado = tablaAlojamientos.getSelectionModel().getSelectedItem();
        if (alojamientoSeleccionado != null) {
            controladorPrincipal.navegarVentanaObservableConAlojamiento(
                    "/fxml/EditarAlojamiento.fxml",
                    "Editar Alojamiento",
                    alojamientoSeleccionado,
                    null
            );
        } else {
            lblMensaje.setText("Seleccione un alojamiento para editar.");
        }
    }

    @FXML
    private void eliminarAlojamiento(ActionEvent event) {
        Alojamiento alojamientoSeleccionado = tablaAlojamientos.getSelectionModel().getSelectedItem();
        if (alojamientoSeleccionado != null) {
            try {
                controladorPrincipal.eliminarAlojamiento(alojamientoSeleccionado.getDireccionAlojamiento());
                lblMensaje.setText("Alojamiento eliminado correctamente.");
                cargarAlojamientos();
            } catch (Exception e) {
                lblMensaje.setText("Error al eliminar alojamiento: " + e.getMessage());
            }
        } else {
            lblMensaje.setText("Seleccione un alojamiento para eliminar.");
        }
    }
}
