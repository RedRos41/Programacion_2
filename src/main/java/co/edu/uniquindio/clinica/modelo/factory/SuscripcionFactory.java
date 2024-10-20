package co.edu.uniquindio.clinica.modelo.factory;

public class SuscripcionFactory {

    public static Suscripcion crearSuscripcion(String tipoSuscripcion) throws Exception {
        switch (tipoSuscripcion.toLowerCase()) {
            case "basica":
                return new SuscripcionBasica();
            case "premium":
                return new SuscripcionPremium();
            case "vip":  // Agregamos el nuevo tipo de suscripción
                return new SuscripcionVip();
            default:
                throw new Exception("Tipo de suscripción no válido.");
        }
    }
}
