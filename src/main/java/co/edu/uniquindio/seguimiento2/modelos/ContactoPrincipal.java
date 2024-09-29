package co.edu.uniquindio.seguimiento2.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class ContactoPrincipal {

    private final List<Contacto> contactos;

    public ContactoPrincipal() {
        contactos = new ArrayList<>();
    }

    public void agregarContacto(String nombre, String apellido, String telefono, LocalDate cumpleaños, String correoElectronico, String fotoUrl) throws Exception {
        if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || correoElectronico.isEmpty()) {
            throw new Exception("estos campos deben ser ingresados.");
        }

        if (!Pattern.matches("\\d{10}", telefono)) {
            throw new Exception("El número de teléfono debe tener 10 dígitos numéricos");
        }

        if (!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", correoElectronico)) {
            throw new Exception("El formato del correo electrónico es inválido");
        }

        Contacto contacto = Contacto.builder()
                .id(UUID.randomUUID().toString())
                .nombre(nombre)
                .apellido(apellido)
                .telefono(telefono)
                .cumpleaños(cumpleaños)
                .correoElectronico(correoElectronico)
                .fotoUrl(fotoUrl)
                .build();

        contactos.add(contacto);
    }
}