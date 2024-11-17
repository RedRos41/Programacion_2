package co.edu.uniquindio.proyectofinal.controladores;

import co.edu.uniquindio.proyectofinal.modelos.Alojamiento;
import co.edu.uniquindio.proyectofinal.modelos.Oferta;
import co.edu.uniquindio.proyectofinal.modelos.enums.CiudadAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.TipoAlojamiento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class InicioControlador {

    @FXML
    private TableView<Alojamiento> tablaAlojamientosRecomendados;

    @FXML
    private TableColumn<Alojamiento, String> colNombreAlojamiento;

    @FXML
    private TableColumn<Alojamiento, TipoAlojamiento> colTipoAlojamiento;

    @FXML
    private TableColumn<Alojamiento, CiudadAlojamiento> colCiudadAlojamiento;

    @FXML
    private TableColumn<Alojamiento, Double> colPrecioAlojamiento;

    @FXML
    private TableView<Alojamiento> tablaResultadosBusqueda;

    @FXML
    private TableColumn<Alojamiento, String> colResultadoNombre;

    @FXML
    private TableColumn<Alojamiento, TipoAlojamiento> colResultadoTipo;

    @FXML
    private TableColumn<Alojamiento, CiudadAlojamiento> colResultadoCiudad;

    @FXML
    private TableColumn<Alojamiento, Double> colResultadoPrecio;

    @FXML
    private TableView<Oferta> tablaOfertas;

    @FXML
    private TableColumn<Oferta, String> colDescripcionOferta;

    @FXML
    private TableColumn<Oferta, Float> colDescuentoOferta;

    @FXML
    private TextField txtBusqueda;

    @FXML
    private ComboBox<TipoAlojamiento> comboTipoAlojamiento;

    @FXML
    private ComboBox<CiudadAlojamiento> comboCiudadAlojamiento;

    @FXML
    private TextField txtPrecioMin;

    @FXML
    private TextField txtPrecioMax;

    private final ControladorPrincipal controladorPrincipal;

    public InicioControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    public void initialize() {
        // Configurar columnas de "Alojamientos Recomendados"
        colNombreAlojamiento.setCellValueFactory(new PropertyValueFactory<>("nombreAlojamiento"));
        colTipoAlojamiento.setCellValueFactory(new PropertyValueFactory<>("tipoAlojamiento"));
        colCiudadAlojamiento.setCellValueFactory(new PropertyValueFactory<>("ciudadAlojamiento"));
        colPrecioAlojamiento.setCellValueFactory(new PropertyValueFactory<>("precioPorNocheAlojamiento"));

        // Configurar columnas de "Resultados de Búsqueda"
        colResultadoNombre.setCellValueFactory(new PropertyValueFactory<>("nombreAlojamiento"));
        colResultadoTipo.setCellValueFactory(new PropertyValueFactory<>("tipoAlojamiento"));
        colResultadoCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudadAlojamiento"));
        colResultadoPrecio.setCellValueFactory(new PropertyValueFactory<>("precioPorNocheAlojamiento"));

        // Configurar columnas de "Ofertas Activas"
        colDescripcionOferta.setCellValueFactory(new PropertyValueFactory<>("descripcionOferta"));
        colDescuentoOferta.setCellValueFactory(new PropertyValueFactory<>("descuentoOferta"));

        // Poblar combo boxes
        comboTipoAlojamiento.getItems().addAll(TipoAlojamiento.values());
        comboCiudadAlojamiento.getItems().addAll(CiudadAlojamiento.values());

        // Cargar datos iniciales
        cargarAlojamientosRecomendados();
        cargarOfertasActivas();
    }

    private void cargarAlojamientosRecomendados() {
        List<Alojamiento> alojamientosRecomendados = controladorPrincipal.obtenerAlojamientosAleatorios();
        ObservableList<Alojamiento> listaRecomendados = FXCollections.observableArrayList(alojamientosRecomendados);
        tablaAlojamientosRecomendados.setItems(listaRecomendados);
    }

    private void cargarOfertasActivas() {
        List<Oferta> ofertasActivas = controladorPrincipal.obtenerOfertasActivas();
        ObservableList<Oferta> listaOfertas = FXCollections.observableArrayList(ofertasActivas);
        tablaOfertas.setItems(listaOfertas);
    }

    @FXML
    public void buscarAlojamientos(ActionEvent event) {
        try {
            String nombre = txtBusqueda.getText();
            TipoAlojamiento tipo = comboTipoAlojamiento.getValue();
            CiudadAlojamiento ciudad = comboCiudadAlojamiento.getValue();
            double precioMin = txtPrecioMin.getText().isEmpty() ? 0 : Double.parseDouble(txtPrecioMin.getText());
            double precioMax = txtPrecioMax.getText().isEmpty() ? 0 : Double.parseDouble(txtPrecioMax.getText());

            List<Alojamiento> resultados = controladorPrincipal.filtrarAlojamientos(nombre, tipo, ciudad, precioMin, precioMax);

            mostrarResultadosBusqueda(resultados);
        } catch (NumberFormatException e) {
            controladorPrincipal.mostrarAlerta("Por favor, ingrese valores válidos para los precios.", "Error de formato", Alert.AlertType.WARNING);
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta("Error al buscar alojamientos: " + e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
    }

    private void mostrarResultadosBusqueda(List<Alojamiento> resultados) {
        ObservableList<Alojamiento> listaResultados = FXCollections.observableArrayList(resultados);
        tablaResultadosBusqueda.setItems(listaResultados);
    }

    @FXML
    public void irIniciarSesion(ActionEvent actionEvent) {
        controladorPrincipal.navegarVentana("/IniciarSesion.fxml", "Iniciar Sesión");
    }

    @FXML
    public void irRegistroCliente(ActionEvent actionEvent) {
        controladorPrincipal.navegarVentana("/RegistrarUsuario.fxml", "Registro Persona");
    }
}
