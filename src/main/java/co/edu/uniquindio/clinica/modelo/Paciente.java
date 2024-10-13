package co.edu.uniquindio.clinica.modelo;

import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Paciente {

    private String id;
    private String nombre;
    private String cedula;
    private String telefono;
    private String email;
    private Suscripcion suscripcion;
}
