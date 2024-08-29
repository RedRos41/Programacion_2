package org.example.Entrenamiento;

import java.util.ArrayList;
import java.util.List;

public class GestionEntrenamiento {
    private List<Entrenamiento> entrenamientos = new ArrayList<>();

    public void agregarEntrenamiento(Entrenamiento entrenamiento) {
        entrenamientos.add(entrenamiento);
    }


    public List<Entrenamiento> listarEntrenamientos() {
        return new ArrayList<>(entrenamientos);
    }

}


