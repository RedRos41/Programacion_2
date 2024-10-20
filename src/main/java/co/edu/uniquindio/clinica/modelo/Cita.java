package co.edu.uniquindio.clinica.modelo;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDateTime fechaHora;
    private Factura factura;

}
