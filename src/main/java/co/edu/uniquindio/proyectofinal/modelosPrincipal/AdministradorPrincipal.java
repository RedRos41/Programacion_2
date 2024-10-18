package co.edu.uniquindio.proyectofinal.modelosPrincipal;


import co.edu.uniquindio.proyectofinal.modelos.Administrador;
import java.util.List;
import java.util.ArrayList;


public class AdministradorPrincipal {

    private final List<Administrador> administradores;

    public AdministradorPrincipal() {

        administradores = new ArrayList<>();

    }


    public List<Administrador> listarAdministradores() {

        return administradores;

    }

}
