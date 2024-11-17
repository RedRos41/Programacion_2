package co.edu.uniquindio.proyectofinal.modelos;


import co.edu.uniquindio.proyectofinal.modelos.enums.CiudadAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.ServicioAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.TipoAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.TipoUsuario;
import co.edu.uniquindio.proyectofinal.servicios.ServicioEmpresa;
import co.edu.uniquindio.proyectofinal.utils.EnvioEmail;
import co.edu.uniquindio.proyectofinal.utils.Qr;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;


@Getter
public class Empresa implements ServicioEmpresa {

    private final List<Usuario> usuarios;
    private final List<Alojamiento> alojamientos;
    private final List<Alojamiento> filtroAlojamiento;
    private final List<BarChart.Data<String, Number>> estadisticaAlojamiento;
    private final List<Alojamiento> alojamientoPopular;
    private final List<PieChart.Data> alojamientoRentable;


    public Empresa() {

        this.usuarios = new ArrayList<>();
        this.alojamientos = new ArrayList<>();
        this.filtroAlojamiento = new ArrayList<>();
        this.estadisticaAlojamiento = new ArrayList<>();
        this.alojamientoPopular = new ArrayList<>();
        this.alojamientoRentable = new ArrayList<>();


        inicializarAlojamientos();

        //CUENTAS DE PRUEBAS, YA AL TERMINAR *BORRARLAS*
        try {
            Usuario admin = crearUsuario(
                    TipoUsuario.ADMINISTRADOR,
                    1111111111,
                    "Admin",
                    "bb@bb.co",
                    "12345678",
                    1111111111
            );
            admin.setUsuarioActivado(true);
            usuarios.add(admin);

            Usuario cliente = crearUsuario(
                    TipoUsuario.CLIENTE,
                    1222222222,
                    "ClientePrueba",
                    "testemailuq01@gmail.com",
                    "cliente123",
                    1222222222
            );
            cliente.setUsuarioActivado(true);

            if (cliente instanceof Cliente clienteEspecifico) {
                clienteEspecifico.getBilleteraCliente().setSaldoBilletera(500000.0); // saldo inicial
            }
            usuarios.add(cliente);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void inicializarAlojamientos() {
        try {
            registrarCasa(
                    100, "Casa Bella", CiudadAlojamiento.ARMENIA, "Casa amplia y cómoda",
                    "casa_bella.jpg", 200.0, 5, ServicioAlojamiento.WIFI, 30.0, 50.0);

            registrarApartamento(
                    101, "Apartamento Moderno", CiudadAlojamiento.PEREIRA, "Moderno y bien ubicado",
                    "apto_moderno.jpg", 150.0, 4, ServicioAlojamiento.DESAYUNO, 20.0, 40.0);

            registrarHotel(
                    102, "Hotel Premium", CiudadAlojamiento.CALARCA, "Hotel de alta categoría",
                    "hotel_premium.jpg", 300.0, 20, ServicioAlojamiento.PISCINA);

        } catch (Exception e) {
            System.out.println("Error al inicializar alojamientos: " + e.getMessage());
        }
    }
//---


    @Override
    public void registrarUsuario(TipoUsuario tipoUsuario, long cedulaUsuario, String nombreUsuario, String emailUsuario, String contraseñaUsuario, long telefonoUsuario) throws Exception {

        if (tipoUsuario == null) {

            throw new Exception("El tipo de usuario no puede estar vacio.");

        }

        if (tipoUsuario == TipoUsuario.ADMINISTRADOR && buscarAdministrador()) {

            throw new Exception("Ya existe un administrador registrado.");

        }

        if (!numeroValido(cedulaUsuario)) {

            throw new Exception("La cedula debe tener exactamente 10 digitos numericos.");

        }

        if (buscarUsuario(cedulaUsuario) != -1) {

            throw new Exception("Ya existe un usuario con la cédula proporcionada.");

        }

        if (nombreUsuario.isBlank()) {

            throw new Exception("El nombre no puede estar vacio.");

        }

        if (!nombreUsuario.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {

            throw new Exception("El nombre solo puede contener letras.");

        }

        if (emailUsuario.isBlank()) {

            throw new Exception("El email no puede estar vacio.");

        }

        if (!emailUsuario.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {

            throw new Exception("El email debe tener el formato de correo electronico.");

        }

        if (contraseñaUsuario.isBlank()) {

            throw new Exception("La contraseña no puede estar vacia.");

        }

        if (contraseñaUsuario.length() < 8) {

            throw new Exception("La contraseña debe tener al menos 8 caracteres.");

        }

        if (!numeroValido(telefonoUsuario)) {

            throw new Exception("El telefono debe tener exactamente 10 digitos numericos.");

        }

        Usuario usuario = crearUsuario(tipoUsuario, cedulaUsuario, nombreUsuario, emailUsuario, contraseñaUsuario, telefonoUsuario);

        usuarios.add(usuario);

        String codigoActivacion = generarCodigo();

        usuario.setCodigoActivacion(codigoActivacion);

        try {

            EnvioEmail.enviarCorreo(emailUsuario, "Código de Activación", "Su código de activación es: " + codigoActivacion, null);

        }catch (Exception e) {

            throw new Exception("Error al enviar el codigo de activacion. Por favor, intente nuevamente.");

        }

    }


    @Override
    public void registrarCasa(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoCasa, double mantenimientoCasa) throws Exception {

        if (direccionAlojamiento <= 0) {

            throw new Exception("La direccion solo puede tener numeros positivos.");

        }

        if (buscarAlojamiento(direccionAlojamiento) != -1) {

            throw new Exception("Ya existe un alojamiento con la direccion proporcionada.");

        }

        if (nombreAlojamiento.isBlank()) {

            throw new Exception("El nombre no puede estar vacio.");

        }

        if (!nombreAlojamiento.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {

            throw new Exception("El nombre solo puede contener letras.");

        }

        if (buscarAlojamientoPorNombre(nombreAlojamiento) != null) {

            throw new Exception("Ya existe un alojamiento con el nombre proporcionado.");

        }

        if (ciudadAlojamiento == null) {

            throw new Exception("La ciudad no puede estar vacia.");

        }

        if (descripcionAlojamiento.isBlank()) {

            throw new Exception("La descripcion no puede estar vacio.");

        }

        if (precioPorNocheAlojamiento <= 0) {

            throw new Exception("El precio por noche solo puede tener numeros positivos.");

        }

        if (capacidadMaximaAlojamiento <= 0) {

            throw new Exception("La capacidad maxima solo puede tener numeros positivos.");

        }

        if (servicioAlojamiento == null) {

            throw new Exception("El servicio no puede estar vacio.");

        }

        if (aseoCasa <= 0) {

            throw new Exception("El aseo de la casa solo puede tener numeros positivos.");

        }

        if (mantenimientoCasa <= 0) {

            throw new Exception("El mantenimiento de la casa solo puede tener numeros positivos.");

        }

        Casa casa = crearCasa(direccionAlojamiento, nombreAlojamiento, ciudadAlojamiento, descripcionAlojamiento, imagenAlojamiento, precioPorNocheAlojamiento, capacidadMaximaAlojamiento, servicioAlojamiento, aseoCasa, mantenimientoCasa);

        alojamientos.add(casa);

    }


    @Override
    public void registrarApartamento(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoApartamento, double mantenimientoApartamento) throws Exception {

        if (direccionAlojamiento <= 0) {

            throw new Exception("La direccion solo puede tener numeros positivos.");

        }

        if (buscarAlojamiento(direccionAlojamiento) != -1) {

            throw new Exception("Ya existe un alojamiento con la direccion proporcionada.");

        }

        if (nombreAlojamiento.isBlank()) {

            throw new Exception("El nombre no puede estar vacio.");

        }

        if (!nombreAlojamiento.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {

            throw new Exception("El nombre solo puede contener letras.");

        }

        if (buscarAlojamientoPorNombre(nombreAlojamiento) != null) {

            throw new Exception("Ya existe un alojamiento con el nombre proporcionado.");

        }

        if (ciudadAlojamiento == null) {

            throw new Exception("La ciudad no puede estar vacia.");

        }

        if (descripcionAlojamiento.isBlank()) {

            throw new Exception("La descripcion no puede estar vacio.");

        }

        if (precioPorNocheAlojamiento <= 0) {

            throw new Exception("El precio por noche solo puede tener numeros positivos.");

        }

        if (capacidadMaximaAlojamiento <= 0) {

            throw new Exception("La capacidad maxima solo puede tener numeros positivos.");

        }

        if (servicioAlojamiento == null) {

            throw new Exception("El servicio no puede estar vacio.");

        }

        if (aseoApartamento <= 0) {

            throw new Exception("El aseo del apartamento solo puede tener numeros positivos.");

        }

        if (mantenimientoApartamento <= 0) {

            throw new Exception("El mantenimiento del apartamento solo puede tener numeros positivos.");

        }

        Apartamento apartamento = crearApartamento(direccionAlojamiento, nombreAlojamiento, ciudadAlojamiento, descripcionAlojamiento, imagenAlojamiento, precioPorNocheAlojamiento, capacidadMaximaAlojamiento, servicioAlojamiento, aseoApartamento, mantenimientoApartamento);

        alojamientos.add(apartamento);

    }


    @Override
    public void registrarHotel(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento) throws Exception {

        if (direccionAlojamiento <= 0) {

            throw new Exception("La direccion solo puede tener numeros positivos.");

        }

        if (buscarAlojamiento(direccionAlojamiento) != -1) {

            throw new Exception("Ya existe un alojamiento con la direccion proporcionada.");

        }

        if (nombreAlojamiento.isBlank()) {

            throw new Exception("El nombre no puede estar vacio.");

        }

        if (!nombreAlojamiento.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {

            throw new Exception("El nombre solo puede contener letras.");

        }

        if (buscarAlojamientoPorNombre(nombreAlojamiento) != null) {

            throw new Exception("Ya existe un alojamiento con el nombre proporcionado.");

        }

        if (ciudadAlojamiento == null) {

            throw new Exception("La ciudad no puede estar vacia.");

        }

        if (descripcionAlojamiento.isBlank()) {

            throw new Exception("La descripcion no puede estar vacio.");

        }

        if (precioPorNocheAlojamiento <= 0) {

            throw new Exception("El precio por noche solo puede tener numeros positivos.");

        }

        if (capacidadMaximaAlojamiento <= 0) {

            throw new Exception("La capacidad maxima solo puede tener numeros positivos.");

        }

        if (servicioAlojamiento == null) {

            throw new Exception("El servicio no puede estar vacio.");

        }

        Hotel hotel = crearHotel(direccionAlojamiento, nombreAlojamiento, ciudadAlojamiento, descripcionAlojamiento, imagenAlojamiento, precioPorNocheAlojamiento, capacidadMaximaAlojamiento, servicioAlojamiento);

        alojamientos.add(hotel);

    }


    @Override
    public void registrarHabitacion(Hotel hotel, int numeroHabitacion, int capacidadHabitacion, double precioHabitacion, String imagenHabitacion, String descripcionHabitacion) throws Exception {

        if (hotel == null) {

            throw new Exception("El hotel no puede estar vacio.");

        }

        if (numeroHabitacion <= 0) {

            throw new Exception("El numero de la habitacion solo puede tener numeros positivos.");

        }

        if (buscarHabitacion(hotel, numeroHabitacion) != -1) {

            throw new Exception("Ya existe una habitacion con la numero proporcionada.");

        }

        if (capacidadHabitacion <= 0) {

            throw new Exception("La capacidad de la habitacion solo puede tener numeros positivos.");

        }

        if (precioHabitacion <= 0) {

            throw new Exception("El precio de la habitacion solo puede tener numeros positivos.");

        }

        if (descripcionHabitacion.isBlank()) {

            throw new Exception("La descripcion de la habitacion no puede estar vacio.");

        }

        Habitacion habitacion = crearHabitacion(numeroHabitacion, capacidadHabitacion, precioHabitacion, imagenHabitacion, descripcionHabitacion);

        hotel.getHabitaciones().add(habitacion);

    }


    @Override
    public void registrarOferta(Alojamiento alojamiento, int idOferta, String descripcionOferta, float descuentoOferta, LocalDateTime fechaInicioOferta, LocalDateTime fechaFinOferta) throws Exception {

        if (alojamiento == null) {

            throw new Exception("El alojamiento no puede estar vacio.");

        }

        if (idOferta <= 0) {

            throw new Exception("La id de la oferta solo puede tener numeros positivos.");

        }

        if (buscarOferta(alojamiento, idOferta) != -1) {

            throw new Exception("Ya existe una oferta con la id proporcionada.");

        }

        if (descripcionOferta.isBlank()) {

            throw new Exception("La descripcion no puede estar vacio.");

        }

        if (descuentoOferta <= 0) {

            throw new Exception("El descuento de la oferta solo puede tener numeros positivos.");

        }

        if (fechaInicioOferta == null) {

            throw new Exception("La fecha de inicio no puede estar vacio.");

        }

        if (fechaInicioOferta.isBefore(LocalDateTime.now())) {

            throw new Exception("La fecha de inicio no puede estar en el pasado.");

        }

        if (fechaFinOferta == null) {

            throw new Exception("La fecha de fin no puede estar vacio.");

        }

        if (fechaFinOferta.isBefore(fechaInicioOferta)) {

            throw new Exception("La fecha de fin no puede estar antes de la fecha de inicio.");

        }

        Oferta oferta = crearOferta(idOferta, descripcionOferta, descuentoOferta, fechaInicioOferta, fechaFinOferta);

        alojamiento.getOfertas().add(oferta);

    }


    @Override
    public void registrarReserva(Usuario clienteReserva, int idReserva, Alojamiento alojamientoReserva, int numHuespedesReserva, LocalDateTime fechaInicioReserva, LocalDateTime fechaFinReserva) throws Exception {

        if (clienteReserva == null) {

            throw new Exception("El cliente no puede estar vacio.");

        }

        if (!(clienteReserva instanceof Cliente)) {

            throw new Exception("El usuario proporcionado no es un cliente");

        }

        if (idReserva <= 0) {

            throw new Exception("La id de la reserva solo puede tener numeros positivos.");

        }

        if (buscarReserva(clienteReserva, idReserva) != -1) {

            throw new Exception("Ya existe una reserva con la id proporcionada.");

        }

        if (alojamientoReserva == null) {

            throw new Exception("El alojamiento no puede estar vacio.");

        }

        if (numHuespedesReserva <= 0) {

            throw new Exception("El numero de huespedes solo puede tener numeros positivos.");

        }

        if (numHuespedesReserva > alojamientoReserva.getCapacidadMaximaAlojamiento()) {

            throw new Exception("El numero de huespedes no puede exceder la capacidad maxima.");

        }

        if (fechaInicioReserva == null) {

            throw new Exception("La fecha de inicio no puede estar vacio.");

        }

        if (fechaInicioReserva.isBefore(LocalDateTime.now())) {

            throw new Exception("La fecha de inicio no puede estar en el pasado.");

        }

        if (fechaFinReserva == null) {

            throw new Exception("La fecha de fin no puede estar vacio.");

        }

        if (fechaFinReserva.isBefore(fechaInicioReserva)) {

            throw new Exception("La fecha de fin no puede estar antes de la fecha de inicio.");

        }

        for (Reserva reservaExistente : alojamientoReserva.getReservasAlojamiento()) {

            if (fechasSeSuperponen(fechaInicioReserva, fechaFinReserva, reservaExistente.getFechaInicioReserva(), reservaExistente.getFechaFinReserva())) {

                throw new Exception("Las fechas proporcionadas se superponen con una reserva existente.");

            }

        }

        double saldoActual = ((Cliente) clienteReserva).getBilleteraCliente().getSaldoBilletera();

        double costoReserva = calcularCostoReserva(alojamientoReserva, fechaInicioReserva, fechaFinReserva);

        if (saldoActual < costoReserva) {

            throw new Exception("El saldo actual no puede ser menor al costo de la reserva.");

        }

        Reserva reserva = crearReserva(clienteReserva, idReserva, alojamientoReserva, numHuespedesReserva, fechaInicioReserva, fechaFinReserva);

        ((Cliente) clienteReserva).getReservas().add(reserva);

        alojamientoReserva.getReservasAlojamiento().add(reserva);

        double descuentoOferta = obtenerDescuento(alojamientoReserva, fechaInicioReserva, fechaFinReserva);

        double calculoDescuento = calcularDescuento(descuentoOferta);

        double totalFactura = costoReserva * calculoDescuento;

        desontarSaldo(clienteReserva, totalFactura);

        Factura factura = generarFactura(costoReserva, totalFactura);

        String codigoFactura = factura.getCodigoFactura();

        String rutaQr = "Qr_" + codigoFactura + ".png";

        Qr.generarQr("Código de la factura: " + codigoFactura + "\n" +
                "Detalles de la reserva:\n" +
                " - Código de Reserva: " + reserva.getIdReserva() + "\n" +
                " - Cliente: " + clienteReserva.getNombreUsuario() + " - " + clienteReserva.getCedulaUsuario() + "\n" +
                " - Alojamiento: " + alojamientoReserva.getNombreAlojamiento() + " - " + alojamientoReserva.getDireccionAlojamiento() + "\n" +
                " - Numero de Huespedes: " + reserva.getNumHuespedesReserva() + "\n" +
                " - Fecha de Inicio: " + reserva.getFechaInicioReserva() + "\n" +
                " - Fecha de Fin: " + reserva.getFechaFinReserva() + "\n",
                rutaQr);

        String emailUsuario = clienteReserva.getEmailUsuario();

        try {

            EnvioEmail.enviarCorreo(emailUsuario, "Factura de la Reserva", "Su factura de la reserva es: ", rutaQr);

        }catch (Exception e) {

            throw new Exception("Error al enviar la factura de la reserva. Por favor, intente nuevamente.");

        }

    }


    @Override
    public void registrarReseña(Alojamiento alojamiento, int idReseña, String cometarioReseña, float calificacionReseña) throws Exception {

        if (alojamiento == null) {

            throw new Exception("El cliente no puede estar vacio.");

        }

        if (idReseña <= 0) {

            throw new Exception("La id de la reseña solo puede tener numeros positivos.");

        }

        if (buscarReseña(alojamiento, idReseña) != -1) {

            throw new Exception("Ya existe una reseña con la id proporcionada.");

        }

        if (cometarioReseña.isBlank()) {

            throw new Exception("El cometario no puede estar vacio.");

        }

        if (calificacionReseña < 1 || calificacionReseña > 5) {

            throw new Exception("La calificacion debe estar entre 1 y 5.");

        }

        if (!reservaPasada(alojamiento)) {

            throw new Exception("La reseña solo puede ser creada después de finalizar la reserva.");

        }

        Reseña reseña = crearReseña(idReseña, cometarioReseña, calificacionReseña);

        alojamiento.getReseñas().add(reseña);

    }


    @Override
    public Usuario iniciarSesion(String emailUsuario, String codigoActivacion, String contraseñaUsuario) throws Exception {

        if (emailUsuario.isBlank()) {

            throw new Exception("El email no puede estar vacio.");

        }

        if (!emailUsuario.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {

            throw new Exception("El email debe tener el formato de correo electronico.");

        }

        Usuario usuario = buscarUsuarioPorEmail(emailUsuario);

        if (usuario == null) {

            throw new Exception("No existe un usuario con el email proporcionado.");

        }

        if (usuario.isUsuarioActivado()) {

            if (contraseñaUsuario.isBlank()) {

                throw new Exception("La contraseña no puede estar vacia.");

            }

            if (contraseñaUsuario.length() < 8) {

                throw new Exception("La contraseña debe tener al menos 8 caracteres.");

            }

            if (!usuario.getContraseñaUsuario().equals(contraseñaUsuario)) {

                throw new Exception("La contraseña es incorrecta.");

            }

        } else {

            if (codigoActivacion.isBlank()) {

                throw new Exception("El codigo de activacion no puede estar vacio.");

            }

            if (codigoActivacion.length() != 6 || !codigoActivacion.matches("\\d{6}")) {

                throw new Exception("El codigo de activacion debe tener exactamente 6 digitos numericos.");

            }

            if (!usuario.getCodigoActivacion().equals(codigoActivacion)) {

                throw new Exception("El codigo de activacion es incorrecto.");

            }

            if (contraseñaUsuario.isBlank()) {

                throw new Exception("La contraseña no puede estar vacia.");

            }

            if (contraseñaUsuario.length() < 8) {

                throw new Exception("La contraseña debe tener al menos 8 caracteres.");

            }

            if (!usuario.getContraseñaUsuario().equals(contraseñaUsuario)) {

                throw new Exception("La contraseña es incorrecta.");

            }

            usuario.setUsuarioActivado(true);

        }
        return usuario;

    }


    @Override
    public void solicitarCambioContraseña(String emailUsuario) throws Exception {

        if (emailUsuario.isBlank()) {

            throw new Exception("El email no puede estar vacio.");

        }

        if (!emailUsuario.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {

            throw new Exception("El email debe tener el formato de correo electronico.");

        }

        Usuario usuario = buscarUsuarioPorEmail(emailUsuario);

        if (usuario == null) {

            throw new Exception("No existe un usuario con el email proporcionado.");

        }

        String codigoContraseña = generarCodigo();

        usuario.setCodigoContraseña(codigoContraseña);

        try {

            EnvioEmail.enviarCorreo(emailUsuario, "Código de cambio de Contraseña", "Su código de cambio de contraseña es: " + codigoContraseña, null);

        }catch (Exception e) {

            throw new Exception("Error al enviar el correo de cambio de contraseña. Por favor, intente nuevamente.");

        }

    }


    @Override
    public void cambiarContraseña(String codigoContraseña, String nuevaContraseña) throws Exception {

        if (codigoContraseña.isBlank()) {

            throw new Exception("El codigo de cambio de contraseña no puede estar vacio.");

        }

        if (codigoContraseña.length() != 6 || !codigoContraseña.matches("\\d{6}")) {

            throw new Exception("El codigo de cambio de contraseña debe tener exactamente 6 digitos numericos.");

        }

        Usuario usuario = buscarUsuarioPorCodigoContraseña(codigoContraseña);

        if (usuario == null) {

            throw new Exception("El codigo de cambio de contraseña proporcionado es incorrecto.");

        }

        if (nuevaContraseña.isBlank()) {

            throw new Exception("La contraseña no puede estar vacio.");

        }
        if (nuevaContraseña.length() < 8) {

            throw new Exception("La contraseña debe tener al menos 8 caracteres.");

        }

        if (usuario.getContraseñaUsuario().equals(nuevaContraseña)) {

            throw new Exception("La nueva contraseña no puede ser igual a la contraseña actual.");

        }

        usuario.setContraseñaUsuario(nuevaContraseña);

        usuario.setCodigoContraseña(null);

    }


    @Override
    public void editarUsuario(long cedulaUsuario, String nombreUsuario, String emailUsuario, long telefonoUsuario) throws Exception {

        if (!numeroValido(cedulaUsuario)) {

            throw new Exception("La cedula debe tener exactamente 10 digitos numericos.");

        }

        int idUsuario = buscarUsuario(cedulaUsuario);

        if (idUsuario == -1) {

            throw new Exception("No existe un usuario con la cedula proporcionada.");

        }

        if (nombreUsuario.isBlank()) {

            throw new Exception("El nombre no puede estar vacio.");

        }

        if (!nombreUsuario.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {

            throw new Exception("El nombre solo puede contener letras.");

        }

        if (emailUsuario.isBlank()) {

            throw new Exception("El email no puede estar vacio.");

        }

        if (!emailUsuario.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {

            throw new Exception("El email debe tener el formato de correo electronico.");

        }

        if (!numeroValido(telefonoUsuario)) {

            throw new Exception("El telefono debe tener exactamente 10 digitos numericos.");

        }

        Usuario editarUsuario = usuarios.get(idUsuario);
        editarUsuario.setCedulaUsuario(cedulaUsuario);
        editarUsuario.setNombreUsuario(nombreUsuario);
        editarUsuario.setEmailUsuario(emailUsuario);
        editarUsuario.setTelefonoUsuario(telefonoUsuario);

    }


    @Override
    public void editarCasa(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoCasa, double mantenimientoCasa) throws Exception {

        if (direccionAlojamiento <= 0) {

            throw new Exception("La direccion solo puede tener numeros positivos.");

        }

        int idAlojamiento = buscarAlojamiento(direccionAlojamiento);

        if (idAlojamiento == -1) {

            throw new Exception("No existe un alojamiento con la direccion proporcionada.");

        }

        if (nombreAlojamiento.isBlank()) {

            throw new Exception("El nombre no puede estar vacio.");

        }

        if (!nombreAlojamiento.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {

            throw new Exception("El nombre solo puede contener letras.");

        }

        if (buscarAlojamientoPorNombre(nombreAlojamiento) != null) {

            throw new Exception("Ya existe un alojamiento con el nombre proporcionado.");

        }

        if (ciudadAlojamiento == null) {

            throw new Exception("La ciudad no puede estar vacia.");

        }

        if (descripcionAlojamiento.isBlank()) {

            throw new Exception("La descripcion no puede estar vacio.");

        }

        if (precioPorNocheAlojamiento <= 0) {

            throw new Exception("El precio por noche solo puede tener numeros positivos.");

        }

        if (capacidadMaximaAlojamiento <= 0) {

            throw new Exception("La capacidad maxima solo puede tener numeros positivos.");

        }

        if (servicioAlojamiento == null) {

            throw new Exception("El servicio no puede estar vacio.");

        }

        if (aseoCasa <= 0) {

            throw new Exception("El aseo de la casa solo puede tener numeros positivos.");

        }

        if (mantenimientoCasa <= 0) {

            throw new Exception("El mantenimiento de la casa solo puede tener numeros positivos.");

        }

        Alojamiento alojamiento = alojamientos.get(idAlojamiento);

        if (alojamiento instanceof Casa editarCasa) {

            editarCasa.setDireccionAlojamiento(direccionAlojamiento);
            editarCasa.setNombreAlojamiento(nombreAlojamiento);
            editarCasa.setCiudadAlojamiento(ciudadAlojamiento);
            editarCasa.setDescripcionAlojamiento(descripcionAlojamiento);
            editarCasa.setImagenAlojamiento(imagenAlojamiento);
            editarCasa.setPrecioPorNocheAlojamiento(precioPorNocheAlojamiento);
            editarCasa.setCapacidadMaximaAlojamiento(capacidadMaximaAlojamiento);
            editarCasa.setServicioAlojamiento(servicioAlojamiento);
            editarCasa.setAseoCasa(aseoCasa);
            editarCasa.setMantenimientoCasa(mantenimientoCasa);

        }

    }


    @Override
    public void editarApartamento(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoApartamento, double mantenimientoApartamento) throws Exception {

        if (direccionAlojamiento <= 0) {

            throw new Exception("La direccion solo puede tener numeros positivos.");

        }

        int idAlojamiento = buscarAlojamiento(direccionAlojamiento);

        if (idAlojamiento == -1) {

            throw new Exception("No existe un alojamiento con la direccion proporcionada.");

        }

        if (nombreAlojamiento.isBlank()) {

            throw new Exception("El nombre no puede estar vacio.");

        }

        if (!nombreAlojamiento.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {

            throw new Exception("El nombre solo puede contener letras.");

        }

        if (buscarAlojamientoPorNombre(nombreAlojamiento) != null) {

            throw new Exception("Ya existe un alojamiento con el nombre proporcionado.");

        }

        if (ciudadAlojamiento == null) {

            throw new Exception("La ciudad no puede estar vacia.");

        }

        if (descripcionAlojamiento.isBlank()) {

            throw new Exception("La descripcion no puede estar vacio.");

        }

        if (precioPorNocheAlojamiento <= 0) {

            throw new Exception("El precio por noche solo puede tener numeros positivos.");

        }

        if (capacidadMaximaAlojamiento <= 0) {

            throw new Exception("La capacidad maxima solo puede tener numeros positivos.");

        }

        if (servicioAlojamiento == null) {

            throw new Exception("El servicio no puede estar vacio.");

        }

        if (aseoApartamento <= 0) {

            throw new Exception("El aseo de la casa solo puede tener numeros positivos.");

        }

        if (mantenimientoApartamento <= 0) {

            throw new Exception("El mantenimiento de la casa solo puede tener numeros positivos.");

        }

        Alojamiento alojamiento = alojamientos.get(idAlojamiento);

        if (alojamiento instanceof Apartamento editarApartamento) {

            editarApartamento.setDireccionAlojamiento(direccionAlojamiento);
            editarApartamento.setNombreAlojamiento(nombreAlojamiento);
            editarApartamento.setCiudadAlojamiento(ciudadAlojamiento);
            editarApartamento.setDescripcionAlojamiento(descripcionAlojamiento);
            editarApartamento.setImagenAlojamiento(imagenAlojamiento);
            editarApartamento.setPrecioPorNocheAlojamiento(precioPorNocheAlojamiento);
            editarApartamento.setCapacidadMaximaAlojamiento(capacidadMaximaAlojamiento);
            editarApartamento.setServicioAlojamiento(servicioAlojamiento);
            editarApartamento.setAseoApartamento(aseoApartamento);
            editarApartamento.setMantenimientoApartamento(mantenimientoApartamento);

        }

    }


    @Override
    public void editarHotel(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento) throws Exception {

        if (direccionAlojamiento <= 0) {

            throw new Exception("La direccion solo puede tener numeros positivos.");

        }

        int idAlojamiento = buscarAlojamiento(direccionAlojamiento);

        if (idAlojamiento == -1) {

            throw new Exception("No existe un alojamiento con la direccion proporcionada.");

        }

        if (nombreAlojamiento.isBlank()) {

            throw new Exception("El nombre no puede estar vacio.");

        }

        if (!nombreAlojamiento.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {

            throw new Exception("El nombre solo puede contener letras.");

        }

        if (buscarAlojamientoPorNombre(nombreAlojamiento) != null) {

            throw new Exception("Ya existe un alojamiento con el nombre proporcionado.");

        }

        if (ciudadAlojamiento == null) {

            throw new Exception("La ciudad no puede estar vacia.");

        }

        if (descripcionAlojamiento.isBlank()) {

            throw new Exception("La descripcion no puede estar vacio.");

        }

        if (precioPorNocheAlojamiento <= 0) {

            throw new Exception("El precio por noche solo puede tener numeros positivos.");

        }

        if (capacidadMaximaAlojamiento <= 0) {

            throw new Exception("La capacidad maxima solo puede tener numeros positivos.");

        }

        if (servicioAlojamiento == null) {

            throw new Exception("El servicio no puede estar vacio.");

        }

        Alojamiento alojamiento = alojamientos.get(idAlojamiento);

        if (alojamiento instanceof Hotel editarHotel) {

            editarHotel.setDireccionAlojamiento(direccionAlojamiento);
            editarHotel.setNombreAlojamiento(nombreAlojamiento);
            editarHotel.setCiudadAlojamiento(ciudadAlojamiento);
            editarHotel.setDescripcionAlojamiento(descripcionAlojamiento);
            editarHotel.setImagenAlojamiento(imagenAlojamiento);
            editarHotel.setPrecioPorNocheAlojamiento(precioPorNocheAlojamiento);
            editarHotel.setCapacidadMaximaAlojamiento(capacidadMaximaAlojamiento);
            editarHotel.setServicioAlojamiento(servicioAlojamiento);

        }

    }


    @Override
    public void editarHabitacion(Hotel hotel, int numeroHabitacion, int capacidadHabitacion, double precioHabitacion, String imagenHabitacion, String descripcionHabitacion) throws Exception {

        if (hotel == null) {

            throw new Exception("El hotel no puede estar vacio.");

        }

        if (numeroHabitacion <= 0) {

            throw new Exception("El numero de la habitacion solo puede tener numeros positivos.");

        }

        int idHabitacion = buscarHabitacion(hotel, numeroHabitacion);

        if (idHabitacion == -1) {

            throw new Exception("No existe una habitacion con el numero proporcionada.");

        }

        if (capacidadHabitacion <= 0) {

            throw new Exception("La capacidad de la habitacion solo puede tener numeros positivos.");

        }

        if (precioHabitacion <= 0) {

            throw new Exception("El precio de la habitacion solo puede tener numeros positivos.");

        }

        if (descripcionHabitacion.isBlank()) {

            throw new Exception("La descripcion de la habitacion no puede estar vacio.");

        }

        Habitacion editarHabitacion = hotel.getHabitaciones().get(idHabitacion);
        editarHabitacion.setNumeroHabitacion(numeroHabitacion);
        editarHabitacion.setCapacidadHabitacion(capacidadHabitacion);
        editarHabitacion.setPrecioHabitacion(precioHabitacion);
        editarHabitacion.setImagenHabitacion(imagenHabitacion);
        editarHabitacion.setDescripcionHabitacion(descripcionHabitacion);

    }


    @Override
    public void editarOferta(Alojamiento alojamiento, int idOferta, String descripcionOferta, float descuentoOferta, LocalDateTime fechaInicioOferta, LocalDateTime fechaFinOferta) throws Exception {

        if (alojamiento == null) {

            throw new Exception("El alojamiento no puede estar vacio.");

        }

        if (idOferta <= 0) {

            throw new Exception("La id de la oferta solo puede tener numeros positivos.");

        }

        int identificacionOferta = buscarOferta(alojamiento, idOferta);

        if (identificacionOferta == -1) {

            throw new Exception("No existe una oferta con la identificacion proporcionada.");

        }

        if (descripcionOferta.isBlank()) {

            throw new Exception("La descripcion no puede estar vacio.");

        }

        if (descuentoOferta <= 0) {

            throw new Exception("El descuento de la oferta solo puede tener numeros positivos.");

        }

        if (fechaInicioOferta == null) {

            throw new Exception("La fecha de inicio no puede estar vacio.");

        }

        if (fechaInicioOferta.isBefore(LocalDateTime.now())) {

            throw new Exception("La fecha de inicio no puede estar en el pasado.");

        }

        if (fechaFinOferta == null) {

            throw new Exception("La fecha de fin no puede estar vacio.");

        }

        if (fechaFinOferta.isBefore(fechaInicioOferta)) {

            throw new Exception("La fecha de fin no puede estar antes de la fecha de inicio.");

        }

        Oferta editarOferta = alojamiento.getOfertas().get(identificacionOferta);
        editarOferta.setIdOferta(idOferta);
        editarOferta.setDescripcionOferta(descripcionOferta);
        editarOferta.setDescuentoOferta(descuentoOferta);
        editarOferta.setFechaInicioOferta(fechaInicioOferta);
        editarOferta.setFechaFinOferta(fechaFinOferta);

    }


    @Override
    public void editarReseña(Alojamiento alojamiento, int idReseña, String cometarioReseña, float calificacionReseña) throws Exception {

        if (alojamiento == null) {

            throw new Exception("El cliente no puede estar vacio.");

        }

        if (idReseña <= 0) {

            throw new Exception("La id de la reseña solo puede tener numeros positivos.");

        }

        int identificacionReseña = buscarReseña(alojamiento, idReseña);

        if (identificacionReseña == -1) {

            throw new Exception("No existe una reseña con la identificacion proporcionada.");

        }

        if (cometarioReseña.isBlank()) {

            throw new Exception("El cometario no puede estar vacio.");

        }

        if (calificacionReseña < 1 || calificacionReseña > 5) {

            throw new Exception("La calificacion debe estar entre 1 y 5.");

        }

        Reseña editarReseña = alojamiento.getReseñas().get(identificacionReseña);
        editarReseña.setIdReseña(idReseña);
        editarReseña.setCometarioReseña(cometarioReseña);
        editarReseña.setCalificacionReseña(calificacionReseña);

    }


    @Override
    public void eliminarUsuario(long cedulaUsuario) throws Exception {

        if (!numeroValido(cedulaUsuario)) {

            throw new Exception("La cedula debe tener exactamente 10 digitos numericos.");

        }

        int idUsuario = buscarUsuario(cedulaUsuario);

        if (idUsuario == -1) {

            throw new Exception("No existe un usuario con la cedula proporcionada.");

        }

        usuarios.remove(idUsuario);

    }


    @Override
    public void eliminarAlojamiento(int direccionAlojamiento) throws Exception {

        if (direccionAlojamiento <= 0) {

            throw new Exception("La direccion solo puede tener numeros positivos.");

        }

        int idAlojamiento = buscarAlojamiento(direccionAlojamiento);

        if (idAlojamiento == -1) {

            throw new Exception("No existe un alojamiento con la direccion proporcionada.");

        }

        alojamientos.remove(idAlojamiento);

    }


    @Override
    public void eliminarHabitacion(Hotel hotel, int numeroHabitacion) throws Exception {

        if (hotel == null) {

            throw new Exception("El hotel no puede estar vacio.");

        }

        if (numeroHabitacion <= 0) {

            throw new Exception("El numero de la habitacion solo puede tener numeros positivos.");

        }

        int idHabitacion = buscarHabitacion(hotel, numeroHabitacion);

        if (idHabitacion == -1) {

            throw new Exception("No existe una habitacion con el numero proporcionada.");

        }

        hotel.getHabitaciones().remove(idHabitacion);

    }


    @Override
    public void eliminarOferta(Alojamiento alojamiento, int idOferta) throws Exception {

        if (alojamiento == null) {

            throw new Exception("El alojamiento no puede estar vacio.");

        }

        if (idOferta <= 0) {

            throw new Exception("La id de la oferta solo puede tener numeros positivos.");

        }

        int identificacionOferta = buscarOferta(alojamiento, idOferta);

        if (identificacionOferta == -1) {

            throw new Exception("No existe una oferta con la identificacion proporcionada.");

        }

        alojamiento.getOfertas().remove(identificacionOferta);

    }


    @Override
    public void eliminarReserva(Usuario clienteReserva, int idReserva) throws Exception {

        if (clienteReserva == null) {

            throw new Exception("El cliente no puede estar vacio.");

        }

        if (!(clienteReserva instanceof Cliente)) {

            throw new Exception("El usuario proporcionado no es un cliente");

        }

        if (idReserva <= 0) {

            throw new Exception("La id de la reserva solo puede tener numeros positivos.");

        }

        int identificacionReserva = buscarReserva(clienteReserva, idReserva);

        if (identificacionReserva == -1) {

            throw new Exception("No existe una reserva con la identificacion proporcionada.");

        }

        ((Cliente) clienteReserva).getReservas().remove(identificacionReserva);

    }


    @Override
    public void eliminarReseña(Alojamiento alojamiento, int idReseña) throws  Exception {

        if (alojamiento == null) {

            throw new Exception("El cliente no puede estar vacio.");

        }

        if (idReseña <= 0) {

            throw new Exception("La id de la reseña solo puede tener numeros positivos.");

        }

        int identificacionReseña = buscarReseña(alojamiento, idReseña);

        if (identificacionReseña == -1) {

            throw new Exception("No existe una reseña con la identificacion proporcionada.");

        }

        alojamiento.getReseñas().remove(identificacionReseña);

    }


    @Override
    public Usuario crearUsuario(TipoUsuario tipoUsuario, long cedulaUsuario, String nombreUsuario, String emailUsuario, String contraseñaUsuario, long telefonoUsuario) throws Exception {

        return switch (tipoUsuario) {

            case ADMINISTRADOR -> Administrador.builder()
                    .tipoUsuario(TipoUsuario.ADMINISTRADOR)
                    .cedulaUsuario(cedulaUsuario)
                    .nombreUsuario(nombreUsuario)
                    .emailUsuario(emailUsuario)
                    .contraseñaUsuario(contraseñaUsuario)
                    .telefonoUsuario(telefonoUsuario)
                    .build();

            case CLIENTE -> Cliente.builder()
                    .tipoUsuario(TipoUsuario.CLIENTE)
                    .cedulaUsuario(cedulaUsuario)
                    .nombreUsuario(nombreUsuario)
                    .emailUsuario(emailUsuario)
                    .contraseñaUsuario(contraseñaUsuario)
                    .telefonoUsuario(telefonoUsuario)
                    .billeteraCliente(new Billetera(0.0))
                    .reservas(new ArrayList<>())
                    .build();

            default -> throw new Exception("Tipo de usuario no valido.");

        };

    }


    @Override
    public Casa crearCasa(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoCasa, double mantenimientoCasa) {

        return Casa.builder()
                .tipoAlojamiento(TipoAlojamiento.CASA)
                .direccionAlojamiento(direccionAlojamiento)
                .nombreAlojamiento(nombreAlojamiento)
                .ciudadAlojamiento(ciudadAlojamiento)
                .descripcionAlojamiento(descripcionAlojamiento)
                .imagenAlojamiento(imagenAlojamiento)
                .precioPorNocheAlojamiento(precioPorNocheAlojamiento)
                .capacidadMaximaAlojamiento(capacidadMaximaAlojamiento)
                .servicioAlojamiento(servicioAlojamiento)
                .aseoCasa(aseoCasa)
                .mantenimientoCasa(mantenimientoCasa)
                .ofertas(new ArrayList<>())
                .reseñas(new ArrayList<>())
                .reservasAlojamiento(new ArrayList<>())
                .build();

    }


    @Override
    public Apartamento crearApartamento(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento, double aseoApartamento, double mantenimientoApartamento) {

        return Apartamento.builder()
                .tipoAlojamiento(TipoAlojamiento.APARTAMENTO)
                .direccionAlojamiento(direccionAlojamiento)
                .nombreAlojamiento(nombreAlojamiento)
                .ciudadAlojamiento(ciudadAlojamiento)
                .descripcionAlojamiento(descripcionAlojamiento)
                .imagenAlojamiento(imagenAlojamiento)
                .precioPorNocheAlojamiento(precioPorNocheAlojamiento)
                .capacidadMaximaAlojamiento(capacidadMaximaAlojamiento)
                .servicioAlojamiento(servicioAlojamiento)
                .aseoApartamento(aseoApartamento)
                .mantenimientoApartamento(mantenimientoApartamento)
                .ofertas(new ArrayList<>())
                .reseñas(new ArrayList<>())
                .reservasAlojamiento(new ArrayList<>())
                .build();

    }


    @Override
    public Hotel crearHotel(int direccionAlojamiento, String nombreAlojamiento, CiudadAlojamiento ciudadAlojamiento, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, ServicioAlojamiento servicioAlojamiento) {

        return Hotel.builder()
                .tipoAlojamiento(TipoAlojamiento.HOTEL)
                .direccionAlojamiento(direccionAlojamiento)
                .nombreAlojamiento(nombreAlojamiento)
                .ciudadAlojamiento(ciudadAlojamiento)
                .descripcionAlojamiento(descripcionAlojamiento)
                .imagenAlojamiento(imagenAlojamiento)
                .precioPorNocheAlojamiento(precioPorNocheAlojamiento)
                .capacidadMaximaAlojamiento(capacidadMaximaAlojamiento)
                .servicioAlojamiento(servicioAlojamiento)
                .habitaciones(new ArrayList<>())
                .ofertas(new ArrayList<>())
                .reseñas(new ArrayList<>())
                .reservasAlojamiento(new ArrayList<>())
                .build();

    }


    @Override
    public Habitacion crearHabitacion(int numeroHabitacion, int capacidadHabitacion, double precioHabitacion, String imagenHabitacion, String descripcionHabitacion) {

        return Habitacion.builder()
                .numeroHabitacion(numeroHabitacion)
                .capacidadHabitacion(capacidadHabitacion)
                .precioHabitacion(precioHabitacion)
                .imagenHabitacion(imagenHabitacion)
                .descripcionHabitacion(descripcionHabitacion)
                .build();

    }


    @Override
    public Oferta crearOferta(int idOferta, String descripcionOferta, float descuentoOferta, LocalDateTime fechaInicioOferta, LocalDateTime fechaFinOferta) {

        return Oferta.builder()
                .idOferta(idOferta)
                .descripcionOferta(descripcionOferta)
                .descuentoOferta(descuentoOferta)
                .fechaInicioOferta(fechaInicioOferta)
                .fechaFinOferta(fechaFinOferta)
                .build();

    }


    @Override
    public Reserva crearReserva(Usuario clienteReserva, int idReserva, Alojamiento alojamientoReserva, int numHuespedesReserva, LocalDateTime fechaInicioReserva, LocalDateTime fechaFinReserva) {

        return Reserva.builder()
                .clienteReserva(clienteReserva)
                .idReserva(idReserva)
                .alojamientoReserva(alojamientoReserva)
                .numHuespedesReserva(numHuespedesReserva)
                .fechaInicioReserva(fechaInicioReserva)
                .fechaFinReserva(fechaFinReserva)
                .build();

    }


    @Override
    public Reseña crearReseña(int idReseña, String cometarioReseña, float calificacionReseña) {

        return Reseña.builder()
                .idReseña(idReseña)
                .cometarioReseña(cometarioReseña)
                .calificacionReseña(calificacionReseña)
                .build();

    }


    @Override
    public Estadistica crearEstadistica(double ocupacionPorcentual, double gananciasTotales) {

        return Estadistica.builder()
                .ocupacionPorcentual(ocupacionPorcentual)
                .gananciasTotales(gananciasTotales)
                .build();

    }


    @Override
    public int buscarUsuario(long cedulaUsuario) {

        for (int i = 0; i < usuarios.size(); i++) {

            if (usuarios.get(i).getCedulaUsuario() == cedulaUsuario) {

                return i;

            }

        }
        return -1;

    }


    @Override
    public int buscarAlojamiento(int direccionAlojamiento) {

        for (int i = 0; i < alojamientos.size(); i++) {

            if (alojamientos.get(i).getDireccionAlojamiento() == direccionAlojamiento) {

                return i;

            }

        }
        return -1;

    }


    @Override
    public int buscarHabitacion(Hotel hotel, int numeroHabitacion) {

        List<Habitacion> habitaciones = hotel.getHabitaciones();

        for (int i = 0; i < habitaciones.size(); i++) {

            Habitacion habitacion = habitaciones.get(i);

            if (habitacion.getNumeroHabitacion() == numeroHabitacion) {

                return i;

            }

        }
        return -1;

    }


    @Override
    public int buscarOferta(Alojamiento alojamiento, int idOferta) {

        List<Oferta> ofertas = alojamiento.getOfertas();

        for (int i = 0; i < ofertas.size(); i++) {

            Oferta oferta = ofertas.get(i);

            if (oferta.getIdOferta() == idOferta) {

                return i;

            }

        }
        return -1;

    }


    @Override
    public int buscarReserva(Usuario clienteReserva, int idReserva) {

        if (clienteReserva instanceof Cliente cliente) {

            List<Reserva> reservas = cliente.getReservas();

            for (int i = 0; i < reservas.size(); i++) {

                Reserva reserva = reservas.get(i);

                if (reserva.getIdReserva() == idReserva) {

                    return i;

                }

            }

        }
        return -1;

    }


    @Override
    public int buscarReseña(Alojamiento alojamiento, int idReseña) {

        List<Reseña> reseñas = alojamiento.getReseñas();

        for (int i = 0; i < reseñas.size(); i++) {

            Reseña reseña = reseñas.get(i);

            if (reseña.getIdReseña() == idReseña) {

                return i;

            }

        }
        return -1;

    }


    @Override
    public boolean buscarAdministrador() {

        for (Usuario usuario : usuarios) {

            if (usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {

                return true;

            }

        }
        return false;

    }


    @Override
    public Usuario buscarUsuarioPorEmail(String emailUsuario) {

        for (Usuario usuario : usuarios) {

            if (usuario.getEmailUsuario().equals(emailUsuario)) {

                return usuario;

            }

        }
        return null;

    }


    @Override
    public Usuario buscarUsuarioPorCodigoContraseña(String codigoContraseña) {

        for (Usuario usuario : usuarios) {

            if (usuario.getCodigoContraseña().equals(codigoContraseña)) {

                return usuario;

            }

        }
        return null;

    }


    @Override
    public Alojamiento buscarAlojamientoPorNombre(String nombreAlojamiento) {

        for (Alojamiento alojamiento : alojamientos) {

            if (alojamiento.getNombreAlojamiento().equals(nombreAlojamiento)) {

                return alojamiento;

            }

        }
        return null;

    }


    @Override
    public void recargarBilletera(Usuario cliente, double saldoBilletera) throws Exception {

        if (cliente == null) {

            throw new Exception("El cliente no puede estar vacio.");

        }

        if (!(cliente instanceof Cliente)) {

            throw new Exception("El usuario proporcionado no es un cliente");

        }

        if (saldoBilletera <= 0) {

            throw new Exception("El saldo de la billetera solo puede tener numeros positivos.");

        }

        Billetera billetera = ((Cliente) cliente).getBilleteraCliente();

        billetera.setSaldoBilletera(billetera.getSaldoBilletera() + saldoBilletera);

    }


    @Override
    public void desontarSaldo(Usuario cliente, double totalFactura) {

        Billetera billetera = ((Cliente) cliente).getBilleteraCliente();

        billetera.setSaldoBilletera(billetera.getSaldoBilletera() - totalFactura);

    }


    @Override
    public boolean numeroValido(long numero) {

        return numero >= 1000000000L && numero <= 9999999999L;

    }


    @Override
    public int calcularDiasReserva(LocalDateTime fechaInicioReserva, LocalDateTime fechaFinReserva) {

        return (int) ChronoUnit.DAYS.between(fechaInicioReserva, fechaFinReserva);

    }


    @Override
    public double calcularCostoReserva(Alojamiento alojamiento, LocalDateTime fechaInicioReserva, LocalDateTime fechaFinReserva) {

        int dias = calcularDiasReserva(fechaInicioReserva, fechaFinReserva);

        double precioPorNoche = alojamiento.getPrecioPorNocheAlojamiento();

        return dias * precioPorNoche;

    }


    @Override
    public boolean fechasSeSuperponen(LocalDateTime fechaInicio1, LocalDateTime fechaFin1, LocalDateTime fechaInicio2, LocalDateTime fechaFin2) {

        return (fechaInicio1.isBefore(fechaFin2) && fechaFin1.isAfter(fechaInicio2));

    }


    @Override
    public double obtenerDescuento(Alojamiento alojamiento, LocalDateTime fechaInicio, LocalDateTime fechaFin) {

        for (Oferta oferta : alojamiento.getOfertas()) {

            if (!fechaInicio.isBefore(oferta.getFechaInicioOferta()) && !fechaFin.isAfter(oferta.getFechaFinOferta())) {

                return oferta.getDescuentoOferta();

            }

        }
        return 0;

    }


    @Override
    public double calcularDescuento(double descuentoOferta) {

        return 1 - descuentoOferta / 100;

    }


    @Override
    public boolean reservaPasada(Alojamiento alojamiento) {

        for (Reserva reserva : alojamiento.getReservasAlojamiento()) {

            if (reserva.getClienteReserva() instanceof Cliente && reserva.getFechaFinReserva().isBefore(LocalDateTime.now())) {

                return true;

            }

        }
        return false;

    }


    @Override
    public String generarCodigo() {

        int codigo = (int) (Math.random() * 900000) + 100000;

        return String.valueOf(codigo);

    }


    @Override
    public Factura generarFactura(double subTotalFactura, double totalFactura) {

        return Factura.builder()
                .codigoFactura(String.valueOf(UUID.randomUUID()))
                .fechaFactura(LocalDateTime.now())
                .subTotalFactura(subTotalFactura)
                .totalFactura(totalFactura)
                .build();

    }


    public List<Alojamiento> obtenerAlojamientosAleatorios() {
        return alojamientos.stream()
                .filter(alojamiento -> !alojamiento.getReservasAlojamiento().isEmpty())
                .sorted((a, b) -> Math.random() > 0.5 ? 1 : -1)
                .limit(5)
                .toList();
    }


    public List<Oferta> obtenerOfertasActivas() {
        return alojamientos.stream()
                .flatMap(alojamiento -> alojamiento.getOfertas().stream())
                .filter(oferta -> oferta.getFechaInicioOferta().isBefore(LocalDateTime.now()) &&
                        oferta.getFechaFinOferta().isAfter(LocalDateTime.now()))
                .toList();
    }


    @Override
    public List<Alojamiento> filtrarAlojamientos(String nombreAlojamiento, TipoAlojamiento tipoAlojamiento, CiudadAlojamiento ciudadAlojamiento, double precioMin, double precioMax) throws Exception {

        if (!nombreAlojamiento.isBlank() && !nombreAlojamiento.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {

            throw new Exception("El nombre solo puede contener letras.");

        }

        filtroAlojamiento.clear();

        filtroAlojamiento.addAll(alojamientos.stream()
                .filter(alojamiento -> nombreAlojamiento.isBlank() || alojamiento.getNombreAlojamiento().equalsIgnoreCase(nombreAlojamiento))
                .filter(alojamiento -> tipoAlojamiento == null || alojamiento.getTipoAlojamiento() == tipoAlojamiento)
                .filter(alojamiento -> ciudadAlojamiento == null || alojamiento.getCiudadAlojamiento() == ciudadAlojamiento)
                .filter(alojamiento -> precioMin <= 0 || alojamiento.getPrecioPorNocheAlojamiento() >= precioMin)
                .filter(alojamiento -> precioMax <= 0 || alojamiento.getPrecioPorNocheAlojamiento() <= precioMax)
                .toList());

        if (filtroAlojamiento.isEmpty()) {

            throw new Exception("No se encontraron alojamientos con los criterios proporcionados.");

        }

        return filtroAlojamiento;

    }


    @Override
    public List<BarChart.Data<String, Number>> obtenerEstadisticas() throws Exception {

        estadisticaAlojamiento.clear();

        for (Alojamiento alojamiento : alojamientos) {

            int totalDiasReservados = 0;

            double gananciasTotales = 0;

            for (Reserva reserva : alojamiento.getReservasAlojamiento()) {

                int diasReservados = calcularDiasReserva(reserva.getFechaInicioReserva(), reserva.getFechaFinReserva());

                totalDiasReservados += diasReservados;

                double costo = calcularCostoReserva(reserva.getAlojamientoReserva(), reserva.getFechaInicioReserva(), reserva.getFechaFinReserva());

                double descuento = obtenerDescuento(reserva.getAlojamientoReserva(), reserva.getFechaInicioReserva(), reserva.getFechaFinReserva());

                double calculo = calcularDescuento(descuento);

                gananciasTotales += costo * calculo;

            }

            double ocupacionPorcentual = (double) totalDiasReservados / (alojamiento.getCapacidadMaximaAlojamiento() * 365) * 100;

            estadisticaAlojamiento.add(new BarChart.Data<>(alojamiento.getNombreAlojamiento() + " (Ocupación)", ocupacionPorcentual));

            estadisticaAlojamiento.add(new BarChart.Data<>(alojamiento.getNombreAlojamiento() + " (Ganancias)", gananciasTotales));

        }

        if (estadisticaAlojamiento.isEmpty()) {

            throw new Exception("No se encontraron estadísticas de alojamientos.");

        }

        return estadisticaAlojamiento;

    }


    @Override
    public List<Alojamiento> alojamientosPopulares() throws Exception {

        alojamientoPopular.clear();

        for (CiudadAlojamiento ciudad : CiudadAlojamiento.values()) {

            alojamientos.stream()
                    .filter(alojamiento -> alojamiento.getCiudadAlojamiento() == ciudad)
                    .max(Comparator.comparingInt(a -> a.getReservasAlojamiento().size()))
                    .ifPresent(alojamientoPopular::add);

        }

        if (alojamientoPopular.isEmpty()) {

            throw new Exception("No se encontraron alojamientos populares.");

        }

        return alojamientoPopular;

    }


    @Override
    public List<PieChart.Data> alojamientosRentables() throws Exception {

        alojamientoRentable.clear();

        double totalGananciasCasas = 0;

        double totalGananciasApartamentos = 0;

        double totalGananciasHoteles = 0;

        for (Alojamiento alojamiento : alojamientos) {

            double totalReservas = 0;

            for (Reserva reserva : alojamiento.getReservasAlojamiento()) {

                double costo = calcularCostoReserva(reserva.getAlojamientoReserva(), reserva.getFechaInicioReserva(), reserva.getFechaFinReserva());

                double descuento = obtenerDescuento(reserva.getAlojamientoReserva(), reserva.getFechaInicioReserva(), reserva.getFechaFinReserva());

                double calculo = calcularDescuento(descuento);

                totalReservas += costo * calculo;

            }

            switch (alojamiento.getTipoAlojamiento()) {

                case CASA -> totalGananciasCasas += totalReservas;

                case APARTAMENTO -> totalGananciasApartamentos += totalReservas;

                case HOTEL -> totalGananciasHoteles += totalReservas;

            }

        }

        if (totalGananciasCasas == 0 && totalGananciasApartamentos == 0 && totalGananciasHoteles == 0) {

            throw new Exception("No se encontraron ganancias para los tipos de alojamiento.");

        }

        alojamientoRentable.add(new PieChart.Data(TipoAlojamiento.CASA.getNombre(), totalGananciasCasas));

        alojamientoRentable.add(new PieChart.Data(TipoAlojamiento.APARTAMENTO.getNombre(), totalGananciasApartamentos));

        alojamientoRentable.add(new PieChart.Data(TipoAlojamiento.HOTEL.getNombre(), totalGananciasHoteles));

        return alojamientoRentable;

    }

}
