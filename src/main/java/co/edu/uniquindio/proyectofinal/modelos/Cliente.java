package co.edu.uniquindio.proyectofinal.modelos;


import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@ToString
public class Cliente extends Usuario {

    private Billetera billeteraCliente;

}
