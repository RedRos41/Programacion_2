package co.edu.uniquindio.reservasuq.modelo;

import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Persona {

    @EqualsAndHashCode.Include
    private String cedula;

    private String nombre;
    private String email;
    private TipoPersona tipoPersona;
    private String password;
}
