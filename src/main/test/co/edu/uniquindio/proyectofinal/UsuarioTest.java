package co.edu.uniquindio.proyectofinal;


import co.edu.uniquindio.proyectofinal.modelos.Empresa;
import co.edu.uniquindio.proyectofinal.modelos.enums.TipoUsuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class UsuarioTest {

    private final Empresa empresa = Empresa.getInstancia();


    @Test
    public void testRegistrarUsuario() throws Exception {

        empresa.registrarUsuario(TipoUsuario.CLIENTE, 1125999432, "Derek Rodriguez Rodriguez", "DerekRoseRod@gmail.com", "RedRosa1", 3026707477L);

        int usuario = empresa.buscarUsuario(1125999432);

        assertNotEquals(-1, usuario, "El usuario no se registro");

        String codigo = empresa.getUsuarios().get(usuario).getCodigoActivacion();

        assertNotEquals(null, codigo, "El correo no se envio");

    }

}
