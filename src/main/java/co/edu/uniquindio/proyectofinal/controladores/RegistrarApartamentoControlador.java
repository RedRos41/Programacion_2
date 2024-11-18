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

public class RegistrarApartamentoControlador extends VentanaObservable {

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<CiudadAlojamiento> comboCiudad;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtCapacidad;

    @FXML
    private ComboBox<ServicioAlojamiento> comboServicio;

    @FXML
    private TextField txtAdministracion;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private Button btnSeleccionarImagen;

    @FXML
    private Label lblRutaImagen;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnCancelar;

    private final ControladorPrincipal controladorPrincipal;

    public RegistrarApartamentoControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    public void initialize() {
        comboCiudad.getItems().setAll(CiudadAlojamiento.values());
        comboServicio.getItems().setAll(ServicioAlojamiento.values());
    }

    @FXML
    private void seleccionarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
        File archivoSeleccionado = fileChooser.showOpenDialog(btnSeleccionarImagen.getScene().getWindow());
        if (archivoSeleccionado != null) {
            lblRutaImagen.setText(archivoSeleccionado.getAbsolutePath());
        } else {
            lblRutaImagen.setText("No se seleccionó ninguna imagen");
        }
    }

    @FXML
    private void registrarApartamento(ActionEvent event) {
        try {
            // Validar campos obligatorios
            String nombre = validarCampoTexto(txtNombre.getText(), "Nombre");
            CiudadAlojamiento ciudad = validarComboBox(comboCiudad, "Ciudad");
            String direccion = validarCampoTexto(txtDireccion.getText(), "Dirección");
            ServicioAlojamiento servicio = validarComboBox(comboServicio, "Servicio");
            double precio = validarCampoNumerico(txtPrecio.getText(), "Precio");
            int capacidad = (int) validarCampoNumerico(txtCapacidad.getText(), "Capacidad");
            double administracion = validarCampoNumerico(txtAdministracion.getText(), "Costo de Administración");
            String descripcion = validarCampoTexto(txtDescripcion.getText(), "Descripción");
            String imagen = lblRutaImagen.getText().equals("No se seleccionó ninguna imagen") ? "default.jpg" : lblRutaImagen.getText();

            // Registrar el apartamento en la empresa
            controladorPrincipal.registrarApartamento(
                    direccion.hashCode(), // Generar dirección única
                    nombre,
                    ciudad,
                    descripcion,
                    imagen,
                    precio,
                    capacidad,
                    servicio,
                    administracion,
                    0 // Costo de mantenimiento no aplica para apartamento
            );

            // Notificar a los observadores
            notificarObservador();

            // Mostrar alerta de éxito
            mostrarMensajeExito("Apartamento registrado exitosamente", "El apartamento " + nombre + " se ha registrado correctamente.");

            // Limpiar campos
            limpiarCampos();
        } catch (Exception e) {
            mostrarError("Error al registrar apartamento: " + e.getMessage());
        }
    }

    @FXML
    private void cancelarRegistro(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
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

    private void limpiarCampos() {
        txtNombre.clear();
        comboCiudad.getSelectionModel().clearSelection();
        txtDireccion.clear();
        txtPrecio.clear();
        txtCapacidad.clear();
        comboServicio.getSelectionModel().clearSelection();
        txtAdministracion.clear();
        txtDescripcion.clear();
        lblRutaImagen.setText("No se seleccionó ninguna imagen");
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
