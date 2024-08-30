package org.example;

import org.example.Entrenador.Entrenador;
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
    void testAgregarEntrenador() {
        gestionEntrenador.agregarEntrenador("Juan", 123, "Calle 123");
        assertEquals(1, gestionEntrenador.getEntrenadores().size(), "El tamaño de la lista de entrenador debería ser 1");
    }

    @Test
    void testAgregarEntrenadorExistente() {
        gestionEntrenador.agregarEntrenador("Carlos", 123, "especialidad flexibilidad");
        gestionEntrenador.agregarEntrenador("Pedro", 123, "especialidad cardio");
        assertEquals(1, gestionEntrenador.getEntrenadores().size(), "El tamaño de la lista de entrenadores debería seguir siendo 1 ya que el ID es el mismo");
    }

    @Test
    void testActualizarEntrenador() {
        gestionEntrenador.agregarEntrenador("Carlos", 123, "Especialidad flexibilidad");
        gestionEntrenador.actualizarEntrenador(1L, "Carlos Actualizado", "especialidad cardio");

        Entrenador entrenadorActualizado = gestionEntrenador.getEntrenadores().get(0);
        assertEquals("Carlos Actualizado", entrenadorActualizado.getNombre(), "El nombre debería haberse actualizado");
        assertEquals("Especialidad cardio", entrenadorActualizado.getEspecialidad(), "La especializacion debería haberse actualizado");
    }

    @Test
    void testActualizarEntrenadorNoExistente() {
        gestionEntrenador.actualizarEntrenador(234, "Carlos Actualizado", "especialidad cardio");
        assertEquals(0, gestionEntrenador.getEntrenadores().size(), "No debería haber entrenadores ya que no se agregó ninguno");
    }

    @Test
    void testEliminarEntrenador() {
        gestionEntrenador.agregarEntrenador("Carlos", 789, "especialidad fuerza");
        gestionEntrenador.eliminarEntrenador(789);
        assertEquals(0, gestionEntrenador.getEntrenadores().size(), "El tamaño de la lista de entrenadores debería ser 0 después de la eliminación");
    }

    @Test
    void testEliminarEntrenadorNoExistente() {
        gestionEntrenador.agregarEntrenador("Carlos", 123, "especialidad fuerza");
        gestionEntrenador.eliminarEntrenador(789);
        assertEquals(1, gestionEntrenador.getEntrenadores().size(), "El tamaño de la lista de entrenadores debería seguir siendo 1 porque no se eliminó ningún entrenador existente");
    }

    @Test
    void testImprimirEntrenador() {
        gestionEntrenador.agregarEntrenador("Carlos", 456, "especialidad fuerza");
        gestionEntrenador.imprimirEntrenador();
    }

    @Test
    void testImprimirEntrenadorNoExistente() {
        gestionEntrenador.imprimirEntrenador();
    }
}

