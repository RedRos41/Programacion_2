package co.edu.uniquindio.clinica.modelo.factory;

public class SuscripcionFactory {
    // Método estático que crea el tipo de suscripción basado en la entrada
    public static Suscripcion crearSuscripcion(String tipo) {
        if (tipo.equalsIgnoreCase("basica")) {
            return new SuscripcionBasica();
        } else if (tipo.equalsIgnoreCase("premium")) {
            return new SuscripcionPremium();
        }
        throw new IllegalArgumentException("Tipo de suscripción no válido");
    }
}
