package org.example.Clase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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

                    System.out.println("Seleccione el tipo de clase:");
                    System.out.println("1. AEROBICOS");
                    System.out.println("2. FLEXIBILIDAD");
                    System.out.println("3. FUERZA");
                    System.out.println("4. RESISTENCIA");
                    int tipoOpcion = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea

                    TipoClase tipoClase;
                    switch (tipoOpcion) {
                        case 1:
                            tipoClase = TipoClase.AEROBICOS;
                            break;
                        case 2:
                            tipoClase = TipoClase.FLEXIBILIDAD;
                            break;
                        case 3:
                            tipoClase = TipoClase.FUERZA;
                            break;
                        case 4:
                            tipoClase = TipoClase.RESISTENCIA;
                            break;
                        default:
                            System.out.println("Opción no válida. Seleccionando AEROBICOS por defecto.");
                            tipoClase = TipoClase.AEROBICOS;
                    }

                    System.out.println("Ingrese el horario de la clase (en formato HH:MM):");
                    String horarioClaseInput = scanner.nextLine();
                    LocalTime horarioClase = LocalTime.parse(horarioClaseInput);

                    System.out.println("Ingrese la fecha de inicio de la clase (en formato AAAA-MM-DD):");
                    String fechaInicioInput = scanner.nextLine();
                    LocalDate fechaInicioClase = LocalDate.parse(fechaInicioInput);

                    System.out.println("Ingrese la fecha de fin de la clase (en formato AAAA-MM-DD):");
                    String fechaFinInput = scanner.nextLine();
                    LocalDate fechaFinClase = LocalDate.parse(fechaFinInput);

                    System.out.println("Ingrese la capacidad de la clase:");
                    short capacidad = scanner.nextShort();

                    System.out.println("Ingrese el estado de la clase (true para activa, false para inactiva):");
                    boolean estadoClase = scanner.nextBoolean();
                    scanner.nextLine();

                    System.out.println("Ingrese el ID de la clase:");
                    String idClase = scanner.nextLine();

                    gestionClase.agregarClase(idClase, estadoClase, capacidad, fechaInicioClase, fechaFinClase, horarioClase, tipoClase, nombreClase);
                    System.out.println("Clase agregada: " + nombreClase);
                    break;

                case 2:
                    System.out.println("Ingrese el horario de la clase (en formato HH:MM):");
                    String horarioInput = scanner.nextLine();
                    LocalTime horario = LocalTime.parse(horarioInput);
                    List<Clase> clasesHorario = gestionClase.buscarClaseHorario(horario);

                    if (!clasesHorario.isEmpty()) {
                        System.out.println("Clases encontradas en el horario " + horario + ":");
                        for (Clase clase : clasesHorario) {
                            System.out.println(clase);
                        }
                    } else {
                        System.out.println("No se encontró ninguna clase en ese horario.");
                    }
                    break;

                case 3:
                    System.out.println("Seleccione el tipo de clase:");
                    System.out.println("1. AEROBICOS");
                    System.out.println("2. FLEXIBILIDAD");
                    System.out.println("3. FUERZA");
                    System.out.println("4. RESISTENCIA");
                    int tipoOpcionBusqueda = scanner.nextInt();
                    scanner.nextLine();

                    TipoClase tipoClaseBusqueda=null;
                    boolean opcionValida=false;

                    while (!opcionValida) {
                        switch (tipoOpcionBusqueda) {
                            case 1:
                                tipoClaseBusqueda = TipoClase.AEROBICOS;
                                opcionValida=true;
                                break;
                            case 2:
                                tipoClaseBusqueda = TipoClase.FLEXIBILIDAD;
                                opcionValida=true;
                                break;
                            case 3:
                                tipoClaseBusqueda = TipoClase.FUERZA;
                                opcionValida=true;
                                break;
                            case 4:
                                tipoClaseBusqueda = TipoClase.RESISTENCIA;
                                opcionValida=true;
                                break;
                            default:
                                System.out.println("Opción no válida. Por favor, intente nuevamente.");
                        }
                    }
                    List<Clase> clasesTipo = gestionClase.buscarClaseTipo(tipoClaseBusqueda);

                    if (!clasesTipo.isEmpty()) {
                        System.out.println("Clases encontradas para el tipo " + tipoClaseBusqueda + ":");
                        for (Clase clase : clasesTipo) {
                            System.out.println(clase);
                        }
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
