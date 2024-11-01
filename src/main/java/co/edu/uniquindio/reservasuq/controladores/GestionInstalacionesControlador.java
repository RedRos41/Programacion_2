package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.modelo.Horario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import co.edu.uniquindio.reservasuq.modelo.Instalacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
import java.util.ArrayList;

public class GestionInstalacionesControlador {

    @FXML
    private TextField txtNombreInstalacion;

    @FXML
    private TextField txtAforo;

    @FXML
    private TextField txtCosto;

    private final ControladorPrincipal controladorPrincipal;

    public GestionInstalacionesControlador() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    public void agregarInstalacion() {
        try {
            String nombre = txtNombreInstalacion.getText();
            int aforo = Integer.parseInt(txtAforo.getText());
            float costo = Float.parseFloat(txtCosto.getText());

            List<Horario> horarios = new ArrayList<>(); // Configurar horarios si es necesario

            controladorPrincipal.crearInstalacion(nombre, aforo, costo, horarios);
            mostrarAlerta("Instalación agregada correctamente", "Éxito", Alert.AlertType.INFORMATION);
            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta("Por favor, ingrese valores numéricos válidos para aforo y costo.", "Error de Entrada", Alert.AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Error al agregar la instalación: " + e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void actualizarInstalacion() {
        try {
            // Obtener los datos y actualizar la instalación seleccionada
            String nombre = txtNombreInstalacion.getText();
            int aforo = Integer.parseInt(txtAforo.getText());
            float costo = Float.parseFloat(txtCosto.getText());

            Instalacion instalacion = controladorPrincipal.buscarInstalacionPorNombre(nombre);
            if (instalacion != null) {
                instalacion.setAforo(aforo);
                instalacion.setCosto(costo);
                mostrarAlerta("Instalación actualizada correctamente", "Éxito", Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("La instalación no existe.", "Error", Alert.AlertType.ERROR);
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Por favor, ingrese valores numéricos válidos para aforo y costo.", "Error de Entrada", Alert.AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Error al actualizar la instalación: " + e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String mensaje, String titulo, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        txtNombreInstalacion.clear();
        txtAforo.clear();
        txtCosto.clear();
    }
}