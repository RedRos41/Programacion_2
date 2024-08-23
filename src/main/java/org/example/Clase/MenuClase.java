package org.example.Clase;

import java.util.Scanner;

public class MenuClase {

    private final GestionClase gestionClase;

    public MenuClase() {
        this.gestionClase = new GestionClase();
    }

    public void mostrarMenu(Scanner scanner) {
        boolean salir = false;

        while (!salir) {
            System.out.println("Menú de Gestión de Clases:");
            System.out.println("1. Agregar Clase");
            System.out.println("2. Buscar Clase por Horario");
            System.out.println("3. Buscar Clase por Tipo");
            System.out.println("4. Cancelar Clase");
            System.out.println("5. Consultar Disponibilidad de Clase");
            System.out.println("6. Imprimir clases");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre de la clase:");
                    String nombreClase = scanner.nextLine();

                    System.out.println("Ingrese el tipo de clase:");
                    String tipoClase = scanner.nextLine();

                    System.out.println("Ingrese el horario de la clase (en formato HH-MM):");
                    int horarioClase = scanner.nextInt();

                    System.out.println("Ingrese la fecha de inicio de la clase (en formato AAAAMMDD):");
                    int fechaInicioClase = scanner.nextInt();

                    System.out.println("Ingrese la fecha de fin de la clase (en formato AAAA-MM-DD):");
                    int fechaFinClase = scanner.nextInt();

                    System.out.println("Ingrese la capacidad de la clase:");
                    short capacidad = scanner.nextShort();

                    System.out.println("Ingrese el estado de la clase (true para activa, false para inactiva):");
                    boolean estadoClase = scanner.nextBoolean();

                    System.out.println("Ingrese el ID de la clase:");
                    long idClase = scanner.nextLong();
                    scanner.nextLine();

                    gestionClase.agregarClase(idClase, estadoClase, capacidad, fechaInicioClase, fechaFinClase, horarioClase, tipoClase, nombreClase);
                    System.out.println("Clase agregada: " + nombreClase);
                    break;

                case 2:
                    System.out.println("Ingrese el horario de la clase (en formato HH-MM):");
                    int horario = scanner.nextInt();
                    Clase claseHorario = gestionClase.buscarClaseHorario(horario);
                    if (claseHorario != null) {
                        System.out.println("Clase encontrada: " + claseHorario);
                    } else {
                        System.out.println("No se encontró ninguna clase en ese horario.");
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el tipo de clase:");

                    String tipo = scanner.nextLine();
                    Clase claseTipo = gestionClase.buscarClaseTipo(tipo);
                    if (claseTipo != null) {
                        System.out.println("Clases encontradas:" + claseTipo);
                    } else {
                        System.out.println("No se encontraron clases de ese tipo.");
                    }
                    break;
                case 4:
                    // Implementar lógica para cancelar clase
                    break;
                case 5:
                    // Implementar lógica para consultar disponibilidad de clase
                    break;
                case 6:
                    gestionClase.imprimirClases();
                    break;

                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}
