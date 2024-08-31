package org.example.Usuario;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class GestionUsuario {
    private final List<Usuario> usuarios;

    public GestionUsuario() {
        this.usuarios = new ArrayList<>();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void agregarUsuario(String nombre, long id, String direccion, String correo, String contrasena, long telefono) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                System.out.println("Usuario con ID " + id + " ya existe.");
                return;
            }
        }
        Usuario usuario = new Usuario(nombre, id, direccion, correo, contrasena, telefono);
        usuarios.add(usuario);
        System.out.println("Usuario con ID " + id + " agregado correctamente.");
    }

    public void actualizarUsuario(long id, String nombre, String direccion, String correo, String contrasena, String telefono) {
        boolean actualizar = false;

        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                if (!nombre.isEmpty()) {
                    usuario.setNombre(nombre);
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
                System.out.println("Usuario con ID " + id + " actualizado correctamente.");
                break;
            }
        }
        if (!actualizar) {
            System.out.println("Usuario con ID " + id + " no encontrado.");
        }
    }

    public void eliminarUsuario(long id) {
        boolean eliminar = false;

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == id) {
                usuarios.remove(i);
                eliminar = true;
                System.out.println("Usuario con ID " + id + " eliminado correctamemte.");
                break;
            }
        }
        if (!eliminar) {
            System.out.println("Usuario con ID " + id + " no encontrado.");
        }
    }

    public void imprimirUsuario() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            System.out.println("Usuarios registrados:");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        }
    }

    public Optional<Usuario> buscarUsuarioPorId(long id) {
        return usuarios.stream()
                .filter(usuario -> usuario.getId() == id)
                .findFirst();
    }
}
