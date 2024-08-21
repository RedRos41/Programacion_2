package org.example;

import java.util.Scanner;
import org.example.Usuario.MenuUsuario;

public class Gym {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuUsuario menuUsuario = new MenuUsuario();
        boolean salir = false;

        while (!salir) {
            System.out.println("Menú Principal:");
            System.out.println("1. Gestión de Usuarios");
            System.out.println("2. Gestión de Clases");
            System.out.println("3. Gestión de Entrenadores");
            System.out.println("4. Gestión de Entrenamientos");
            System.out.println("5. Gestión de Reportes");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuUsuario.mostrarMenu(scanner);
                    break;

                case 2:
                    // Menú para gestionar Clases
                    System.out.println("Funcionalidad de gestión de clases no implementada aún.");
                    break;

                case 3:
                    // Menú para gestionar Entrenadores
                    System.out.println("Funcionalidad de gestión de entrenadores no implementada aún.");
                    break;

                case 4:
                    // Menú para gestionar Entrenamientos
                    System.out.println("Funcionalidad de gestión de entrenamientos no implementada aún.");
                    break;

                case 5:
                    // Menú para gestionar Reportes
                    System.out.println("Funcionalidad de gestión de reportes no implementada aún.");
                    break;

                case 6:
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 6.");
                    break;
            }
        }
        scanner.close();
    }
}
