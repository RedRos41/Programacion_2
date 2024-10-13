package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Clinica;
import co.edu.uniquindio.clinica.modelo.Paciente;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListaPacientesControlador {

    @FXML
    private TableView<Paciente> tablaPacientes;
    @FXML
    private TableColumn<Paciente, String> colIdentificacion;
    @FXML
    private TableColumn<Paciente, String> colNombre;
    @FXML
    private TableColumn<Paciente, String> colTelefono;
    @FXML
    private TableColumn<Paciente, String> colCorreo;
    @FXML
    private TableColumn<Paciente, String> colSuscripcion;

    private Clinica clinica;

    public void inicializar(Clinica clinica) {
        this.clinica = clinica;
        inicializarTabla();
    }

    private void inicializarTabla() {
        colIdentificacion.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSuscripcion.setCellValueFactory(new PropertyValueFactory<>("suscripcion"));
        tablaPacientes.setItems(FXCollections.observableArrayList(clinica.getPacientes()));
    }
}
