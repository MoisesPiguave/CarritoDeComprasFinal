package ec.edu.ups.modelo;

import ec.edu.ups.Util.FormateadorUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Usuario {
    private String username;
    private String contrasenia;
    private Rol rol;
    private Date fechaNacimiento;
    private List<PreguntasDeSeguridad> preguntasDeSeguridad;

    public Usuario() {
        this.preguntasDeSeguridad = new ArrayList<>();
    }

    // Constructor completo
    public Usuario(String username, String contrasenia, Rol rol, Date fechaNacimiento, List<PreguntasDeSeguridad> preguntasDeSeguridad) {
        this.username = username;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.fechaNacimiento = fechaNacimiento;
        this.preguntasDeSeguridad = preguntasDeSeguridad != null ? preguntasDeSeguridad : new ArrayList<>();
    }

    // Constructor sin preguntas
    public Usuario(String username, String contrasenia, Rol rol, Date fechaNacimiento) {
        this(username, contrasenia, rol, fechaNacimiento, new ArrayList<>());
    }

    // **Nuevo constructor para mantener compatibilidad**
    public Usuario(String username, String contrasenia, Rol rol) {
        this(username, contrasenia, rol, null, new ArrayList<>());
    }

    // Getters y Setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<PreguntasDeSeguridad> getPreguntasDeSeguridad() {
        return preguntasDeSeguridad;
    }
    public void setPreguntasDeSeguridad(List<PreguntasDeSeguridad> preguntasDeSeguridad) {
        this.preguntasDeSeguridad = preguntasDeSeguridad != null ? preguntasDeSeguridad : new ArrayList<>();
    }

    @Override
    public String toString() {
        Locale locale = new Locale("es", "EC");
        String fechaFormateada = fechaNacimiento != null ? FormateadorUtils.formatearFecha(fechaNacimiento, locale) : "N/A";

        return "Usuario{" +
                "username='" + username + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", rol=" + rol +
                ", fechaNacimiento=" + fechaFormateada +
                ", preguntasDeSeguridad=" + preguntasDeSeguridad +
                '}';
    }
}
