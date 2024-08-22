package org.example.Usuario;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionUsuario {
    private final List<Usuario> usuarios;

    public GestionUsuario() {
        this.usuarios = new ArrayList<>();
    }

    public void agregarUsuario(String nombreUsuario, String direccion, String correo, String contrasena, long idUsuario, long telefono) {
        Usuario usuario = new Usuario(nombreUsuario, direccion, correo, contrasena, idUsuario, telefono);
        usuarios.add(usuario);
        System.out.println("Usuario con ID " + idUsuario + " agregado correctamente.");
    }

    public void actualizarUsuario(long idUsuario, String nombreUsuario, String direccion, String correo, String contrasena, String telefono) {
        boolean actualizar = false;

        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario() == idUsuario) {
                if (!nombreUsuario.isEmpty()) {
                    usuario.setNombreUsuario(nombreUsuario);
                }
                if (!direccion.isEmpty()) {
                    usuario.setDireccion(direccion);
                }
                if (!correo.isEmpty()) {
                    usuario.setCorreo(correo);
                }
                if (!contrasena.isEmpty()) {
                    usuario.setContrasena(contrasena);
                }
                if (!telefono.isEmpty()) {
                    usuario.setTelefono(Long.parseLong(telefono));
                }
                actualizar = true;
                System.out.println("Usuario con ID " + idUsuario + " actualizado correctamente.");
                break;
            }
        }
        if (!actualizar) {
            System.out.println("Usuario con ID " + idUsuario + " no encontrado.");
        }
    }

    public void eliminarUsuario(long idUsuario) {
        boolean eliminar = false;

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getIdUsuario() == idUsuario) {
                usuarios.remove(i);
                eliminar = true;
                System.out.println("Usuario con ID " + idUsuario + " eliminado correctamemte.");
                break;
            }
        }
        if (!eliminar) {
            System.out.println("Usuario con ID " + idUsuario + " no encontrado.");
        }
    }

    public void imprimirUsuario() {
        System.out.println("Usuarios registrados:");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }
}
