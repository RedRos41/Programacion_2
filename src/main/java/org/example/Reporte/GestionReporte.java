package org.example.Reporte;

import org.example.Clase.Clase;
import org.example.Clase.GestionClase;
import org.example.Entrenamiento.Entrenamiento;
import org.example.Entrenamiento.TipoEntrenamiento;
import org.example.Usuario.GestionUsuario;
import org.example.Usuario.Usuario;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GestionReporte {

    private final GestionClase gestionClase;
    private final GestionUsuario gestionUsuario;

    public GestionReporte(GestionClase gestionClase, GestionUsuario gestionUsuario) {
        this.gestionClase = gestionClase;
        this.gestionUsuario = gestionUsuario;
    }

    /**
     * Método para generar un reporte completo que incluye la clase más popular,
     * los tres usuarios más activos y el tipo de ejercicio más practicado.
     * @param entrenamientos Lista de entrenamientos.
     * @return Un objeto Reporte con la información generada.
     */
    public Reporte generarReporte(List<Entrenamiento> entrenamientos) {
        Clase claseMasPopular = obtenerClaseMasPopular().orElse(null);
        List<Usuario> topTresUsuariosMasActivos = obtenerTopTresUsuariosMasActivos(entrenamientos);
        TipoEntrenamiento tipoEjercicioMasPracticado = obtenerTipoEjercicioMasPracticado(entrenamientos).orElse(null);

        return new Reporte(claseMasPopular, topTresUsuariosMasActivos, tipoEjercicioMasPracticado);
    }

    private Optional<Clase> obtenerClaseMasPopular() {
        return gestionClase.getClases().stream()
                .max(Comparator.comparingInt(clase -> clase.getRegistroReserva().size()));
    }

    private List<Usuario> obtenerTopTresUsuariosMasActivos(List<Entrenamiento> entrenamientos) {
        Map<Usuario, Integer> caloriasPorUsuario = entrenamientos.stream()
                .collect(Collectors.groupingBy(Entrenamiento::getUsuario, Collectors.summingInt(Entrenamiento::getCaloriasQuemadas)));

        return caloriasPorUsuario.entrySet().stream()
                .sorted(Map.Entry.<Usuario, Integer>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private Optional<TipoEntrenamiento> obtenerTipoEjercicioMasPracticado(List<Entrenamiento> entrenamientos) {
        Map<TipoEntrenamiento, Integer> tiempoPorTipo = entrenamientos.stream()
                .collect(Collectors.groupingBy(Entrenamiento::getTipoEntrenamiento, Collectors.summingInt(Entrenamiento::getDuracion)));

        return tiempoPorTipo.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }
}
