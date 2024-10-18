package co.edu.uniquindio.proyectofinal.modelosPrincipal;


import co.edu.uniquindio.proyectofinal.modelos.Usuario;
import java.util.List;
import java.util.ArrayList;


public class UsuarioPrincipal {

    private final List<Usuario> usuarios;

    public UsuarioPrincipal() {

        usuarios = new ArrayList<>();

    }


    public void registrarUsuario(long cedula, String nombreCompleto, String email, String contraseña, long telefono) throws Exception {

        if (nombreCompleto.isEmpty() || email.isEmpty() || contraseña.isEmpty())
            throw new Exception("Este campo no puede estar vacio");

        if (cedula < 1000000000L || cedula > 9999999999L || telefono < 1000000000L || telefono > 9999999999L)
            throw new Exception("Este campo debe tener exactamente 10 digitos");

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"))
            throw new Exception("Este campo debe tener el formato del correo electrónico");

        if (buscarUsuario(cedula) != -1)
            throw new Exception("Ya existe un usuario con la cédula proporcionada.");

        Usuario usuario = Usuario.builder()
                .cedula(cedula)
                .nombreCompleto(nombreCompleto)
                .email(email)
                .contraseña(contraseña)
                .telefono(telefono)
                .build();

        usuarios.add(usuario);

    }


    public void editarUsuario(long cedula, String nombreCompleto, String email, long telefono) throws Exception {

        if(nombreCompleto.isEmpty() || email.isEmpty())
            throw new Exception("Este campo no puede estar vacio");

        if(cedula < 1000000000L || cedula > 9999999999L || telefono < 1000000000L || telefono > 9999999999L)
            throw new Exception("Este campo debe tener exactamente 10 digitos");

        if(!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"))
            throw new Exception("Este campo debe tener el formato del correo electrónico");

        int idUsuario = buscarUsuario(cedula);

        if(idUsuario == -1)
            throw new Exception("No existe un usuario con la cedula proporcionada");

        Usuario editarUsuario = usuarios.get(idUsuario);
        editarUsuario.setCedula(cedula);
        editarUsuario.setNombreCompleto(nombreCompleto);
        editarUsuario.setEmail(email);
        editarUsuario.setTelefono(telefono);

        usuarios.set(idUsuario, editarUsuario);

    }


    public void eliminarUsuario(long cedula) throws Exception {

        if(cedula < 1000000000L || cedula > 9999999999L)
            throw new Exception("Este campo debe tener exactamente 10 digitos");

        int idUsuario = buscarUsuario(cedula);

        if(idUsuario == -1)
            throw new Exception("No existe un usuario con la cedula proporcionada");

        usuarios.remove(usuarios.get(idUsuario));

    }


    public void iniciarSesion(String email, String contraseña) throws Exception {

    }


    public void cambiarContraseña(String codigo, String nuevaContraseña) throws Exception {

    }


    private int buscarUsuario(long cedula) {

        for(int i = 0; i < usuarios.size(); i++) {

            if(usuarios.get(i).getCedula() == cedula) {

                return i;

            }

        }
        return -1;

    }


    public List<Usuario> listarUsuarios() {

        return usuarios;

    }

}
