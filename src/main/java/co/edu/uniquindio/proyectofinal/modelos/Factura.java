package co.edu.uniquindio.proyectofinal.modelos;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@ToString
public class Factura {

    private double subTotalFactura, totalFactura;
    private LocalDateTime fechaFactura;
    private String codigoFactura;

}
