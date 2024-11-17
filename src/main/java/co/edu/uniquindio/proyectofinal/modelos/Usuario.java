package co.edu.uniquindio.proyectofinal.modelos;


import co.edu.uniquindio.proyectofinal.modelos.enums.TipoUsuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @EqualsAndHashCode.Include
    protected long cedulaUsuario;

    protected String nombreUsuario, emailUsuario, contraseñaUsuario, codigoActivacion, codigoContraseña;
    protected long telefonoUsuario;
    protected boolean usuarioActivado;
    protected TipoUsuario tipoUsuario;

}
