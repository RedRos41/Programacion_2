package org.example.Usuario;

import java.util.Scanner;

public class MenuUsuario {
    private final GestionUsuario gestionUsuario;

    public MenuUsuario(GestionUsuario gestionUsuario) {

        this.gestionUsuario = gestionUsuario;
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
                    String nombre = scanner.nextLine();

                    System.out.println("Ingrese el ID del usuario:");
                    long id = scanner.nextLong();
                    scanner.nextLine();

                    System.out.println("Ingrese la dirección del usuario:");
                    String direccion = scanner.nextLine();

                    System.out.println("Ingrese el correo del usuario:");
                    String correo = scanner.nextLine();

                    System.out.println("Ingrese la contraseña del usuario:");
                    String contrasena = scanner.nextLine();

                    System.out.println("Ingrese el teléfono del usuario:");
                    long telefono = scanner.nextLong();

                    gestionUsuario.agregarUsuario(nombre, id, direccion, correo, contrasena, telefono);
                    break;

                case 2:
                    System.out.println("Ingrese el ID del usuario:");
                    long idUActualizar = scanner.nextLong();
                    scanner.nextLine();

                    System.out.println("Ingrese el nombre del usuario:");
                    String actualizarNombre = scanner.nextLine();

                    System.out.println("Ingrese la dirección del usuario:");
                    String actualizarDireccion = scanner.nextLine();

                    System.out.println("Ingrese el correo del usuario:");
                    String actualizarCorreo = scanner.nextLine();

                    System.out.println("Ingrese la contraseña del usuario:");
                    String actualizarContrasena = scanner.nextLine();

                    System.out.println("Ingrese el teléfono del usuario:");
                    String actualizarTelefono = scanner.nextLine();

                    gestionUsuario.actualizarUsuario(idUActualizar, actualizarNombre, actualizarDireccion, actualizarCorreo, actualizarContrasena, actualizarTelefono);
                    break;

                case 3:
                    System.out.println("Ingrese el ID del usuario:");
                    long eliminarUsuario = scanner.nextLong();

                    gestionUsuario.eliminarUsuario(eliminarUsuario);
                    break;

                case 4:
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
