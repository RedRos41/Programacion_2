package co.edu.uniquindio.clinica.modelo.factory;

public class SuscripcionPremium implements Suscripcion {

    @Override
    public double calcularPrecio(double precioBase) {
        return precioBase * 0.5;
    }
}
