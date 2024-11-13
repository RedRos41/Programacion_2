package co.edu.uniquindio.proyectofinal.modelos;

import lombok.Getter;
import lombok.Setter;

public class Sesion {
    public static Sesion INSTANCIA;

    @Getter @Setter
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