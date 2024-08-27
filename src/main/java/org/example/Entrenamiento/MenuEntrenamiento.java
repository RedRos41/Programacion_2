package org.example.Entrenamiento;

import org.example.Clase.TipoClase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuEntrenamiento {
    private List<Entrenamiento> entrenamientos = new ArrayList<>();

    public void mostrarMenu(Scanner scanner) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- Menú Entrenamiento ---");
            System.out.println("1. Agregar Entrenamiento");
            System.out.println("2. Ver Entrenamientos por Tipo de Clase");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    agregarEntrenamiento(scanner);
                    break;
                case 2:
                    mostrarEntrenamientosPorTipoClase(scanner);
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void agregarEntrenamiento(Scanner scanner) {
        System.out.print("Ingrese ID del Entrenamiento: ");
        long id = Long.parseLong(scanner.nextLine());
        System.out.print("Ingrese ID del Usuario: ");
        long idUsuario = Long.parseLong(scanner.nextLine());
        System.out.print("Ingrese Tipo de Clase (AEROBICOS, FLEXIBILIDAD, FUERZA, RESISTENCIA): ");
        TipoClase tipoClase = TipoClase.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Ingrese Tipo de Entrenamiento (CARDIO, PESAS, FLEXIBILIDAD, HIIT, YOGA, PILATES, ZUMBA, NATACION, CROSSFIT): ");
        TipoEntrenamiento tipoEntrenamiento = TipoEntrenamiento.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Ingrese Descripción del Entrenamiento: ");
        String descripcion = scanner.nextLine();

        try {
            Entrenamiento entrenamiento = new Entrenamiento(id, idUsuario, tipoClase, tipoEntrenamiento, descripcion);
            entrenamientos.add(entrenamiento);
            System.out.println("Entrenamiento agregado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private void mostrarEntrenamientosPorTipoClase(Scanner scanner) {
        System.out.print("Ingrese Tipo de Clase (AEROBICOS, FLEXIBILIDAD, FUERZA, RESISTENCIA): ");
        TipoClase tipoClase = TipoClase.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("Entrenamientos para la clase " + tipoClase + ":");
        for (Entrenamiento entrenamiento : entrenamientos) {
            if (entrenamiento.getTipoClase() == tipoClase) {
                System.out.println(entrenamiento);
            }
        }
    }
}
