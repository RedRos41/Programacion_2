package co.edu.uniquindio.proyectofinal.modelos;


import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Usuario {

    @EqualsAndHashCode.Include
    protected long cedula;

    protected String nombreCompleto, email, contraseña;
    protected long telefono;

}
