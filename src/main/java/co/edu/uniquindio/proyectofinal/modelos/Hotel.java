package co.edu.uniquindio.proyectofinal.modelos;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Getter
@Setter
@SuperBuilder
@ToString
public class Hotel extends Alojamiento {

    private List<Habitacion> habitacion;

}
