package org.example;

import org.example.Entrenador.GestionEntrenador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestEntrenador {
    private GestionEntrenador gestionEntrenador;

    @BeforeEach
    void setUp() {
        gestionEntrenador = new GestionEntrenador();
    }

    @Test
    void testAgregarUsuario() {
        gestionEntrenador.agregarEntrenador("Juan", 1L, "Calle 123");
        assertEquals(1, gestionEntrenador.getEntrenadores().size(), "El tamaño de la lista de entrenador debería ser 1");
    }
}
