package co.edu.uniquindio.proyectofinal.servicios;


import co.edu.uniquindio.proyectofinal.modelos.*;
import co.edu.uniquindio.proyectofinal.modelos.enums.CiudadAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.ServicioAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.TipoAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.TipoUsuario;

import java.util.Date;
import java.util.List;


public interface ServicioEmpresa {

    void registrarUsuario(TipoUsuario tipoUsuario, long cedulaUsuario, String nombreUsuario, String emailUsuario, String contraseñaUsuario, long telefonoUsuario) throws Exception;

    void registrarCasa(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoCasa, double mantenimientoCasa) throws Exception;

    void registrarApartamento(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoApartamento, double mantenimientoApartamento) throws Exception;

    void registrarHotel(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento) throws Exception;

    void registrarHabitacion(Hotel hotel, int numeroHabitacion, int capacidadHabitacion, double precioHabitacion, String imagenHabitacion, String descripcionHabitacion) throws Exception;

    void registrarOferta(Alojamiento alojamiento, int idOferta, String descripcionOferta, float descuentoOferta, Date fechaInicioOferta, Date fechaFinOferta) throws Exception;

    void registrarReserva(Cliente clienteReserva, int idReserva, Alojamiento alojamientoReserva, int numHuespedesReserva, Date fechaInicioReserva, Date fechaFinReserva) throws Exception;

    void editarUsuario(long cedulaUsuario, String nombreUsuario, String emailUsuario, long telefonoUsuario) throws Exception;

    void editarCasa(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoCasa, double mantenimientoCasa) throws Exception;

    void editarApartamento(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoApartamento, double mantenimientoApartamento) throws Exception;

    void editarHotel(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento) throws Exception;

    void editarHabitacion(Hotel hotel, int numeroHabitacion, int capacidadHabitacion, double precioHabitacion, String imagenHabitacion, String descripcionHabitacion) throws Exception;

    void editarOferta(Alojamiento alojamiento, int idOferta, String descripcionOferta, float descuentoOferta, Date fechaInicioOferta, Date fechaFinOferta) throws Exception;

    void editarReserva(Cliente clienteReserva, int idReserva, Alojamiento alojamientoReserva, int numHuespedesReserva, Date fechaInicioReserva, Date fechaFinReserva) throws Exception;

    void eliminarUsuario(long cedulaUsuario) throws Exception;

    void eliminarAlojamiento(int direccionAlojamiento) throws Exception;

    void eliminarHabitacion(Hotel hotel, int numeroHabitacion) throws Exception;

    void eliminarOferta(Alojamiento alojamiento, int idOferta) throws Exception;

    void eliminarReserva(Cliente clienteReserva, int idReserva) throws Exception;

    void solicitarCambioContraseña(String emailUsuario) throws Exception;

    void cambiarContraseña(String codigoContraseña, String nuevaContraseña) throws Exception;

    void recargarBilletera(Cliente cliente, double saldoBilletera) throws Exception;

    int buscarUsuario(long cedulaUsuario);

    int buscarAlojamiento(int direccionAlojamiento);

    int buscarHabitacion(Hotel hotel, int numeroHabitacion);

    int buscarOferta(Alojamiento alojamiento, int idOferta);

    int buscarReserva(Cliente clienteReserva, int idReserva);

    boolean buscarAdministrador();

    boolean numeroValido(long numero);

    Usuario crearUsuario(TipoUsuario tipoUsuario, long cedulaUsuario, String nombreUsuario, String emailUsuario, String contraseñaUsuario, long telefonoUsuario) throws Exception;

    Usuario iniciarSesion(String emailUsuario, String codigoActivacion, String contraseñaUsuario) throws Exception;

    Usuario buscarUsuarioPorEmail(String emailUsuario);

    Usuario buscarUsuarioPorCodigoContraseña(String codigoContraseña);

    Alojamiento buscarAlojamientoPorNombre(String nombreAlojamiento);

    Casa crearCasa(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoCasa, double mantenimientoCasa);

    Apartamento crearApartamento(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoApartamento, double mantenimientoApartamento);

    Hotel crearHotel(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento);

    Habitacion crearHabitacion(int numeroHabitacion, int capacidadHabitacion, double precioHabitacion, String imagenHabitacion, String descripcionHabitacion);

    Oferta crearOferta(int idOferta, String descripcionOferta, float descuentoOferta, Date fechaInicioOferta, Date fechaFinOferta);

    Reserva crearReserva(Cliente clienteReserva, int idReserva, Alojamiento alojamientoReserva, int numHuespedesReserva, Date fechaInicioReserva, Date fechaFinReserva);

    Reseña crearReseña(int idReseña, String cometarioReseña, float calificacionReseña);

    Estadistica obtenerEstadisticas();

    Factura generarFactura() throws Exception;

    Qr generarQr() throws Exception;

    String generarCodigo();

    List<Alojamiento> filtrarAlojamientos(String nombreAlojamiento, TipoAlojamiento tipoAlojamiento, CiudadAlojamiento ciudadAlojamiento, double precioMin, double precioMax) throws Exception;

}
