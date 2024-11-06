package co.edu.uniquindio.proyectofinal.modelos;


import lombok.EqualsAndHashCode;import lombok.EqualsAndHashCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Reseña {

    @EqualsAndHashCode.Include
    private int idReseña;

    private String cometarioReseña;
    private float calificacionReseña;

}
