package co.edu.uniquindio.proyectofinal.modelos;


import lombok.*;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Usuario {

    @EqualsAndHashCode.Include
    protected UUID cedula;

    protected String nombreCompleto, email, contraseña;
    protected int telefono;

}
