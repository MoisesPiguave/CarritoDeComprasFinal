package ec.edu.ups.dao;

import ec.edu.ups.modelo.PreguntasDeSeguridad;
import ec.edu.ups.modelo.Usuario;
import java.util.List;

public interface UsuarioDAO {
    void crearUsuario(Usuario usuario);
    Usuario buscarPorUsuarioYContrasenia(String usuario, String contrasenia);
    Usuario buscarPorNombre(String usuario);
    void actualizarContrasenia(String usuario, String nuevaContrasenia);
    void guardarPreguntasDeSeguridad(String usuario, List<PreguntasDeSeguridad> preguntas);
    List<PreguntasDeSeguridad> obtenerPreguntasDeSeguridad(String usuario);
    boolean validarRespuestaDeSeguridad(String usuario, String pregunta, String respuesta);
}
