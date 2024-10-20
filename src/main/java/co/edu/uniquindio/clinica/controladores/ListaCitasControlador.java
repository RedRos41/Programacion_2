package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Cita;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListaCitasControlador extends AbstractControlador {

    @FXML
    private TableView<Cita> tablaCitas;

    @FXML
    private TableColumn<Cita, String> colPaciente;

    @FXML
    private TableColumn<Cita, String> colServicio;

    @FXML
    private TableColumn<Cita, String> colFecha;

    @FXML
    private Button eliminarCitaButton;

    private ObservableList<Cita> citasList;

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            if (getClinica() != null) {
                citasList = FXCollections.observableArrayList(getClinica().listarCitas());

                // Configurar las columnas de la tabla
                colPaciente.setCellValueFactory(new PropertyValueFactory<>("paciente"));
                colServicio.setCellValueFactory(new PropertyValueFactory<>("servicio"));
                colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

                // Asignar los datos a la tabla
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
                citasList.remove(citaSeleccionada);  // Eliminar de la tabla
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
