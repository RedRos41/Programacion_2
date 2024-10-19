package co.edu.uniquindio.clinica.modelo.factory;

public class SuscripcionFactory {

    public static Suscripcion crearSuscripcion(String tipo) {
        switch (tipo) {
            case "Premium":
                return new SuscripcionPremium();
            case "Basica":
            default:
                return new SuscripcionBasica();
        }
    }
}
