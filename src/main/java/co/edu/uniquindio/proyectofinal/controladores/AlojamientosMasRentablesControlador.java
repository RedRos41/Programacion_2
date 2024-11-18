package co.edu.uniquindio.proyectofinal.controladores;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonType;

import java.util.List;

public class AlojamientosMasRentablesControlador {

    @FXML
    private PieChart graficoRentabilidad;

    private final ControladorPrincipal controladorPrincipal;

    public AlojamientosMasRentablesControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    public void initialize() {
        cargarAlojamientosRentables();
    }

    private void cargarAlojamientosRentables() {
        try {
            // Obtener los datos rentables
            List<PieChart.Data> datosRentables = controladorPrincipal.obtenerAlojamientosRentables();

            // Crear el gráfico de pie
            graficoRentabilidad.setData(FXCollections.observableArrayList(datosRentables));
            graficoRentabilidad.setTitle("Alojamientos Más Rentables");

            // Aplicar colores personalizados asegurando que los nodos se actualicen después de añadirlos
            graficoRentabilidad.getData().forEach(data -> {
                data.getNode().setStyle(""); // Resetea el estilo previo
                data.nodeProperty().addListener((observable, oldNode, newNode) -> {
                    if (newNode != null) {
                        switch (data.getName()) {
                            case "Casa" -> newNode.setStyle("-fx-pie-color: #77dd77;"); // Verde pastel
                            case "Apartamento" -> newNode.setStyle("-fx-pie-color: #aec6cf;"); // Azul pastel
                            case "Hotel" -> newNode.setStyle("-fx-pie-color: #d5a6bd;"); // Morado pastel
                        }
                    }
                });
            });
        } catch (Exception e) {
            mostrarAlerta("Error al cargar los alojamientos más rentables: " + e.getMessage(), "Error", Alert.AlertType.ERROR);
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
