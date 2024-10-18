package co.edu.uniquindio.proyectofinal.modelos;


import lombok.*;
import java.util.List;


@Getter
@Setter
@Builder
@ToString
public class Cliente extends Usuario {

    private Billetera billeteraCliente;

}
