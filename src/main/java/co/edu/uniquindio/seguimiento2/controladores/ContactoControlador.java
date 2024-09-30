package co.edu.uniquindio.seguimiento2.controladores;

import co.edu.uniquindio.seguimiento2.modelos.Contacto;
import co.edu.uniquindio.seguimiento2.modelos.ContactoPrincipal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class ContactoControlador {

    @FXML
    private TableView<Contacto> tablaContactos;
    @FXML
    private TableColumn<Contacto, String> colNombre;
    @FXML
    private TableColumn<Contacto, String> colApellido;
    @FXML
    private TableColumn<Contacto, String> colTelefono;
    @FXML
    private TextField txtNombre, txtApellido, txtTelefono, txtCorreo;

    private ObservableList<Contacto> listaContactos;
    private ContactoPrincipal gestionContactos;

    @FXML
    public void initialize() {
        // Inicializar la lista y la clase de gestión
        gestionContactos = new ContactoPrincipal();
        listaContactos = FXCollections.observableArrayList(gestionContactos.listarContactos());
        tablaContactos.setItems(listaContactos);

        // Configurar las columnas de la tabla
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    }

    @FXML
    private void agregarContacto() throws Exception {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String telefono = txtTelefono.getText();
        String correoElectronico = txtCorreo.getText();
        LocalDate cumpleanos = LocalDate.now(); // Por ahora se usará la fecha actual como ejemplo

        gestionContactos.agregarContacto(nombre, apellido, telefono, cumpleanos, correoElectronico, "");
        listaContactos.setAll(gestionContactos.listarContactos()); // Actualizar la tabla
        limpiarCampos();
    }

    @FXML
    private void eliminarContacto(ActionEvent event) throws Exception {
        // Obtener el contacto seleccionado en la tabla
        Contacto contactoSeleccionado = tablaContactos.getSelectionModel().getSelectedItem();
        if (contactoSeleccionado != null) {
            gestionContactos.eliminarContacto(contactoSeleccionado.getId());
            listaContactos.setAll(gestionContactos.listarContactos()); // Actualizar la tabla
        } else {
            System.out.println("Por favor seleccione un contacto para eliminar.");
        }
    }

    @FXML
    private void editarContacto(ActionEvent event) throws Exception {
        // Obtener el contacto seleccionado en la tabla
        Contacto contactoSeleccionado = tablaContactos.getSelectionModel().getSelectedItem();
        if (contactoSeleccionado != null) {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String telefono = txtTelefono.getText();
            String correoElectronico = txtCorreo.getText();
            LocalDate cumpleanos = LocalDate.now(); // Por ahora se usará la fecha actual como ejemplo

            gestionContactos.editarContacto(contactoSeleccionado.getId(), nombre, apellido, telefono, cumpleanos, correoElectronico, "");
            listaContactos.setAll(gestionContactos.listarContactos()); // Actualizar la tabla
            limpiarCampos();
        } else {
            System.out.println("Por favor seleccione un contacto para editar.");
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtTelefono.clear();
        txtCorreo.clear();
    }
}
