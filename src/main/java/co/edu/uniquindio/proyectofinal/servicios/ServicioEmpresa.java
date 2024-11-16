package co.edu.uniquindio.proyectofinal.servicios;


import co.edu.uniquindio.proyectofinal.modelos.*;
import co.edu.uniquindio.proyectofinal.modelos.enums.CiudadAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.ServicioAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.TipoAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.TipoUsuario;

import java.time.LocalDateTime;
import java.util.List;


public interface ServicioEmpresa {

    void registrarUsuario(TipoUsuario tipoUsuario, long cedulaUsuario, String nombreUsuario, String emailUsuario, String contraseñaUsuario, long telefonoUsuario) throws Exception;
    void registrarCasa(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoCasa, double mantenimientoCasa) throws Exception;
    void registrarApartamento(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoApartamento, double mantenimientoApartamento) throws Exception;
    void registrarHotel(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento) throws Exception;
    void registrarHabitacion(Hotel hotel, int numeroHabitacion, int capacidadHabitacion, double precioHabitacion, String imagenHabitacion, String descripcionHabitacion) throws Exception;
    void registrarOferta(Alojamiento alojamiento, int idOferta, String descripcionOferta, float descuentoOferta, LocalDateTime fechaInicioOferta, LocalDateTime fechaFinOferta) throws Exception;
    void registrarReserva(Usuario clienteReserva, int idReserva, Alojamiento alojamientoReserva, int numHuespedesReserva, LocalDateTime fechaInicioReserva, LocalDateTime fechaFinReserva) throws Exception;
    void registrarReseña(Alojamiento alojamiento, int idReseña, String cometarioReseña, float calificacionReseña) throws Exception;

    Usuario iniciarSesion(String emailUsuario, String codigoActivacion, String contraseñaUsuario) throws Exception;
    void solicitarCambioContraseña(String emailUsuario) throws Exception;
    void cambiarContraseña(String codigoContraseña, String nuevaContraseña) throws Exception;

    void editarUsuario(long cedulaUsuario, String nombreUsuario, String emailUsuario, long telefonoUsuario) throws Exception;
    void editarCasa(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoCasa, double mantenimientoCasa) throws Exception;
    void editarApartamento(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoApartamento, double mantenimientoApartamento) throws Exception;
    void editarHotel(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento) throws Exception;
    void editarHabitacion(Hotel hotel, int numeroHabitacion, int capacidadHabitacion, double precioHabitacion, String imagenHabitacion, String descripcionHabitacion) throws Exception;
    void editarOferta(Alojamiento alojamiento, int idOferta, String descripcionOferta, float descuentoOferta, LocalDateTime fechaInicioOferta, LocalDateTime fechaFinOferta) throws Exception;
    void editarReseña(Alojamiento alojamiento, int idReseña, String cometarioReseña, float calificacionReseña) throws Exception;

    void editarReserva(Usuario clienteReserva, int idReserva, Alojamiento alojamientoReserva, int numHuespedesReserva, LocalDateTime fechaInicioReserva, LocalDateTime fechaFinReserva) throws Exception;

    void eliminarUsuario(long cedulaUsuario) throws Exception;
    void eliminarAlojamiento(int direccionAlojamiento) throws Exception;
    void eliminarHabitacion(Hotel hotel, int numeroHabitacion) throws Exception;
    void eliminarOferta(Alojamiento alojamiento, int idOferta) throws Exception;
    void eliminarReserva(Usuario clienteReserva, int idReserva) throws Exception;
    void eliminarReseña(Alojamiento alojamiento, int idReseña) throws  Exception;

    Usuario crearUsuario(TipoUsuario tipoUsuario, long cedulaUsuario, String nombreUsuario, String emailUsuario, String contraseñaUsuario, long telefonoUsuario) throws Exception;
    Casa crearCasa(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoCasa, double mantenimientoCasa);
    Apartamento crearApartamento(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoApartamento, double mantenimientoApartamento);
    Hotel crearHotel(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento);
    Habitacion crearHabitacion(int numeroHabitacion, int capacidadHabitacion, double precioHabitacion, String imagenHabitacion, String descripcionHabitacion);
    Oferta crearOferta(int idOferta, String descripcionOferta, float descuentoOferta, LocalDateTime fechaInicioOferta, LocalDateTime fechaFinOferta);
    Reserva crearReserva(Usuario clienteReserva, int idReserva, Alojamiento alojamientoReserva, int numHuespedesReserva, LocalDateTime fechaInicioReserva, LocalDateTime fechaFinReserva);
    Reseña crearReseña(int idReseña, String cometarioReseña, float calificacionReseña);

    int buscarUsuario(long cedulaUsuario);
    int buscarAlojamiento(int direccionAlojamiento);
    int buscarHabitacion(Hotel hotel, int numeroHabitacion);
    int buscarOferta(Alojamiento alojamiento, int idOferta);
    int buscarReserva(Usuario clienteReserva, int idReserva);
    int buscarReseña(Alojamiento alojamiento, int idReseña);
    boolean buscarAdministrador();
    Usuario buscarUsuarioPorEmail(String emailUsuario);
    Usuario buscarUsuarioPorCodigoContraseña(String codigoContraseña);
    Alojamiento buscarAlojamientoPorNombre(String nombreAlojamiento);

    void recargarBilletera(Usuario cliente, double saldoBilletera) throws Exception;
    boolean numeroValido(long numero);
    int calcularDiasReserva(LocalDateTime fechaInicioReserva, LocalDateTime fechaFinReserva);
    double calcularCostoReserva(Alojamiento alojamiento, LocalDateTime fechaInicioReserva, LocalDateTime fechaFinReserva);
    boolean fechasSeSuperponen(LocalDateTime fechaInicio1, LocalDateTime fechaFin1, LocalDateTime fechaInicio2, LocalDateTime fechaFin2);
    double obtenerDescuento(Alojamiento alojamiento, LocalDateTime fechaInicio, LocalDateTime fechaFin);
    double calcularDescuento(double descuentoOferta);
    boolean reservaPasada(Alojamiento alojamiento);

    String generarCodigo();
    Factura generarFactura(double subTotalFactura, double totalFactura);

    List<Alojamiento> filtrarAlojamientos(String nombreAlojamiento, TipoAlojamiento tipoAlojamiento, CiudadAlojamiento ciudadAlojamiento, double precioMin, double precioMax) throws Exception;

    //Estadistica obtenerEstadisticas();
    //Qr generarQr() throws Exception;

}
