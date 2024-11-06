package co.edu.uniquindio.proyectofinal.modelos;


import co.edu.uniquindio.proyectofinal.modelos.enums.CiudadAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.ServicioAlojamiento;
import co.edu.uniquindio.proyectofinal.modelos.enums.TipoAlojamiento;
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
    protected CiudadAlojamiento ciudadAlojamiento;
    protected String imagenAlojamiento;
    protected double precioPorNocheAlojamiento;
    protected int capacidadMaximaAlojamiento;
    protected ServicioAlojamiento servicioAlojamiento;
    protected TipoAlojamiento tipoAlojamiento;
    protected List<Oferta> ofertas;
    protected List<Reseña> reseñas;

}
