package org.example.Reporte;

import org.example.Clase.Clase;
import org.example.Entrenamiento.TipoEntrenamiento;
import org.example.Usuario.Usuario;

import java.util.List;

public class Reporte {

    private Clase claseMasPopular;
    private List<Usuario> topTresUsuariosMasActivos;
    private TipoEntrenamiento tipoEjercicioMasPracticado;

    public Reporte(Clase claseMasPopular, List<Usuario> topTresUsuariosMasActivos, TipoEntrenamiento tipoEjercicioMasPracticado) {
        this.claseMasPopular = claseMasPopular;
        this.topTresUsuariosMasActivos = topTresUsuariosMasActivos;
        this.tipoEjercicioMasPracticado = tipoEjercicioMasPracticado;
    }

    public Clase getClaseMasPopular() {
        return claseMasPopular;
    }

    public List<Usuario> getTopTresUsuariosMasActivos() {
        return topTresUsuariosMasActivos;
    }

    public TipoEntrenamiento getTipoEjercicioMasPracticado() {
        return tipoEjercicioMasPracticado;
    }

    @Override
    public String toString() {
        return "Reporte{" +
                "claseMasPopular=" + claseMasPopular +
                ", topTresUsuariosMasActivos=" + topTresUsuariosMasActivos +
                ", tipoEjercicioMasPracticado=" + tipoEjercicioMasPracticado +
                '}';
    }
}
