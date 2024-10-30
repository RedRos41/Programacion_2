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
public class Alojamiento {

    private String nombreAlojamiento, descripcionAlojamiento;
    private List<Ciudad> ciudad;
    private String imagenAlojamiento;
    private double precioPorNocheAlojamiento;
    private int capacidadMaximaAlojamiento;
    private List<Servicio> servicio;

}
