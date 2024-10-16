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
    protected int cedula;

    protected String nombreCompleto, email, contraseña;
    protected int telefono;

}
