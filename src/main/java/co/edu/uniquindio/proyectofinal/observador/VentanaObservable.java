package co.edu.uniquindio.proyectofinal.observador;

public abstract class VentanaObservable {

    protected Observador observador;


    public void setObservador(Observador observador) {
        this.observador = observador;
    }

    public void notificarObservador() {
        if (observador != null) {
            observador.notificar();
        }
    }
}
