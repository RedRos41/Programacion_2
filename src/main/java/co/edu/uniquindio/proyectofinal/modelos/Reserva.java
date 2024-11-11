package co.edu.uniquindio.proyectofinal.modelos;


import lombok.EqualsAndHashCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Reserva {

    @EqualsAndHashCode.Include
    private int idReserva;

    private Usuario clienteReserva;
    private Alojamiento alojamientoReserva;
    private LocalDateTime fechaInicioReserva, fechaFinReserva;
    private int numHuespedesReserva;

}