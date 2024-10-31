package co.edu.uniquindio.proyectofinal.modelos;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@ToString
public class Casa extends Alojamiento {

    protected double aseoCasa, mantenimientoCasa;

}
