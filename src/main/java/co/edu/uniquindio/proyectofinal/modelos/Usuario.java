package co.edu.uniquindio.proyectofinal.modelos;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Usuario {

    @EqualsAndHashCode.Include
    protected long cedula;

    protected String nombreCompleto, email, contraseña;
    protected long telefono;

}
