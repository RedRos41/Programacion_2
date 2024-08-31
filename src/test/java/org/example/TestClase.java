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

        gestionClase.agregarClase(nombreClase, tipoClase, entrenador, horarioClase, fechaInicioClase, fechaFinClase, capacidad, idClase);

        assertEquals(1, gestionClase.getClases().size(), "El tamaño de la lista de clases debería seguir siendo 1 ya que no se deben permitir IDs duplicados.");
    }

    @Test
    void testVerificarClaseId() {

        Entrenador entrenador = new Entrenador("Toro", 2L, "nose");

        String nombreClase = "Test";
        TipoClase tipoClase = TipoClase.AEROBICOS;
        LocalTime horarioClase = LocalTime.of(10, 0);
        LocalDate fechaInicioClase = LocalDate.of(2024, 1, 1);
        LocalDate fechaFinClase = LocalDate.of(2024, 12, 31);
        short capacidad = 30;
        String idClase = "CLASE001";

        gestionClase.agregarClase(nombreClase, tipoClase, entrenador, horarioClase, fechaInicioClase, fechaFinClase, capacidad, idClase);

        Clase claseEncontrada = gestionClase.verificarClaseId(idClase);
        assertNotNull(claseEncontrada, "La clase debería ser encontrada por su ID.");
        assertEquals(idClase, claseEncontrada.getIdClase(), "El ID de la clase encontrada debería ser 'CLASE001'.");

        Clase claseNoExistente = gestionClase.verificarClaseId("CLASE_NO_EXISTE");
        assertNull(claseNoExistente, "No debería encontrar ninguna clase con un ID inexistente.");
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

        List<Clase> clasesEncontradas = gestionClase.buscarClaseHorario(horarioClase);

        assertEquals(1, clasesEncontradas.size(), "Debería haber exactamente una clase con el horario 20:00.");

        Clase claseEncontrada = clasesEncontradas.get(0);
        assertEquals(horarioClase, claseEncontrada.getHorarioClase(), "El horario de la clase encontrada debería ser 20:00.");
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

        List<Clase> clasesEncontradas = gestionClase.buscarClaseTipo(tipoClase);

        assertEquals(1, clasesEncontradas.size(), "Debería haber exactamente una clase con el tipo FUERZA.");

        Clase claseEncontrada = clasesEncontradas.get(0);
        assertEquals(tipoClase, claseEncontrada.getTipoClase(), "El tipo de la clase encontrada debería ser FUERZA.");
    }

    @Test
    void testBuscarClaseEntrenador() {

        Entrenador entrenador = new Entrenador("Toro", 2L, "nose");

        String nombreClase = "Test3";
        TipoClase tipoClase = TipoClase.FUERZA;
        LocalTime horarioClase = LocalTime.of(20, 3);
        LocalDate fechaInicioClase = LocalDate.of(2024, 3, 3);
        LocalDate fechaFinClase = LocalDate.of(2024, 12, 31);
        short capacidad = 10;
        String idClase = "CLASE003";

        gestionClase.agregarClase(nombreClase, tipoClase, entrenador, horarioClase, fechaInicioClase, fechaFinClase, capacidad, idClase);

        List<Clase> clasesEncontradas = gestionClase.buscarClaseEntrenador(entrenador);

        assertEquals(1, clasesEncontradas.size(), "Debería haber exactamente una clase para el entrenador Toro.");

        Clase claseEncontrada = clasesEncontradas.get(0);
        assertEquals(entrenador, claseEncontrada.getEntrenador(), "El entrenador de la clase encontrada debería ser Toro.");
    }

    @Test
    void testVerificarDisponibilidadClase() {

        Entrenador entrenador = new Entrenador("Toro", 2L, "nose");
        Usuario usuario = new Usuario("Juan", 1L, "Calle 123", "juan@example.com", "password123", 1234567890L);

        String nombreClase = "Test3";
        TipoClase tipoClase = TipoClase.FUERZA;
        LocalTime horarioClase = LocalTime.of(20, 3);
        LocalDate fechaInicioClase = LocalDate.of(2024, 3, 3);
        LocalDate fechaFinClase = LocalDate.of(2024, 12, 31);
        short capacidad = 10;
        String idClase = "CLASE003";
        LocalDateTime fechaReserva = LocalDateTime.of(2024, 3, 5, 10, 0);

        gestionClase.agregarClase(nombreClase, tipoClase, entrenador, horarioClase, fechaInicioClase, fechaFinClase, capacidad, idClase);

        Clase clase = gestionClase.getClases().get(0);

        assertTrue(gestionClase.verificarDisponibilidadClase(clase), "La clase debería estar disponible cuando no hay reservas.");

        for (int i = 0; i < 9; i++) {
            String codigo = "RESERVA00" + (i + 1);
            Reserva reserva = gestionClase.agregarReserva(usuario, fechaReserva.plusDays(i), clase, codigo);
            assertNotNull(reserva, "La reserva debería haberse creado correctamente.");
        }

        assertTrue(gestionClase.verificarDisponibilidadClase(clase), "La clase debería estar disponible cuando hay menos reservas que la capacidad.");

        String ultimoCodigo = "RESERVA0010";
        Reserva ultimaReserva = gestionClase.agregarReserva(usuario, fechaReserva.plusDays(10), clase, ultimoCodigo);
        assertNotNull(ultimaReserva, "La última reserva debería haberse creado correctamente.");

        assertFalse(gestionClase.verificarDisponibilidadClase(clase), "La clase no debería estar disponible cuando está llena.");
    }

    @Test
    void testActualizarEstadoClase() {

        Entrenador entrenador = new Entrenador("Toro", 2L, "nose");
        Usuario usuario = new Usuario("Juan", 1L, "Calle 123", "juan@example.com", "password123", 1234567890L);

        String nombreClase = "Test3";
        TipoClase tipoClase = TipoClase.FUERZA;
        LocalTime horarioClase = LocalTime.of(20, 3);
        LocalDate fechaInicioClase = LocalDate.of(2024, 3, 3);
        LocalDate fechaFinClase = LocalDate.of(2024, 12, 31);
        short capacidad = 10;
        String idClase = "CLASE003";
        LocalDateTime fechaReserva = LocalDateTime.of(2024, 3, 5, 10, 0);

        gestionClase.agregarClase(nombreClase, tipoClase, entrenador, horarioClase, fechaInicioClase, fechaFinClase, capacidad, idClase);

        Clase clase = gestionClase.getClases().get(0);

        gestionClase.actualizarEstadoClase(clase);
        assertTrue(clase.isEstadoClase(), "El estado de la clase debería ser true cuando no está llena.");

        for (int i = 0; i < 9; i++) {
            String codigo = "RESERVA00" + (i + 1);
            Reserva reserva = gestionClase.agregarReserva(usuario, fechaReserva.plusDays(i), clase, codigo);
            assertNotNull(reserva, "La reserva debería haberse creado correctamente.");
        }

        gestionClase.actualizarEstadoClase(clase);
        assertTrue(clase.isEstadoClase(), "El estado de la clase debería ser true cuando hay menos reservas que la capacidad.");

        String ultimoCodigo = "RESERVA0010";
        Reserva ultimaReserva = gestionClase.agregarReserva(usuario, fechaReserva.plusDays(10), clase, ultimoCodigo);
        assertNotNull(ultimaReserva, "La última reserva debería haberse creado correctamente.");

        gestionClase.actualizarEstadoClase(clase);
        assertFalse(clase.isEstadoClase(), "El estado de la clase debería ser false cuando está llena.");
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
        LocalDateTime fechaReserva = LocalDateTime.of(2024, 3, 5, 10, 0);

        gestionClase.agregarClase(nombreClase, tipoClase, entrenador, horarioClase, fechaInicioClase, fechaFinClase, capacidad, idClase);

        Clase clase = gestionClase.getClases().get(0);

        String codigo = "RESERVA001";
        Reserva reserva = gestionClase.agregarReserva(usuario, fechaReserva, clase, codigo);
        assertNotNull(reserva, "La reserva debería haberse creado correctamente.");
        assertEquals(1, clase.getRegistroReserva().size(), "El tamaño de la lista de reservas debería ser 1");

        for (int i = 0; i < capacidad; i++) {
            String nuevoCodigo = "RESERVA00" + (i + 2);
            gestionClase.agregarReserva(usuario, fechaReserva.plusDays(i + 1), clase, nuevoCodigo);
        }

        String codigoExtra = "RESERVA0010";
        Reserva reservaExtra = gestionClase.agregarReserva(usuario, fechaReserva.plusDays(capacidad + 1), clase, codigoExtra);
        assertNull(reservaExtra, "No debería poderse crear una reserva cuando la clase está llena.");
        assertEquals(capacidad, clase.getRegistroReserva().size(), "El tamaño de la lista de reservas debería ser igual a la capacidad máxima.");
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
        LocalDateTime fechaReserva = LocalDateTime.of(2024, 3, 5, 10, 0);
        String codigo = "RESERVA001";

        gestionClase.agregarClase(nombreClase, tipoClase, entrenador, horarioClase, fechaInicioClase, fechaFinClase, capacidad, idClase);

        Clase clase = gestionClase.getClases().get(0);

        gestionClase.agregarReserva(usuario, fechaReserva, clase, codigo);

        boolean resultadoCancelacion = gestionClase.cancelarReserva(usuario, codigo);

        assertTrue(resultadoCancelacion, "La cancelación de la reserva debería haber sido exitosa.");
        assertEquals(0, clase.getRegistroReserva().size(), "El tamaño de la lista de reservas debería ser 0 después de la cancelación.");
        assertTrue(clase.isEstadoClase(), "La clase debería estar disponible después de cancelar la reserva.");

        boolean resultadoCancelacionInvalida = gestionClase.cancelarReserva(usuario, "RESERVA_NO_EXISTE");

        assertFalse(resultadoCancelacionInvalida, "La cancelación no debería ser exitosa porque la reserva no existe.");
        assertEquals(0, clase.getRegistroReserva().size(), "El tamaño de la lista de reservas debería seguir siendo 0.");
    }
}
