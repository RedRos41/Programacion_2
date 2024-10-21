package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Cita;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.LocalDateTimeStringConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ListaCitasControlador extends AbstractControlador {

    @FXML
    private TableView<Cita> tablaCitas;

    @FXML
    private TableColumn<Cita, String> colPaciente;

    @FXML
    private TableColumn<Cita, String> colServicio;

    @FXML
    private TableColumn<Cita, String> colFechaHora;

    @FXML
    private TableColumn<Cita, Double> colTotal;

    @FXML
    private TableColumn<Cita, String> colFechaFactura;

    @FXML
    private Button eliminarCitaButton;

    private ObservableList<Cita> citasList;

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            if (getClinica() != null) {
                citasList = FXCollections.observableArrayList(getClinica().listarCitas());

                colPaciente.setCellValueFactory(cita -> new javafx.beans.property.SimpleStringProperty(cita.getValue().getPaciente().getNombre()));
                colServicio.setCellValueFactory(cita -> new javafx.beans.property.SimpleStringProperty(cita.getValue().getServicio().getNombre()));

                colFechaHora.setCellValueFactory(cita -> {
                    LocalDateTimeStringConverter converter = new LocalDateTimeStringConverter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"), null);
                    return new javafx.beans.property.SimpleStringProperty(converter.toString(cita.getValue().getFechaHora()));
                });

                // Total factura
                colTotal.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFactura().getTotal()));

                // Formatear fecha de factura
                colFechaFactura.setCellValueFactory(cellData -> {
                    LocalDateTime fechaFactura = cellData.getValue().getFactura().getFechaFactura();
                    if (fechaFactura != null) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        return new javafx.beans.property.SimpleStringProperty(fechaFactura.format(formatter));
                    } else {
                        return new javafx.beans.property.SimpleStringProperty("No disponible");
                    }
                });

                tablaCitas.setItems(citasList);
            } else {
                System.out.println("Error: No se pudo inicializar la instancia de la clínica.");
            }
        });
    }

    @FXML
    public void eliminarCita() {
        Cita citaSeleccionada = tablaCitas.getSelectionModel().getSelectedItem();

        if (citaSeleccionada == null) {
            mostrarAlerta("Error", "Por favor, seleccione una cita para eliminar.", Alert.AlertType.ERROR);
            return;
        }

        try {
            boolean resultado = getClinica().cancelarCita(citaSeleccionada.getIdCita());
            if (resultado) {
                citasList.remove(citaSeleccionada);
                mostrarAlerta("Éxito", "Cita eliminada correctamente.", Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("Error", "No se pudo eliminar la cita.", Alert.AlertType.ERROR);
            }
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