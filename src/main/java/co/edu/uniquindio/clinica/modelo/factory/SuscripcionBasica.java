package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Servicio;

public class SuscripcionBasica implements Suscripcion {

    @Override
    public double calcularPrecio(Servicio servicio) {
        // en suscripción básica, se aplica un descuento del 10% si el servicio es básico
        return servicio.getPrecio() * 0.90;
    }
}
