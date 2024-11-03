package co.edu.uniquindio.reservasuq.modelo;

import co.edu.uniquindio.reservasuq.modelo.enums.DiaSemana;
import lombok.*;
import java.time.LocalTime;
import java.time.DayOfWeek;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Horario {
    private DiaSemana diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
}