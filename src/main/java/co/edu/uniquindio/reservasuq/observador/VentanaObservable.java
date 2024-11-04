package co.edu.uniquindio.reservasuq.observador;


public abstract class VentanaObservable {

    protected Observador observador;

    // Método para establecer el observador
    public void setObservador(Observador observador) {
        this.observador = observador;
    }

    // Método para notificar al observador de cambios
    protected void notificarObservador() {
        if (observador != null) {
            observador.notificar();
        }
    }
}