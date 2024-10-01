package co.edu.uniquindio.seguimiento2.modelos;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Contacto {
    @EqualsAndHashCode.Include
    private String id;
    private String nombre;
    private String apellido;
    private String telefono;
    private LocalDate cumpleaños;
    private String correoElectronico;
    private String fotoUrl; // Atributo para la URL de la foto de perfil
}
