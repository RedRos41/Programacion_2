package co.edu.uniquindio.reservasuq.modelo;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Instalacion {

    private String id;
    private String nombre;
    private int aforo;
    private float costo;
    private List<Horario> horarios;
}