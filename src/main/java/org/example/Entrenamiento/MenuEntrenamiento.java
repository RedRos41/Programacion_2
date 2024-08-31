package org.example.Entrenamiento;



import org.example.Usuario.GestionUsuario;
import org.example.Usuario.Usuario;


import java.util.Optional;
import java.util.Scanner;

public class MenuEntrenamiento {
    private GestionEntrenamiento gestionEntrenamiento;
    private GestionUsuario gestionUsuario;

    public MenuEntrenamiento(GestionEntrenamiento gestionEntrenamiento) {
        this.gestionEntrenamiento = new GestionEntrenamiento();
        this.gestionUsuario = new GestionUsuario();
    }

    public void mostrarMenu(Scanner scanner) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- Menú Entrenamiento ---");
            System.out.println("1. Agregar Entrenamiento");
            System.out.println("2. Ver Entrenamientos por Tipo de Clase");
            System.out.println("3. Listar Todos los Entrenamientos");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    agregarEntrenamiento(scanner);
                    break;
                case 2:
                    verEntrenamientosPorTipo(scanner);
                    break;
                case 3:
                    listarEntrenamientos();
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
        try {
            System.out.print("Ingrese la duracion del entrenamiento: ");
            int duracion = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingrese las calorias quemadas: ");
            int caloriasQuemadas = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingrese Tipo de Entrenamiento (CARDIO, PESAS, FLEXIBILIDAD, HIIT, YOGA, PILATES, ZUMBA, NATACION, CROSSFIT): ");
            TipoEntrenamiento tipoEntrenamiento = TipoEntrenamiento.valueOf(scanner.nextLine().toUpperCase());

            System.out.print("Ingrese Descripción del Entrenamiento: ");
            String descripcion = scanner.nextLine();

            System.out.print("Ingrese el codigo del entrenamiento: ");
            long codigo = scanner.nextLong();
            scanner.nextLine();

            System.out.println("Ingrese el ID del usuario: ");
            long id = scanner.nextLong();
            scanner.nextLine();

            Optional<Usuario> usuarioOptional = gestionUsuario.buscarUsuarioPorId(id);

            if (usuarioOptional.isEmpty()) {
                System.out.println("Usuario no encontrado.");
                return;
            }

            Usuario usuario = usuarioOptional.get();

            Entrenamiento entrenamiento = new Entrenamiento(tipoEntrenamiento, descripcion, duracion, caloriasQuemadas, codigo, usuario);
            gestionEntrenamiento.agregarEntrenamiento(entrenamiento);
            System.out.println("Entrenamiento agregado exitosamente.");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Entrada inválida. Por favor, intente nuevamente.");
        }
    }

    private void verEntrenamientosPorTipo(Scanner scanner) {
        try {
            System.out.print("Ingrese Tipo de Entrenamiento para buscar (CARDIO, PESAS, FLEXIBILIDAD, HIIT, YOGA, PILATES, ZUMBA, NATACION, CROSSFIT): ");
            TipoEntrenamiento tipoEntrenamiento = TipoEntrenamiento.valueOf(scanner.nextLine().toUpperCase());

            var entrenamientosPorTipo = gestionEntrenamiento.buscarEntrenamientoPorTipo(tipoEntrenamiento);
            if (entrenamientosPorTipo.isEmpty()) {
                System.out.println("No se encontraron entrenamientos de este tipo.");
            } else {
                entrenamientosPorTipo.forEach(System.out::println);
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Tipo de entrenamiento no válido.");
        }
    }

    private void listarEntrenamientos() {
        gestionEntrenamiento.imprimirEntrenamientos();
    }
}
