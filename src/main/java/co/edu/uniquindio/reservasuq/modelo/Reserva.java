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
    private LocalDate fechaReserva;
    private String horaReserva;
    private LocalDateTime fechaHoraCreacion; // Para llevar el registro de cuándo se hizo la reserva
}