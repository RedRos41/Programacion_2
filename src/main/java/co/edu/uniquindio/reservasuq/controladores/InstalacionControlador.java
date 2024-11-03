package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.modelo.Horario;
import co.edu.uniquindio.reservasuq.modelo.Instalacion;
import co.edu.uniquindio.reservasuq.modelo.enums.DiaSemana;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class InstalacionControlador {

    private final ServiciosReservasUQ controladorPrincipal;

    @FXML
    private TextField txtNombreInstalacion;

    @FXML
    private TextField txtAforo;

    @FXML
    private TextField txtCosto;

    @FXML
    private ComboBox<DiaSemana> comboDiaSemana;

    @FXML
    private TextField txtHoraInicio;

    @FXML
    private TextField txtHoraFin;

    @FXML
    private TableView<Instalacion> tablaInstalaciones;

    @FXML
    private TableColumn<Instalacion, String> colNombre;

    @FXML
    private TableColumn<Instalacion, Integer> colAforo;

    @FXML
    private TableColumn<Instalacion, Float> colCosto;

    @FXML
    private Button btnAgregarHorario;

    private ObservableList<Horario> horariosList;

    public InstalacionControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    public void initialize() {
        horariosList = FXCollections.observableArrayList();
        comboDiaSemana.setItems(FXCollections.observableArrayList(DiaSemana.values()));
        comboDiaSemana.setConverter(new StringConverter<>() {
            @Override
            public String toString(DiaSemana dia) {
                return dia.name().charAt(0) + dia.name().substring(1).toLowerCase();
            }

            @Override
            public DiaSemana fromString(String string) {
                return DiaSemana.valueOf(string.toUpperCase());
            }
        });

        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colAforo.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAforo()));
        colCosto.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCosto()));

        cargarInstalaciones();
    }

    @FXML
    public void agregarHorario() {
        try {
            DiaSemana diaSemana = comboDiaSemana.getValue();
            LocalTime horaInicio = LocalTime.parse(txtHoraInicio.getText());
            LocalTime horaFin = LocalTime.parse(txtHoraFin.getText());

            Horario horario = new Horario(diaSemana, horaInicio, horaFin);
            horariosList.add(horario);
            mostrarAlerta("Horario agregado", "El horario se ha agregado correctamente.", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            mostrarAlerta("Error", "Error al agregar horario: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void crearInstalacion() {
        try {
            String nombre = txtNombreInstalacion.getText();
            int aforo = Integer.parseInt(txtAforo.getText());
            float costo = Float.parseFloat(txtCosto.getText());

            List<Horario> horarios = new ArrayList<>(horariosList);
            controladorPrincipal.crearInstalacion(nombre, aforo, costo, horarios);
            mostrarAlerta("Éxito", "Instalación creada correctamente.", Alert.AlertType.INFORMATION);
            limpiarFormulario();
            cargarInstalaciones();
        } catch (Exception e) {
            mostrarAlerta("Error", "Error al crear instalación: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarFormulario() {
        txtNombreInstalacion.clear();
        txtAforo.clear();
        txtCosto.clear();
        txtHoraInicio.clear();
        txtHoraFin.clear();
        horariosList.clear();
    }

    private void cargarInstalaciones() {
        ObservableList<Instalacion> listaInstalaciones = FXCollections.observableArrayList(controladorPrincipal.listarInstalaciones());
        tablaInstalaciones.setItems(listaInstalaciones);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
