package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.modelo.Horario;
import co.edu.uniquindio.reservasuq.modelo.Instalacion;
import co.edu.uniquindio.reservasuq.modelo.enums.DiaSemana;
import co.edu.uniquindio.reservasuq.observador.VentanaObservable;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GestionInstalacionesControlador extends VentanaObservable {

    @FXML
    private TextField txtNombreInstalacion;

    @FXML
    private TextField txtAforo;

    @FXML
    private TextField txtCosto;

    @FXML
    private TextField txtHoraInicio;

    @FXML
    private TextField txtHoraFin;

    @FXML
    private CheckBox chkLunes;

    @FXML
    private CheckBox chkMartes;

    @FXML
    private CheckBox chkMiercoles;

    @FXML
    private CheckBox chkJueves;

    @FXML
    private CheckBox chkViernes;

    @FXML
    private CheckBox chkSabado;

    @FXML
    private CheckBox chkDomingo;

    private final ControladorPrincipal controladorPrincipal;

    public GestionInstalacionesControlador() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    public void agregarInstalacion() {
        try {
            String nombre = txtNombreInstalacion.getText();
            int aforo = Integer.parseInt(txtAforo.getText());
            float costo = Float.parseFloat(txtCosto.getText());

            LocalTime horaInicio = validarHora(txtHoraInicio.getText());
            LocalTime horaFin = validarHora(txtHoraFin.getText());

            if (horaInicio == null || horaFin == null) {
                mostrarAlerta("Por favor, ingrese horas en formato HH:mm", "Error de Formato de Hora", Alert.AlertType.ERROR);
                return;
            }

            if (horaInicio.isAfter(horaFin)) {
                mostrarAlerta("La hora de inicio debe ser anterior a la hora de fin.", "Error de Horario", Alert.AlertType.ERROR);
                return;
            }

            List<Horario> horarios = new ArrayList<>();
            agregarHorariosSeleccionados(horarios, horaInicio, horaFin);

            controladorPrincipal.crearInstalacion(nombre, aforo, costo, horarios);
            mostrarAlerta("Instalación agregada correctamente", "Éxito", Alert.AlertType.INFORMATION);
            limpiarCampos();

            notificarObservador();

        } catch (NumberFormatException e) {
            mostrarAlerta("Por favor, ingrese valores numéricos válidos para aforo y costo.", "Error de Entrada", Alert.AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Error al agregar la instalación: " + e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
    }

    private LocalTime validarHora(String hora) {
        try {
            return LocalTime.parse(hora);
        } catch (Exception e) {
            return null;
        }
    }

    private void agregarHorariosSeleccionados(List<Horario> horarios, LocalTime horaInicio, LocalTime horaFin) {
        if (chkLunes.isSelected()) horarios.add(new Horario(DiaSemana.LUNES, horaInicio, horaFin));
        if (chkMartes.isSelected()) horarios.add(new Horario(DiaSemana.MARTES, horaInicio, horaFin));
        if (chkMiercoles.isSelected()) horarios.add(new Horario(DiaSemana.MIERCOLES, horaInicio, horaFin));
        if (chkJueves.isSelected()) horarios.add(new Horario(DiaSemana.JUEVES, horaInicio, horaFin));
        if (chkViernes.isSelected()) horarios.add(new Horario(DiaSemana.VIERNES, horaInicio, horaFin));
        if (chkSabado.isSelected()) horarios.add(new Horario(DiaSemana.SABADO, horaInicio, horaFin));
        if (chkDomingo.isSelected()) horarios.add(new Horario(DiaSemana.DOMINGO, horaInicio, horaFin));
    }

    private void mostrarAlerta(String mensaje, String titulo, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        txtNombreInstalacion.clear();
        txtAforo.clear();
        txtCosto.clear();
        txtHoraInicio.clear();
        txtHoraFin.clear();
        chkLunes.setSelected(false);
        chkMartes.setSelected(false);
        chkMiercoles.setSelected(false);
        chkJueves.setSelected(false);
        chkViernes.setSelected(false);
        chkSabado.setSelected(false);
        chkDomingo.setSelected(false);
    }
}
