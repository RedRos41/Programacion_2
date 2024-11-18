package co.edu.uniquindio.proyectofinal.controladores;

import co.edu.uniquindio.proyectofinal.modelos.Alojamiento;
import co.edu.uniquindio.proyectofinal.modelos.Apartamento;
import co.edu.uniquindio.proyectofinal.modelos.Casa;
import co.edu.uniquindio.proyectofinal.modelos.Hotel;
import co.edu.uniquindio.proyectofinal.modelos.enums.CiudadAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.TipoAlojamiento;
import co.edu.uniquindio.proyectofinal.observador.Observador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class GestionAlojamientosControlador implements Observador {

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
        controladorPrincipal.registrarVentanaObservable(this);
    }

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreAlojamiento"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoAlojamiento"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudadAlojamiento"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioPorNocheAlojamiento"));

        notificar();
    }

    @Override
    public void notificar() {
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
        ChoiceDialog<TipoAlojamiento> dialog = new ChoiceDialog<>(null, TipoAlojamiento.values());
        dialog.setTitle("Seleccionar Tipo de Alojamiento");
        dialog.setHeaderText("Seleccione el tipo de alojamiento a registrar:");
        dialog.setContentText("Tipo:");

        dialog.showAndWait().ifPresent(tipoAlojamiento -> {
            switch (tipoAlojamiento) {
                case CASA -> controladorPrincipal.navegarVentanaObservable(
                        "/RegistrarCasa.fxml", "Registrar Casa", this);
                case APARTAMENTO -> controladorPrincipal.navegarVentanaObservable(
                        "/RegistrarApartamento.fxml", "Registrar Apartamento", this);
                case HOTEL -> controladorPrincipal.navegarVentanaObservable(
                        "/RegistrarHotel.fxml", "Registrar Hotel", this);
            }
        });
    }

    @FXML
    private void abrirFormularioEdicion(ActionEvent event) {
        Alojamiento alojamientoSeleccionado = tablaAlojamientos.getSelectionModel().getSelectedItem();
        if (alojamientoSeleccionado != null) {
            switch (alojamientoSeleccionado.getTipoAlojamiento()) {
                case CASA -> controladorPrincipal.navegarVentanaObservableConAlojamiento(
                        "/EditarCasa.fxml", "Editar Casa", alojamientoSeleccionado, this);
                case APARTAMENTO -> controladorPrincipal.navegarVentanaObservableConAlojamiento(
                        "/EditarApartamento.fxml", "Editar Apartamento", alojamientoSeleccionado, this);
                case HOTEL -> controladorPrincipal.navegarVentanaObservableConAlojamiento(
                        "/EditarHotel.fxml", "Editar Hotel", alojamientoSeleccionado, this);
                default -> controladorPrincipal.mostrarAlerta("Tipo de alojamiento no soportado para edición.", "Error", Alert.AlertType.ERROR);
            }
        } else {
            controladorPrincipal.mostrarAlerta("Por favor, seleccione un alojamiento para editar.", "Advertencia", Alert.AlertType.WARNING);
        }
    }



    @FXML
    private void eliminarAlojamiento(ActionEvent event) {
        Alojamiento alojamientoSeleccionado = tablaAlojamientos.getSelectionModel().getSelectedItem();
        if (alojamientoSeleccionado != null) {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar Eliminación");
            confirmacion.setHeaderText("¿Está seguro de que desea eliminar este alojamiento?");
            confirmacion.setContentText("Nombre: " + alojamientoSeleccionado.getNombreAlojamiento());

            confirmacion.showAndWait().ifPresent(respuesta -> {
                if (respuesta == ButtonType.OK) {
                    try {
                        controladorPrincipal.eliminarAlojamiento(alojamientoSeleccionado.getDireccionAlojamiento());
                        controladorPrincipal.mostrarAlerta("Alojamiento eliminado correctamente.", "Éxito", Alert.AlertType.INFORMATION);
                        cargarAlojamientos();
                    } catch (Exception e) {
                        controladorPrincipal.mostrarAlerta("Error al eliminar alojamiento: " + e.getMessage(), "Error", Alert.AlertType.ERROR);
                    }
                }
            });
        } else {
            controladorPrincipal.mostrarAlerta("Por favor, seleccione un alojamiento para eliminar.", "Advertencia", Alert.AlertType.WARNING);
        }
    }
}
