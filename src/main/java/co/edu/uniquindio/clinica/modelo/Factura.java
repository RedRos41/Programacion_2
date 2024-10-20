package co.edu.uniquindio.clinica.modelo;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

public class Factura {

    @EqualsAndHashCode.Include
    private String idFactura;

    private Paciente paciente;
    private Servicio servicio;
    private double total;
    private LocalDateTime fechaFactura;

    public Factura(Paciente paciente, Servicio servicio, double total) {
        this.idFactura = java.util.UUID.randomUUID().toString();
        this.paciente = paciente;
        this.servicio = servicio;
        this.total = total;
        this.fechaFactura = LocalDateTime.now(); // Fecha de creación de la factura
    }
}