package co.edu.uniquindio.seguimiento2.controladores;

import co.edu.uniquindio.seguimiento2.modelos.Contacto;
import co.edu.uniquindio.seguimiento2.modelos.ContactoPrincipal;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ContactoControlador implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCorreo;
    @FXML
    private DatePicker dateCumpleanos;

    @FXML
    private TableView<Contacto> tablaContactos;
    @FXML
    private TableColumn<Contacto, String> colNombre;
    @FXML
    private TableColumn<Contacto, String> colApellido;
    @FXML
    private TableColumn<Contacto, String> colTelefono;

    private final ContactoPrincipal contactoPrincipal; // Instancia del modelo ContactoPrincipal
    private Contacto contactoSeleccionado; // Contacto seleccionado en la tabla
    private ObservableList<Contacto> contactosObservable; // Lista observable de contactos

    public ContactoControlador() {
        contactoPrincipal = new ContactoPrincipal();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configurar las columnas de la tabla
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
        colTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));

        // Inicializar lista observable y cargar los contactos
        contactosObservable = FXCollections.observableArrayList();
        cargarContactos();

        // Configurar la selección en la tabla
        tablaContactos.setOnMouseClicked(e -> {
            contactoSeleccionado = tablaContactos.getSelectionModel().getSelectedItem();
            if (contactoSeleccionado != null) {
                txtNombre.setText(contactoSeleccionado.getNombre());
                txtApellido.setText(contactoSeleccionado.getApellido());
                txtTelefono.setText(contactoSeleccionado.getTelefono());
                txtCorreo.setText(contactoSeleccionado.getCorreoElectronico());
                dateCumpleanos.setValue(contactoSeleccionado.getCumpleaños());
            }
        });
    }

    public void agregarContacto(ActionEvent e) {
        try {
            contactoPrincipal.agregarContacto(
                    txtNombre.getText(),
                    txtApellido.getText(),
                    txtTelefono.getText(),
                    dateCumpleanos.getValue(),
                    txtCorreo.getText(),
                    ""
            );
            limpiarCampos();
            actualizarContactos();
            mostrarAlerta("Contacto agregado correctamente", Alert.AlertType.INFORMATION);
        } catch (Exception ex) {
            mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void eliminarContacto(ActionEvent e) {
        if (contactoSeleccionado != null) {
            try {
                contactoPrincipal.eliminarContacto(contactoSeleccionado.getId());
                limpiarCampos();
                actualizarContactos();
                mostrarAlerta("Contacto eliminado correctamente", Alert.AlertType.INFORMATION);
            } catch (Exception ex) {
                mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Debe seleccionar un contacto de la tabla", Alert.AlertType.WARNING);
        }
    }

    public void editarContacto(ActionEvent e) {
        if (contactoSeleccionado != null) {
            try {
                contactoPrincipal.editarContacto(
                        contactoSeleccionado.getId(),
                        txtNombre.getText(),
                        txtApellido.getText(),
                        txtTelefono.getText(),
                        dateCumpleanos.getValue(),
                        txtCorreo.getText(),
                        ""
                );
                limpiarCampos();
                actualizarContactos();
                mostrarAlerta("Contacto editado correctamente", Alert.AlertType.INFORMATION);
            } catch (Exception ex) {
                mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Debe seleccionar un contacto de la tabla", Alert.AlertType.WARNING);
        }
    }

    private void cargarContactos() {
        contactosObservable.setAll(contactoPrincipal.listarContactos());
        tablaContactos.setItems(contactosObservable);
    }

    private void actualizarContactos() {
        contactosObservable.setAll(contactoPrincipal.listarContactos());
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        dateCumpleanos.setValue(null);
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }
}
