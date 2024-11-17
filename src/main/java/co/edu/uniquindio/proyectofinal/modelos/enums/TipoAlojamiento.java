package co.edu.uniquindio.proyectofinal.modelos.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum TipoAlojamiento {

    CASA("Casa"),
    APARTAMENTO("Apartamento"),
    HOTEL("Hotel");

    private final String nombre;

}
