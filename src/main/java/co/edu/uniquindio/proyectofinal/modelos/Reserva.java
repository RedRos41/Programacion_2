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
public class Reserva {

    @EqualsAndHashCode.Include
    private int idReserva;

    @ToString.Exclude
    private Usuario clienteReserva;

    private Alojamiento alojamientoReserva;
    private LocalDateTime fechaInicioReserva, fechaFinReserva;
    private int numHuespedesReserva;


    public String getNombreAlojamiento() {
        return alojamientoReserva != null ? alojamientoReserva.getNombreAlojamiento() : "Sin Alojamiento";
    }

}
