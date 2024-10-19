package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Servicio;

public class SuscripcionPremium implements Suscripcion {

    @Override
    public double calcularPrecio(Servicio servicio) {
        return servicio.getPrecio() * 0.9;  // 10% de descuento
    }
}
