package co.edu.uniquindio.clinica.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Factura {

    private String id;
    private Paciente paciente;
    private Servicio servicio;
    private double total;

    public Factura(Paciente paciente, Servicio servicio, double total) {
        this.id = java.util.UUID.randomUUID().toString();
        this.paciente = paciente;
        this.servicio = servicio;
        this.total = total;
    }


}
