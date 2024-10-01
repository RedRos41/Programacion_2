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
        validarCampos(nombre, apellido, telefono, correoElectronico);
        Contacto contacto = Contacto.builder()
                .id(UUID.randomUUID().toString())
                .nombre(nombre)
                .apellido(apellido)
                .telefono(telefono)
                .cumpleaños(cumpleaños)
                .correoElectronico(correoElectronico)
                .fotoUrl(fotoUrl) // Agregar la URL de la foto al contacto
                .build();
        contactos.add(contacto);
    }

    public void eliminarContacto(String id) throws Exception {
        Contacto contacto = buscarContactoPorId(id);
        if (contacto != null) {
            contactos.remove(contacto);
        } else {
            throw new Exception("No se encontró el contacto.");
        }
    }

    public void editarContacto(String id, String nombre, String apellido, String telefono, LocalDate cumpleaños, String correoElectronico, String fotoUrl) throws Exception {
        Contacto contacto = buscarContactoPorId(id);
        if (contacto != null) {
            validarCampos(nombre, apellido, telefono, correoElectronico);
            contacto.setNombre(nombre);
            contacto.setApellido(apellido);
            contacto.setTelefono(telefono);
            contacto.setCumpleaños(cumpleaños);
            contacto.setCorreoElectronico(correoElectronico);
            contacto.setFotoUrl(fotoUrl); // Actualizar la URL de la foto
        } else {
            throw new Exception("No se encontró el contacto.");
        }
    }

    public List<Contacto> listarContactos() {
        return contactos;
    }

    public List<Contacto> buscarContactosNombre(String nombre) {
        List<Contacto> contactosEncontrados = new ArrayList<>();
        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                contactosEncontrados.add(c);
            }
        }
        return contactosEncontrados;
    }

    public List<Contacto> buscarContactosTelefono(String telefono) {
        List<Contacto> contactosEncontrados = new ArrayList<>();
        for (Contacto c : contactos) {
            if (c.getTelefono().equals(telefono)) {
                contactosEncontrados.add(c);
            }
        }
        return contactosEncontrados;
    }

    private void validarCampos(String nombre, String apellido, String telefono, String correoElectronico) throws Exception {
        if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || correoElectronico.isEmpty()) {
            throw new Exception("Todos los campos son obligatorios.");
        }
        if (!Pattern.matches("\\d{10}", telefono)) {
            throw new Exception("El número de teléfono debe tener 10 dígitos.");
        }
        if (!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", correoElectronico)) {
            throw new Exception("Correo electrónico incorrecto.");
        }
    }

    private Contacto buscarContactoPorId(String id) {
        return contactos.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    public boolean contactoDuplicado(String nombre, String apellido, String telefono) {
        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre) &&
                    c.getApellido().equalsIgnoreCase(apellido) &&
                    c.getTelefono().equals(telefono)) {
                return true; // Contacto duplicado encontrado
            }
        }
        return false;
    }

}


