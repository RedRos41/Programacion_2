package org.example.Usuario;


import org.example.Entrenamiento.Entrenamiento;
import org.example.Entrenamiento.GestionEntrenamiento;

import java.util.Optional;
import java.util.Scanner;

public class MenuUsuario {
    private final GestionUsuario gestionUsuario;
    private  final GestionEntrenamiento gestionEntrenamiento;

    public MenuUsuario(GestionUsuario gestionUsuario, GestionEntrenamiento gestionEntrenamiento) {
        this.gestionUsuario = gestionUsuario;
        this.gestionEntrenamiento = gestionEntrenamiento;
    }

    public void mostrarMenu(Scanner scanner) {
        boolean salir = false;

        while (!salir) {
            System.out.println("Menú de Gestión de Usuarios:");
            System.out.println("1. Agregar usuario");
            System.out.println("2. Actualizar usuario");
            System.out.println("3. Eliminar usuario");
            System.out.println("4. Imprimir usuarios");
            System.out.println("0. Volver al menú principal");
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

                    System.out.print("Ingrese el codigo del entrenamiento: ");
                    long codigoEntrenador = scanner.nextLong();
                    scanner.nextLine();
                    Optional<Entrenamiento> entrenamientoOptional = gestionEntrenamiento.buscarEntrenadorCodigo(codigoEntrenador);

                    if (entrenamientoOptional.isEmpty()) {
                        System.out.println("Entrenamiento no encontrado.");
                        return;
                    }

                    Entrenamiento entrenamiento = entrenamientoOptional.get();




                    gestionUsuario.agregarUsuario(nombre, id, direccion, correo, contrasena, telefono, entrenamiento);
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

                case 0:
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 5.");
                    break;
            }
        }
    }
}
