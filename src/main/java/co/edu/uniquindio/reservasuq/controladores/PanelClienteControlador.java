package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.modelo.enums.DiaSemana;
import co.edu.uniquindio.reservasuq.modelo.Horario;
import co.edu.uniquindio.reservasuq.modelo.Instalacion;
import co.edu.uniquindio.reservasuq.modelo.Reserva;
import co.edu.uniquindio.reservasuq.modelo.Sesion;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PanelClienteControlador {

    @FXML
    private ComboBox<Instalacion> comboInstalacion;
    @FXML
    private DatePicker datePickerFecha;
    @FXML
    private ComboBox<LocalTime> comboHorario;
    @FXML
    private Button btnReservar;
    @FXML
    private TableView<Reserva> tablaReservas;
    @FXML
    private TableColumn<Reserva, String> colInstalacion;
    @FXML
    private TableColumn<Reserva, String> colFechaHora;
    @FXML
    private TableColumn<Reserva, Float> colCosto;

    private final ServiciosReservasUQ controladorPrincipal;
    private final Sesion sesion;

    public PanelClienteControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
        this.sesion = Sesion.getInstancia();
    }

    @FXML
    public void initialize() {
        cargarInstalaciones();
        configurarTablaReservas();
        cargarReservas();
    }

    private void cargarInstalaciones() {
        List<Instalacion> instalaciones = controladorPrincipal.listarInstalaciones();
        comboInstalacion.setItems(FXCollections.observableArrayList(instalaciones));
    }

    @FXML
    public void actualizarHorarios() {
        Instalacion instalacionSeleccionada = comboInstalacion.getValue();
        LocalDate fechaSeleccionada = datePickerFecha.getValue();

        if (instalacionSeleccionada != null && fechaSeleccionada != null) {
            DiaSemana diaSemana = DiaSemana.fromLocalDate(fechaSeleccionada);
            List<Horario> horariosDisponibles = instalacionSeleccionada.obtenerHorariosPorDia(diaSemana);

            List<LocalTime> bloquesHorario = new ArrayList<>();
            for (Horario horario : horariosDisponibles) {
                LocalTime horaInicio = horario.getHoraInicio();
                LocalTime horaFin = horario.getHoraFin();

                while (horaInicio.isBefore(horaFin)) {
                    bloquesHorario.add(horaInicio);
                    horaInicio = horaInicio.plusMinutes(30); // Intervalos de media hora
                }
            }

            comboHorario.setItems(FXCollections.observableArrayList(bloquesHorario));
        }
    }


    @FXML
    public void reservarInstalacion() {
        try {
            Instalacion instalacion = comboInstalacion.getValue();
            LocalDate fecha = datePickerFecha.getValue();
            LocalTime hora = comboHorario.getValue();

            if (instalacion == null || fecha == null || hora == null) {
                mostrarAlerta("Seleccione todos los campos para reservar", "Error", Alert.AlertType.ERROR);
                return;
            }

            if (!fecha.isAfter(LocalDate.now().plusDays(1))) {
                mostrarAlerta("La reserva debe hacerse con al menos 2 días de anticipación", "Error", Alert.AlertType.ERROR);
                return;
            }

            controladorPrincipal.crearReserva(instalacion.getId(), sesion.getPersona().getCedula(), fecha, hora.toString());
            mostrarAlerta("Reserva realizada con éxito", "Éxito", Alert.AlertType.INFORMATION);
            cargarReservas();

        } catch (Exception e) {
            mostrarAlerta(e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
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

    private void mostrarAlerta(String mensaje, String titulo, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
