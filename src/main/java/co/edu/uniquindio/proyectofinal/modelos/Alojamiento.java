package co.edu.uniquindio.proyectofinal.modelos;


import lombok.*;
import java.util.List;


@Getter
@Setter
@Builder
@ToString
public class Alojamiento {

    private String nombreAlojamiento, descripcionAlojamiento;
    private List<Ciudad> ciudadAlojamiento;
    private String imagenAlojamiento;
    private double precioPorNocheAlojamiento;
    private int capacidadMaximaAlojamiento;
    private List<Servicio> servicioAlojamiento;
}
