package co.edu.uniquindio.proyectofinal.modelos;


import lombok.*;
import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@Builder
@ToString
public class Factura {

    private double subTotalFactura, totalFactura;
    private Date fechaFactura;
    private UUID codigoFactura;

}
