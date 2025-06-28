package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.PreguntasDeSeguridad;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.Usuario.CrearUsuario2;
import ec.edu.ups.vista.Usuario.CrearUsuarioView;
import ec.edu.ups.vista.Usuario.LoginView;
import ec.edu.ups.vista.Usuario.RecuperarUsuaioVIew;
import ec.edu.ups.vista.Usuario.RecuperarUsuario2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController {

    private final UsuarioDAO usuarioDAO;
    private final LoginView loginView;
    private final CrearUsuarioView crearUsuarioView;
    private final CrearUsuario2 crearUsuario2View;
    private final RecuperarUsuaioVIew recuperarUsuarioView;
    private final RecuperarUsuario2 recuperarUsuario2View;

    private Usuario usuarioAutenticado;

    private String tempUsername = "";
    private String tempContrasenia = "";

    public UsuarioController(UsuarioDAO usuarioDAO,
                             LoginView loginView,
                             CrearUsuarioView crearUsuarioView,
                             CrearUsuario2 crearUsuario2View,
                             RecuperarUsuaioVIew recuperarUsuarioView,
                             RecuperarUsuario2 recuperarUsuario2View) {
        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.crearUsuarioView = crearUsuarioView;
        this.crearUsuario2View = crearUsuario2View;
        this.recuperarUsuarioView = recuperarUsuarioView;
        this.recuperarUsuario2View = recuperarUsuario2View;

        configurarEventos();
    }

    private void configurarEventos() {
        loginView.getBtnIniciarSesion().addActionListener(e -> autenticar());
        loginView.getBtnRegistrarse().addActionListener(e -> {
            loginView.setVisible(false);
            crearUsuarioView.setVisible(true);
        });


        loginView.getBtnRecuperar().addActionListener(e -> {
            loginView.setVisible(false);
            recuperarUsuarioView.setVisible(true);
            cargarPreguntasEnComboBox("");
        });


        crearUsuarioView.getBtnCancelar().addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(crearUsuarioView,
                    "¿Desea cancelar la creación de usuario?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                crearUsuarioView.dispose();
                loginView.setVisible(true);
            }
        });


        crearUsuarioView.getBtnContinuar().addActionListener(e -> {
            String usuario = crearUsuarioView.getTxtUsuario().getText().trim();
            String contra = new String(crearUsuarioView.getPswContrasena().getPassword());
            String confirmar = new String(crearUsuarioView.getPswConfirmar().getPassword());

            if (usuario.isEmpty() || contra.isEmpty() || confirmar.isEmpty()) {
                crearUsuarioView.mostrarMensaje("Complete todos los campos.");
                return;
            }
            if (!contra.equals(confirmar)) {
                crearUsuarioView.mostrarMensaje("Las contraseñas no coinciden.");
                return;
            }
            if (usuarioDAO.buscarPorNombre(usuario) != null) {
                crearUsuarioView.mostrarMensaje("El usuario ya existe.");
                return;
            }

            crearUsuarioView.setVisible(false);
            crearUsuario2View.setVisible(true);


            tempUsername = usuario;
            tempContrasenia = contra;
        });


        crearUsuario2View.getBtnContinuar().addActionListener(e -> {
            List<PreguntasDeSeguridad> preguntas = new ArrayList<>();

            preguntas.add(new PreguntasDeSeguridad(crearUsuario2View.getLlblP1().getText(),
                    crearUsuario2View.getTextField2().getText()));
            preguntas.add(new PreguntasDeSeguridad(crearUsuario2View.getLblP2().getText(),
                    crearUsuario2View.getTextField4().getText()));
            preguntas.add(new PreguntasDeSeguridad(crearUsuario2View.getLblP3().getText(),
                    crearUsuario2View.getTextField6().getText()));

            if (preguntas.stream().anyMatch(p -> p.getRespuesta() == null || p.getRespuesta().isEmpty())) {
                crearUsuario2View.mostrarMensaje("Debe responder todas las preguntas.");
                return;
            }

            Usuario nuevoUsuario = new Usuario(tempUsername, tempContrasenia, Rol.USUARIO);
            usuarioDAO.crearUsuario(nuevoUsuario);
            usuarioDAO.guardarPreguntasDeSeguridad(tempUsername, preguntas);

            crearUsuario2View.mostrarMensaje("Usuario creado correctamente.");
            crearUsuario2View.dispose();
            loginView.setVisible(true);
        });


        recuperarUsuarioView.getBtnContinuar().addActionListener(e -> {
            String usuario = recuperarUsuarioView.getTextField1().getText().trim();
            String pregunta = (String) recuperarUsuarioView.getComboBox1().getSelectedItem();
            String respuesta = recuperarUsuarioView.getTextField1().getText().trim();

            if (usuario.isEmpty()) {
                recuperarUsuarioView.mostrarMensaje("Ingrese el usuario.");
                return;
            }
            if (pregunta == null || pregunta.isEmpty()) {
                recuperarUsuarioView.mostrarMensaje("Seleccione una pregunta.");
                return;
            }
            if (respuesta.isEmpty()) {
                recuperarUsuarioView.mostrarMensaje("Ingrese la respuesta.");
                return;
            }

            boolean valida = usuarioDAO.validarRespuestaDeSeguridad(usuario, pregunta, respuesta);
            if (valida) {
                usuarioAutenticado = usuarioDAO.buscarPorNombre(usuario);
                recuperarUsuarioView.dispose();
                recuperarUsuario2View.setVisible(true);
            } else {
                recuperarUsuarioView.mostrarMensaje("Respuesta incorrecta.");
            }
        });


        recuperarUsuario2View.getContinuarButton().addActionListener(e -> {
            String nuevaContra = new String(recuperarUsuario2View.getPasswordField1().getPassword());
            String confirmarContra = new String(recuperarUsuario2View.getPasswordField2().getPassword());

            if (nuevaContra.isEmpty() || confirmarContra.isEmpty()) {
                recuperarUsuario2View.mostrarMensaje("Complete ambos campos.");
                return;
            }
            if (!nuevaContra.equals(confirmarContra)) {
                recuperarUsuario2View.mostrarMensaje("Las contraseñas no coinciden.");
                return;
            }

            usuarioDAO.actualizarContrasenia(usuarioAutenticado.getUsername(), nuevaContra);
            recuperarUsuario2View.mostrarMensaje("Contraseña actualizada correctamente.");
            recuperarUsuario2View.dispose();
            loginView.setVisible(true);
        });
    }

    private void autenticar() {
        String usuario = loginView.getTextField1().getText().trim();
        String contrasenia = new String(loginView.getPasswordField1().getPassword()).trim();

        if (usuario.isEmpty() || contrasenia.isEmpty()) {
            loginView.mostrarMensaje("Complete usuario y contraseña.");
            return;
        }

        usuarioAutenticado = usuarioDAO.buscarPorUsuarioYContrasenia(usuario, contrasenia);

        if (usuarioAutenticado == null) {
            loginView.mostrarMensaje("Usuario o contraseña incorrectos.");
        } else {
            loginView.mostrarMensaje("Bienvenido, " + usuarioAutenticado.getUsername());
            loginView.dispose();

        }
    }

    private void cargarPreguntasEnComboBox(String username) {
        recuperarUsuarioView.getComboBox1().removeAllItems();
        if (username.isEmpty()) return;

        List<PreguntasDeSeguridad> preguntas = usuarioDAO.obtenerPreguntasDeSeguridad(username);
        for (PreguntasDeSeguridad p : preguntas) {
            recuperarUsuarioView.getComboBox1().addItem(p.getPregunta());
        }
    }
}
