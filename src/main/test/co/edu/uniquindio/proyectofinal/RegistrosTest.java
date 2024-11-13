package co.edu.uniquindio.proyectofinal;


import co.edu.uniquindio.proyectofinal.modelos.*;
import co.edu.uniquindio.proyectofinal.modelos.enums.CiudadAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.ServicioAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.TipoUsuario;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class RegistrosTest {

    private final Empresa empresa = new Empresa();


    @Test
    public void testRegistrarUsuario() throws Exception {

        empresa.registrarUsuario(TipoUsuario.CLIENTE, 1125999432, "Derek Rodriguez Rodriguez", "testemailuq01@gmail.com", "RedRosa1", 3026707477L);

        int usuario = empresa.buscarUsuario(1125999432);

        assertNotEquals(-1, usuario, "El usuario no se registro");

        String codigo = empresa.getUsuarios().get(usuario).getCodigoActivacion();

        assertNotEquals(null, codigo, "El correo no se envio");

    }


    @Test
    public void testRegistrarCasa() throws Exception {

        empresa.registrarCasa(123, "SierraGrandeA", CiudadAlojamiento.CALARCA, "Apto 203", "", 200000, 3, ServicioAlojamiento.DESAYUNO, 40000, 1000000);

        int casa = empresa.buscarAlojamiento(123);

        assertNotEquals(-1, casa, "La casa no se registro");

    }


    @Test
    public void testRegistrarApartamento() throws Exception {

        empresa.registrarApartamento(456, "SierraGrandeB", CiudadAlojamiento.ARMENIA, "Apto 203", "", 200000, 3, ServicioAlojamiento.PISCINA, 40000, 1000000);

        int apartamento = empresa.buscarAlojamiento(456);

        assertNotEquals(-1, apartamento, "El apartamento no se registro");

    }


    @Test
    public void testRegistrarHotel() throws Exception {

        empresa.registrarHotel(789, "SierraGrandeC", CiudadAlojamiento.PEREIRA, "Apto 203", "", 200000, 3, ServicioAlojamiento.WIFI);

        int hotel = empresa.buscarAlojamiento(789);

        assertNotEquals(-1, hotel, "El hotel no se registro");

    }


    @Test
    public void testRegistrarHabitacion() throws Exception {

        Hotel hotel = empresa.crearHotel(101, "SierraGrandeC", CiudadAlojamiento.PEREIRA, "Apto 203", "", 200000, 3, ServicioAlojamiento.WIFI);

        empresa.registrarHabitacion(hotel, 112, 3, 200000, "", "Apto 203");

        int habitacion = empresa.buscarHabitacion(hotel, 112);

        assertNotEquals(-1, habitacion, "La habitacion no se registro");

    }


    @Test
    public void testRegistrarOferta() throws Exception {

        Casa casa = empresa.crearCasa(131, "SierraGrandeD", CiudadAlojamiento.CALARCA, "Apto 203", "", 200000, 3, ServicioAlojamiento.DESAYUNO, 40000, 1000000);

        empresa.registrarOferta(casa, 415, "Apto 203", 20, LocalDateTime.of(2024, 11, 15, 12, 0), LocalDateTime.of(2024, 12, 15, 12, 0));

        int oferta = empresa.buscarOferta(casa, 415);

        assertNotEquals(-1, oferta, "La oferta no se registro");

    }


    @Test
    public void testRegistrarReserva() throws Exception {

        Usuario cliente = empresa.crearUsuario(TipoUsuario.CLIENTE, 1234567890, "Cliente", "Cliente@gmail.com", "Cliente123", 1234567890);

        Alojamiento alojamiento = empresa.crearApartamento(718, "SierraGrandeE", CiudadAlojamiento.ARMENIA, "Apto 203", "", 200000, 3, ServicioAlojamiento.PISCINA, 40000, 1000000);

        empresa.registrarReserva(cliente, 192, alojamiento, 3, LocalDateTime.of(2024, 11, 15, 12, 0), LocalDateTime.of(2024, 12, 15, 12, 0));

        int reserva = empresa.buscarReserva(cliente, 192);

        assertNotEquals(-1, reserva, "La reserva no se registro");

    }

}
