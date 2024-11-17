package co.edu.uniquindio.proyectofinal.modelos;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
public class Apartamento extends Alojamiento {

    private double aseoApartamento, mantenimientoApartamento;

}
