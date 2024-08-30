package org.example.Clase;

import org.example.Usuario.GestionUsuario;
import org.example.Usuario.Usuario;
import org.example.Entrenador.GestionEntrenador;
import org.example.Entrenador.Entrenador;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class MenuClase {

    private GestionClase gestionClase;
    private GestionUsuario gestionUsuario;
    private GestionEntrenador gestionEntrenador;
    private Scanner scanner;

    public MenuClase(GestionUsuario gestionUsuario, GestionEntrenador gestionEntrenador) {
        this.gestionUsuario = gestionUsuario;
        this.gestionEntrenador = gestionEntrenador;
        this.gestionClase = new GestionClase();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu(Scanner scanner) {
        int opcion;

        do {
            System.out.println("\n--- Menú de Gestión de Clases ---");
            System.out.println("1. Agregar Clase");
            System.out.println("2. Buscar Clase");
            System.out.println("3. Reservar Clase");
            System.out.println("4. Cancelar Reserva");
            System.out.println("5. Listar Clases");
            System.out.println("6. Listar Reservas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarClase();
                    break;
                case 2:
                    buscarClase();
                    break;
                case 3:
                    reservarClase();
                    break;
                case 4:
                    cancelarReserva();
                    break;
                case 5:
                    listarClases();
                    break;

                case 6:
                    listarReservas();
                    break;

                case 0:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private void agregarClase() {
        System.out.print("Nombre de la Clase: ");
        String nombreClase = scanner.nextLine();

        System.out.println("Seleccione el Tipo de Clase (1: AEROBICOS, 2: FLEXIBILIDAD, 3: FUERZA, 4: RESISTENCIA): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        TipoClase tipoClase = TipoClase.values()[tipo - 1];

        System.out.print("Ingrese el ID del entrenador: ");
        long idEntrenador = scanner.nextLong();
        scanner.nextLine();
        Optional<Entrenador> entrenadorOptional = gestionEntrenador.buscarEntrenadorPorId(idEntrenador);

        if (entrenadorOptional.isEmpty()) {
            System.out.println("Entrenador no encontrado.");
            return;
        }

        Entrenador entrenador = entrenadorOptional.get();

        System.out.print("Horario de la Clase (HH:mm): ");
        String horarioClaseStr = scanner.nextLine();
        LocalTime horarioClase = LocalTime.parse(horarioClaseStr, DateTimeFormatter.ofPattern("HH:mm"));

        System.out.print("Fecha de Inicio de la Clase (yyyy-MM-dd): ");
        String fechaInicioStr = scanner.nextLine();
        LocalDate fechaInicioClase = LocalDate.parse(fechaInicioStr);

        System.out.print("Fecha de Fin de la Clase (yyyy-MM-dd): ");
        String fechaFinStr = scanner.nextLine();
        LocalDate fechaFinClase = LocalDate.parse(fechaFinStr);

        System.out.print("Capacidad de la Clase: ");
        short capacidad = scanner.nextShort();
        scanner.nextLine();

        System.out.print("Id de la Clase: ");
        String idClase = scanner.nextLine();

        gestionClase.agregarClase(nombreClase, tipoClase, entrenador, horarioClase, fechaInicioClase, fechaFinClase, capacidad, idClase);

        System.out.println("Clase agregada exitosamente.");
    }

    private void buscarClase() {
        System.out.println("Buscar por (1: Horario, 2: Tipo, 3: Entrenador): ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el horario de la clase (HH:mm): ");
                String horarioStr = scanner.nextLine();
                LocalTime horario = LocalTime.parse(horarioStr, DateTimeFormatter.ofPattern("HH:mm"));
                List<Clase> clasesPorHorario = gestionClase.buscarClaseHorario(horario);
                if (!clasesPorHorario.isEmpty()) {
                    clasesPorHorario.forEach(System.out::println);
                } else {
                    System.out.println("No se encontraron clases en ese horario.");
                }
                break;

            case 2:
                System.out.println("Seleccione el Tipo de Clase (1: AEROBICOS, 2: FLEXIBILIDAD, 3: FUERZA, 4: RESISTENCIA): ");
                int tipo = scanner.nextInt();
                scanner.nextLine();
                TipoClase tipoClase = TipoClase.values()[tipo - 1];
                List<Clase> clasesPorTipo = gestionClase.buscarClaseTipo(tipoClase);
                if (!clasesPorTipo.isEmpty()) {
                    clasesPorTipo.forEach(System.out::println);
                } else {
                    System.out.println("No se encontraron clases de ese tipo.");
                }
                break;

            case 3:
                System.out.print("Ingrese el ID del entrenador: ");
                long idEntrenador = scanner.nextLong();
                scanner.nextLine();
                Optional<Entrenador> entrenadorOptional = gestionEntrenador.buscarEntrenadorPorId(idEntrenador);

                if (entrenadorOptional.isEmpty()) {
                    System.out.println("Entrenador no encontrado.");
                    return; // Terminar el método si no se encuentra el entrenador
                }

                Entrenador entrenador = entrenadorOptional.get();
                // Implementación adicional si es necesario...
                break;

            default:
                System.out.println("Opción no válida.");
        }
    }

    private void reservarClase() {
        System.out.println("Ingrese el ID del usuario: ");
        long idUsuario = scanner.nextLong();
        scanner.nextLine();

        Optional<Usuario> usuarioOptional = gestionUsuario.buscarUsuarioPorId(idUsuario);

        if (usuarioOptional.isEmpty()) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        Usuario usuario = usuarioOptional.get();

        System.out.print("Ingrese el ID de la clase a reservar: ");
        String idClase = scanner.nextLine();

        Clase clase = gestionClase.verificarClaseId(idClase);
        if (clase == null) {
            System.out.println("Clase no encontrada.");
            return;
        }
        System.out.print("Codigo de la reserva: ");
        String codigo = scanner.nextLine();

        if (!gestionClase.verificarDisponibilidadClase(clase)) {
            System.out.println("No hay disponibilidad en la clase seleccionada.");
            return;
        }
        Reserva reservada = gestionClase.agregarReserva(usuario, LocalDateTime.now(), clase, codigo);
        if (reservada != null) {
            System.out.println("Reserva realizada exitosamente.");
        }
    }

    private void cancelarReserva() {
        System.out.println("Ingrese el ID del usuario: ");
        long idUsuario = scanner.nextLong();
        scanner.nextLine();

        Optional<Usuario> usuarioOptional = gestionUsuario.buscarUsuarioPorId(idUsuario);

        if (usuarioOptional.isEmpty()) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        Usuario usuario = usuarioOptional.get();

        System.out.print("Ingrese el codigo de la reserva a cancelar: ");
        String codigo = scanner.nextLine();

        // Intentar cancelar la reserva
        boolean cancelada = gestionClase.cancelarReserva(usuario, codigo);
        if (!cancelada) {
            System.out.println("No se pudo cancelar la reserva. Verifique el código y el usuario.");
        }
    }


    private void listarClases() {
        gestionClase.imprimirClases();
    }

    private void listarReservas(){
        gestionClase.imprimirReservas();
    }


}



