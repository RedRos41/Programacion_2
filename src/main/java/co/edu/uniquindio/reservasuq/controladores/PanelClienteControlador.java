package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.modelo.Reserva;
import co.edu.uniquindio.reservasuq.modelo.Sesion;
import co.edu.uniquindio.reservasuq.modelo.enums.DiaSemana;
import co.edu.uniquindio.reservasuq.observador.Observador;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PanelClienteControlador implements Observador {

    @FXML
    private TableView<Reserva> tablaReservas;
    @FXML
    private TableColumn<Reserva, String> colInstalacion;
    @FXML
    private TableColumn<Reserva, String> colFechaHora;
    @FXML
    private TableColumn<Reserva, Float> colCosto;

    private final ControladorPrincipal controladorPrincipal;
    private final Sesion sesion;

    public PanelClienteControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
        this.sesion = Sesion.getInstancia();
    }

    @FXML
    public void initialize() {
        configurarTablaReservas();
        cargarReservas();
    }

    @FXML
    public void mostrarVentanaCrearReserva() {
        controladorPrincipal.navegarVentanaObservable("/crearReserva.fxml", "Crear Reserva", this);
    }

    @Override
    public void notificar() {
        cargarReservas();
    }

    private void cargarReservas() {
        List<Reserva> reservas = controladorPrincipal.listarReservasPorPersona(sesion.getPersona().getCedula());
        tablaReservas.setItems(FXCollections.observableArrayList(reservas));
    }

    private void configurarTablaReservas() {
        colInstalacion.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getInstalacion().getNombre()));
        colFechaHora.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFechaHoraReserva().toString()));
        colCosto.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getCosto()));
    }
}
