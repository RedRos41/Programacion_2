package org.example;

import org.example.Usuario.GestionUsuario;
import org.example.Usuario.Usuario;

public class Gym {

    public static void main(String[] args) {

        Usuario usuario = new Usuario("Derek", "Calle 6N #16-51", "DerekRoseRod@gmail.com", "RedRosa1", 1125999432, 3026707477L);

        GestionUsuario gestionUsuario = new GestionUsuario();

        System.out.println(usuario);

        gestionUsuario.imprimirUsuario();
    }
}
