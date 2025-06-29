package ec.edu.ups.modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String username;
    private String contrasenia;
    private Rol rol;
    private List<PreguntasDeSeguridad> preguntasDeSeguridad;

    public Usuario() {
        this.preguntasDeSeguridad = new ArrayList<>();
    }

    public Usuario(String username, String contrasenia, Rol rol, List<PreguntasDeSeguridad> preguntasDeSeguridad) {
        this.username = username;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.preguntasDeSeguridad = preguntasDeSeguridad != null ? preguntasDeSeguridad : new ArrayList<>();
    }

    // Constructor adicional para crear usuario sin preguntas de seguridad
    public Usuario(String username, String contrasenia, Rol rol) {
        this(username, contrasenia, rol, new ArrayList<>());
    }

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

    public List<PreguntasDeSeguridad> getPreguntasDeSeguridad() {
        return preguntasDeSeguridad;
    }

    public void setPreguntasDeSeguridad(List<PreguntasDeSeguridad> preguntasDeSeguridad) {
        this.preguntasDeSeguridad = preguntasDeSeguridad != null ? preguntasDeSeguridad : new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", rol=" + rol +
                ", preguntasDeSeguridad=" + preguntasDeSeguridad +
                '}';
    }
}
