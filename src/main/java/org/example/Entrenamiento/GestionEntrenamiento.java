package org.example.Entrenamiento;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestionEntrenamiento {
    private List<Entrenamiento> entrenamientos = new ArrayList<>();


    public void agregarEntrenamiento(Entrenamiento entrenamiento) {
        entrenamientos.add(entrenamiento);
    }

    public List<Entrenamiento> listarEntrenamientos() {
        return new ArrayList<>(entrenamientos);
    }


    public List<Entrenamiento> buscarEntrenamientoPorTipo(TipoEntrenamiento tipoEntrenamiento) {
        List<Entrenamiento> entrenamientosPorTipo = new ArrayList<>();
        for (Entrenamiento entrenamiento : entrenamientos) {
            if (entrenamiento.getTipoEntrenamiento().equals(tipoEntrenamiento)) {
                entrenamientosPorTipo.add(entrenamiento);
            }
        }
        return entrenamientosPorTipo;
    }


    public void eliminarEntrenamientoPorTipo(TipoEntrenamiento tipoEntrenamiento) {
        entrenamientos.removeIf(entrenamiento -> entrenamiento.getTipoEntrenamiento().equals(tipoEntrenamiento));
    }

    public Optional<Entrenamiento> buscarEntrenadorCodigo(long codigo) {
        return entrenamientos.stream()
                .filter(entrenador -> entrenador.getCodigo() == codigo)
                .findFirst();
    }


    public void imprimirEntrenamientos() {
        for (Entrenamiento entrenamiento : entrenamientos) {
            System.out.println(entrenamiento);
        }
    }
}






