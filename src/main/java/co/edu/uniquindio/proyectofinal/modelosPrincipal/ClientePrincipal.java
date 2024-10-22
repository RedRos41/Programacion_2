package co.edu.uniquindio.proyectofinal.modelosPrincipal;


import java.util.List;
import java.util.ArrayList;
import co.edu.uniquindio.proyectofinal.modelos.Cliente;


public class ClientePrincipal {

    private final List<Cliente> clientes;

    public ClientePrincipal() {

        clientes = new ArrayList<>();

    }


    public List<Cliente> listarClientes() {

        return clientes;

    }

}
