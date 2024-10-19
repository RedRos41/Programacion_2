package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Paciente;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar columnas de la tabla pero no inicializar los datos hasta que Clinica esté disponible
        columnaIdentificacion.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnaSuscripcion.setCellValueFactory(new PropertyValueFactory<>("suscripcion"));
    }

    public void inicializarListaPacientes() {
        // Este método se puede invocar después de que la instancia de Clinica se haya establecido
        if (getClinica() != null) {
            ObservableList<Paciente> pacientes = getClinica().getPacientes();
            tablaPacientes.setItems(pacientes);
        } else {
            System.out.println("Error: La instancia de Clinica no ha sido inicializada.");
        }
    }
}

