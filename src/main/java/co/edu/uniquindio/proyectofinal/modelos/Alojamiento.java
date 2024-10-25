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
public class Alojamiento {

    private String nombreAlojamiento, descripcionAlojamiento;
    private List<Ciudad> ciudades;
    private String imagenAlojamiento;
    private double precioPorNocheAlojamiento;
    private int capacidadMaximaAlojamiento;
    private List<Servicio> servicios;

}
