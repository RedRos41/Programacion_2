package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.modelo.Instalacion;
import co.edu.uniquindio.reservasuq.modelo.Reserva;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class PanelAdminControlador {

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
        List<Instalacion> instalaciones = controladorPrincipal.listarInstalaciones();
        tablaInstalaciones.setItems(FXCollections.observableArrayList(instalaciones));
    }

    private void cargarDatosReservas() {
        List<Reserva> reservas = controladorPrincipal.listarTodasReservas();
        tablaReservas.setItems(FXCollections.observableArrayList(reservas));
    }

    @FXML
    public void mostrarGestionInstalaciones() {
        controladorPrincipal.navegarVentana("/gestionInstalaciones.fxml", "Gestionar Instalaciones");
    }

    @FXML
    public void mostrarReservas() {
        controladorPrincipal.navegarVentana("/listaReservas.fxml", "Lista de Reservas");
    }
}
