package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.modelo.*;
import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import co.edu.uniquindio.reservasuq.observador.Observador;
import co.edu.uniquindio.reservasuq.observador.VentanaObservable;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class ControladorPrincipal implements ServiciosReservasUQ {

    private static ControladorPrincipal INSTANCIA;
    private final ReservasUQ reservasUQ;

    private ControladorPrincipal() {
        reservasUQ = new ReservasUQ();
    }

    public static ControladorPrincipal getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new ControladorPrincipal();
        }
        return INSTANCIA;
    }

    @Override
    public Persona login(String correo, String contrasena) throws Exception {
        return reservasUQ.login(correo, contrasena);
    }

    @Override
    public void registrarPersona(String cedula, String nombre, TipoPersona tipoPersona, String email, String password) throws Exception {
        reservasUQ.registrarPersona(cedula, nombre, tipoPersona, email, password);
    }

    @Override
    public void crearInstalacion(String nombre, int aforo, float costo, List<Horario> horarios) {
        reservasUQ.crearInstalacion(nombre, aforo, costo, horarios);
    }

    @Override
    public Reserva crearReserva(String idInstalacion, String cedulaPersona, LocalDate diaReserva, String horaReserva) throws Exception {
        return reservasUQ.crearReserva(idInstalacion, cedulaPersona, diaReserva, horaReserva);
    }

    @Override
    public List<Reserva> listarTodasReservas() {
        return reservasUQ.listarTodasReservas();
    }

    @Override
    public List<Reserva> listarReservasPorPersona(String cedulaPersona) {
        return reservasUQ.listarReservasPorPersona(cedulaPersona);
    }

    @Override
    public void cancelarReserva(String idReserva) throws Exception {
        reservasUQ.cancelarReserva(idReserva);
    }

    @Override
    public ObservableList<Instalacion> listarInstalaciones() {
        return FXCollections.observableArrayList(reservasUQ.listarInstalaciones());
    }

    @Override
    public Instalacion buscarInstalacionPorId(String idInstalacion) throws Exception {
        return reservasUQ.buscarInstalacionPorId(idInstalacion);
    }

    @Override
    public void gestionarCapacidadInstalacion(String idInstalacion, int nuevaCapacidad) throws Exception {
        reservasUQ.gestionarCapacidadInstalacion(idInstalacion, nuevaCapacidad);
    }

    @Override
    public void asignarHorarioInstalacion(String idInstalacion, List<Horario> nuevosHorarios) throws Exception {
        reservasUQ.asignarHorarioInstalacion(idInstalacion, nuevosHorarios);
    }

    @Override
    public Instalacion buscarInstalacionPorNombre(String nombre) throws Exception {
        return reservasUQ.buscarInstalacionPorNombre(nombre);
    }

    @Override
    public List<Horario> listarHorariosDisponibles(String idInstalacion, LocalDate fecha) {
        return reservasUQ.listarHorariosDisponibles(idInstalacion, fecha);
    }

    // Métodos adicionales para manejo de la interfaz gráfica

    public void mostrarAlerta(String mensaje, String titulo, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void navegarVentana(String nombreArchivoFxml, String tituloVentana) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(tituloVentana);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navegarVentanaObservable(String nombreFxml, String titulo, Observador observador) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreFxml));
            Parent root = loader.load();

            VentanaObservable ventanaObservable = loader.getController();
            ventanaObservable.setObservador(observador);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(titulo);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cerrarVentana(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

}
