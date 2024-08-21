package org.example;

import java.util.Scanner;
import org.example.Usuario.GestionUsuario;

public class Gym {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GestionUsuario gestionUsuario = new GestionUsuario();

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

        System.out.println("Usuarios registrados:");
        gestionUsuario.imprimirUsuario();
    }
}
