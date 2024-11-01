package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.modelo.Horario;
import co.edu.uniquindio.reservasuq.modelo.Instalacion;
import co.edu.uniquindio.reservasuq.modelo.Reserva;
import co.edu.uniquindio.reservasuq.modelo.Sesion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReservaControlador {

    @FXML
    private ComboBox<Instalacion> comboInstalacion;

    @FXML
    private DatePicker datePickerFecha;

    @FXML
    private ComboBox<String> comboHora;

    private final ControladorPrincipal controladorPrincipal;

    public ReservaControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    public void initialize() {
        cargarInstalaciones();

        datePickerFecha.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (comboInstalacion.getValue() != null && newValue != null) {
                cargarHorariosDisponibles(comboInstalacion.getValue(), newValue);
            }
        });

        comboInstalacion.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && datePickerFecha.getValue() != null) {
                cargarHorariosDisponibles(newValue, datePickerFecha.getValue());
            }
        });
    }

    private void cargarInstalaciones() {
        List<Instalacion> instalaciones = controladorPrincipal.listarInstalaciones();
        comboInstalacion.setItems(FXCollections.observableArrayList(instalaciones));
    }

    private void cargarHorariosDisponibles(Instalacion instalacion, LocalDate fecha) {
        List<Horario> horariosDisponibles = controladorPrincipal.listarHorariosDisponibles(instalacion.getId(), fecha);
        List<String> horas = horariosDisponibles.stream()
                .map(horario -> horario.getHoraInicio().toString() + " - " + horario.getHoraFin().toString())
                .collect(Collectors.toList());
        comboHora.setItems(FXCollections.observableArrayList(horas));
    }

    @FXML
    public void realizarReserva() {
        try {
            Instalacion instalacion = comboInstalacion.getValue();
            LocalDate fecha = datePickerFecha.getValue();
            String horaSeleccionada = comboHora.getValue();

            if (instalacion == null || fecha == null || horaSeleccionada == null) {
                controladorPrincipal.mostrarAlerta("Debe completar todos los campos", "Campos incompletos", Alert.AlertType.WARNING);
                return;
            }

            LocalTime horaInicio = LocalTime.parse(horaSeleccionada.split(" - ")[0]);

            Reserva nuevaReserva = controladorPrincipal.crearReserva(instalacion.getId(),
                    Sesion.getInstancia().getPersona().getCedula(), fecha, horaInicio.toString());

            controladorPrincipal.mostrarAlerta("Reserva creada con éxito", "Éxito", Alert.AlertType.INFORMATION);
            controladorPrincipal.cerrarVentana(comboInstalacion);
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta("Error al crear la reserva: " + e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
    }
}