package co.edu.uniquindio.proyectofinal.controladores;

import co.edu.uniquindio.proyectofinal.modelos.*;
import co.edu.uniquindio.proyectofinal.modelos.enums.CiudadAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.TipoAlojamiento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class RealizarReservaControlador {

    @FXML
    private ComboBox<Alojamiento> comboAlojamientos;

    @FXML
    private DatePicker fechaInicioReserva;

    @FXML
    private DatePicker fechaFinReserva;

    @FXML
    private Spinner<Integer> spinnerNumHuespedes;

    private final ControladorPrincipal controladorPrincipal;
    private Alojamiento alojamientoSeleccionado;

    public RealizarReservaControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }
    public void setAlojamientoSeleccionado(Alojamiento alojamiento) {
        this.alojamientoSeleccionado = alojamiento;
        // Aquí puedes inicializar los campos de la interfaz con los datos del alojamiento
    }

    @FXML
    public void initialize() {
        // Configurar el ComboBox de alojamientos
        cargarAlojamientosDisponibles();

        // Configurar el Spinner de número de huéspedes
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        spinnerNumHuespedes.setValueFactory(valueFactory);
    }

    private void cargarAlojamientosDisponibles() {
        try {
            List<Alojamiento> alojamientos = controladorPrincipal.filtrarAlojamientos("", null, null, 0, 0);
            ObservableList<Alojamiento> alojamientoObservableList = FXCollections.observableArrayList(alojamientos);
            comboAlojamientos.setItems(alojamientoObservableList);
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta("Error al cargar alojamientos", "Error", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void confirmarReserva(ActionEvent event) {
        Alojamiento alojamientoSeleccionado = comboAlojamientos.getValue();
        LocalDate fechaInicio = fechaInicioReserva.getValue();
        LocalDate fechaFin = fechaFinReserva.getValue();
        int numHuespedes = spinnerNumHuespedes.getValue();

        if (alojamientoSeleccionado == null || fechaInicio == null || fechaFin == null || fechaInicio.isAfter(fechaFin)) {
            controladorPrincipal.mostrarAlerta("Por favor, complete todos los campos correctamente.", "Advertencia", Alert.AlertType.WARNING);
            return;
        }

        try {
            controladorPrincipal.registrarReserva(
                    Sesion.getInstancia().getUsuario(),
                    controladorPrincipal.generarCodigoReserva(),
                    alojamientoSeleccionado,
                    numHuespedes,
                    fechaInicio.atStartOfDay(),
                    fechaFin.atStartOfDay()
            );
            controladorPrincipal.mostrarAlerta("Reserva confirmada con éxito", "Éxito", Alert.AlertType.INFORMATION);
            controladorPrincipal.cerrarVentana(comboAlojamientos);  // Cierra la ventana actual
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta(e.getMessage(), "Error al realizar la reserva", Alert.AlertType.ERROR);
        }
    }
}
