package co.edu.uniquindio.clinica.modelo;

import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;

import java.util.List;
import java.util.UUID;

public class Clinica {

    private final List<Paciente> pacientes;
    private final  List<Servicio> servicios;
    private final List<Cita> citas;

    public Clinica(List<Paciente> pacientes, List<Servicio> servicios, List<Cita> citas) {
        this.pacientes = pacientes;
        this.servicios = servicios;
        this.citas = citas;
    }


    public void registrarPaciente(String telefono, String nombre, String cedula, String email) throws  Exception{
        if (nombre.isEmpty() || cedula.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
            throw new Exception("Todos los campos son obligatorios.");
        }

        Paciente paciente = Paciente.builder()
                .id(UUID.randomUUID().toString())
                .telefono(telefono)
                .nombre(nombre)
                .cedula(cedula)
                .email(email)

                .build();
        pacientes.add(paciente);
    }

    public void registrarServicio(double precio, String nombre){ //FALTA VALIDACIONES

        Servicio servicio = Servicio.builder()
                .id(UUID.randomUUID().toString())
                .precio(precio)
                .nombre(nombre)
                .build();

        servicios.add(servicio);

    }

    public List<Servicio> listarDisponibles(){
        return servicios;
    }


    public void registrarCita(){

    }

    public void generarFactura(){

    }

    public void disponibilidadServicio(){

    }




}
