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


    public Empresa() {

        usuarios = new ArrayList<>();

    }


    public void registrarUsuario(TipoUsuario tipoUsuario, long cedula, String nombreCompleto, String email, String contraseña, long telefono) throws Exception {

        if (nombreCompleto.isBlank() || email.isBlank() || contraseña.isBlank()) {

            throw new Exception("Este campo no puede estar vacio.");

        }

        if (numeroValido(cedula) || numeroValido(telefono)) {

            throw new Exception("Este campo debe tener exactamente 10 digitos.");

        }

        if (!nombreCompleto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {

            throw new Exception("El nombre no puede contener números ni caracteres especiales.");

        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {

            throw new Exception("Este campo debe tener el formato del correo electronico.");

        }

        if (contraseña.length() < 8) {

            throw new Exception("La contraseña debe tener al menos 8 caracteres.");

        }

        if (buscarUsuario(cedula) != -1) {

            throw new Exception("Ya existe un usuario con la cédula proporcionada.");

        }

        if (tipoUsuario == TipoUsuario.ADMINISTRADOR && buscarAdministrador()) {

            throw new Exception("Ya existe un administrador registrado.");

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

        if (nombreCompleto.isBlank() || email.isBlank()) {

            throw new Exception("Este campo no puede estar vacio.");

        }

        if (numeroValido(cedula) || numeroValido(telefono)) {

            throw new Exception("Este campo debe tener exactamente 10 digitos.");

        }

        if (!nombreCompleto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {

            throw new Exception("El nombre no puede contener números ni caracteres especiales.");

        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {

            throw new Exception("Este campo debe tener el formato del correo electronico.");

        }

        int idUsuario = buscarUsuario(cedula);

        if (idUsuario == -1) {

            throw new Exception("No existe un usuario con la cedula proporcionada.");

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

            throw new Exception("Este campo debe tener exactamente 10 digitos.");

        }

        int idUsuario = buscarUsuario(cedula);

        if (idUsuario == -1) {

            throw new Exception("No existe un usuario con la cedula proporcionada.");

        }

        usuarios.remove(usuarios.get(idUsuario));

    }


    public void solicitarCambioContraseña(String email) throws Exception {

        if (email.isBlank()) {

            throw new Exception("Este campo no puede estar vacio.");

        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {

            throw new Exception("Este campo debe tener el formato del correo electrónico.");

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

        Usuario usuario = buscarUsuarioPorCodigoContraseña(codigoContraseña);

        if (usuario == null) {

            throw new Exception("El código de cambio de contraseña proporcionado es incorrecto.");

        }

        if (nuevaContraseña.isBlank()) {

            throw new Exception("Este campo no puede estar vacio.");

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


    public Usuario iniciarSesion(String codigoActivacion, String email, String contraseña) throws Exception {

        for (Usuario usuario : usuarios) {

            if (usuario.getCodigoActivacion().equals(codigoActivacion)) {

                if (usuario.getEmail().equals(email) && usuario.getContraseña().equals(contraseña)) {

                    return usuario;

                } else {

                    throw new Exception("Las credenciales son incorrectas.");

                }

            }

        }
        throw new Exception("El codigo de activación proporcionado es incorrecto.");

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

}
