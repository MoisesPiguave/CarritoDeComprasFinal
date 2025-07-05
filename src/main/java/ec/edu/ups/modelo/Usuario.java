package ec.edu.ups.modelo;

import ec.edu.ups.Util.FormateadorUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Usuario {

    private String nombre;
    private String telefono;
    private String username;
    private String correo;
    private Date fechaNacimiento;
    private String contrasenia;
    private Rol rol;
    private List<PreguntasDeSeguridad> preguntasDeSeguridad;

    public Usuario() {
        this.preguntasDeSeguridad = new ArrayList<>();
    }

    public Usuario(String nombre, String telefono, String username, String correo,
                   Date fechaNacimiento, String contrasenia, Rol rol, List<PreguntasDeSeguridad> preguntasDeSeguridad) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.username = username;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.preguntasDeSeguridad = preguntasDeSeguridad != null ? preguntasDeSeguridad : new ArrayList<>();
    }

    // Constructor sin preguntas
    public Usuario(String nombre, String telefono, String username, String correo,
                   Date fechaNacimiento, String contrasenia, Rol rol) {
        this(nombre, telefono, username, correo, fechaNacimiento, contrasenia, rol, null);
    }

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<PreguntasDeSeguridad> getPreguntasDeSeguridad() {
        return preguntasDeSeguridad;
    }

    public void setPreguntasDeSeguridad(List<PreguntasDeSeguridad> preguntasDeSeguridad) {
        this.preguntasDeSeguridad = preguntasDeSeguridad != null ? preguntasDeSeguridad : new ArrayList<>();
    }

    @Override
    public String toString() {
        String fechaFormateada = (fechaNacimiento != null)
                ? FormateadorUtils.formatearFecha(fechaNacimiento, new Locale("es", "EC"))
                : "N/A";

        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", username='" + username + '\'' +
                ", correo='" + correo + '\'' +
                ", fechaNacimiento=" + fechaFormateada +
                ", contrasenia='" + contrasenia + '\'' +
                ", rol=" + rol +
                ", preguntasDeSeguridad=" + preguntasDeSeguridad +
                '}';
    }
}
