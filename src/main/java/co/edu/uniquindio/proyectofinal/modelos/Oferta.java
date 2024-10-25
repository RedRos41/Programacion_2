package co.edu.uniquindio.proyectofinal.modelos;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@Builder
@ToString
public class Oferta {

    private String descripcionOferta;
    private float descuentoOferta;
    private Date fechaInicioOferta, fechaFinOferta;

}
