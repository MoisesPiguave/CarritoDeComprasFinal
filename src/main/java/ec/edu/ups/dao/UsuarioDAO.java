package ec.edu.ups.dao;

import ec.edu.ups.modelo.Usuario;

import java.util.List;

public interface UsuarioDAO {

    void create(Usuario usuario);

    Usuario read(String username);

    void update(Usuario usuario);

    void delete(Usuario usuario);

    List<Usuario> listarTodos();

}
