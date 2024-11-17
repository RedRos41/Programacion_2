package co.edu.uniquindio.proyectofinal.modelos;


import lombok.EqualsAndHashCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reseña {

    @EqualsAndHashCode.Include
    private int idReseña;

    private String cometarioReseña;
    private float calificacionReseña;

}
