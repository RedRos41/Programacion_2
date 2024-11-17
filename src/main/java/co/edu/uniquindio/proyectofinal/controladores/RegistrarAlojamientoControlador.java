package co.edu.uniquindio.proyectofinal.controladores;

import co.edu.uniquindio.proyectofinal.modelos.enums.CiudadAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.TipoAlojamiento;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegistrarAlojamientoControlador {

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<TipoAlojamiento> comboTipo;

    @FXML
    private ComboBox<CiudadAlojamiento> comboCiudad;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtCapacidad;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private Label lblMensaje;

    private final ControladorPrincipal controladorPrincipal;

    public RegistrarAlojamientoControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    public void initialize() {
        comboTipo.getItems().addAll(TipoAlojamiento.values());
        comboCiudad.getItems().addAll(CiudadAlojamiento.values());
    }

    @FXML
    private void registrarAlojamiento() {
        try {
            String nombre = txtNombre.getText();
            TipoAlojamiento tipo = comboTipo.getValue();
            CiudadAlojamiento ciudad = comboCiudad.getValue();
            double precio = Double.parseDouble(txtPrecio.getText());
            int capacidad = Integer.parseInt(txtCapacidad.getText());
            String descripcion = txtDescripcion.getText();

            if (nombre.isEmpty() || tipo == null || ciudad == null || descripcion.isEmpty()) {
                throw new Exception("Todos los campos deben estar completos.");
            }

            if (precio <= 0 || capacidad <= 0) {
                throw new Exception("El precio y la capacidad deben ser números positivos.");
            }

            // Llamar al ControladorPrincipal para registrar el alojamiento
            controladorPrincipal.registrarCasa( // Para simplicidad, consideramos registrar como "Casa"
                    (int) (Math.random() * 10000),
                    nombre,
                    ciudad,
                    descripcion,
                    "imagen_default.jpg", // Imagen por defecto
                    precio,
                    capacidad,
                    null, // Servicio opcional
                    50.0, // Valores arbitrarios
                    50.0
            );

            lblMensaje.setText("Alojamiento registrado con éxito.");
            lblMensaje.setStyle("-fx-text-fill: green;");
            limpiarFormulario();
        } catch (NumberFormatException e) {
            lblMensaje.setText("Por favor, ingrese valores válidos para el precio y la capacidad.");
        } catch (Exception e) {
            lblMensaje.setText(e.getMessage());
        }
    }

    private void limpiarFormulario() {
        txtNombre.clear();
        comboTipo.setValue(null);
        comboCiudad.setValue(null);
        txtPrecio.clear();
        txtCapacidad.clear();
        txtDescripcion.clear();
    }

    @FXML
    private void cancelar() {
        controladorPrincipal.cerrarVentana(txtNombre); // Usa cualquier nodo para cerrar la ventana
    }
}
