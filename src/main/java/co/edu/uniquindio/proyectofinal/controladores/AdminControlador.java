package co.edu.uniquindio.proyectofinal.controladores;

import co.edu.uniquindio.proyectofinal.modelos.Alojamiento;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;

public class AdminControlador {

    @FXML
    private VBox panelGestionAlojamientos, panelAlojamientosPopulares, panelGraficaRentabilidad, panelGestionAdmin, panelGraficasReservas;

    @FXML
    private TableView<Alojamiento> tablaAlojamientos, tablaAlojamientosPopulares;

    @FXML
    private TableColumn<Alojamiento, Integer> columnaIdAlojamiento, columnaReservasPopular;

    @FXML
    private TableColumn<Alojamiento, String> columnaNombreAlojamiento, columnaCiudadAlojamiento, columnaNombrePopular, columnaCiudadPopular;

    @FXML
    private TableColumn<Alojamiento, Double> columnaPrecioAlojamiento;

    @FXML
    private PieChart graficaRentabilidad;

    @FXML
    private BarChart<String, Number> barChartReservas;

    @FXML
    private CategoryAxis xAxisReservas;

    @FXML
    private NumberAxis yAxisReservas;

    @FXML
    private TextField txtEmail, txtCodigo;

    @FXML
    private PasswordField txtNuevaContraseña;

    private final ControladorPrincipal controladorPrincipal;

    public AdminControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    public void initialize() {
        // Inicialización de las tablas y gráficos si es necesario
        System.out.println("Controlador inicializado correctamente.");
    }

    @FXML
    public void mostrarGestionAlojamientos() {
        mostrarPanel(panelGestionAlojamientos);
    }

    @FXML
    public void mostrarAlojamientosPopulares() {
        mostrarPanel(panelAlojamientosPopulares);
    }

    @FXML
    public void mostrarGraficaRentabilidad() {
        mostrarPanel(panelGraficaRentabilidad);
    }

    @FXML
    public void mostrarGestionAdmin() {
        mostrarPanel(panelGestionAdmin);
    }

    @FXML
    public void mostrarGraficasReservas() {
        mostrarPanel(panelGraficasReservas);
    }

    private void mostrarPanel(VBox panel) {
        // Hacer visibles solo el panel seleccionado
        panelGestionAlojamientos.setVisible(false);
        panelAlojamientosPopulares.setVisible(false);
        panelGraficaRentabilidad.setVisible(false);
        panelGestionAdmin.setVisible(false);
        panelGraficasReservas.setVisible(false);

        panel.setVisible(true);
    }

    @FXML
    public void cambiarContraseña() {
        // Lógica para cambiar la contraseña con verificación del código enviado
        System.out.println("Cambio de contraseña iniciado.");
    }

    // Método para abrir la ventana de creación de alojamiento
    @FXML
    public void abrirCrearAlojamiento() {
        System.out.println("Abrir ventana para crear alojamiento.");
        // Aquí agregas la lógica que necesitas para crear el alojamiento
    }

    // Método para abrir la ventana de edición de alojamiento
    @FXML
    public void abrirEditarAlojamiento() {
        System.out.println("Abrir ventana para editar alojamiento.");
        // Lógica de edición aquí
    }

    // Método para abrir la ventana de eliminación de alojamiento
    @FXML
    public void abrirEliminarAlojamiento() {
        System.out.println("Abrir ventana para eliminar alojamiento.");
        // Lógica de eliminación aquí
    }
}
