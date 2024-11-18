package co.edu.uniquindio.proyectofinal.controladores;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;

import java.util.List;

public class EstadisticasAlojamientosControlador {

    @FXML
    private BarChart<String, Number> graficoEstadisticas;

    private final ControladorPrincipal controladorPrincipal;

    public EstadisticasAlojamientosControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    public void initialize() {
        cargarEstadisticas();
    }

    private void cargarEstadisticas() {
        try {
            // Obtener los datos de estadísticas
            List<PieChart.Data> datosEstadisticas = controladorPrincipal.obtenerEstadisticas();

            if (datosEstadisticas == null || datosEstadisticas.isEmpty()) {
                mostrarAlerta("No hay estadísticas disponibles para mostrar.", "Información", Alert.AlertType.INFORMATION);
                return;
            }


            graficoEstadisticas.setData(FXCollections.observableArrayList());
            graficoEstadisticas.setTitle("Estadísticas de Alojamientos");
            graficoEstadisticas.getData().forEach(data -> {
                data.getNode().setStyle(""); // Resetea el estilo previo
                data.nodeProperty().addListener((observable, oldNode, newNode) -> {
                    if (newNode != null) {
                        if (data.getName().contains("(Ocupación)")) {
                            newNode.setStyle("-fx-pie-color: #77dd77;"); // Verde pastel
                        } else if (data.getName().contains("(Ganancias)")) {
                            newNode.setStyle("-fx-pie-color: #ff6961;"); // Rojo pastel
                        }
                    }
                });
            });
        } catch (Exception e) {
            mostrarAlerta("Error al cargar las estadísticas: " + e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
    }



    private void mostrarAlerta(String mensaje, String titulo, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
