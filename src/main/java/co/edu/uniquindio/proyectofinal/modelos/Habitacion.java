package co.edu.uniquindio.proyectofinal.modelos;


import lombok.*;


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
