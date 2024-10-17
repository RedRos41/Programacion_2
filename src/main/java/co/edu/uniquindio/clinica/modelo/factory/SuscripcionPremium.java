package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Servicio;

public class SuscripcionPremium implements Suscripcion {

    @Override
    public double calcularPrecio(Servicio servicio) {
        // En la suscripción premium, más servicios son gratuitos o tienen un mayor descuento
        if (servicioIncluido(servicio)) {
            return 0;  // Servicios incluidos son gratuitos
        }
        // Otros servicios pueden tener un pequeño descuento, por ejemplo, 50%
        return servicio.getPrecio() * 0.5;
    }

    @Override
    public boolean servicioIncluido(Servicio servicio) {
        // La suscripción premium cubre más servicios
        return servicio.getNombre().equalsIgnoreCase("Consulta General")
                || servicio.getNombre().equalsIgnoreCase("Examen Avanzado")
                || servicio.getNombre().equalsIgnoreCase("Terapia Fisioterapia");
    }
}
