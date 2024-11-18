package co.edu.uniquindio.proyectofinal.controladores;

import co.edu.uniquindio.proyectofinal.modelos.enums.CiudadAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.TipoAlojamiento;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RegistrarAlojamientoControlador {

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<TipoAlojamiento> cbTipo;

    @FXML
    private ComboBox<CiudadAlojamiento> cbCiudad;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtCapacidad;

    @FXML
    private Label lblMensaje;

    private final ControladorPrincipal controladorPrincipal;

    public RegistrarAlojamientoControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    public void initialize() {
        try {
            cbTipo.setItems(FXCollections.observableArrayList(TipoAlojamiento.values()));
            cbCiudad.setItems(FXCollections.observableArrayList(CiudadAlojamiento.values()));
        } catch (Exception e) {
            lblMensaje.setText("Error al inicializar los campos: " + e.getMessage());
        }
    }

    @FXML
    private void registrarAlojamiento() {
        try {
            // Validar campos
            String nombre = validarCampoTexto(txtNombre.getText(), "Nombre");
            TipoAlojamiento tipo = validarComboBox(cbTipo, "Tipo de Alojamiento");
            CiudadAlojamiento ciudad = validarComboBox(cbCiudad, "Ciudad");
            String descripcion = validarCampoTexto(txtDescripcion.getText(), "Descripción");
            double precio = validarCampoNumerico(txtPrecio.getText(), "Precio");
            int capacidad = (int) validarCampoNumerico(txtCapacidad.getText(), "Capacidad");

            // Lógica según tipo de alojamiento
            switch (tipo) {
                case CASA -> controladorPrincipal.navegarVentana("/RegistrarCasa.fxml", "Registrar Casa");
                case APARTAMENTO -> controladorPrincipal.navegarVentana("/RegistrarApartamento.fxml", "Registrar Apartamento");
                case HOTEL -> controladorPrincipal.navegarVentana("/RegistrarHotel.fxml", "Registrar Hotel");
                default -> throw new Exception("El tipo de alojamiento no es válido.");
            }

            controladorPrincipal.notificarObservadores();

            lblMensaje.setText("Alojamiento registrado con éxito.");
            limpiarCampos();
        } catch (Exception e) {
            lblMensaje.setText("Error al registrar alojamiento: " + e.getMessage());
        }
    }

    @FXML
    private void cancelar() {
        try {
            Stage stage = (Stage) txtNombre.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            lblMensaje.setText("Error al cerrar la ventana: " + e.getMessage());
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
        try {
            txtNombre.clear();
            cbTipo.getSelectionModel().clearSelection();
            cbCiudad.getSelectionModel().clearSelection();
            txtDescripcion.clear();
            txtPrecio.clear();
            txtCapacidad.clear();
            lblMensaje.setText("");
        } catch (Exception e) {
            lblMensaje.setText("Error al limpiar campos: " + e.getMessage());
        }
    }
}
