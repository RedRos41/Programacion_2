package co.edu.uniquindio.proyectofinal.controladores;

import co.edu.uniquindio.proyectofinal.modelos.Reserva;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;

public class AgregarReseñaControlador {

    @FXML
    private Spinner<Integer> spinnerCalificacion;

    @FXML
    private TextArea txtComentario;

    private final ControladorPrincipal controladorPrincipal;
    private Reserva reservaSeleccionada;

    public AgregarReseñaControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    public void initialize() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        spinnerCalificacion.setValueFactory(valueFactory);
    }

    public void setReserva(Reserva reserva) {
        this.reservaSeleccionada = reserva;
    }

    @FXML
    private void guardarReseña(ActionEvent event) {
        try {
            int calificacion = spinnerCalificacion.getValue();
            String comentario = txtComentario.getText();

            if (comentario.isBlank()) {
                throw new Exception("El comentario no puede estar vacío.");
            }

            controladorPrincipal.registrarReseña(
                    reservaSeleccionada.getAlojamientoReserva(),
                    controladorPrincipal.generarCodigoReseña(),
                    comentario,
                    calificacion
            );

            controladorPrincipal.mostrarAlerta("Reseña guardada exitosamente.", "Éxito", Alert.AlertType.INFORMATION);
            controladorPrincipal.cerrarVentana(spinnerCalificacion);

        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta(e.getMessage(), "Error al guardar reseña", Alert.AlertType.ERROR);
        }
    }
}
