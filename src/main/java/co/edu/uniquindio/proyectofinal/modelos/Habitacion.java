package co.edu.uniquindio.proyectofinal.modelos;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Builder
@ToString
public class Habitacion {

    private int numeroHabitacion;
    private int  capacidadHabitacion;
    private double precioHabitacion;
    private String imagenHabitacion, descripcionHabitacion;

}
