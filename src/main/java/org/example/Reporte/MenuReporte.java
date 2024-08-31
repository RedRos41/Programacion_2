package org.example.Reporte;

import org.example.Entrenamiento.Entrenamiento;

import java.util.List;
import java.util.Scanner;

public class MenuReporte {

    private final GestionReporte gestionReporte;
    private final List<Entrenamiento> entrenamientos;

    public MenuReporte(GestionReporte gestionReporte, List<Entrenamiento> entrenamientos) {
        this.gestionReporte = gestionReporte;
        this.entrenamientos = entrenamientos;
    }

    public void mostrarMenu(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n--- Menú de Reportes ---");
            System.out.println("1. Generar Reporte Completo");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    Reporte reporte = gestionReporte.generarReporte(entrenamientos);
                    System.out.println(reporte);
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }
}
