package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ListaPacientesControlador extends AbstractControlador implements Initializable {

    @FXML
    private TableView<Paciente> tablaPacientes;

    @FXML
    private TableColumn<Paciente, String> columnaIdentificacion;

    @FXML
    private TableColumn<Paciente, String> columnaNombre;

    @FXML
    private TableColumn<Paciente, String> columnaTelefono;

    @FXML
    private TableColumn<Paciente, String> columnaCorreo;

    @FXML
    private TableColumn<Paciente, String> columnaSuscripcion;

    private ObservableList<Paciente> listaPacientesObservable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inicializa las columnas de la tabla
        columnaIdentificacion.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        columnaSuscripcion.setCellValueFactory(new PropertyValueFactory<>("tipoSuscripcion"));

        // Inicializa la tabla con los pacientes de la clínica
        inicializarListaPacientes();
    }

    public void inicializarListaPacientes() {
        if (getClinica() != null) {
            listaPacientesObservable = FXCollections.observableArrayList(getClinica().getPacientes());
            tablaPacientes.setItems(listaPacientesObservable);
        } else {
            System.out.println("Error: La instancia de Clinica no ha sido inicializada.");
        }
    }
}
