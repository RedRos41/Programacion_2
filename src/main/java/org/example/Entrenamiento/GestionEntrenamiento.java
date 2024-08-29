package org.example.Entrenamiento;

import java.util.ArrayList;
import java.util.List;

public class GestionEntrenamiento {
    private List<Integer> entrenamientos = new ArrayList<>();

    public void agregarEntrenamiento(int entrenamiento) {
        entrenamientos.add(entrenamiento);
    }


    public ArrayList<Integer> listarEntrenamientos() {
        return new ArrayList<>(entrenamientos);
    }

}


