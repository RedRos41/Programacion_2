package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Servicio;

public interface Suscripcion {
    // Método que calculará el costo del servicio dependiendo de la suscripción
    double calcularPrecio(Servicio servicio);

    // Método para listar los servicios que están cubiertos por la suscripción
    boolean servicioIncluido(Servicio servicio);
}
