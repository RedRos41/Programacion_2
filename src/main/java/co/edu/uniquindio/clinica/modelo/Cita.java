package co.edu.uniquindio.clinica.modelo;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

public class Cita {
    @EqualsAndHashCode.Include
    private String idCita;

    private Paciente paciente;
    private Servicio servicio;
    private LocalDate fecha;
    private Factura factura;

    public String getIdCita() {
        return idCita;
    }

}
