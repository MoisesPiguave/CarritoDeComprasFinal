package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOMemoria implements UsuarioDAO {

    private List<Usuario> listaUsuarios;

    public UsuarioDAOMemoria() {
        listaUsuarios = new ArrayList<>();

        // Usuario admin por defecto para prueba:
        // (nombre, telefono, username, correo, fechaNacimiento, contrasenia, rol)
        Usuario admin = new Usuario(
                "Admin Principal",
                "0999999999",
                "admin",
                "admin@empresa.com",
                null,  // puede ser null o alguna fecha por defecto
                "admin123",
                ec.edu.ups.modelo.Rol.ADMINISTRADOR
        );
        listaUsuarios.add(admin);
    }

    @Override
    public void create(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    @Override
    public Usuario read(String username) {
        for (Usuario u : listaUsuarios) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void update(Usuario usuario) {
        Usuario u = read(usuario.getUsername());
        if (u != null) {
            listaUsuarios.remove(u);
            listaUsuarios.add(usuario);
        }
    }

    @Override
    public void delete(Usuario usuario) {
        listaUsuarios.removeIf(u -> u.getUsername().equalsIgnoreCase(usuario.getUsername()));
    }

    @Override
    public List<Usuario> listarTodos() {
        return new ArrayList<>(listaUsuarios);
    }
}
