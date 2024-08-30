package org.example.Entrenador;

import org.example.Usuario.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestionEntrenador {
    private final List<Entrenador> entrenadores;

    public GestionEntrenador() {
        this.entrenadores = new ArrayList<>();
    }

    public List<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public void agregarEntrenador(String nombre, long id, String especialidad) {
        if (buscarEntrenadorPorId(id).isPresent()) {
            System.out.println("Entrenador con ID " + id + " ya existe.");
            return;
        }
        Entrenador entrenador = new Entrenador(nombre, id, especialidad);
        entrenadores.add(entrenador);
        System.out.println("Entrenador con ID " + id + " agregado correctamente.");
    }

    public void actualizarEntrenador(long id, String nuevoNombre, String nuevaEspecialidad) {
        Optional<Entrenador> entrenadorOpt = buscarEntrenadorPorId(id);
        if (entrenadorOpt.isPresent()) {
            Entrenador entrenador = entrenadorOpt.get();
            if (!nuevoNombre.isEmpty()) {
                entrenador.setNombre(nuevoNombre);
            }
            if (!nuevaEspecialidad.isEmpty()) {
                entrenador.setEspecialidad(nuevaEspecialidad);
            }
            System.out.println("Entrenador con ID " + id + " actualizado correctamente.");
        } else {
            System.out.println("Entrenador con ID " + id + " no encontrado.");
        }
    }

    public void eliminarEntrenador(long id) {
        if (entrenadores.removeIf(entrenador -> entrenador.getId() == id)) {
            System.out.println("Entrenador con ID " + id + " eliminado correctamente.");
        } else {
            System.out.println("Entrenador con ID " + id + " no encontrado.");
        }
    }

    public void imprimirEntrenador() {
        if (entrenadores.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            System.out.println("Usuarios registrados:");
            for (Entrenador entrenador : entrenadores) {
                System.out.println(entrenador);
            }
        }
    }

    public Optional<Entrenador> buscarEntrenadorPorId(long id) {
        return entrenadores.stream()
                .filter(entrenador -> entrenador.getId() == id)
                .findFirst();
    }

    public List<Entrenador> buscarEntrenadoresPorEspecialidad(String especialidad) {
        List<Entrenador> resultado = new ArrayList<>();
        entrenadores.stream()
                .filter(entrenador -> entrenador.getEspecialidad().equalsIgnoreCase(especialidad))
                .forEach(resultado::add);
        return resultado;
    }
}
