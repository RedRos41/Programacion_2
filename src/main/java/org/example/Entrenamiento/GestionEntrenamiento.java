package org.example.Entrenamiento;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestionEntrenamiento {
    private List<Entrenamiento> entrenamientos = new ArrayList<>();

    public void agregarEntrenamiento(Entrenamiento entrenamiento) {
        entrenamientos.add(entrenamiento);
    }

    public Optional<Entrenamiento> buscarEntrenamientoPorId(long id) {
        return entrenamientos.stream()
                .filter(entrenamiento -> entrenamiento.getId() == id)
                .findFirst();
    }

    public List<Entrenamiento> listarEntrenamientos() {
        return new ArrayList<>(entrenamientos);
    }

    public List<Entrenamiento> buscarEntrenamientosPorUsuario(long idUsuario) {
        List<Entrenamiento> resultados = new ArrayList<>();
        for (Entrenamiento entrenamiento : entrenamientos) {
            if (entrenamiento.getIdUsuario() == idUsuario) {
                resultados.add(entrenamiento);
            }
        }
        return resultados;
    }

    public void eliminarEntrenamiento(long id) {
        entrenamientos.removeIf(entrenamiento -> entrenamiento.getId() == id);
    }
}


