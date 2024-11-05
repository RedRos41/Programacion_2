package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.modelo.Instalacion;
import co.edu.uniquindio.reservasuq.modelo.Reserva;
import co.edu.uniquindio.reservasuq.observador.VentanaObservable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.List;

public class EditarInstalacionControlador extends VentanaObservable {

    @FXML
    private TextField txtNombreInstalacion;

    @FXML
    private TextField txtAforo;

    @FXML
    private TextField txtCosto;

    private Instalacion instalacion;
    private PanelAdminControlador panelAdminControlador;
    private final ControladorPrincipal controladorPrincipal;


    public EditarInstalacionControlador(){
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }
    public void setInstalacion(Instalacion instalacion) {
        this.instalacion = instalacion;
        cargarDatosInstalacion();
    }

    public void setPanelAdminControlador(PanelAdminControlador panelAdminControlador) {
        this.panelAdminControlador = panelAdminControlador;
    }

    @FXML
    public void initialize() {
    }

    private void cargarDatosInstalacion() {
        txtNombreInstalacion.setText(instalacion.getNombre());
        txtAforo.setText(String.valueOf(instalacion.getAforo()));
        txtCosto.setText(String.valueOf(instalacion.getCosto()));
    }

    @FXML
    public void actualizarInstalacion() {
        try {
            String nuevoNombre = txtNombreInstalacion.getText();
            int nuevoAforo = Integer.parseInt(txtAforo.getText());
            float nuevoCosto = Float.parseFloat(txtCosto.getText());

            instalacion.setNombre(nuevoNombre);
            instalacion.setAforo(nuevoAforo);
            instalacion.setCosto(nuevoCosto);

            ControladorPrincipal.getInstancia().actualizarReservasConInstalacionEditada(instalacion);
            notificarObservador();

            mostrarAlerta("Instalación actualizada con éxito.", "Éxito", Alert.AlertType.INFORMATION);
            controladorPrincipal.cerrarVentana(txtNombreInstalacion);

        } catch (NumberFormatException e) {
            mostrarAlerta("Por favor ingrese valores numéricos válidos.", "Error", Alert.AlertType.ERROR);
        }
        controladorPrincipal.cerrarVentana(txtNombreInstalacion);
    }


    private void mostrarAlerta(String mensaje, String titulo, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
