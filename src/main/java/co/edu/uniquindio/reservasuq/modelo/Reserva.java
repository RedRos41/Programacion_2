package co.edu.uniquindio.reservasuq.modelo;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Reserva {

    private String idReserva;
    private Instalacion instalacion;
    private Persona persona;
    private LocalDateTime fechaHoraReserva;
    private LocalDate fechaCreacion;
    private float costo;

}