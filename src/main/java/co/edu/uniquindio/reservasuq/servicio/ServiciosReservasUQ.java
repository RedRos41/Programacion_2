package co.edu.uniquindio.reservasuq.servicio;


import co.edu.uniquindio.reservasuq.modelo.Horario;
import co.edu.uniquindio.reservasuq.modelo.Instalacion;
import co.edu.uniquindio.reservasuq.modelo.Persona;
import co.edu.uniquindio.reservasuq.modelo.Reserva;
import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import java.time.LocalDate;
import java.util.List;


public interface ServiciosReservasUQ {

    Persona login(String correo, String contrasena) throws Exception;

    void registrarPersona(String cedula, String nombre, TipoPersona tipoPersona, String email, String password) throws Exception;

    void crearInstalacion(String nombre, int aforo, float costo, List<Horario> horarios);

    Reserva crearReserva(String idInstalacion, String cedulaPersona, LocalDate diaReserva, String horaReserva) throws Exception;

    List<Reserva> listarTodasReservas();

    List<Reserva> listarReservasPorPersona(String cedulaPersona);

    void cancelarReserva(String idReserva) throws Exception;

    List<Instalacion> listarInstalaciones();

    Instalacion buscarInstalacionPorId(String idInstalacion) throws Exception;

    void gestionarCapacidadInstalacion(String idInstalacion, int nuevaCapacidad) throws Exception;

    void asignarHorarioInstalacion(String idInstalacion, List<Horario> nuevosHorarios) throws Exception;

    List<Horario> listarHorariosDisponibles(String idInstalacion, LocalDate fecha);

    Instalacion buscarInstalacionPorNombre(String nombre) throws Exception;

}
