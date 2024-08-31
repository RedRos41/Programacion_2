package org.example;

import org.example.Usuario.GestionUsuario;
import org.example.Usuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestUsuario {
    private GestionUsuario gestionUsuario;

    @BeforeEach
    void setUp() {
        this.gestionUsuario = new GestionUsuario();
    }

    @Test
    void testAgregarUsuario() {
        gestionUsuario.agregarUsuario("Juan", 1L, "Calle 123", "juan@example.com", "password123", 1234567890L);
        assertEquals(1, gestionUsuario.getUsuarios().size(), "El tamaño de la lista de usuarios debería ser 1");
    }

    @Test
    void testAgregarUsuarioExistente() {
        gestionUsuario.agregarUsuario("Juan", 1L, "Calle 123", "juan@example.com", "password123", 1234567890L);
        gestionUsuario.agregarUsuario("Pedro", 1L, "Calle 456", "pedro@example.com", "password456", 9876543210L);
        assertEquals(1, gestionUsuario.getUsuarios().size(), "El tamaño de la lista de usuarios debería seguir siendo 1 ya que el ID es el mismo");
    }

    @Test
    void testActualizarUsuario() {
        gestionUsuario.agregarUsuario("Juan", 1L, "Calle 123", "juan@example.com", "password123", 1234567890L);
        gestionUsuario.actualizarUsuario(1L, "Juan Actualizado", "Calle 456", "juan_updated@example.com", "newpassword", "9876543210");

        Usuario usuarioActualizado = gestionUsuario.getUsuarios().get(0);
        assertEquals("Juan Actualizado", usuarioActualizado.getNombre(), "El nombre debería haberse actualizado");
        assertEquals("Calle 456", usuarioActualizado.getDireccion(), "La dirección debería haberse actualizado");
        assertEquals("juan_updated@example.com", usuarioActualizado.getCorreo(), "El correo debería haberse actualizado");
        assertEquals("newpassword", usuarioActualizado.getContrasena(), "La contraseña debería haberse actualizado");
        assertEquals(9876543210L, usuarioActualizado.getTelefono(), "El teléfono debería haberse actualizado");
    }

    @Test
    void testActualizarUsuarioNoExistente() {
        gestionUsuario.actualizarUsuario(2L, "Juan Actualizado", "Calle 456", "juan_updated@example.com", "newpassword", "9876543210");
        assertEquals(0, gestionUsuario.getUsuarios().size(), "No debería haber usuarios ya que no se agregó ninguno");
    }

    @Test
    void testEliminarUsuario() {
        gestionUsuario.agregarUsuario("Juan", 1L, "Calle 123", "juan@example.com", "password123", 1234567890L);
        gestionUsuario.eliminarUsuario(1L);
        assertEquals(0, gestionUsuario.getUsuarios().size(), "El tamaño de la lista de usuarios debería ser 0 después de la eliminación");
    }

    @Test
    void testEliminarUsuarioNoExistente() {
        gestionUsuario.agregarUsuario("Juan", 1L, "Calle 123", "juan@example.com", "password123", 1234567890L);
        gestionUsuario.eliminarUsuario(2L);
        assertEquals(1, gestionUsuario.getUsuarios().size(), "El tamaño de la lista de usuarios debería seguir siendo 1 porque no se eliminó ningún usuario existente");
    }

    @Test
    void testImprimirUsuario() {
        gestionUsuario.agregarUsuario("Juan", 1L, "Calle 123", "juan@example.com", "password123", 1234567890L);
        gestionUsuario.imprimirUsuario();
    }

    @Test
    void testImprimirUsuarioNoExistente() {
        gestionUsuario.imprimirUsuario();
    }
}
