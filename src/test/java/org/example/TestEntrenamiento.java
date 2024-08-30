package org.example;

import org.example.Entrenador.Entrenador;
import org.example.Entrenamiento.Entrenamiento;
import org.example.Entrenamiento.GestionEntrenamiento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.Entrenamiento.TipoEntrenamiento;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class TestEntrenamiento {
    private GestionEntrenamiento gestionEntrenamiento;


    @BeforeEach
    void setUp() {
        gestionEntrenamiento = new GestionEntrenamiento();
    }

    void testAgregarEntrenamiento() {
        Entrenamiento entrenamiento = new Entrenamiento(TipoEntrenamiento.CARDIO, "Cardio Básico", 30, 300);
        gestionEntrenamiento.agregarEntrenamiento(entrenamiento);
        assertEquals(1, gestionEntrenamiento.listarEntrenamientos().size(), "El tamaño de la lista de entrenamientos debería ser 1");
    }

    @Test
    void testAgregarEntrenamientoExistente() {
        Entrenamiento entrenamiento1 = new Entrenamiento(TipoEntrenamiento.CARDIO, "Cardio Básico", 30, 300);
        Entrenamiento entrenamiento2 = new Entrenamiento(TipoEntrenamiento.HIIT, "HIIT Avanzado", 45, 500);
        gestionEntrenamiento.agregarEntrenamiento(entrenamiento1);
        gestionEntrenamiento.agregarEntrenamiento(entrenamiento2);
        assertEquals(2, gestionEntrenamiento.listarEntrenamientos().size(), "El tamaño de la lista de entrenamientos debería ser 2 ya que los tipos son diferentes");
    }

    @Test
    void testBuscarEntrenamientoPorTipo() {
        Entrenamiento entrenamiento1 = new Entrenamiento(TipoEntrenamiento.CARDIO, "Cardio Básico", 30, 300);
        Entrenamiento entrenamiento2 = new Entrenamiento(TipoEntrenamiento.HIIT, "HIIT Avanzado", 45, 500);
        gestionEntrenamiento.agregarEntrenamiento(entrenamiento1);
        gestionEntrenamiento.agregarEntrenamiento(entrenamiento2);

        List<Entrenamiento> entrenamientosCardio = gestionEntrenamiento.buscarEntrenamientoPorTipo(TipoEntrenamiento.CARDIO);
        assertEquals(1, entrenamientosCardio.size(), "Debería haber 1 entrenamiento de tipo CARDIO");

        List<Entrenamiento> entrenamientosHIIT = gestionEntrenamiento.buscarEntrenamientoPorTipo(TipoEntrenamiento.HIIT);
        assertEquals(1, entrenamientosHIIT.size(), "Debería haber 1 entrenamiento de tipo HIIT");
    }

    @Test
    void testBuscarEntrenamientoPorTipoNoExistente() {
        Entrenamiento entrenamiento = new Entrenamiento(TipoEntrenamiento.CARDIO, "Cardio Básico", 30, 300);
        gestionEntrenamiento.agregarEntrenamiento(entrenamiento);

        List<Entrenamiento> entrenamientosPesas = gestionEntrenamiento.buscarEntrenamientoPorTipo(TipoEntrenamiento.PESAS);
        assertEquals(0, entrenamientosPesas.size(), "No debería haber entrenamientos de tipo PESAS");
    }

    @Test
    void testEliminarEntrenamientoPorTipo() {
        Entrenamiento entrenamiento = new Entrenamiento(TipoEntrenamiento.CARDIO, "Cardio Básico", 30, 300);
        gestionEntrenamiento.agregarEntrenamiento(entrenamiento);
        gestionEntrenamiento.eliminarEntrenamientoPorTipo(TipoEntrenamiento.CARDIO);
        assertEquals(0, gestionEntrenamiento.listarEntrenamientos().size(), "El tamaño de la lista de entrenamientos debería ser 0 después de eliminar el entrenamiento de tipo CARDIO");
    }

    @Test
    void testEliminarEntrenamientoPorTipoNoExistente() {
        Entrenamiento entrenamiento = new Entrenamiento(TipoEntrenamiento.CARDIO, "Cardio Básico", 30, 300);
        gestionEntrenamiento.agregarEntrenamiento(entrenamiento);
        gestionEntrenamiento.eliminarEntrenamientoPorTipo(TipoEntrenamiento.PESAS);
        assertEquals(1, gestionEntrenamiento.listarEntrenamientos().size(), "El tamaño de la lista de entrenamientos debería seguir siendo 1 porque no se eliminó ningún entrenamiento existente");
    }

    @Test
    void testImprimirEntrenamiento() {
        Entrenamiento entrenamiento = new Entrenamiento(TipoEntrenamiento.CARDIO, "Cardio Básico", 30, 300);
        gestionEntrenamiento.agregarEntrenamiento(entrenamiento);
        gestionEntrenamiento.imprimirEntrenamientos();
    }

    @Test
    void testImprimirEntrenamientoNoExistente() {
        gestionEntrenamiento.imprimirEntrenamientos();
        assertTrue(gestionEntrenamiento.listarEntrenamientos().isEmpty(), "La lista de entrenamientos debería estar vacía.");
    }
}

