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
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ContactoControlador implements Initializable {

    @FXML
    private TextField txtNombre, txtApellido, txtTelefono, txtCorreo, txtUrlFoto, txtBuscar;
    @FXML
    private DatePicker dateCumpleanos;
    @FXML
    private TableView<Contacto> tablaContactos;
    @FXML
    private TableColumn<Contacto, String> colNombre, colApellido, colTelefono;
    @FXML
    private ComboBox<String> comboBuscarPor;

    private final ContactoPrincipal contactoPrincipal;
    private Contacto contactoSeleccionado;
    private ObservableList<Contacto> contactosObservable;

    public ContactoControlador() {
        contactoPrincipal = new ContactoPrincipal();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
        colTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));

        contactosObservable = FXCollections.observableArrayList();
        cargarContactos();


        comboBuscarPor.setItems(FXCollections.observableArrayList("Nombre", "Teléfono"));
        comboBuscarPor.setValue("Nombre");

        tablaContactos.setOnMouseClicked(e -> {
            contactoSeleccionado = tablaContactos.getSelectionModel().getSelectedItem();
            if (contactoSeleccionado != null) {
                txtNombre.setText(contactoSeleccionado.getNombre());
                txtApellido.setText(contactoSeleccionado.getApellido());
                txtTelefono.setText(contactoSeleccionado.getTelefono());
                txtCorreo.setText(contactoSeleccionado.getCorreoElectronico());
                txtUrlFoto.setText(contactoSeleccionado.getFotoUrl());
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
                    txtUrlFoto.getText()
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
                        txtUrlFoto.getText()
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
    public void buscarContacto(ActionEvent e) {
        String textoBusqueda = txtBuscar.getText().toLowerCase();
        String criterioBusqueda = comboBuscarPor.getValue();

        List<Contacto> contactosFiltrados;

        if (criterioBusqueda.equals("Nombre")) {
            contactosFiltrados = contactoPrincipal.listarContactos().stream()
                    .filter(c -> c.getNombre().toLowerCase().contains(textoBusqueda))
                    .collect(Collectors.toList());
        } else {
            contactosFiltrados = contactoPrincipal.listarContactos().stream()
                    .filter(c -> c.getTelefono().contains(textoBusqueda))
                    .collect(Collectors.toList());
        }

        contactosObservable.setAll(contactosFiltrados);
        tablaContactos.setItems(contactosObservable);
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
        txtUrlFoto.clear();
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
