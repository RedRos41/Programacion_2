package org.example.Usuario;

import java.util.List;
import java.util.ArrayList;

public class GestionUsuario {

    private final List<Usuario> usuarios;

    public GestionUsuario() {
        this.usuarios = new ArrayList<>();
    }

    public void agregarUsuario(String nombreUsuario, String direccion, String correo, String contrasena, long idUsuario, long telefono) {
        Usuario usuario = new Usuario(nombreUsuario, direccion, correo, contrasena, idUsuario, telefono);
        usuarios.add(usuario);
    }

    public void actualizarUsuario() {

    }

    public void eliminarUsuario() {

    }

    public void imprimirUsuario() {
        System.out.println(usuarios);
    }
}
