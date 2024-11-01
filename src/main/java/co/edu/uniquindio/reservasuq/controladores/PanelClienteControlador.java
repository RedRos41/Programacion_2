package co.edu.uniquindio.reservasuq.controladores;


import co.edu.uniquindio.reservasuq.modelo.Persona;
import co.edu.uniquindio.reservasuq.modelo.Reserva;
import co.edu.uniquindio.reservasuq.modelo.Sesion;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

public class PanelClienteControlador {

    @FXML
    private TableView<Reserva> tablaReservas;

    @FXML
    private TableColumn<Reserva, String> colInstalacion;

    @FXML
    private TableColumn<Reserva, String> colFecha;

    @FXML
    private TableColumn<Reserva, String> colHora;

    @FXML
    private TableColumn<Reserva, String> colCosto;

    private final ControladorPrincipal controladorPrincipal;
    private final Sesion sesion;

    public PanelClienteControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
        this.sesion = Sesion.getInstancia();
    }

    @FXML
    public void initialize() {
        Persona usuario = sesion.getPersona();

        colInstalacion.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getInstalacion().getNombre()));
        colFecha.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFechaHoraReserva().toLocalDate().toString()));
        colHora.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFechaHoraReserva().toLocalTime().toString()));
        colCosto.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getCosto())));

        cargarReservasUsuario(usuario.getCedula());
    }

    private void cargarReservasUsuario(String cedula) {
        try {
            List<Reserva> reservas = controladorPrincipal.listarReservasPorPersona(cedula);
            tablaReservas.getItems().setAll(reservas);
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta("Error al cargar las reservas", "Error", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void reservarInstalacion() {
        controladorPrincipal.navegarVentana("/reserva.fxml", "Reservar Instalación");
    }

    @FXML
    public void cerrarSesion() {
        sesion.cerrarSesion();
        controladorPrincipal.cerrarVentana(tablaReservas);
        controladorPrincipal.navegarVentana("/login.fxml", "Iniciar Sesión");
    }
}