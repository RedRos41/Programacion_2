package org.example;

import org.example.Clase.Clase;
import org.example.Clase.GestionClase;
import org.example.Clase.Reserva;
import org.example.Clase.TipoClase;
import org.example.Entrenador.Entrenador;
import org.example.Usuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestClase {
    private GestionClase gestionClase;

    @BeforeEach
    void setUp() {
        this.gestionClase = new GestionClase();
    }

    @Test
    void testAgregarClase() {

        Entrenador entrenador = new Entrenador("Toro", 2L, "nose");

        String nombreClase = "Test";
        TipoClase tipoClase = TipoClase.AEROBICOS;
        LocalTime horarioClase = LocalTime.of(10, 0);
        LocalDate fechaInicioClase = LocalDate.of(2024, 1, 1);
        LocalDate fechaFinClase = LocalDate.of(2024, 12, 31);
        short capacidad = 30;
        String idClase = "CLASE001";

        gestionClase.agregarClase(nombreClase, tipoClase, entrenador, horarioClase, fechaInicioClase, fechaFinClase, capacidad, idClase);

        assertEquals(1, gestionClase.getClases().size(), "El tamaño de la lista de clases debería ser 1");
    }

    @Test
    void testVerificarClaseId() {

        String idClase = "CLASE001";

        gestionClase.verificarClaseId(idClase);

        assertEquals("CLASE001", idClase, "El idClase de la clase debería ser CLASE001");
    }

    @Test
    void testBuscarClaseHorario() {

        Entrenador entrenador = new Entrenador("Toro", 2L, "nose");

        String nombreClase = "Test2";
        TipoClase tipoClase = TipoClase.FLEXIBILIDAD;
        LocalTime horarioClase = LocalTime.of(20, 0);
        LocalDate fechaInicioClase = LocalDate.of(2024, 2, 2);
        LocalDate fechaFinClase = LocalDate.of(2024, 12, 31);
        short capacidad = 20;
        String idClase = "CLASE002";

        gestionClase.agregarClase(nombreClase, tipoClase, entrenador, horarioClase, fechaInicioClase, fechaFinClase, capacidad, idClase);

        gestionClase.buscarClaseHorario(horarioClase);

        assertEquals(LocalTime.of(20, 0), horarioClase, "El horario de la clase debería ser 20:10");
    }

    @Test
    void testBuscarClaseTipo() {

        Entrenador entrenador = new Entrenador("Toro", 2L, "nose");

        String nombreClase = "Test3";
        TipoClase tipoClase = TipoClase.FUERZA;
        LocalTime horarioClase = LocalTime.of(20, 3);
        LocalDate fechaInicioClase = LocalDate.of(2024, 3, 3);
        LocalDate fechaFinClase = LocalDate.of(2024, 12, 31);
        short capacidad = 10;
        String idClase = "CLASE003";

        gestionClase.agregarClase(nombreClase, tipoClase, entrenador, horarioClase, fechaInicioClase, fechaFinClase, capacidad, idClase);

        gestionClase.buscarClaseTipo(tipoClase);

        assertEquals(TipoClase.FUERZA, tipoClase, "El tipo de la clase debería ser FUERZA");
    }

    @Test
    void testVerificarDisponibilidadClase() {

        Entrenador entrenador = new Entrenador("Toro", 2L, "nose");

        String nombreClase = "Test3";
        TipoClase tipoClase = TipoClase.FUERZA;
        LocalTime horarioClase = LocalTime.of(20, 3);
        LocalDate fechaInicioClase = LocalDate.of(2024, 3, 3);
        LocalDate fechaFinClase = LocalDate.of(2024, 12, 31);
        short capacidad = 10;
        String idClase = "CLASE003";

        gestionClase.agregarClase(nombreClase, tipoClase, entrenador, horarioClase, fechaInicioClase, fechaFinClase, capacidad, idClase);

        Clase clase = gestionClase.getClases().get(0);

        assertTrue(gestionClase.verificarDisponibilidadClase(clase), "La clase debería estar disponible cuando no hay reservas");
    }

    @Test
    void testActualizarEstadoClase() {

        Entrenador entrenador = new Entrenador("Toro", 2L, "nose");

        String nombreClase = "Test3";
        TipoClase tipoClase = TipoClase.FUERZA;
        LocalTime horarioClase = LocalTime.of(20, 3);
        LocalDate fechaInicioClase = LocalDate.of(2024, 3, 3);
        LocalDate fechaFinClase = LocalDate.of(2024, 12, 31);
        short capacidad = 10;
        String idClase = "CLASE003";

        gestionClase.agregarClase(nombreClase, tipoClase, entrenador, horarioClase, fechaInicioClase, fechaFinClase, capacidad, idClase);

        Clase clase = gestionClase.getClases().get(0);

        gestionClase.actualizarEstadoClase(clase);

        assertTrue(clase.isEstadoClase(), "El estado de la clase debería ser true cuando no está llena");
    }

    @Test
    void testAgregarReserva() {

        Entrenador entrenador = new Entrenador("Toro", 2L, "nose");
        Usuario usuario = new Usuario("Juan", 1L, "Calle 123", "juan@example.com", "password123", 1234567890L);

        String nombreClase = "Test";
        TipoClase tipoClase = TipoClase.AEROBICOS;
        LocalTime horarioClase = LocalTime.of(10, 0);
        LocalDate fechaInicioClase = LocalDate.of(2024, 1, 1);
        LocalDate fechaFinClase = LocalDate.of(2024, 12, 31);
        short capacidad = 30;
        String idClase = "CLASE001";
        LocalDateTime fechaReserva = LocalDateTime.of(2024, 3, 5, 10, 0);;
        String codigo = "RESERVA001";

        gestionClase.agregarClase(nombreClase, tipoClase, entrenador, horarioClase, fechaInicioClase, fechaFinClase, capacidad, idClase);

        Clase clase = gestionClase.getClases().get(0);

        gestionClase.agregarReserva(usuario, fechaReserva, clase, codigo);

        assertEquals(1, clase.getRegistroReserva().size(), "El tamaño de la lista de reservas debería ser 1");
    }

    @Test
    void testCancelarReserva() {

        Entrenador entrenador = new Entrenador("Toro", 2L, "nose");
        Usuario usuario = new Usuario("Juan", 1L, "Calle 123", "juan@example.com", "password123", 1234567890L);

        String nombreClase = "Test";
        TipoClase tipoClase = TipoClase.AEROBICOS;
        LocalTime horarioClase = LocalTime.of(10, 0);
        LocalDate fechaInicioClase = LocalDate.of(2024, 1, 1);
        LocalDate fechaFinClase = LocalDate.of(2024, 12, 31);
        short capacidad = 30;
        String idClase = "CLASE001";
        LocalDateTime fechaReserva = LocalDateTime.of(2024, 3, 5, 10, 0);;
        String codigo = "RESERVA001";

        gestionClase.agregarClase(nombreClase, tipoClase, entrenador, horarioClase, fechaInicioClase, fechaFinClase, capacidad, idClase);

        Clase clase = gestionClase.getClases().get(0);

        gestionClase.agregarReserva(usuario, fechaReserva, clase, codigo);

        gestionClase.cancelarReserva(usuario, codigo);

        assertEquals(0, clase.getRegistroReserva().size(), "El tamaño de la lista de clases debería ser 0");
    }

    @Test
    void testImprimirClases() {

        Entrenador entrenador = new Entrenador("Toro", 2L, "nose");

        String nombreClase = "Test";
        TipoClase tipoClase = TipoClase.AEROBICOS;
        LocalTime horarioClase = LocalTime.of(10, 0);
        LocalDate fechaInicioClase = LocalDate.of(2024, 1, 1);
        LocalDate fechaFinClase = LocalDate.of(2024, 12, 31);
        short capacidad = 30;
        String idClase = "CLASE001";

        gestionClase.agregarClase(nombreClase, tipoClase, entrenador, horarioClase, fechaInicioClase, fechaFinClase, capacidad, idClase);

        gestionClase.imprimirClases();
    }

    @Test
    void testImprimirReservas() {

        Entrenador entrenador = new Entrenador("Toro", 2L, "nose");
        Usuario usuario = new Usuario("Juan", 1L, "Calle 123", "juan@example.com", "password123", 1234567890L);

        String nombreClase = "Test";
        TipoClase tipoClase = TipoClase.AEROBICOS;
        LocalTime horarioClase = LocalTime.of(10, 0);
        LocalDate fechaInicioClase = LocalDate.of(2024, 1, 1);
        LocalDate fechaFinClase = LocalDate.of(2024, 12, 31);
        short capacidad = 30;
        String idClase = "CLASE001";
        LocalDateTime fechaReserva = LocalDateTime.of(2024, 3, 5, 10, 0);;
        String codigo = "RESERVA001";

        gestionClase.agregarClase(nombreClase, tipoClase, entrenador, horarioClase, fechaInicioClase, fechaFinClase, capacidad, idClase);

        Clase clase = gestionClase.getClases().get(0);

        gestionClase.agregarReserva(usuario, fechaReserva, clase, codigo);

        List<Reserva> reservas = gestionClase.imprimirReservas();

        assertEquals(1, reservas.size(), "Debería haber 1 reservas en total.");
    }
}
