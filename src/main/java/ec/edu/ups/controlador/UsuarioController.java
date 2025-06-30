package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.PreguntasDeSeguridad;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.Usuario.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UsuarioController {

    private UsuarioDAO usuarioDAO;
    private LoginView loginView;
    private CrearUsuarioView crearUsuarioView;
    private CrearUsuario2 crearUsuario2View;
    private RecuperarUsuaioVIew recuperarView1;
    private RecuperarUsuario2 recuperarView2;
    private EliminarUsuarioView eliminarUsuarioView;
    private ListarUsuariosView listarUsuariosView;
    private Usuario usuarioLogueado;

    public UsuarioController(UsuarioDAO usuarioDAO,
                             LoginView loginView,
                             CrearUsuarioView crearUsuarioView,
                             CrearUsuario2 crearUsuario2View,
                             RecuperarUsuaioVIew recuperarView1,
                             RecuperarUsuario2 recuperarView2,
                             EliminarUsuarioView eliminarUsuarioView,
                             ListarUsuariosView listarUsuariosView) {

        if (usuarioDAO == null || loginView == null || crearUsuarioView == null ||
                crearUsuario2View == null || recuperarView1 == null || recuperarView2 == null ||
                eliminarUsuarioView == null || listarUsuariosView == null) {
            throw new IllegalArgumentException("Ninguna vista o DAO puede ser null al crear UsuarioController");
        }

        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.crearUsuarioView = crearUsuarioView;
        this.crearUsuario2View = crearUsuario2View;
        this.recuperarView1 = recuperarView1;
        this.recuperarView2 = recuperarView2;
        this.eliminarUsuarioView = eliminarUsuarioView;
        this.listarUsuariosView = listarUsuariosView;

        configurarEventos();
    }

    private void configurarEventos() {
        loginView.getBtnIniciarSesion().addActionListener(e -> {
            String username = loginView.getTextField1().getText().trim();
            String contrasenia = new String(loginView.getPasswordField1().getPassword());

            if (username.isEmpty() || contrasenia.isEmpty()) {
                loginView.mostrarMensaje("Ingrese usuario y contraseña");
                return;
            }

            Usuario usuario = usuarioDAO.buscarPorUsuarioYContrasenia(username, contrasenia);
            if (usuario == null) {
                loginView.mostrarMensaje("Usuario o contraseña incorrectos");
            } else {
                usuarioLogueado = usuario;
                loginView.mostrarMensaje("Bienvenido " + usuario.getUsername());
                abrirVentanaPrincipalSegunRol();
                loginView.dispose();
            }
        });

        loginView.getBtnRegistrarse().addActionListener(e -> {
            crearUsuarioView.setVisible(true);
            loginView.dispose();
        });

        crearUsuarioView.getBtnContinuar().addActionListener(e -> {
            String username = crearUsuarioView.getTxtUsuario().getText().trim();
            String contrasenia = new String(crearUsuarioView.getPswContrasena().getPassword());
            String confirmar = new String(crearUsuarioView.getPswConfirmar().getPassword());

            if (username.isEmpty() || contrasenia.isEmpty() || confirmar.isEmpty()) {
                crearUsuarioView.mostrarMensaje("Complete todos los campos");
                return;
            }
            if (!contrasenia.equals(confirmar)) {
                crearUsuarioView.mostrarMensaje("Las contraseñas no coinciden");
                return;
            }
            if (usuarioDAO.buscarPorNombre(username) != null) {
                crearUsuarioView.mostrarMensaje("El usuario ya existe");
                return;
            }

            crearUsuario2View.setVisible(true);
            crearUsuarioView.dispose();
        });

        crearUsuario2View.getBtnContinuar().addActionListener(e -> {
            String p1 = crearUsuario2View.getTextField2().getText().trim();
            String p2 = crearUsuario2View.getTextField4().getText().trim();
            String p3 = crearUsuario2View.getTextField6().getText().trim();

            if (p1.isEmpty() || p2.isEmpty() || p3.isEmpty()) {
                crearUsuario2View.mostrarMensaje("Complete las respuestas de seguridad");
                return;
            }

            String username = crearUsuarioView.getTxtUsuario().getText().trim();
            String contrasenia = new String(crearUsuarioView.getPswContrasena().getPassword());

            Usuario nuevoUsuario = new Usuario(username, contrasenia, Rol.USUARIO);
            usuarioDAO.crearUsuario(nuevoUsuario);

            List<PreguntasDeSeguridad> preguntas = List.of(
                    new PreguntasDeSeguridad(crearUsuario2View.getLlblP1().getText(), p1),
                    new PreguntasDeSeguridad(crearUsuario2View.getLblP2().getText(), p2),
                    new PreguntasDeSeguridad(crearUsuario2View.getLblP3().getText(), p3)
            );
            usuarioDAO.guardarPreguntasDeSeguridad(username, preguntas);

            crearUsuario2View.mostrarMensaje("Usuario creado con éxito");
            crearUsuario2View.dispose();
            loginView.setVisible(true);
        });

        recuperarView1.getBtnContinuar().addActionListener(e -> {
            String username = loginView.getTextField1().getText().trim();
            String preguntaSeleccionada = (String) recuperarView1.getComboBox1().getSelectedItem();
            String respuesta = recuperarView1.getTextField1().getText().trim();

            if (username.isEmpty() || respuesta.isEmpty()) {
                recuperarView1.mostrarMensaje("Complete todos los campos");
                return;
            }
            if (!usuarioDAO.validarRespuestaDeSeguridad(username, preguntaSeleccionada, respuesta)) {
                recuperarView1.mostrarMensaje("Respuesta incorrecta");
                return;
            }

            recuperarView2.setVisible(true);
            recuperarView1.dispose();
        });

        recuperarView2.getContinuarButton().addActionListener(e -> {
            String nuevaContrasenia = new String(recuperarView2.getPasswordField1().getPassword());
            String confirmarContrasenia = new String(recuperarView2.getPasswordField2().getPassword());
            String username = loginView.getTextField1().getText().trim();

            if (nuevaContrasenia.isEmpty() || confirmarContrasenia.isEmpty()) {
                recuperarView2.mostrarMensaje("Complete todos los campos");
                return;
            }
            if (!nuevaContrasenia.equals(confirmarContrasenia)) {
                recuperarView2.mostrarMensaje("Las contraseñas no coinciden");
                return;
            }

            usuarioDAO.actualizarContrasenia(username, nuevaContrasenia);
            recuperarView2.mostrarMensaje("Contraseña actualizada");
            recuperarView2.dispose();
            loginView.setVisible(true);
        });

        listarUsuariosView.getLblListar().addActionListener(e -> {
            if (usuarioLogueado == null || usuarioLogueado.getRol() != Rol.ADMINISTRADOR) {
                listarUsuariosView.mostrarMensaje("No tiene permiso para listar usuarios");
                return;
            }
            cargarUsuariosEnTabla();
        });

        eliminarUsuarioView.getBtnBuscar().addActionListener(e -> {
            if (usuarioLogueado == null || usuarioLogueado.getRol() != Rol.ADMINISTRADOR) {
                eliminarUsuarioView.mostrarMensaje("No tiene permiso para eliminar usuarios");
                return;
            }
            String username = eliminarUsuarioView.getTxtUsuario().getText().trim();
            if (username.isEmpty()) {
                eliminarUsuarioView.mostrarMensaje("Ingrese el usuario a eliminar");
                return;
            }
            Usuario u = usuarioDAO.buscarPorNombre(username);
            if (u == null) {
                eliminarUsuarioView.mostrarMensaje("Usuario no encontrado");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(eliminarUsuarioView,
                    "¿Eliminar usuario: " + username + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                usuarioDAO.eliminarUsuario(username);
                eliminarUsuarioView.mostrarMensaje("Usuario eliminado");
            }
        });
    }

    private void abrirVentanaPrincipalSegunRol() {
        ec.edu.ups.Main.abrirMenuPrincipal(usuarioLogueado);
    }

    public void cargarUsuariosEnTabla() {
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        DefaultTableModel modelo = listarUsuariosView.getModelo();
        modelo.setRowCount(0);

        for (Usuario u : usuarios) {
            modelo.addRow(new Object[]{ u.getUsername(), u.getContrasenia(), "Carritos..." });
        }
    }

    public void cerrarSesion() {
        usuarioLogueado = null;
        loginView.limpiarCampos();
        loginView.setVisible(true);
    }
}
