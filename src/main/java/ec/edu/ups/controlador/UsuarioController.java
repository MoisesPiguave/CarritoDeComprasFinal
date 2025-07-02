package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.Util.FormateadorUtils;
import ec.edu.ups.vista.Usuario.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UsuarioController {

    private UsuarioDAO usuarioDAO;
    private LoginView loginView;
    private CrearUsuarioView crearUsuarioView;
    private CrearUsuario1 crearUsuario2View;
    private RecuperarUsuaioVIew recuperarView1;
    private RecuperarUsuario2 recuperarView2;
    private EliminarUsuarioView eliminarUsuarioView;
    private ListarUsuariosView listarUsuariosView;
    private Usuario usuarioLogueado;

    public UsuarioController(UsuarioDAO usuarioDAO,
                             LoginView loginView,
                             CrearUsuarioView crearUsuarioView,
                             CrearUsuario1 crearUsuario2View,
                             RecuperarUsuaioVIew recuperarView1,
                             RecuperarUsuario2 recuperarView2,
                             EliminarUsuarioView eliminarUsuarioView,
                             ListarUsuariosView listarUsuariosView) {

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
            String fechaTexto = crearUsuarioView.getTxtFechaNacimiento().getText().trim();

            if (username.isEmpty() || contrasenia.isEmpty() || confirmar.isEmpty() || fechaTexto.isEmpty()) {
                crearUsuarioView.mostrarMensaje("Complete todos los campos, incluyendo la fecha de nacimiento");
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

            if (fechaTexto.length() != 10 || fechaTexto.charAt(2) != '/' || fechaTexto.charAt(5) != '/') {
                crearUsuarioView.mostrarMensaje("Formato de fecha incorrecto. Use dd/MM/yyyy");
                return;
            }

            int dia = Integer.parseInt(fechaTexto.substring(0, 2));
            int mes = Integer.parseInt(fechaTexto.substring(3, 5));
            int anio = Integer.parseInt(fechaTexto.substring(6, 10));

            if (dia < 1 || dia > 31 || mes < 1 || mes > 12 || anio < 1900 || anio > 2100) {
                crearUsuarioView.mostrarMensaje("Fecha no válida");
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaNacimiento = null;
            try {
                fechaNacimiento = sdf.parse(fechaTexto);
            } catch (Exception ex) {
                crearUsuarioView.mostrarMensaje("Error al convertir la fecha");
                return;
            }

            Usuario nuevoUsuario = new Usuario(username, contrasenia, Rol.USUARIO, fechaNacimiento);
            usuarioDAO.crearUsuario(nuevoUsuario);

            crearUsuario2View.setVisible(true);
            crearUsuarioView.dispose();
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

        listarUsuariosView.getLblListar().addActionListener(e -> {
            if (usuarioLogueado == null || usuarioLogueado.getRol() != Rol.ADMINISTRADOR) {
                listarUsuariosView.mostrarMensaje("No tiene permiso para listar usuarios");
                return;
            }
            cargarUsuariosEnTabla();
        });
    }

    private void abrirVentanaPrincipalSegunRol() {
        ec.edu.ups.Main.abrirMenuPrincipal(usuarioLogueado);
    }

    public void cargarUsuariosEnTabla() {
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        DefaultTableModel modelo = listarUsuariosView.getModelo();
        modelo.setRowCount(0);
        Locale locale = new Locale("es", "EC");

        for (Usuario u : usuarios) {
            String fechaFormateada = "N/A";
            if (u.getFechaNacimiento() != null) {
                fechaFormateada = FormateadorUtils.formatearFecha(u.getFechaNacimiento(), locale);
            }
            modelo.addRow(new Object[]{ u.getUsername(), u.getContrasenia(), fechaFormateada });
        }
    }

    public void cerrarSesion() {
        usuarioLogueado = null;
        loginView.limpiarCampos();
        loginView.setVisible(true);
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }
}
