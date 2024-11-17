package co.edu.uniquindio.proyectofinal.controladores;

import co.edu.uniquindio.proyectofinal.modelos.*;
import co.edu.uniquindio.proyectofinal.modelos.enums.CiudadAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.ServicioAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.TipoAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.TipoUsuario;
import co.edu.uniquindio.proyectofinal.servicios.ServicioEmpresa;
import co.edu.uniquindio.proyectofinal.observador.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class ControladorPrincipal implements ServicioEmpresa{

    private static ControladorPrincipal INSTANCIA;
    private final Empresa empresa;
    private final List<Observador> observadores = new ArrayList<>();


    private ControladorPrincipal() {
        empresa = new Empresa();
    }

    public static ControladorPrincipal getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new ControladorPrincipal();
        }
        return INSTANCIA;
    }


    public void registrarVentanaObservable(Observador observador) {
        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void notificarObservadores() {
        for (Observador observador : observadores) {
            observador.notificar();
        }
    }

    @Override
    public void registrarUsuario(TipoUsuario tipoUsuario, long cedulaUsuario, String nombreUsuario, String emailUsuario, String contraseñaUsuario, long telefonoUsuario) throws Exception {
        empresa.registrarUsuario(tipoUsuario,cedulaUsuario,nombreUsuario,emailUsuario,contraseñaUsuario, telefonoUsuario);
        notificarObservadores();
    }
    @Override
    public void registrarCasa(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoCasa, double mantenimientoCasa) throws Exception{
        empresa.registrarCasa(direccionAlojamiento,nombreAlojamiento,ciudadAlojamiento,descripcionAlojamiento,imagenAlojamiento,precioPorNocheAlojamiento,capacidadMaximaAlojamiento,servicioAlojamiento,aseoCasa,mantenimientoCasa);
    }
    @Override
    public void registrarApartamento(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoApartamento, double mantenimientoApartamento) throws Exception{
        empresa.registrarApartamento(direccionAlojamiento,nombreAlojamiento,ciudadAlojamiento,descripcionAlojamiento,imagenAlojamiento,precioPorNocheAlojamiento,capacidadMaximaAlojamiento,servicioAlojamiento,aseoApartamento,mantenimientoApartamento);
    }
    @Override
    public void registrarHotel(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento) throws Exception{
        empresa.registrarHotel(direccionAlojamiento,nombreAlojamiento,ciudadAlojamiento,descripcionAlojamiento,imagenAlojamiento,precioPorNocheAlojamiento,capacidadMaximaAlojamiento,servicioAlojamiento);
    }
    @Override
    public void registrarHabitacion(Hotel hotel, int numeroHabitacion, int capacidadHabitacion, double precioHabitacion, String imagenHabitacion, String descripcionHabitacion) throws Exception{
        empresa.registrarHabitacion(hotel,numeroHabitacion,capacidadHabitacion,precioHabitacion,imagenHabitacion,descripcionHabitacion);
    }
    @Override
    public void registrarOferta(Alojamiento alojamiento, int idOferta, String descripcionOferta, float descuentoOferta, LocalDateTime fechaInicioOferta, LocalDateTime fechaFinOferta) throws Exception{
        empresa.registrarOferta(alojamiento,idOferta,descripcionOferta,descuentoOferta,fechaInicioOferta,fechaFinOferta);
    }
    @Override
    public void registrarReserva(Usuario clienteReserva, int idReserva, Alojamiento alojamientoReserva, int numHuespedesReserva, LocalDateTime fechaInicioReserva, LocalDateTime fechaFinReserva) throws Exception{
        empresa.registrarReserva(clienteReserva,idReserva,alojamientoReserva,numHuespedesReserva,fechaInicioReserva,fechaFinReserva);
    }
    @Override
    public void registrarReseña(Alojamiento alojamiento, int idReseña, String cometarioReseña, float calificacionReseña) throws Exception {
        empresa.registrarReseña(alojamiento,idReseña,cometarioReseña,calificacionReseña);
    }



    @Override
    public Usuario iniciarSesion(String emailUsuario, String codigoActivacion, String contraseñaUsuario) throws Exception {
        return empresa.iniciarSesion(emailUsuario, codigoActivacion, contraseñaUsuario);
    }
    @Override
    public void solicitarCambioContraseña(String emailUsuario) throws Exception{
        empresa.solicitarCambioContraseña(emailUsuario);
    }
    @Override
    public void cambiarContraseña(String codigoContraseña, String nuevaContraseña) throws Exception{
        empresa.cambiarContraseña(codigoContraseña,nuevaContraseña);
    }


    @Override
    public void editarUsuario(long cedulaUsuario, String nombreUsuario, String emailUsuario, long telefonoUsuario) throws Exception{
        empresa.editarUsuario(cedulaUsuario,nombreUsuario,emailUsuario,telefonoUsuario);
    }
    @Override
    public void editarCasa(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoCasa, double mantenimientoCasa) throws Exception{
        empresa.editarCasa(direccionAlojamiento,nombreAlojamiento,ciudadAlojamiento,descripcionAlojamiento,imagenAlojamiento,precioPorNocheAlojamiento,capacidadMaximaAlojamiento,servicioAlojamiento,aseoCasa,mantenimientoCasa);
    }
    @Override
    public void editarApartamento(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoApartamento, double mantenimientoApartamento) throws Exception{
        empresa.editarApartamento(direccionAlojamiento,nombreAlojamiento,ciudadAlojamiento,descripcionAlojamiento,imagenAlojamiento,precioPorNocheAlojamiento,capacidadMaximaAlojamiento,servicioAlojamiento,aseoApartamento,mantenimientoApartamento);
    }
    @Override
    public void editarHotel(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento) throws Exception{
        empresa.editarHotel(direccionAlojamiento,nombreAlojamiento,ciudadAlojamiento,descripcionAlojamiento,imagenAlojamiento,precioPorNocheAlojamiento,capacidadMaximaAlojamiento,servicioAlojamiento);
    }
    @Override
    public void editarHabitacion(Hotel hotel, int numeroHabitacion, int capacidadHabitacion, double precioHabitacion, String imagenHabitacion, String descripcionHabitacion) throws Exception{
        empresa.editarHabitacion(hotel,numeroHabitacion,capacidadHabitacion,precioHabitacion,imagenHabitacion,descripcionHabitacion);
    }
    @Override
    public void editarOferta(Alojamiento alojamiento, int idOferta, String descripcionOferta, float descuentoOferta, LocalDateTime fechaInicioOferta, LocalDateTime fechaFinOferta) throws Exception{
        empresa.editarOferta(alojamiento,idOferta,descripcionOferta,descuentoOferta,fechaInicioOferta,fechaFinOferta);
    }
    @Override
    public void editarReseña(Alojamiento alojamiento, int idReseña, String cometarioReseña, float calificacionReseña) throws Exception{
        empresa.editarReseña(alojamiento, idReseña, cometarioReseña, calificacionReseña);
    }


    @Override
    public void eliminarUsuario(long cedulaUsuario) throws Exception{
        empresa.eliminarUsuario(cedulaUsuario);
    }
    @Override
    public void eliminarAlojamiento(int direccionAlojamiento) throws Exception{
        empresa.eliminarAlojamiento(direccionAlojamiento);
    }
    @Override
    public void eliminarHabitacion(Hotel hotel, int numeroHabitacion) throws Exception{
        empresa.eliminarHabitacion(hotel,numeroHabitacion);
    }
    @Override
    public void eliminarOferta(Alojamiento alojamiento, int idOferta) throws Exception{
        empresa.eliminarOferta(alojamiento,idOferta);
    }
    @Override
    public void eliminarReserva(Usuario clienteReserva, int idReserva) throws Exception{
        empresa.eliminarReserva(clienteReserva,idReserva);
        notificarObservadores();
    }
    @Override
    public void eliminarReseña(Alojamiento alojamiento, int idReseña) throws  Exception{
        empresa.eliminarReseña(alojamiento,idReseña);
    }


    @Override
    public int buscarUsuario(long cedulaUsuario) {
        return empresa.buscarUsuario(cedulaUsuario);
    }
    @Override
    public int buscarAlojamiento(int direccionAlojamiento) {
        return empresa.buscarAlojamiento(direccionAlojamiento);
    }
    @Override
    public int buscarHabitacion(Hotel hotel, int numeroHabitacion) {
        return empresa.buscarHabitacion(hotel, numeroHabitacion);
    }
    @Override
    public int buscarOferta(Alojamiento alojamiento, int idOferta) {
        return empresa.buscarOferta(alojamiento, idOferta);
    }
    @Override
    public int buscarReserva(Usuario clienteReserva, int idReserva) {
        return empresa.buscarReserva(clienteReserva, idReserva);
    }
    @Override
    public int buscarReseña(Alojamiento alojamiento, int idReseña){
        return empresa.buscarReseña(alojamiento,idReseña);
    }

    @Override
    public boolean buscarAdministrador() {
        return empresa.buscarAdministrador();
    }
    @Override
    public Usuario buscarUsuarioPorEmail(String emailUsuario) {
        return empresa.buscarUsuarioPorEmail(emailUsuario);
    }
    @Override
    public Usuario buscarUsuarioPorCodigoContraseña(String codigoContraseña) {
        return empresa.buscarUsuarioPorCodigoContraseña(codigoContraseña);
    }
    @Override
    public Alojamiento buscarAlojamientoPorNombre(String nombreAlojamiento) {
        return empresa.buscarAlojamientoPorNombre(nombreAlojamiento);
    }


    @Override
    public Usuario crearUsuario(TipoUsuario tipoUsuario, long cedulaUsuario, String nombreUsuario, String emailUsuario, String contraseñaUsuario, long telefonoUsuario) throws Exception {
        return empresa.crearUsuario(tipoUsuario, cedulaUsuario, nombreUsuario, emailUsuario, contraseñaUsuario, telefonoUsuario);
    }
    @Override
    public Casa crearCasa(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoCasa, double mantenimientoCasa) {
        return empresa.crearCasa(direccionAlojamiento, nombreAlojamiento, ciudadAlojamiento, descripcionAlojamiento, imagenAlojamiento, precioPorNocheAlojamiento, capacidadMaximaAlojamiento, servicioAlojamiento, aseoCasa, mantenimientoCasa);
    }
    @Override
    public Apartamento crearApartamento(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoApartamento, double mantenimientoApartamento) {
        return empresa.crearApartamento(direccionAlojamiento, nombreAlojamiento, ciudadAlojamiento, descripcionAlojamiento, imagenAlojamiento, precioPorNocheAlojamiento, capacidadMaximaAlojamiento, servicioAlojamiento, aseoApartamento, mantenimientoApartamento);
    }
    @Override
    public Hotel crearHotel(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento) {
        return empresa.crearHotel(direccionAlojamiento, nombreAlojamiento, ciudadAlojamiento, descripcionAlojamiento, imagenAlojamiento, precioPorNocheAlojamiento, capacidadMaximaAlojamiento, servicioAlojamiento);
    }
    @Override
    public Habitacion crearHabitacion(int numeroHabitacion, int capacidadHabitacion, double precioHabitacion, String imagenHabitacion, String descripcionHabitacion) {
        return empresa.crearHabitacion(numeroHabitacion, capacidadHabitacion, precioHabitacion, imagenHabitacion, descripcionHabitacion);
    }
    @Override
    public Oferta crearOferta(int idOferta, String descripcionOferta, float descuentoOferta, LocalDateTime fechaInicioOferta, LocalDateTime fechaFinOferta) {
        return empresa.crearOferta(idOferta, descripcionOferta, descuentoOferta, fechaInicioOferta, fechaFinOferta);
    }
    @Override
    public Reserva crearReserva(Usuario clienteReserva, int idReserva, Alojamiento alojamientoReserva, int numHuespedesReserva, LocalDateTime fechaInicioReserva, LocalDateTime fechaFinReserva) {
        return empresa.crearReserva(clienteReserva, idReserva, alojamientoReserva, numHuespedesReserva, fechaInicioReserva, fechaFinReserva);
    }
    @Override
    public Reseña crearReseña(int idReseña, String cometarioReseña, float calificacionReseña) {
        return empresa.crearReseña(idReseña, cometarioReseña, calificacionReseña);
    }



    @Override
    public void recargarBilletera(Usuario cliente, double monto) throws Exception {
        empresa.recargarBilletera(cliente, monto);
    }
    @Override
    public boolean numeroValido(long numero) {
        return empresa.numeroValido(numero);
    }

    @Override
    public String generarCodigo() {
        return empresa.generarCodigo();
    }
    @Override
    public List<Alojamiento> filtrarAlojamientos(String nombreAlojamiento, TipoAlojamiento tipoAlojamiento, CiudadAlojamiento ciudadAlojamiento, double precioMin, double precioMax) throws Exception {
        return empresa.filtrarAlojamientos(nombreAlojamiento, tipoAlojamiento, ciudadAlojamiento, precioMin, precioMax);
    }

    @Override
    public List<Alojamiento> obtenerAlojamientos() {
        return List.of();
    }

    @Override
    public List<Alojamiento> obtenerAlojamientosPopulares() {
        return List.of();
    }

    @Override
    public List<Alojamiento> obtenerAlojamientosRentables() {
        return List.of();
    }

    @Override
    public List<Alojamiento> getAlojamientos() {
        return List.of();
    }

    @Override
    public int calcularDiasReserva(LocalDateTime fechaInicioReserva, LocalDateTime fechaFinReserva){
        return empresa.calcularDiasReserva(fechaInicioReserva, fechaFinReserva);
    }

    @Override
    public double calcularCostoReserva(Alojamiento alojamiento, LocalDateTime fechaInicioReserva, LocalDateTime fechaFinReserva){
        return empresa.calcularCostoReserva(alojamiento,fechaInicioReserva,fechaFinReserva);
    }
    @Override
    public double obtenerDescuento(Alojamiento alojamiento, LocalDateTime fechaInicio, LocalDateTime fechaFin){
        return empresa.obtenerDescuento(alojamiento,fechaInicio,fechaFin);
    }

    @Override
    public boolean fechasSeSuperponen(LocalDateTime inicio1, LocalDateTime fin1, LocalDateTime inicio2, LocalDateTime fin2){
        return empresa.fechasSeSuperponen(inicio1,fin1,inicio2,fin2);
    }

    @Override
    public  boolean reservaPasada(Alojamiento alojamiento){
        return empresa.reservaPasada(alojamiento);
    }
    @Override
    public  double calcularDescuento(double descuentoOferta){
        return empresa.calcularDescuento(descuentoOferta);
    }

    @Override
    public Factura generarFactura(double subTotalFactura, double totalFactura){
        return empresa.generarFactura(subTotalFactura,totalFactura);
    }

    @Override
    public void desontarSaldo(Usuario cliente, double totalFactura){
        empresa.desontarSaldo(cliente, totalFactura);
    }

    @Override
    public Estadistica crearEstadistica(double ocupacionPorcentual, double gananciasTotales){
        return empresa.crearEstadistica(ocupacionPorcentual, gananciasTotales);
    }



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

    public int generarCodigoReserva() {

        int maxId = 0;
        for (Usuario usuario : empresa.getUsuarios()) {
            if (usuario instanceof Cliente cliente) {
                for (Reserva reserva : cliente.getReservas()) {
                    if (reserva.getIdReserva() > maxId) {
                        maxId = reserva.getIdReserva();
                    }
                }
            }
        }
        return maxId + 1;
    }

    public int generarCodigoReseña() {
        return (int) (Math.random() * 9000) + 1000;
    }

    public List<Oferta> obtenerOfertasActivas() {
        return empresa.obtenerOfertasActivas();
    }

    public List<Alojamiento> obtenerAlojamientosAleatorios(int limite) {
        return empresa.obtenerAlojamientosAleatorios(limite);
    }

    public List<Estadistica> obtenerEstadisticas() throws Exception {
            return empresa.obtenerEstadisticas();
    }

    public List<Alojamiento> alojamientosPopulares() throws Exception {
            return empresa.alojamientosPopulares();
    }

    public List<Alojamiento> alojamientosRentables() throws Exception {
            return empresa.alojamientosRentables();
    }

    public void navegarVentanaObservableConAlojamiento(String nombreFxml, String titulo, Alojamiento alojamiento, Observador observador) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreFxml));
            Parent root = loader.load();

            RealizarReservaControlador realizarReservaControlador = loader.getController();
            realizarReservaControlador.setAlojamientoSeleccionado(alojamiento);
            realizarReservaControlador.setObservador(observador);

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
}
