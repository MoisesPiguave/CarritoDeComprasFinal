package ec.edu.ups.controlador;

import ec.edu.ups.dao.PreguntaSeguridadDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.PreguntasDeSeguridad;
import ec.edu.ups.vista.Usuario.CrearUsuario1;
import ec.edu.ups.vista.Usuario.RecuperarUsuaioVIew;
import ec.edu.ups.vista.Usuario.RecuperarUsuario2;

import java.util.ArrayList;
import java.util.List;

public class PreguntasDeSeguridadController {

    private PreguntaSeguridadDAO preguntaDAO;
    private UsuarioDAO usuarioDAO;
    private CrearUsuario1 crearUsuario2View;
    private RecuperarUsuaioVIew recuperarView1;
    private RecuperarUsuario2 recuperarView2;

    public PreguntasDeSeguridadController(PreguntaSeguridadDAO preguntaDAO,
                                          UsuarioDAO usuarioDAO,
                                          CrearUsuario1 crearUsuario2View,
                                          RecuperarUsuaioVIew recuperarView1,
                                          RecuperarUsuario2 recuperarView2) {
        this.preguntaDAO = preguntaDAO;
        this.usuarioDAO = usuarioDAO;
        this.crearUsuario2View = crearUsuario2View;
        this.recuperarView1 = recuperarView1;
        this.recuperarView2 = recuperarView2;

        configurarEventos();
    }

    private void configurarEventos() {
        // Evento para guardar preguntas al finalizar el registro
        crearUsuario2View.getBtnContinuar().addActionListener(e -> guardarPreguntasDeSeguridad());

        // Evento para validar pregunta durante recuperación
        recuperarView1.getBtnContinuar().addActionListener(e -> validarPreguntaSeguridad());

        // Evento para actualizar la contraseña después de validar las preguntas
        recuperarView2.getContinuarButton().addActionListener(e -> actualizarContrasenia());
    }

    private void guardarPreguntasDeSeguridad() {
        String username = crearUsuario2View.getUsuarioAsociado();
        String respuesta1 = crearUsuario2View.getTextField2().getText().trim();
        String respuesta2 = crearUsuario2View.getTextField4().getText().trim();
        String respuesta3 = crearUsuario2View.getTextField6().getText().trim();

        if (respuesta1.isEmpty() || respuesta2.isEmpty() || respuesta3.isEmpty()) {
            crearUsuario2View.mostrarMensaje("Complete todas las respuestas de seguridad");
            return;
        }

        List<PreguntasDeSeguridad> preguntas = new ArrayList<>();
        preguntas.add(new PreguntasDeSeguridad(crearUsuario2View.getLlblP1().getText(), respuesta1));
        preguntas.add(new PreguntasDeSeguridad(crearUsuario2View.getLblP2().getText(), respuesta2));
        preguntas.add(new PreguntasDeSeguridad(crearUsuario2View.getLblP3().getText(), respuesta3));

        usuarioDAO.guardarPreguntasDeSeguridad(username, preguntas);

        crearUsuario2View.mostrarMensaje("Preguntas de seguridad guardadas exitosamente");
        crearUsuario2View.dispose();
    }

    private void validarPreguntaSeguridad() {
        String username = recuperarView1.getUsername();
        String preguntaSeleccionada = (String) recuperarView1.getComboBox1().getSelectedItem();
        String respuestaIngresada = recuperarView1.getTextField1().getText().trim();

        if (username.isEmpty() || respuestaIngresada.isEmpty()) {
            recuperarView1.mostrarMensaje("Complete todos los campos");
            return;
        }

        boolean valida = usuarioDAO.validarRespuestaDeSeguridad(username, preguntaSeleccionada, respuestaIngresada);

        if (valida) {
            recuperarView2.setVisible(true);
            recuperarView1.dispose();
        } else {
            recuperarView1.mostrarMensaje("Respuesta incorrecta");
        }
    }

    private void actualizarContrasenia() {
        String username = recuperarView1.getUsername();
        String nuevaContrasenia = new String(recuperarView2.getPasswordField1().getPassword());
        String confirmarContrasenia = new String(recuperarView2.getPasswordField2().getPassword());

        if (nuevaContrasenia.isEmpty() || confirmarContrasenia.isEmpty()) {
            recuperarView2.mostrarMensaje("Complete todos los campos");
            return;
        }

        if (!nuevaContrasenia.equals(confirmarContrasenia)) {
            recuperarView2.mostrarMensaje("Las contraseñas no coinciden");
            return;
        }

        usuarioDAO.actualizarContrasenia(username, nuevaContrasenia);
        recuperarView2.mostrarMensaje("Contraseña actualizada exitosamente");
        recuperarView2.dispose();
    }

    public List<String> obtenerTodasLasPreguntas() {
        return preguntaDAO.obtenerTodasLasPreguntas();
    }
}
