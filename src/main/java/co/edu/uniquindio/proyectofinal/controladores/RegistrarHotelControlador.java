package co.edu.uniquindio.proyectofinal.controladores;

import co.edu.uniquindio.proyectofinal.modelos.enums.CiudadAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.ServicioAlojamiento;
import co.edu.uniquindio.proyectofinal.observador.VentanaObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class RegistrarHotelControlador extends VentanaObservable {

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<CiudadAlojamiento> comboCiudad;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtCapacidad;

    @FXML
    private ComboBox<ServicioAlojamiento> comboServicio;

    @FXML
    private TextField txtDireccion;

    @FXML
    private Button btnSeleccionarImagen;

    @FXML
    private Label lblRutaImagen;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label lblMensaje;

    private final ControladorPrincipal controladorPrincipal;

    public RegistrarHotelControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    public void initialize() {
        // Configurar los ComboBox con los valores de los enums
        comboCiudad.getItems().setAll(CiudadAlojamiento.values());
        comboServicio.getItems().setAll(ServicioAlojamiento.values());
    }

    @FXML
    private void registrarHotel(ActionEvent event) {
        try {
            // Validar campos obligatorios
            String nombre = validarCampoTexto(txtNombre.getText(), "Nombre");
            CiudadAlojamiento ciudad = validarComboBox(comboCiudad, "Ciudad");
            String descripcion = validarCampoTexto(txtDescripcion.getText(), "Descripción");
            double precio = validarCampoNumerico(txtPrecio.getText(), "Precio");
            int capacidad = (int) validarCampoNumerico(txtCapacidad.getText(), "Capacidad");
            ServicioAlojamiento servicio = validarComboBox(comboServicio, "Servicio");

            String imagen = lblRutaImagen.getText().equals("No se seleccionó ninguna imagen") ? "default.jpg" : lblRutaImagen.getText();

            String direccion = validarCampoTexto(txtDireccion.getText(), "Dirección");

            // Registrar el hotel en la empresa
            controladorPrincipal.registrarHotel(
                    direccion.hashCode(),
                    nombre,
                    ciudad,
                    descripcion,
                    imagen,
                    precio,
                    capacidad,
                    servicio
            );

            // Notificar a los observadores
            notificarObservador();

            // Mostrar alerta de éxito
            mostrarMensajeExito("Hotel registrado exitosamente", "El hotel " + nombre + " se ha registrado correctamente.");

            // Limpiar campos
            limpiarCampos();
        } catch (Exception e) {
            mostrarError("Error al registrar hotel: " + e.getMessage());
        }
    }

    @FXML
    private void cancelarRegistro(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void seleccionarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de Imagen", "*.jpg", "*.png", "*.jpeg"));
        File archivoSeleccionado = fileChooser.showOpenDialog(btnSeleccionarImagen.getScene().getWindow());

        if (archivoSeleccionado != null) {
            lblRutaImagen.setText(archivoSeleccionado.getAbsolutePath());
        }
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
                throw new Exception("El campo " + nombreCampo + " debe ser un número positivo.");
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

    private void limpiarCampos() {
        txtNombre.clear();
        comboCiudad.getSelectionModel().clearSelection();
        txtDescripcion.clear();
        txtPrecio.clear();
        txtCapacidad.clear();
        comboServicio.getSelectionModel().clearSelection();
        lblRutaImagen.setText("No se seleccionó ninguna imagen");
        lblMensaje.setText("");
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
}
