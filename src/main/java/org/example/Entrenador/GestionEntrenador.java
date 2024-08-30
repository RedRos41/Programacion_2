package org.example.Entrenador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestionEntrenador {
    private List<Entrenador> entrenadores;

    public GestionEntrenador() {
        this.entrenadores = new ArrayList<>();
    }

    public void agregarEntrenador(String nombre, long id, String especialidad) {
        if (entrenadores.stream().noneMatch(e -> e.getId() == id)) {
            entrenadores.add(new Entrenador(nombre, id, especialidad));
        }
    }

    public void actualizarEntrenador(long id, String nuevoNombre, String nuevaEspecialidad) {
        for (Entrenador entrenador : entrenadores) {
            if (entrenador.getId() == id) {
                entrenador.setNombre(nuevoNombre);
                entrenador.setEspecialidad(nuevaEspecialidad);
                break;
            }
        }
    }

    public void eliminarEntrenador(long id) {
        entrenadores.removeIf(entrenador -> entrenador.getId() == id);
    }

    public List<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public void imprimirEntrenador() {
        if (entrenadores.isEmpty()) {
            System.out.println("No hay entrenadores para imprimir.");
        } else {
            entrenadores.forEach(System.out::println);
        }
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

    public List<Entrenador> listarEntrenadores() {
        return entrenadores;
    }

}
