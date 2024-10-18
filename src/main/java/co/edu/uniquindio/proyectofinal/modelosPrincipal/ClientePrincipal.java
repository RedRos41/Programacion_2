package co.edu.uniquindio.proyectofinal.modelosPrincipal;


import co.edu.uniquindio.proyectofinal.modelos.Cliente;
import java.util.List;
import java.util.ArrayList;


public class ClientePrincipal {

    private final List<Cliente> clientes;

    public ClientePrincipal() {

        clientes = new ArrayList<>();

    }


    public List<Cliente> listarClientes() {

        return clientes;

    }

}
