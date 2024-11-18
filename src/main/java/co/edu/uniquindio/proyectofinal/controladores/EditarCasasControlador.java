package co.edu.uniquindio.proyectofinal.controladores;

import co.edu.uniquindio.proyectofinal.modelos.Casa;
import co.edu.uniquindio.proyectofinal.modelos.enums.CiudadAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.ServicioAlojamiento;
import co.edu.uniquindio.proyectofinal.observador.VentanaObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EditarCasasControlador extends VentanaObservable {

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<CiudadAlojamiento> comboCiudad;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtCapacidad;

    @FXML
    private ComboBox<ServicioAlojamiento> comboServicio;

    @FXML
    private TextField txtAseo;

    @FXML
    private TextField txtMantenimiento;

    @FXML
    private Label lblMensaje;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    private final ControladorPrincipal controladorPrincipal;

    private Casa casaSeleccionada;

    public EditarCasasControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    public void setCasaSeleccionada(Casa casa) {
        this.casaSeleccionada = casa;
        cargarDatosCasa();
    }

    @FXML
    public void initialize() {
        comboCiudad.getItems().setAll(CiudadAlojamiento.values());
        comboServicio.getItems().setAll(ServicioAlojamiento.values());
    }

    private void cargarDatosCasa() {
        if (casaSeleccionada != null) {
            txtNombre.setText(casaSeleccionada.getNombreAlojamiento());
            comboCiudad.setValue(casaSeleccionada.getCiudadAlojamiento());
            txtDescripcion.setText(casaSeleccionada.getDescripcionAlojamiento());
            txtPrecio.setText(String.valueOf(casaSeleccionada.getPrecioPorNocheAlojamiento()));
            txtCapacidad.setText(String.valueOf(casaSeleccionada.getCapacidadMaximaAlojamiento()));
            comboServicio.setValue(casaSeleccionada.getServicioAlojamiento());
            txtAseo.setText(String.valueOf(casaSeleccionada.getAseoCasa()));
            txtMantenimiento.setText(String.valueOf(casaSeleccionada.getMantenimientoCasa()));
        }
    }

    @FXML
    private void guardarCambios(ActionEvent event) {
        try {
            // Validar campos obligatorios
            String nombre = validarCampoTexto(txtNombre.getText(), "Nombre");
            CiudadAlojamiento ciudad = validarComboBox(comboCiudad, "Ciudad");
            String descripcion = validarCampoTexto(txtDescripcion.getText(), "Descripción");
            double precio = validarCampoNumerico(txtPrecio.getText(), "Precio");
            int capacidad = (int) validarCampoNumerico(txtCapacidad.getText(), "Capacidad");
            ServicioAlojamiento servicio = validarComboBox(comboServicio, "Servicio");
            double aseo = validarCampoNumerico(txtAseo.getText(), "Costo de Aseo");
            double mantenimiento = validarCampoNumerico(txtMantenimiento.getText(), "Costo de Mantenimiento");

            // Validar duplicados de nombre excluyendo la casa seleccionada
            if (!casaSeleccionada.getNombreAlojamiento().equals(nombre) &&
                    controladorPrincipal.buscarAlojamientoPorNombre(nombre) != null) {
                throw new Exception("Ya existe un alojamiento con el nombre proporcionado.");
            }

            controladorPrincipal.editarCasa(
                    casaSeleccionada.getDireccionAlojamiento(),
                    nombre,
                    ciudad,
                    descripcion,
                    casaSeleccionada.getImagenAlojamiento(),
                    precio,
                    capacidad,
                    servicio,
                    aseo,
                    mantenimiento
            );


            controladorPrincipal.notificarObservadores();

            mostrarMensajeExito("Cambios guardados exitosamente", "Los cambios en la casa fueron actualizados correctamente.");
            cerrarVentana();
        } catch (Exception e) {
            mostrarError("Error al guardar cambios: " + e.getMessage());
        }
    }

    @FXML
    private void cancelarEdicion(ActionEvent event) {
        cerrarVentana();
    }

    private String validarCampoTexto(String texto, String nombreCampo) throws Exception {
        if (texto == null || texto.trim().isEmpty()) {
            throw new Exception("El campo " + nombreCampo + " es obligatorio.");
        }
        return texto.trim();
    }

    private double validarCampoNumerico(String texto, String nombreCampo) throws Exception {
        try {
            double valor = Double.parseDouble(texto);
            if (valor <= 0) {
                throw new Exception("El campo " + nombreCampo + " debe ser mayor a cero.");
            }
            return valor;
        } catch (NumberFormatException e) {
            throw new Exception("El campo " + nombreCampo + " debe ser un número válido.");
        }
    }

    private <T> T validarComboBox(ComboBox<T> comboBox, String nombreCampo) throws Exception {
        T valor = comboBox.getValue();
        if (valor == null) {
            throw new Exception("Debe seleccionar un valor para " + nombreCampo + ".");
        }
        return valor;
    }

    private void mostrarMensajeExito(String titulo, String mensaje) {
        Alert alertaExito = new Alert(Alert.AlertType.INFORMATION);
        alertaExito.setTitle(titulo);
        alertaExito.setHeaderText(null);
        alertaExito.setContentText(mensaje);
        alertaExito.showAndWait();
    }

    private void mostrarError(String mensaje) {
        Alert alertaError = new Alert(Alert.AlertType.ERROR);
        alertaError.setTitle("Error");
        alertaError.setHeaderText(null);
        alertaError.setContentText(mensaje);
        alertaError.showAndWait();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
}
