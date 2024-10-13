package co.edu.uniquindio.proyectofinal.modelos;


import lombok.*;
import java.util.Date;


@Getter
@Setter
@Builder
@ToString
public class Reserva {

    private Cliente clienteReserva;
    private Alojamiento alojamientoReserva;
    private Date fechaInicioReserva, fechaFinReserva;
    private int numHuespedesReserva;

}
