Equipo:


Derek Rodriguez Rodriguez,
Brahian Toro Carmona,
Sara Vergara Quevedo


Diagrama de clases (PlantUML):


@startuml
skinparam classAttributeIconSize 0

class Persona {
    - String nombre
    - long id
    + Persona(String, long)
    + getNombre(): String
    + getId(): long
    + setNombre(String): void
}

class Usuario extends Persona {
    - String direccion
    - String correo
    - String contrasena
    - long telefono
    - List<Entrenamiento> entrenamientos
    + Usuario(String, long, String, String, String, long)
    + getDireccion(): String
    + getCorreo(): String
    + getContrasena(): String
    + getTelefono(): long
    + setDireccion(String): void
    + setCorreo(String): void
    + setContrasena(String): void
    + setTelefono(long): void
}

class Entrenador extends Persona {
    - String especialidad
    + Entrenador(String, long, String)
    + getEspecialidad(): String
    + setEspecialidad(String): void
}

class Clase {
    - String nombreClase
    - TipoClase tipoClase
    - Entrenador entrenador
    - LocalTime horarioClase
    - LocalDate fechaInicioClase
    - LocalDate fechaFinClase
    - short capacidad
    - boolean estadoClase
    - String idClase
    - List<Reserva> registroReserva
    + Clase(String, TipoClase, Entrenador, LocalTime, LocalDate, LocalDate, short, String, List<Reserva>)
    + getters and setters...
}

class Reserva {
    - LocalDateTime fechaReserva
    - Usuario usuario
    - Clase clase
    - String codigo
    + Reserva(Usuario, LocalDateTime, Clase, String)
    + getters and setters...
}

enum TipoClase {
    AEROBICOS
    FLEXIBILIDAD
    FUERZA
    RESISTENCIA
}

class Entrenamiento {
    - int duracion
    - int caloriasQuemadas
    - TipoEntrenamiento tipoEntrenamiento
    - String descripcion
    + Entrenamiento(TipoEntrenamiento, String, int, int)
    + getters and setters...
}

enum TipoEntrenamiento {
    CARDIO
    PESAS
    FLEXIBILIDAD
    HIIT
    YOGA
    PILATES
    ZUMBA
    NATACION
    CROSSFIT
}

class GestionUsuario {
    - List<Usuario> usuarios
    + agregarUsuario(...): void
    + actualizarUsuario(...): void
    + eliminarUsuario(...): void
    + listarUsuarios(): void
}

class GestionEntrenador {
    - List<Entrenador> entrenadores
    + agregarEntrenador(...): void
    + actualizarEntrenador(...): void
    + eliminarEntrenador(...): void
    + listarEntrenadores(): void
}

class GestionClase {
    - List<Clase> clases
    + agregarClase(...): void
    + actualizarClase(...): void
    + eliminarClase(...): void
    + listarClases(): void
}

class GestionEntrenamiento {
    - List<Entrenamiento> entrenamientos
    + agregarEntrenamiento(...): void
    + listarEntrenamientos(): void
}

class Reporte {
    - Clase claseMasPopular
    - List<Usuario> topTresUsuariosMasActivos
    - TipoEntrenamiento tipoEjercicioMasPracticado
    + Reporte(Clase, List<Usuario>, TipoEntrenamiento)
    + getClaseMasPopular(): Clase
    + getTopTresUsuariosMasActivos(): List<Usuario>
    + getTipoEjercicioMasPracticado(): TipoEntrenamiento
}

Persona <|-- Usuario
Persona <|-- Entrenador
Clase "1" --> "*" Reserva
Usuario "1" --> "*" Reserva
Usuario "1" --> "*" Entrenamiento
Clase "1" --> "1" Entrenador
TipoClase <-- Clase
TipoEntrenamiento <-- Entrenamiento
Reporte --> Clase
Reporte --> List<Usuario>
Reporte --> TipoEntrenamiento
@enduml
