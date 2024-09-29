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
            throw new Exception("deje la pereza y copie todo o si no pailas, no le creo ningun contacto");
        }

        if (!Pattern.matches("\\d{10}", telefono)) {
            throw new Exception("el numero no es de noruega o de republica checa, es de 10 numeritos");
        }

        if (!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", correoElectronico)) {
            throw new Exception("Ese correo esta como rarito jmm");
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

    public int obtenerContacto(String id){

        for (int i = 0; i < contactos.size(); i++) {
            if( contactos.get(i).getId().equals(id) ){
                return i;
            }
        }
        return -1;
    }

    public void eliminarContacto (String id)throws Exception{
        int posContacto = obtenerContacto(id);

        if(posContacto == -1){
            throw new Exception("que va a borrar si eso no existe, brut@");
        }

        contactos.remove(contactos.get(posContacto));

    }

    public void editarContacto (String id, String nombre, String apellido, String telefono, LocalDate cumpleaños, String correoElectronico, String fotoUrl)throws Exception{
        
        int posContacto = obtenerContacto(id);

        if(posContacto == -1){
            throw new Exception("oe tont@, no existe un contacto con esa ID, busque bien ");
        }

        if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || correoElectronico.isEmpty()) {
            throw new Exception("deje la pereza y copie todo o si no pailas, no le edito ningun contacto");
        }

        if (!Pattern.matches("\\d{10}", telefono)) {
            throw new Exception("el numero no es de noruega o de republica checa, es de 10 numeritos");
        }

        if (!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", correoElectronico)) {
            throw new Exception("Ese correo esta como rarito jmm");
        }

        Contacto contactoModificado = contactos.get(posContacto);
        contactoModificado.setNombre(nombre);
        contactoModificado.setApellido(apellido);
        contactoModificado.setTelefono(telefono);
        contactoModificado.setCumpleaños(cumpleaños);
        contactoModificado.setCorreoElectronico(correoElectronico);
        contactoModificado.setFotoUrl(fotoUrl);

        contactos.set(posContacto, contactoModificado);

    }

    public List<Contacto> listarContactos(){
        return contactos;
    }

    public List<Contacto> buscarContactosNombre(String nombre){
        List<Contacto> contactosEncontrados = new ArrayList<>();

        for(Contacto c : contactos){

            if(c.getNombre().equals(nombre));
            contactosEncontrados.add(c);
        }
        return contactosEncontrados;
    }

    public List<Contacto> buscarContactosTelefono(String telefono){
        List<Contacto> contactosEncontrados = new ArrayList<>();

        for(Contacto c : contactos){

            if(c.getTelefono().equals(telefono));
            contactosEncontrados.add(c);
        }
        return contactosEncontrados;
    }

}