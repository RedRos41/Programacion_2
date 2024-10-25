package co.edu.uniquindio.proyectofinal.modelos;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@Builder
@ToString
public class Hotel {

    private List<Habitacion> habitaciones;

}
