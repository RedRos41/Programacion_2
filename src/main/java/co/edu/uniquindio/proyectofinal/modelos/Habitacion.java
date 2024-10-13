package co.edu.uniquindio.proyectofinal.modelos;


import lombok.*;
import java.util.UUID;


@Getter
@Setter
@Builder
@ToString
public class Habitacion {

    private UUID numeroHabitacion;
    private int  capacidadHabitacion;
    private double precioHabitacion;
    private String imagenHabitacion, descripcionHabitacion;

}
