package co.edu.uniquindio.clinica.modelo.factory;

public class SuscripcionBasica implements Suscripcion {

    @Override
    public double calcularPrecio(double precioBase) {
        return precioBase * 0.9;  // Descuento del 10% para suscriptores básicos
    }
}
