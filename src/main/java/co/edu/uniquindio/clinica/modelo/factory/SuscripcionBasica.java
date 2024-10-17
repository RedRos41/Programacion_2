package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Servicio;

public class SuscripcionBasica implements Suscripcion {

    @Override
    public double calcularPrecio(Servicio servicio) {
        // Supongamos que algunos servicios tienen un 20% de descuento
        if (servicioIncluido(servicio)) {
            return servicio.getPrecio() * 0.8;  // 20% de descuento
        }
        // Otros servicios no están cubiertos y deben pagarse en su totalidad
        return servicio.getPrecio();
    }

    @Override
    public boolean servicioIncluido(Servicio servicio) {
        // Aquí definimos qué servicios están incluidos en la suscripción básica
        // Por ejemplo, ciertos servicios de consulta general pueden estar incluidos
        return servicio.getNombre().equalsIgnoreCase("Consulta General")
                || servicio.getNombre().equalsIgnoreCase("Examen Básico");
    }
}
