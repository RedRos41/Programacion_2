package co.edu.uniquindio.proyectofinal.modelos;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Alojamiento {

    @EqualsAndHashCode.Include
    protected int direccionAlojamiento;

    protected String nombreAlojamiento, descripcionAlojamiento;
    protected List<Ciudad> ciudades;
    protected String imagenAlojamiento;
    protected double precioPorNocheAlojamiento;
    protected int capacidadMaximaAlojamiento;
    protected List<Servicio> servicios;

}
