package co.edu.uniquindio.proyectofinal.modelos;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Sesion {

    public static Sesion INSTANCIA;

    private Usuario usuario;

    private Sesion() {}


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
