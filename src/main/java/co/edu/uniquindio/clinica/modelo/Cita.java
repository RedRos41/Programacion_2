package co.edu.uniquindio.clinica.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class Cita {

    private String id;
    private Paciente paciente;
    private Servicio servicio;
    private LocalDate fecha;
    private Factura factura;

    public Cita(Paciente paciente, Servicio servicio, LocalDate fecha) {
        this.id = java.util.UUID.randomUUID().toString();
        this.paciente = paciente;
        this.servicio = servicio;
        this.fecha = fecha;
    }
}
