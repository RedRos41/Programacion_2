package co.edu.uniquindio.seguimiento2.controladores;

import co.edu.uniquindio.seguimiento2.modelos.Contacto;
import co.edu.uniquindio.seguimiento2.modelos.ContactoPrincipal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContactoControlador {

    @FXML private TableView<Contacto> tablaContactos;
    @FXML private TableColumn<Contacto, String> colNombre;
    @FXML private TableColumn<Contacto, String> colApellido;
    @FXML private TableColumn<Contacto, String> colTelefono;
    @FXML private TableColumn<Contacto, String> colCorreoElectronico;
    @FXML private TableColumn<Contacto, LocalDate> colCumpleaños;
    @FXML private TableColumn<Contacto, String> colFotoUrl;

    @FXML private TextField txtNombre, txtApellido, txtTelefono, txtCorreo, txtFotoUrl;
    @FXML private DatePicker dateCumpleanos;
    @FXML private ComboBox<String> comboBuscar;
    @FXML private TextField txtBuscar;

    private ObservableList<Contacto> listaContactos;
    private ContactoPrincipal gestionContactos;

    @FXML
    public void initialize() {
        gestionContactos = new ContactoPrincipal();
        listaContactos = FXCollections.observableArrayList(gestionContactos.listarContactos());
        tablaContactos.setItems(listaContactos);
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCorreoElectronico.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));
        colCumpleaños.setCellValueFactory(new PropertyValueFactory<>("cumpleaños"));
        colFotoUrl.setCellValueFactory(new PropertyValueFactory<>("fotoUrl"));
        comboBuscar.setItems(FXCollections.observableArrayList("Nombre", "Teléfono"));
        comboBuscar.getSelectionModel().selectFirst();
    }

    @FXML
    private void agregarContacto() {
        try {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String telefono = txtTelefono.getText();
            String correoElectronico = txtCorreo.getText();
            LocalDate cumpleanos = dateCumpleanos.getValue();
            String fotoUrl = txtFotoUrl.getText();

            if (gestionContactos.contactoDuplicado(nombre, apellido, telefono)) {
                mostrarMensaje("Error", "El contacto ya existe en la lista.", Alert.AlertType.WARNING);
                return;
            }

            gestionContactos.agregarContacto(nombre, apellido, telefono, cumpleanos, correoElectronico, fotoUrl);
            actualizarListaContactos();
            limpiarCampos();
            mostrarMensaje("Contacto agregado", "El contacto ha sido agregado correctamente.", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            mostrarMensaje("Error al agregar contacto", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void eliminarContacto() {
        try {
            Contacto contactoSeleccionado = tablaContactos.getSelectionModel().getSelectedItem();
            if (contactoSeleccionado != null) {
                gestionContactos.eliminarContacto(contactoSeleccionado.getId());
                actualizarListaContactos();
                mostrarMensaje("Contacto eliminado", "El contacto ha sido eliminado correctamente.", Alert.AlertType.INFORMATION);
            } else {
                mostrarMensaje("Seleccionar contacto", "Por favor seleccione un contacto para eliminar.", Alert.AlertType.WARNING);
            }
        } catch (Exception e) {
            mostrarMensaje("Error al eliminar contacto", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void editarContacto() {
        try {
            Contacto contactoSeleccionado = tablaContactos.getSelectionModel().getSelectedItem();
            if (contactoSeleccionado != null) {
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String telefono = txtTelefono.getText();
                String correoElectronico = txtCorreo.getText();
                LocalDate cumpleanos = dateCumpleanos.getValue();
                String fotoUrl = txtFotoUrl.getText();

                if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || correoElectronico.isEmpty()) {
                    mostrarMensaje("Error de validación", "Por favor, llene todos los campos obligatorios.", Alert.AlertType.WARNING);
                    return;
                }

                gestionContactos.editarContacto(contactoSeleccionado.getId(), nombre, apellido, telefono, cumpleanos, correoElectronico, fotoUrl);
                actualizarListaContactos();
                limpiarCampos();
                mostrarMensaje("Contacto editado", "El contacto ha sido editado correctamente.", Alert.AlertType.INFORMATION);
            } else {
                mostrarMensaje("Seleccionar contacto", "Por favor seleccione un contacto para editar.", Alert.AlertType.WARNING);
            }
        } catch (Exception e) {
            mostrarMensaje("Error al editar contacto", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void seleccionarContacto() {
        Contacto contactoSeleccionado = tablaContactos.getSelectionModel().getSelectedItem();
        if (contactoSeleccionado != null) {
            txtNombre.setText(contactoSeleccionado.getNombre());
            txtApellido.setText(contactoSeleccionado.getApellido());
            txtTelefono.setText(contactoSeleccionado.getTelefono());
            txtCorreo.setText(contactoSeleccionado.getCorreoElectronico());
            dateCumpleanos.setValue(contactoSeleccionado.getCumpleaños());
            txtFotoUrl.setText(contactoSeleccionado.getFotoUrl());
        }
    }

    @FXML
    private void buscarContacto() {
        String criterio = comboBuscar.getSelectionModel().getSelectedItem();
        String textoBusqueda = txtBuscar.getText();

        if (textoBusqueda.isEmpty()) {
            actualizarListaContactos();
            return;
        }

        List<Contacto> resultadosBusqueda;
        if (criterio.equals("Nombre")) {
            resultadosBusqueda = gestionContactos.buscarContactosNombre(textoBusqueda);
        } else if (criterio.equals("Teléfono")) {
            resultadosBusqueda = gestionContactos.buscarContactosTelefono(textoBusqueda);
        } else {
            resultadosBusqueda = new ArrayList<>();
        }

        listaContactos.setAll(resultadosBusqueda);
    }

    @FXML
    private void verTodosContactos() {
        actualizarListaContactos();
    }

    private void actualizarListaContactos() {
        listaContactos.setAll(gestionContactos.listarContactos());
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        dateCumpleanos.setValue(null);
        txtFotoUrl.clear();
    }

    private void mostrarMensaje(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
