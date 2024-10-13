package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Servicio;

public class SuscripcionPremium implements Suscripcion {

    @Override
    public double calcularPrecio(Servicio servicio) {
        // en suscripción premium, se aplica un descuento del 20%
        return servicio.getPrecio() * 0.80;
    }
}
