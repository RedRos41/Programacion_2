package co.edu.uniquindio.clinica.modelo;

import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

public class Paciente {
    @EqualsAndHashCode.Include
    private String id;

    private String telefono;
    private String nombre;
    private String cedula;
    private String email;
    private Suscripcion suscripcion;
}