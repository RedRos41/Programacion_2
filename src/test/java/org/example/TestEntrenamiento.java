package org.example;

import org.example.Entrenamiento.GestionEntrenamiento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEntrenamiento {
    private GestionEntrenamiento gestionEntrenamiento;

    @BeforeEach
    void setUp() {
        gestionEntrenamiento = new GestionEntrenamiento();
    }

    @Test
    void testAgregarEntrenamiento() {
        gestionEntrenamiento.agregarEntrenamiento(1);
        assertEquals(1, gestionEntrenamiento.listarEntrenamientos().size(), "El tamaño de la lista de entrenador debería ser 1");
    }
}
