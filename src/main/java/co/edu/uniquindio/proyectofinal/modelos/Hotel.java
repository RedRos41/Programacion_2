package co.edu.uniquindio.proyectofinal.modelos;


import lombok.*;
import java.util.List;


@Getter
@Setter
@Builder
@ToString
public class Hotel {

    private List<Habitacion> habitacionHotel;

}
