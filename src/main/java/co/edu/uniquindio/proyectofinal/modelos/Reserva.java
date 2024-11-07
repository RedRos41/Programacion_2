package co.edu.uniquindio.proyectofinal.modelos;


import lombok.EqualsAndHashCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Reserva {

    @EqualsAndHashCode.Include
    private int idReserva;

    private Cliente clienteReserva;
    private Alojamiento alojamientoReserva;
    private Date fechaInicioReserva, fechaFinReserva;
    private int numHuespedesReserva;

}
