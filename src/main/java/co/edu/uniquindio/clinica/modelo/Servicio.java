package co.edu.uniquindio.clinica.modelo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

public class Servicio {

    @EqualsAndHashCode.Include
    private String id;

    private double precio;
    private String nombre;
}