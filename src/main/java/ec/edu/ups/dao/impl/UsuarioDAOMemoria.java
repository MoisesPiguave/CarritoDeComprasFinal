package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.PreguntasDeSeguridad;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOMemoria implements UsuarioDAO {

    private List<Usuario> usuarios;
    private List<UsuarioPreguntas> usuarioPreguntasList;

    public UsuarioDAOMemoria() {
        usuarios = new ArrayList<>();
        usuarioPreguntasList = new ArrayList<>();

        crearUsuario(new Usuario("admin", "12345", Rol.ADMINISTRADOR));
        crearUsuario(new Usuario("user", "12345", Rol.USUARIO));
    }

    @Override
    public void crearUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public Usuario buscarPorUsuarioYContrasenia(String usuario, String contrasenia) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(usuario) && u.getContrasenia().equals(contrasenia)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public Usuario buscarPorNombre(String usuario) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(usuario)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void actualizarContrasenia(String usuario, String nuevaContrasenia) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(usuario)) {
                u.setContrasenia(nuevaContrasenia);
                break;
            }
        }
    }

    @Override
    public void guardarPreguntasDeSeguridad(String usuario, List<PreguntasDeSeguridad> preguntas) {
        for (UsuarioPreguntas up : usuarioPreguntasList) {
            if (up.getUsername().equals(usuario)) {
                usuarioPreguntasList.remove(up);
                break;
            }
        }
        usuarioPreguntasList.add(new UsuarioPreguntas(usuario, preguntas));
    }

    @Override
    public List<PreguntasDeSeguridad> obtenerPreguntasDeSeguridad(String usuario) {
        for (UsuarioPreguntas up : usuarioPreguntasList) {
            if (up.getUsername().equals(usuario)) {
                return up.getPreguntas();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public boolean validarRespuestaDeSeguridad(String usuario, String pregunta, String respuesta) {
        for (UsuarioPreguntas up : usuarioPreguntasList) {
            if (up.getUsername().equals(usuario)) {
                for (PreguntasDeSeguridad ps : up.getPreguntas()) {
                    if (ps.getPregunta().equals(pregunta) && ps.getRespuesta().equals(respuesta)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private class UsuarioPreguntas {
        private String username;
        private List<PreguntasDeSeguridad> preguntas;

        public UsuarioPreguntas(String username, List<PreguntasDeSeguridad> preguntas) {
            this.username = username;
            this.preguntas = preguntas;
        }

        public String getUsername() {
            return username;
        }

        public List<PreguntasDeSeguridad> getPreguntas() {
            return preguntas;
        }
    }
}
