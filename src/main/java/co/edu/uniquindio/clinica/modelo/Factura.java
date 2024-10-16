package co.edu.uniquindio.clinica.modelo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Factura {

    @EqualsAndHashCode.Include
    private String id;

    private Paciente paciente;
    private Servicio servicio;
    private double total;

}
