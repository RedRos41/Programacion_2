package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Clinica;
import co.edu.uniquindio.clinica.modelo.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListaPacientesControlador {

    @FXML
    private TableView<Paciente> tablaPacientes;

    @FXML
    private TableColumn<Paciente, String> colId;

    @FXML
    private TableColumn<Paciente, String> colNombre;

    @FXML
    private TableColumn<Paciente, String> colCedula;

    @FXML
    private TableColumn<Paciente, String> colTelefono;

    @FXML
    private TableColumn<Paciente, String> colEmail;

    private ObservableList<Paciente> listaPacientes;
    private final Clinica clinica;

    public ListaPacientesControlador(Clinica clinica) {
        this.clinica = clinica;
    }

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        listaPacientes = FXCollections.observableArrayList(clinica.listarPacientes());
        tablaPacientes.setItems(listaPacientes);
    }

    public void actualizarListaPacientes() {
        listaPacientes.setAll(clinica.listarPacientes());
    }
}
