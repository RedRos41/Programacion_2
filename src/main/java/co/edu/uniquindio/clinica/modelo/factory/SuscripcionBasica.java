package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Servicio;

public class SuscripcionBasica implements Suscripcion {

    @Override
    public double calcularPrecio(Servicio servicio) {
        return servicio.getPrecio();  // Precio completo
    }
}
