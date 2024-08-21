package org.example.Usuario;

public class Usuario {

    private String nombreUsuario, direccion, correo, contrasena;
    private final long idUsuario;
    private long telefono;

    public Usuario(String nombreUsuario, String direccion, String correo, String contrasena, long idUsuario, long telefono) {
        this.nombreUsuario = nombreUsuario;
        this.direccion = direccion;
        this.correo = correo;
        this.contrasena = contrasena;
        this.idUsuario = idUsuario;
        this.telefono = telefono;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", direccion='" + direccion + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", idUsuario=" + idUsuario +
                ", telefono=" + telefono +
                '}';
    }
}
