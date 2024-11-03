package co.edu.uniquindio.reservasuq.modelo;

import co.edu.uniquindio.reservasuq.modelo.enums.DiaSemana;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Instalacion {

    private String id;
    private String nombre;
    private int aforo;
    private float costo;
    private Map<DiaSemana, List<Horario>> horarios;

    // Método para agregar un horario a un día específico
    public void agregarHorario(DiaSemana dia, LocalTime horaInicio, LocalTime horaFin) {
        if (horarios == null) {
            horarios = new HashMap<>();
        }
        horarios.computeIfAbsent(dia, k -> new ArrayList<>()).add(new Horario(dia, horaInicio, horaFin));
    }

    // Método para obtener horarios de un día específico
    public List<Horario> obtenerHorariosPorDia(DiaSemana dia) {
        return horarios != null ? horarios.getOrDefault(dia, new ArrayList<>()) : new ArrayList<>();
    }
}
