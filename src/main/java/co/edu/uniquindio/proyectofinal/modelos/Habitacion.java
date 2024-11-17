package co.edu.uniquindio.proyectofinal.modelos;


import lombok.EqualsAndHashCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Habitacion {

    @EqualsAndHashCode.Include
    private int numeroHabitacion;

    private int capacidadHabitacion;
    private double precioHabitacion;
    private String imagenHabitacion, descripcionHabitacion;

}
