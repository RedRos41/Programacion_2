package org.example.Entrenador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestionEntrenador {
    private List<Entrenador> entrenadores = new ArrayList<>();

    public void agregarEntrenador(Entrenador entrenador) {
        if (buscarEntrenadorPorId(entrenador.getId()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un entrenador con este ID.");
        }
        entrenadores.add(entrenador);
    }

    public Optional<Entrenador> buscarEntrenadorPorId(long id) {
        return entrenadores.stream()
                .filter(entrenador -> entrenador.getId() == id)
                .findFirst();
    }

    public List<Entrenador> buscarEntrenadoresPorEspecialidad(String especialidad) {
        List<Entrenador> resultado = new ArrayList<>();
        for (Entrenador entrenador : entrenadores) {
            if (entrenador.getEspecialidad().equalsIgnoreCase(especialidad)) {
                resultado.add(entrenador);
            }
        }
        return resultado;
    }

    public void eliminarEntrenador(long id) {
        entrenadores.removeIf(entrenador -> entrenador.getId() == id);
    }

    public List<Entrenador> listarEntrenadores() {
        return entrenadores;
    }
}
