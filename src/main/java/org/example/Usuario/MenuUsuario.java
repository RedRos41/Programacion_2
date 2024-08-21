package org.example.Usuario;

import java.util.Scanner;

public class MenuUsuario {
    private final GestionUsuario gestionUsuario;

    public MenuUsuario() {
        this.gestionUsuario = new GestionUsuario();
    }

    public void mostrarMenu(Scanner scanner) {
        boolean salir = false;

        while (!salir) {
            System.out.println("Menú de Gestión de Usuarios:");
            System.out.println("1. Agregar usuario");
            System.out.println("2. Actualizar usuario");
            System.out.println("3. Eliminar usuario");
            System.out.println("4. Imprimir usuarios");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre del usuario:");
                    String nombreUsuario = scanner.nextLine();

                    System.out.println("Ingrese la dirección del usuario:");
                    String direccion = scanner.nextLine();

                    System.out.println("Ingrese el correo del usuario:");
                    String correo = scanner.nextLine();

                    System.out.println("Ingrese la contraseña del usuario:");
                    String contrasena = scanner.nextLine();

                    System.out.println("Ingrese el ID del usuario:");
                    long idUsuario = scanner.nextLong();

                    System.out.println("Ingrese el teléfono del usuario:");
                    long telefono = scanner.nextLong();

                    gestionUsuario.agregarUsuario(nombreUsuario, direccion, correo, contrasena, idUsuario, telefono);
                    break;

                case 2:
                    // Funcionalidad de actualización
                    System.out.println("Funcionalidad de actualización de usuario no implementada aún.");
                    break;

                case 3:
                    // Funcionalidad de eliminación
                    System.out.println("Funcionalidad de eliminación de usuario no implementada aún.");
                    break;

                case 4:
                    System.out.println("Usuarios registrados:");
                    gestionUsuario.imprimirUsuario();
                    break;

                case 5:
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 5.");
                    break;
            }
        }
    }
}
