package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.modelo.enums.DiaSemana;
import co.edu.uniquindio.reservasuq.modelo.Horario;
import co.edu.uniquindio.reservasuq.modelo.Instalacion;
import co.edu.uniquindio.reservasuq.modelo.Sesion;
import co.edu.uniquindio.reservasuq.observador.Observador;
import co.edu.uniquindio.reservasuq.observador.VentanaObservable;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;
import co.edu.uniquindio.reservasuq.utils.EnvioEmail;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CrearReservaControlador extends VentanaObservable {

    @FXML
    private ComboBox<Instalacion> comboInstalacion;
    @FXML
    private DatePicker datePickerFecha;
    @FXML
    private ComboBox<LocalTime> comboHorario;
    @FXML
    private TextArea txtDetallesInstalacion;

    private final ControladorPrincipal controladorPrincipal;
    private final Sesion sesion;

    public CrearReservaControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
        this.sesion = Sesion.getInstancia();
    }

    @FXML
    public void initialize() {
        comboInstalacion.setConverter(new javafx.util.StringConverter<>() {
            @Override
            public String toString(Instalacion instalacion) {
                return instalacion != null ? instalacion.getNombre() : "";
            }

            @Override
            public Instalacion fromString(String nombre) {
                return comboInstalacion.getItems().stream()
                        .filter(instalacion -> instalacion.getNombre().equals(nombre))
                        .findFirst()
                        .orElse(null);
            }
        });

        cargarInstalaciones();
    }

    private void cargarInstalaciones() {
        List<Instalacion> instalaciones = controladorPrincipal.listarInstalaciones();
        comboInstalacion.setItems(FXCollections.observableArrayList(instalaciones));
    }

    @FXML
    public void mostrarDetallesInstalacion() {
        Instalacion instalacionSeleccionada = comboInstalacion.getValue();
        if (instalacionSeleccionada != null) {
            StringBuilder detalles = new StringBuilder();
            detalles.append("Aforo: ").append(instalacionSeleccionada.getAforo()).append("\n");
            detalles.append("Costo: ").append(instalacionSeleccionada.getCosto()).append("\n");
            detalles.append("Disponibilidad:\n");

            instalacionSeleccionada.getHorarios().forEach((dia, horarios) -> {
                detalles.append(dia).append(": ");
                horarios.forEach(horario -> detalles.append(horario.getHoraInicio())
                        .append(" - ").append(horario.getHoraFin()).append(", "));
                detalles.delete(detalles.length() - 2, detalles.length());
                detalles.append("\n");
            });

            txtDetallesInstalacion.setText(detalles.toString());
        } else {
            txtDetallesInstalacion.clear();
        }
    }

    @FXML
    public void actualizarHorarios() {
        Instalacion instalacionSeleccionada = comboInstalacion.getValue();
        LocalDate fechaSeleccionada = datePickerFecha.getValue();

        if (instalacionSeleccionada != null && fechaSeleccionada != null) {
            DiaSemana diaSemana = DiaSemana.fromLocalDate(fechaSeleccionada);
            List<Horario> horariosDisponibles = instalacionSeleccionada.getHorarios().get(diaSemana);
            List<LocalTime> bloquesHorario = new ArrayList<>();

            if (horariosDisponibles != null) {
                for (Horario horario : horariosDisponibles) {
                    LocalTime horaInicio = horario.getHoraInicio();
                    LocalTime horaFin = horario.getHoraFin();

                    while (horaInicio.isBefore(horaFin)) {
                        bloquesHorario.add(horaInicio);
                        horaInicio = horaInicio.plusMinutes(30);
                    }
                }
            }

            comboHorario.setItems(FXCollections.observableArrayList(bloquesHorario));
        }
    }

    @FXML
    public void reservarInstalacion() {
        try {
            Instalacion instalacion = comboInstalacion.getValue();
            LocalDate fecha = datePickerFecha.getValue();
            LocalTime hora = comboHorario.getValue();

            if (instalacion == null || fecha == null || hora == null) {
                mostrarAlerta("Seleccione todos los campos para reservar", "Error", Alert.AlertType.ERROR);
                return;
            }

            if (!fecha.isAfter(LocalDate.now().plusDays(1))) {
                mostrarAlerta("La reserva debe hacerse con al menos 2 días de anticipación", "Error", Alert.AlertType.ERROR);
                return;
            }

            ControladorPrincipal.getInstancia().crearReserva(instalacion.getId(), Sesion.getInstancia().getPersona().getCedula(), fecha, hora.toString());
            mostrarAlerta("Reserva realizada con éxito", "Éxito", Alert.AlertType.INFORMATION);

            controladorPrincipal.cerrarVentana(txtDetallesInstalacion);
            notificarObservador();

            String destinatario = sesion.getPersona().getEmail();
            String asunto = "Confirmación de Reserva";
            String mensaje = String.format(
                    "Estimado/a %s,\n\nSu reserva ha sido confirmada.\n\nDetalles:\nInstalación: %s\nFecha: %s\nHora: %s\n\nGracias por su preferencia.",
                    sesion.getPersona().getNombre(),
                    instalacion.getNombre(),
                    fecha,
                    hora
            );

            EnvioEmail.enviarNotificacion(destinatario, asunto, mensaje);

        } catch (Exception e) {
            mostrarAlerta(e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
    }


    private void mostrarAlerta(String mensaje, String titulo, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
