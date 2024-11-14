package co.edu.uniquindio.proyectofinal.controladores;

import co.edu.uniquindio.proyectofinal.modelos.*;
import co.edu.uniquindio.proyectofinal.modelos.enums.TipoAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.CiudadAlojamiento;
import co.edu.uniquindio.proyectofinal.observador.Observador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class PanelClienteControlador {

    @FXML
    private TableView<Reserva> tablaReservas;

    @FXML
    private TableColumn<Reserva, Integer> colIdReserva;

    @FXML
    private TableColumn<Reserva, String> colAlojamiento;

    @FXML
    private TableColumn<Reserva, String> colFechaInicio;

    @FXML
    private TableColumn<Reserva, String> colFechaFin;

    @FXML
    private TableView<Alojamiento> tablaResultadosBusqueda;

    @FXML
    private TableColumn<Alojamiento, String> colNombreAlojamiento;

    @FXML
    private TableColumn<Alojamiento, TipoAlojamiento> colTipoAlojamiento;

    @FXML
    private TableColumn<Alojamiento, CiudadAlojamiento> colCiudadAlojamiento;

    @FXML
    private TableColumn<Alojamiento, Double> colPrecioAlojamiento;

    @FXML
    private TextField txtBuscarNombre;

    @FXML
    private ComboBox<TipoAlojamiento> comboTipoAlojamiento;

    @FXML
    private ComboBox<CiudadAlojamiento> comboCiudadAlojamiento;

    @FXML
    private TextField txtPrecioMin;

    @FXML
    private TextField txtPrecioMax;

    private final ControladorPrincipal controladorPrincipal;

    public PanelClienteControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    public void initialize() {
        // Inicializar la tabla de reservas
        colIdReserva.setCellValueFactory(new PropertyValueFactory<>("idReserva"));
        colAlojamiento.setCellValueFactory(new PropertyValueFactory<>("alojamientoNombre"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));

        // Inicializar la tabla de resultados de búsqueda de alojamientos
        colNombreAlojamiento.setCellValueFactory(new PropertyValueFactory<>("nombreAlojamiento"));
        colTipoAlojamiento.setCellValueFactory(new PropertyValueFactory<>("tipoAlojamiento"));
        colCiudadAlojamiento.setCellValueFactory(new PropertyValueFactory<>("ciudadAlojamiento"));
        colPrecioAlojamiento.setCellValueFactory(new PropertyValueFactory<>("precioPorNocheAlojamiento"));

        // Poblar ComboBoxes
        comboTipoAlojamiento.getItems().addAll(TipoAlojamiento.values());
        comboCiudadAlojamiento.getItems().addAll(CiudadAlojamiento.values());

        cargarReservasCliente();
    }

    private void cargarReservasCliente() {
        Usuario cliente = Sesion.getInstancia().getUsuario();
        if (cliente != null && cliente instanceof Cliente clienteEspecifico) {
            ObservableList<Reserva> reservas = FXCollections.observableArrayList(clienteEspecifico.getReservas());
            tablaReservas.setItems(reservas);
        }
    }

    @FXML
    private void gestionarCliente(ActionEvent event) {
        controladorPrincipal.navegarVentana("/editarPerfilCliente.fxml", "Gestionar Perfil del Cliente");
    }

    @FXML
    private void realizarReserva(ActionEvent event) {
        controladorPrincipal.navegarVentana("/realizarReserva.fxml", "Realizar Reserva");
    }

    @FXML
    private void abrirBilletera(ActionEvent event) {
        controladorPrincipal.navegarVentana("/billetera.fxml", "Billetera");
    }

    @FXML
    private void buscarAlojamientos(ActionEvent event) {
        try {
            String nombre = txtBuscarNombre.getText();
            TipoAlojamiento tipo = comboTipoAlojamiento.getValue();
            CiudadAlojamiento ciudad = comboCiudadAlojamiento.getValue();
            double precioMin = Double.parseDouble(txtPrecioMin.getText());
            double precioMax = Double.parseDouble(txtPrecioMax.getText());

            List<Alojamiento> resultados = controladorPrincipal.filtrarAlojamientos(nombre, tipo, ciudad, precioMin, precioMax);
            mostrarResultadosBusqueda(resultados);
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta("Error en la búsqueda", "Error", Alert.AlertType.ERROR);
        }
    }

    private void mostrarResultadosBusqueda(List<Alojamiento> alojamientos) {
        ObservableList<Alojamiento> listaResultados = FXCollections.observableArrayList(alojamientos);
        tablaResultadosBusqueda.setItems(listaResultados);
    }

    @FXML
    private void seleccionarAlojamientoParaReserva(ActionEvent event) {
        Alojamiento alojamientoSeleccionado = tablaResultadosBusqueda.getSelectionModel().getSelectedItem();
        if (alojamientoSeleccionado != null) {
            controladorPrincipal.navegarVentanaConAlojamiento("/realizarReserva.fxml", "Realizar Reserva", alojamientoSeleccionado);
        } else {
            controladorPrincipal.mostrarAlerta("Por favor, seleccione un alojamiento para reservar", "Seleccionar Alojamiento", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void cancelarReserva(ActionEvent event) {
        Reserva reservaSeleccionada = tablaReservas.getSelectionModel().getSelectedItem();
        if (reservaSeleccionada != null) {
            try {
                controladorPrincipal.eliminarReserva(Sesion.getInstancia().getUsuario(), reservaSeleccionada.getIdReserva());
                cargarReservasCliente(); // Actualizar la tabla de reservas
                controladorPrincipal.mostrarAlerta("Reserva cancelada exitosamente", "Éxito", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                controladorPrincipal.mostrarAlerta(e.getMessage(), "Error al cancelar reserva", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void agregarReseña(ActionEvent event) {
        Reserva reservaSeleccionada = tablaReservas.getSelectionModel().getSelectedItem();
        if (reservaSeleccionada != null) {
            controladorPrincipal.navegarVentanaObservable("/agregarReseña.fxml", "Agregar Reseña", new Observador() {
                @Override
                public void notificar() {
                    cargarReservasCliente(); // Actualizar la tabla después de agregar reseña
                }
            });
        }
    }
}
