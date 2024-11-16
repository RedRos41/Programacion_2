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

import java.time.LocalDateTime;
import java.util.List;

public class PanelClienteControlador implements Observador {

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

    @FXML
    private Label lblSaldoDisponible;

    private final ControladorPrincipal controladorPrincipal;

    public PanelClienteControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
        controladorPrincipal.registrarVentanaObservable(this);
    }

    @FXML
    public void initialize() {
        colIdReserva.setCellValueFactory(new PropertyValueFactory<>("idReserva"));
        colAlojamiento.setCellValueFactory(new PropertyValueFactory<>("nombreAlojamiento"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicioReserva"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaFinReserva"));

        colNombreAlojamiento.setCellValueFactory(new PropertyValueFactory<>("nombreAlojamiento"));
        colTipoAlojamiento.setCellValueFactory(new PropertyValueFactory<>("tipoAlojamiento"));
        colCiudadAlojamiento.setCellValueFactory(new PropertyValueFactory<>("ciudadAlojamiento"));
        colPrecioAlojamiento.setCellValueFactory(new PropertyValueFactory<>("precioPorNocheAlojamiento"));

        comboTipoAlojamiento.getItems().addAll(TipoAlojamiento.values());
        comboCiudadAlojamiento.getItems().addAll(CiudadAlojamiento.values());

        notificar();
    }

    @Override
    public void notificar() {
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

        cargarReservasCliente();
    }

    @FXML
    private void realizarReserva(ActionEvent event) {
        controladorPrincipal.navegarVentana("/realizarReserva.fxml", "Realizar Reserva");
        cargarReservasCliente();
    }

    @FXML
    private void abrirBilletera(ActionEvent event) {
        controladorPrincipal.navegarVentana("/billetera.fxml", "Billetera");
        cargarSaldoDisponible();
    }

    @FXML
    private void cargarSaldoDisponible() {
        Usuario cliente = Sesion.getInstancia().getUsuario();
        if (cliente != null && cliente instanceof Cliente clienteEspecifico) {
            lblSaldoDisponible.setText(String.format("Saldo: $%.2f", clienteEspecifico.getBilleteraCliente().getSaldoBilletera()));
        }
    }

    @FXML
    private void buscarAlojamientos(ActionEvent event) {
        try {

            String nombre = txtBuscarNombre.getText();
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

    private void mostrarResultadosBusqueda(List<Alojamiento> alojamientos) {
        ObservableList<Alojamiento> listaResultados = FXCollections.observableArrayList(alojamientos);
        tablaResultadosBusqueda.setItems(listaResultados);
    }

    @FXML
    private void seleccionarAlojamientoParaReserva(ActionEvent event) {
        Alojamiento alojamientoSeleccionado = tablaResultadosBusqueda.getSelectionModel().getSelectedItem();
        if (alojamientoSeleccionado != null) {
            controladorPrincipal.navegarVentanaObservableConAlojamiento(
                    "/realizarReserva.fxml",
                    "Realizar Reserva",
                    alojamientoSeleccionado,
                    this
            );
        } else {
            controladorPrincipal.mostrarAlerta("Por favor, seleccione un alojamiento para reservar", "Seleccionar Alojamiento", Alert.AlertType.WARNING);
        }
    }


    @FXML
    private void cancelarReserva(ActionEvent event) {
        Reserva reservaSeleccionada = tablaReservas.getSelectionModel().getSelectedItem();
        if (reservaSeleccionada != null) {
            try {
                if (reservaSeleccionada.getFechaInicioReserva().isBefore(LocalDateTime.now())) {
                    controladorPrincipal.mostrarAlerta(
                            "No se puede cancelar una reserva que ya comenzó o terminó.",
                            "Error al cancelar reserva",
                            Alert.AlertType.WARNING
                    );
                    return;
                }

                controladorPrincipal.eliminarReserva(Sesion.getInstancia().getUsuario(), reservaSeleccionada.getIdReserva());
                cargarReservasCliente(); // Actualizar la tabla de reservas
                controladorPrincipal.mostrarAlerta("Reserva cancelada exitosamente", "Éxito", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                controladorPrincipal.mostrarAlerta(e.getMessage(), "Error al cancelar reserva", Alert.AlertType.ERROR);
            }
        } else {
            controladorPrincipal.mostrarAlerta("Por favor, seleccione una reserva para cancelar.", "Advertencia", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void agregarReseña(ActionEvent event) {
        Reserva reservaSeleccionada = tablaReservas.getSelectionModel().getSelectedItem();
        if (reservaSeleccionada != null) {
            try {
                if (reservaSeleccionada.getFechaFinReserva().isAfter(LocalDateTime.now())) {
                    controladorPrincipal.mostrarAlerta(
                            "Solo puede agregar una reseña a reservas finalizadas.",
                            "Error al agregar reseña",
                            Alert.AlertType.WARNING
                    );
                    return;
                }

                controladorPrincipal.navegarVentanaObservable("/agregarReseña.fxml", "Agregar Reseña", this);
            } catch (Exception e) {
                controladorPrincipal.mostrarAlerta(e.getMessage(), "Error al agregar reseña", Alert.AlertType.ERROR);
            }
        } else {
            controladorPrincipal.mostrarAlerta("Por favor, seleccione una reserva para agregar una reseña.", "Advertencia", Alert.AlertType.WARNING);
        }
    }




}
