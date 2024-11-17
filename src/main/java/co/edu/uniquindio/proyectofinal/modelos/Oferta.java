package co.edu.uniquindio.proyectofinal.modelos;


import lombok.EqualsAndHashCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Oferta {

    @EqualsAndHashCode.Include
    private int idOferta;

    private String descripcionOferta;
    private float descuentoOferta;
    private LocalDateTime fechaInicioOferta, fechaFinOferta;

}
