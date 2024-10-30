package co.edu.uniquindio.proyectofinal.modelos;


import co.edu.uniquindio.proyectofinal.utils.EnvioEmail;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@ToString
public class Empresa {

    private final List<Usuario> usuarios;
    private final List<Alojamiento> alojamientos;
    private final List<Alojamiento> buscarAlojamiento;


    public Empresa() {

        usuarios = new ArrayList<>();
        alojamientos = new ArrayList<>();
        buscarAlojamiento = new ArrayList<>();

    }


    public void registrarUsuario(TipoUsuario tipoUsuario, long cedula, String nombreCompleto, String email, String contraseña, long telefono) throws Exception {

        if (tipoUsuario == TipoUsuario.ADMINISTRADOR && buscarAdministrador()) {

            throw new Exception("Ya existe un administrador registrado.");

        }

        if (numeroValido(cedula)) {

            throw new Exception("La cedula debe tener exactamente 10 digitos numericos.");

        }

        if (buscarUsuario(cedula) != -1) {

            throw new Exception("Ya existe un usuario con la cédula proporcionada.");

        }

        if (nombreCompleto.isBlank()) {

            throw new Exception("El nombre no puede estar vacio.");

        }

        if (!nombreCompleto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {

            throw new Exception("El nombre solo puede contener letras.");

        }

        if (email.isBlank()) {

            throw new Exception("El email no puede estar vacio.");

        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {

            throw new Exception("El email debe tener el formato de correo electronico.");

        }

        if (contraseña.isBlank()) {

            throw new Exception("La contraseña no puede estar vacia.");

        }

        if (contraseña.length() < 8) {

            throw new Exception("La contraseña debe tener al menos 8 caracteres.");

        }

        if (numeroValido(telefono)) {

            throw new Exception("El telefono debe tener exactamente 10 digitos numericos.");

        }

        Usuario usuario = crearUsuario(tipoUsuario, cedula, nombreCompleto, email, contraseña, telefono);

        usuarios.add(usuario);

        String codigoActivacion = generarCodigo();

        usuario.setCodigoActivacion(codigoActivacion);

        try {

            EnvioEmail.enviarCorreo(email, "Código de Activación", "Su código de activación es: " + codigoActivacion);

        }catch (Exception e) {

            throw new Exception("Error al enviar el correo de activacion. Por favor, intente nuevamente.");

        }

    }


    public void editarUsuario(long cedula, String nombreCompleto, String email, long telefono) throws Exception {

        if (numeroValido(cedula)) {

            throw new Exception("La cedula debe tener exactamente 10 digitos numericos.");

        }

        int idUsuario = buscarUsuario(cedula);

        if (idUsuario == -1) {

            throw new Exception("No existe un usuario con la cedula proporcionada.");

        }

        if (nombreCompleto.isBlank()) {

            throw new Exception("El nombre no puede estar vacio.");

        }

        if (!nombreCompleto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {

            throw new Exception("El nombre solo puede contener letras.");

        }

        if (email.isBlank()) {

            throw new Exception("El email no puede estar vacio.");

        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {

            throw new Exception("El email debe tener el formato de correo electronico.");

        }

        if (numeroValido(telefono)) {

            throw new Exception("El telefono debe tener exactamente 10 digitos numericos.");

        }

        Usuario editarUsuario = usuarios.get(idUsuario);
        editarUsuario.setCedula(cedula);
        editarUsuario.setNombreCompleto(nombreCompleto);
        editarUsuario.setEmail(email);
        editarUsuario.setTelefono(telefono);

        usuarios.set(idUsuario, editarUsuario);

    }


    public void eliminarUsuario(long cedula) throws Exception {

        if (numeroValido(cedula)) {

            throw new Exception("La cedula debe tener exactamente 10 digitos numericos.");

        }

        int idUsuario = buscarUsuario(cedula);

        if (idUsuario == -1) {

            throw new Exception("No existe un usuario con la cedula proporcionada.");

        }

        usuarios.remove(usuarios.get(idUsuario));

    }


    public void solicitarCambioContraseña(String email) throws Exception {

        if (email.isBlank()) {

            throw new Exception("El email no puede estar vacio.");

        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {

            throw new Exception("El email debe tener el formato de correo electronico.");

        }

        Usuario usuario = buscarUsuarioPorEmail(email);

        if (usuario == null) {

            throw new Exception("No existe un usuario con el email proporcionado.");

        }

        String codigoContraseña = generarCodigo();

        usuario.setCodigoContraseña(codigoContraseña);

        try {

            EnvioEmail.enviarCorreo(email, "Código de cambio de Contraseña", "Su código de cambio de contraseña es: " + codigoContraseña);

        }catch (Exception e) {

            throw new Exception("Error al enviar el correo de cambio de contraseña. Por favor, intente nuevamente.");

        }

    }


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

        if (usuario.getContraseña().equals(nuevaContraseña)) {

            throw new Exception("La nueva contraseña no puede ser igual a la contraseña actual.");

        }

        usuario.setContraseña(nuevaContraseña);

        usuario.setCodigoContraseña(null);

    }


    public void registrarAlojamiento(TipoAlojamiento tipoAlojamiento, String nombreAlojamiento, List<Ciudad> ciudad, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, List<Servicio> servicio) throws Exception {

        Alojamiento alojamiento = crearAlojamiento(tipoAlojamiento, nombreAlojamiento, ciudad, descripcionAlojamiento, imagenAlojamiento, precioPorNocheAlojamiento, capacidadMaximaAlojamiento, servicio);

        alojamientos.add(alojamiento);

    }


    public void registrarHabitacion() throws Exception {



    }


    private Usuario buscarUsuarioPorEmail(String email) {

        for (Usuario usuario : usuarios) {

            if (usuario.getEmail().equals(email)) {

                return usuario;

            }

        }
        return null;

    }

    private Usuario buscarUsuarioPorCodigoContraseña(String codigoContraseña) {

        for (Usuario usuario : usuarios) {

            if (usuario.getCodigoContraseña().equals(codigoContraseña)) {

                return usuario;

            }

        }
        return null;

    }


    private Usuario crearUsuario(TipoUsuario tipoUsuario, long cedula, String nombreCompleto, String email, String contraseña, long telefono) throws Exception {

        return switch (tipoUsuario) {

            case ADMINISTRADOR -> Administrador.builder()
                    .cedula(cedula)
                    .nombreCompleto(nombreCompleto)
                    .email(email)
                    .contraseña(contraseña)
                    .telefono(telefono)
                    .build();

            case CLIENTE -> Cliente.builder()
                    .cedula(cedula)
                    .nombreCompleto(nombreCompleto)
                    .email(email)
                    .contraseña(contraseña)
                    .telefono(telefono)
                    .billeteraCliente(new Billetera(0.0))
                    .build();

            default -> throw new Exception("Tipo de usuario no valido.");

        };

    }


    public Usuario iniciarSesion(String email, String codigoActivacion, String contraseña) throws Exception {

        if (email.isBlank()) {

            throw new Exception("El email no puede estar vacio.");

        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {

            throw new Exception("El email debe tener el formato de correo electronico.");

        }

        Usuario usuario = buscarUsuarioPorEmail(email);

        if (usuario == null) {

            throw new Exception("No existe un usuario con el email proporcionado.");

        }

        if (usuario.isActivado()) {

            if (contraseña.isBlank()) {

                throw new Exception("La contraseña no puede estar vacia.");

            }

            if (contraseña.length() < 8) {

                throw new Exception("La contraseña debe tener al menos 8 caracteres.");

            }

            if (!usuario.getContraseña().equals(contraseña)) {

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

            if (contraseña.isBlank()) {

                throw new Exception("La contraseña no puede estar vacia.");

            }

            if (contraseña.length() < 8) {

                throw new Exception("La contraseña debe tener al menos 8 caracteres.");

            }

            if (!usuario.getContraseña().equals(contraseña)) {

                throw new Exception("La contraseña es incorrecta.");

            }

            usuario.setActivado(true);

        }
        return usuario;

    }


    private Alojamiento crearAlojamiento(TipoAlojamiento tipoAlojamiento, String nombreAlojamiento, List<Ciudad> ciudad, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, List<Servicio> servicio) throws Exception {

        return switch (tipoAlojamiento) {

            case CASA -> Casa.builder()
                    .nombreAlojamiento(nombreAlojamiento)
                    .ciudad(ciudad)
                    .descripcionAlojamiento(descripcionAlojamiento)
                    .imagenAlojamiento(imagenAlojamiento)
                    .precioPorNocheAlojamiento(precioPorNocheAlojamiento)
                    .capacidadMaximaAlojamiento(capacidadMaximaAlojamiento)
                    .servicio(servicio)
                    .AseoCasa(90000)
                    .MantenimientoCasa(120000)
                    .build();

            case APARTAMENTO -> Apartamento.builder()
                    .nombreAlojamiento(nombreAlojamiento)
                    .ciudad(ciudad)
                    .descripcionAlojamiento(descripcionAlojamiento)
                    .imagenAlojamiento(imagenAlojamiento)
                    .precioPorNocheAlojamiento(precioPorNocheAlojamiento)
                    .capacidadMaximaAlojamiento(capacidadMaximaAlojamiento)
                    .servicio(servicio)
                    .AseoApartamento()
                    .MantenimientoApartamento()
                    .build();



            default -> throw new Exception("Tipo de usuario no valido.");

        };

    }


    public Alojamiento crearHotel( String nombreAlojamiento, List<Ciudad> ciudad, String descripcionAlojamiento, String imagenAlojamiento, double precioPorNocheAlojamiento, int capacidadMaximaAlojamiento, List<Servicio> servicio, List<Habitacion> habitacion){
        return Hotel.builder()
                .nombreAlojamiento(nombreAlojamiento)
                .ciudad(ciudad)
                .descripcionAlojamiento(descripcionAlojamiento)
                .imagenAlojamiento(imagenAlojamiento)
                .precioPorNocheAlojamiento(precioPorNocheAlojamiento)
                .capacidadMaximaAlojamiento(capacidadMaximaAlojamiento)
                .servicio(servicio)
                .habitacion(habitacion)
                .build();
    }


    private boolean numeroValido(long numero) {

        return numero < 1000000000L || numero > 9999999999L;

    }


    private boolean buscarAdministrador() {

        for (Usuario usuario : usuarios) {

            if (usuario instanceof Administrador) {

                return true;

            }

        }
        return false;

    }


    private int buscarUsuario(long cedula) {

        for (int i = 0; i < usuarios.size(); i++) {

            if (usuarios.get(i).getCedula() == cedula) {

                return i;

            }

        }
        return -1;

    }


    private String generarCodigo() {

        int codigo = (int) (Math.random() * 900000) + 100000;

        return String.valueOf(codigo);

    }


    public List<Usuario> listarUsuarios() {

        return usuarios;

    }


    public List<Alojamiento> buscarAlojamiento(String nombreAlojamiento, TipoAlojamiento tipoAlojamiento, String ciudad, double precioMin, double precioMax) {

        return buscarAlojamiento;

    }

}
