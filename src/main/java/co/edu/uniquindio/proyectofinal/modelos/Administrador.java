package co.edu.uniquindio.proyectofinal.modelos;


import lombok.*;
import java.util.List;


@Getter
@Setter
@Builder
@ToString
public class Administrador extends Usuario {

    private List<Alojamiento> alojamientos;
    private List<Cliente> clientes;
    private List<Estadistica> estadisticas;
    private List<Oferta> ofertas;
    private List<Reseña> reseñas;

}
