package org.example.Entrenador;

import java.util.Scanner;

public class MenuEntrenador {
    private final GestionEntrenador gestionEntrenador;

    public MenuEntrenador(GestionEntrenador gestionEntrenador) {

        this.gestionEntrenador = gestionEntrenador;
    }

    public void mostrarMenu(Scanner scanner) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- Menú Entrenador ---");
            System.out.println("1. Agregar Entrenador");
            System.out.println("2. Buscar Entrenador por ID");
            System.out.println("3. Buscar Entrenadores por Especialidad");
            System.out.println("4. Eliminar Entrenador");
            System.out.println("5. Listar Entrenadores");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese ID: ");
                    long id = Long.parseLong(scanner.nextLine());

                    System.out.print("Ingrese nombre: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Ingrese especialidad: ");
                    String especialidad = scanner.nextLine();

                    gestionEntrenador.agregarEntrenador(nombre, id, especialidad);
                    break;

                case 2:
                    buscarEntrenadorPorId(scanner);
                    break;
                case 3:
                    buscarEntrenadoresPorEspecialidad(scanner);
                    break;
                case 4:
                    eliminarEntrenador(scanner);
                    break;
                case 5:
                    gestionEntrenador.imprimirEntrenador();
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void buscarEntrenadorPorId(Scanner scanner) {
        System.out.print("Ingrese ID: ");
        long id = Long.parseLong(scanner.nextLine());
        gestionEntrenador.buscarEntrenadorPorId(id)
                .ifPresentOrElse(
                        entrenador -> System.out.println("Entrenador encontrado: " + entrenador),
                        () -> System.out.println("Entrenador no encontrado.")
                );
    }

    private void buscarEntrenadoresPorEspecialidad(Scanner scanner) {
        System.out.print("Ingrese especialidad: ");
        String especialidad = scanner.nextLine();
        var entrenadores = gestionEntrenador.buscarEntrenadoresPorEspecialidad(especialidad);
        if (entrenadores.isEmpty()) {
            System.out.println("No se encontraron entrenadores con la especialidad: " + especialidad);
        } else {
            System.out.println("Entrenadores encontrados:");
            for (Entrenador entrenador : entrenadores) {
                System.out.println(entrenador);
            }
        }
    }

    private void eliminarEntrenador(Scanner scanner) {
        System.out.print("Ingrese ID: ");
        long id = Long.parseLong(scanner.nextLine());
        gestionEntrenador.eliminarEntrenador(id);
        System.out.println("Entrenador eliminado si existía.");
    }
}
