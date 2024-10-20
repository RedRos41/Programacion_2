package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Servicio;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.UUID;

public class RegistroServicioControlador extends AbstractControlador {

    @FXML
    private TextField nombreServicio;

    @FXML
    private TextField precioServicio;

    @FXML
    public void registrarServicio() {
        try {
            String nombre = nombreServicio.getText();
            String precioText = precioServicio.getText();

            if (nombre.isEmpty() || precioText.isEmpty()) {
                mostrarAlerta("Error", "Por favor, complete todos los campos.", Alert.AlertType.ERROR);
                return;
            }

            double precio = Double.parseDouble(precioText);

            Servicio nuevoServicio = Servicio.builder()
                    .idServicio(UUID.randomUUID().toString())
                    .nombre(nombre)
                    .precio(precio)
                    .build();

            getClinica().registrarServicio(nuevoServicio);
            mostrarAlerta("Éxito", "Servicio registrado correctamente", Alert.AlertType.INFORMATION);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El precio debe ser un valor numérico.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
