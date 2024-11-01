package co.edu.uniquindio.reservasuq.modelo;

import lombok.*;
import java.time.LocalTime;
import java.time.DayOfWeek;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Horario {
    private DayOfWeek diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
}