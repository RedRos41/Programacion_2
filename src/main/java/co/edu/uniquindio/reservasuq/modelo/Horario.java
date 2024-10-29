package co.edu.uniquindio.reservasuq.modelo;

import lombok.*;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Horario {
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
}
