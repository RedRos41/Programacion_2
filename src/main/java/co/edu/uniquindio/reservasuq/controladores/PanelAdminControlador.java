package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.modelo.Instalacion;
import co.edu.uniquindio.reservasuq.modelo.Reserva;
import co.edu.uniquindio.reservasuq.observador.Observador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class PanelAdminControlador implements Observador {

    @FXML
    private TableView<Instalacion> tablaInstalaciones;
    @FXML
    private TableColumn<Instalacion, String> colInstalacion;
    @FXML
    private TableColumn<Instalacion, Integer> colAforo;
    @FXML
    private TableColumn<Instalacion, Float> colCosto;

    @FXML
    private TableView<Reserva> tablaReservas;
    @FXML
    private TableColumn<Reserva, String> colPersonaReserva;
    @FXML
    private TableColumn<Reserva, String> colInstalacionReserva;
    @FXML
    private TableColumn<Reserva, String> colFechaHoraReserva;
    @FXML
    private TableColumn<Reserva, Float> colCostoReserva;

    private final ControladorPrincipal controladorPrincipal;

    public PanelAdminControlador() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    public void initialize() {
        configurarColumnas();
        cargarDatosInstalaciones();
        cargarDatosReservas();
    }

    private void configurarColumnas() {
        colInstalacion.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colAforo.setCellValueFactory(new PropertyValueFactory<>("aforo"));
        colCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));

        colPersonaReserva.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPersona().getNombre()));
        colInstalacionReserva.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getInstalacion().getNombre()));
        colFechaHoraReserva.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getFechaHoraReserva().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
        colCostoReserva.setCellValueFactory(new PropertyValueFactory<>("costo"));
    }

    private void cargarDatosInstalaciones() {
        ObservableList<Instalacion> instalaciones = tablaInstalaciones.getItems();
        instalaciones.setAll(controladorPrincipal.listarInstalaciones());
    }

    private void cargarDatosReservas() {
        List<Reserva> reservas = controladorPrincipal.listarTodasReservas();
        tablaReservas.setItems(FXCollections.observableArrayList(reservas));
    }

    @FXML
    public void mostrarGestionInstalaciones() {
        controladorPrincipal.navegarVentanaObservable("/gestionInstalaciones.fxml", "Gestionar Instalaciones", this);
    }

    @FXML
    public void cancelarReservaSeleccionada() {
        Reserva seleccionada = tablaReservas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            controladorPrincipal.mostrarAlerta("Seleccione una reserva para cancelar.", "Advertencia", Alert.AlertType.WARNING);
            return;
        }

        try {
            controladorPrincipal.cancelarReserva(seleccionada.getIdReserva());
            controladorPrincipal.mostrarAlerta("Reserva cancelada con éxito.", "Éxito", Alert.AlertType.INFORMATION);
            cargarDatosReservas();
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta(e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
    }


    @FXML
    public void mostrarVentanaCrearReserva() {
        controladorPrincipal.navegarVentanaObservable("/crearReserva.fxml", "Crear Reserva", this);
    }

    @FXML
    public void mostrarVentanaEditarInstalacion() {
        Instalacion seleccionada = tablaInstalaciones.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            controladorPrincipal.mostrarAlerta("Seleccione una instalación para editar.", "Advertencia", Alert.AlertType.WARNING);
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/editarInstalacion.fxml"));
            Parent root = loader.load();

            EditarInstalacionControlador controlador = loader.getController();
            controlador.setInstalacion(seleccionada);
            controlador.setObservador(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("Editar Instalación");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notificar() {
        javafx.application.Platform.runLater(() -> {
            cargarDatosInstalaciones();
            cargarDatosReservas();
        });
    }
}
