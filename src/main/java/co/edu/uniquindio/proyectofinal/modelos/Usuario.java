package co.edu.uniquindio.proyectofinal.modelos;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Usuario {

    @EqualsAndHashCode.Include
    protected long cedula;

    protected String nombreCompleto, email, contraseña, codigoActivacion, codigoContraseña;
    protected long telefono;

}
