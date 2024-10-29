package co.edu.uniquindio.proyectofinal.utils;


import co.edu.uniquindio.proyectofinal.modelos.Usuario;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@Builder
@ToString
public class Sesion {

    private static Sesion INSTANCIA;
    private Usuario usuario;


    private Sesion() {
    }


    public static Sesion getInstancia() {

        if (INSTANCIA == null) {

            INSTANCIA = new Sesion();

        }
        return INSTANCIA;

    }


    public void cerrarSesion() {

        usuario = null;

    }

}
