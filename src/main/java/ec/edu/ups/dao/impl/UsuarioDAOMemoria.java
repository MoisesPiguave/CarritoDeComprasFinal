package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.PreguntasDeSeguridad;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOMemoria implements UsuarioDAO {

    private List<Usuario> listaUsuarios;

    public UsuarioDAOMemoria() {
        listaUsuarios = new ArrayList<>();
        Usuario admin = new Usuario("admin", "admin123", Rol.ADMINISTRADOR);
        listaUsuarios.add(admin);
    }

    @Override
    public void crearUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    @Override
    public Usuario buscarPorUsuarioYContrasenia(String usuario, String contrasenia) {
        for (Usuario u : listaUsuarios) {
            if (u.getUsername().equalsIgnoreCase(usuario) && u.getContrasenia().equals(contrasenia)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public Usuario buscarPorNombre(String usuario) {
        for (Usuario u : listaUsuarios) {
            if (u.getUsername().equalsIgnoreCase(usuario)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void actualizarContrasenia(String usuario, String nuevaContrasenia) {
        Usuario u = buscarPorNombre(usuario);
        if (u != null) {
            u.setContrasenia(nuevaContrasenia);
        }
    }

    @Override
    public void guardarPreguntasDeSeguridad(String usuario, List<PreguntasDeSeguridad> preguntas) {
        Usuario u = buscarPorNombre(usuario);
        if (u != null) {
            u.setPreguntasDeSeguridad(preguntas);
        }
    }

    @Override
    public List<PreguntasDeSeguridad> obtenerPreguntasDeSeguridad(String usuario) {
        Usuario u = buscarPorNombre(usuario);
        if (u != null) {
            return u.getPreguntasDeSeguridad();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean validarRespuestaDeSeguridad(String usuario, String pregunta, String respuesta) {
        Usuario u = buscarPorNombre(usuario);
        if (u != null && u.getPreguntasDeSeguridad() != null) {
            for (PreguntasDeSeguridad p : u.getPreguntasDeSeguridad()) {
                if (p.getPregunta().equalsIgnoreCase(pregunta) &&
                        p.getRespuesta().equalsIgnoreCase(respuesta)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Usuario> listarTodos() {
        return new ArrayList<>(listaUsuarios);
    }

    @Override
    public void eliminarUsuario(String username) {
        Usuario u = buscarPorNombre(username);
        if (u != null) {
            listaUsuarios.remove(u);
        }
    }
    @Override
    public void actualizarUsuario(Usuario usuarioActualizado) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            Usuario u = listaUsuarios.get(i);
            if (u.getUsername().equalsIgnoreCase(usuarioActualizado.getUsername())) {
                u.setContrasenia(usuarioActualizado.getContrasenia());
                u.setFechaNacimiento(usuarioActualizado.getFechaNacimiento());
                break;
            }
        }
    }
}
