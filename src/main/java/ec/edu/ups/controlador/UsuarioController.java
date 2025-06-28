package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.UsuarioDao;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.MenuPrincipalView;
import ec.edu.ups.vista.Usuario.AdminCarritosView;
import ec.edu.ups.vista.Usuario.AdminUsuariosView;
import ec.edu.ups.vista.Usuario.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UsuarioController {

    private final UsuarioDao usuarioDAO;
    private final CarritoDAO carritoDAO;
    private final LoginView loginView;
    private final AdminUsuariosView adminUsuariosView;
    private final AdminCarritosView adminCarritosView;
    private final MenuPrincipalView menuPrincipalView;

    private Usuario usuario;

    public UsuarioController(UsuarioDao usuarioDAO,
                             CarritoDAO carritoDAO,
                             LoginView loginView,
                             AdminUsuariosView adminUsuariosView,
                             AdminCarritosView adminCarritosView,
                             MenuPrincipalView menuPrincipalView) {
        this.usuarioDAO = usuarioDAO;
        this.carritoDAO = carritoDAO;
        this.loginView = loginView;
        this.adminUsuariosView = adminUsuariosView;
        this.adminCarritosView = adminCarritosView;
        this.menuPrincipalView = menuPrincipalView;
        this.usuario = null;

        configurarEventos();
    }

    private void configurarEventos() {
        loginView.getBtnIniciarSesion().addActionListener(e -> autenticar());

        menuPrincipalView.getMenuItemCerrarSesion().addActionListener(e -> cerrarSesion());

        adminUsuariosView.getBtnEliminar().addActionListener(e -> eliminarUsuarioSeleccionado());
        adminUsuariosView.getBtnActualizar().addActionListener(e -> cargarUsuariosAdmin());

        adminCarritosView.getBtnEliminar().addActionListener(e -> eliminarCarritoSeleccionado());
        adminCarritosView.getBtnActualizar().addActionListener(e -> cargarCarritosAdmin());
    }

    private void autenticar() {
        String username = loginView.getTxtUsername().getText().trim();
        String contrasenia = new String(loginView.getTxtPasswordField().getPassword()).trim();

        if (username.isEmpty() || contrasenia.isEmpty()) {
            loginView.mostrarMensaje("Complete usuario y contraseña.");
            return;
        }

        usuario = usuarioDAO.autenticar(username, contrasenia);

        if (usuario == null) {
            loginView.mostrarMensaje("Usuario o contraseña incorrectos.");
        } else {
            loginView.dispose();

            boolean esAdmin = usuario.getRol() == Rol.ADMINISTRADOR;
            menuPrincipalView.aplicarRestriccionesPorRol(esAdmin);

            menuPrincipalView.mostrarMensaje("Bienvenido: " + usuario.getUsername());
            menuPrincipalView.setVisible(true);

            if (esAdmin) {
                cargarUsuariosAdmin();
                cargarCarritosAdmin();
            }
        }
    }

    public void cerrarSesion() {
        menuPrincipalView.dispose();
        usuario = null;
        loginView.limpiarCampos();
        loginView.setVisible(true);
    }

    public String registrarUsuario(String username, String contrasenia, String confirmar) {
        if (username.isEmpty() || contrasenia.isEmpty() || confirmar.isEmpty()) {
            return "Complete todos los campos.";
        }

        if (!contrasenia.equals(confirmar)) {
            return "Las contraseñas no coinciden.";
        }

        if (usuarioDAO.existeUsuario(username)) {
            return "El nombre de usuario ya existe.";
        }

        Usuario nuevoUsuario = new Usuario(username, contrasenia, Rol.USUARIO);
        usuarioDAO.crear(nuevoUsuario);
        return "Usuario registrado correctamente.";
    }

    public void cargarUsuariosAdmin() {
        DefaultTableModel model = (DefaultTableModel) adminUsuariosView.getTablaUsuarios().getModel();
        model.setRowCount(0);
        List<Usuario> usuarios = usuarioDAO.listarTodos();

        for (Usuario u : usuarios) {
            model.addRow(new Object[]{
                    u.getUsername(),
                    u.getRol().toString()
            });
        }
    }

    public void eliminarUsuarioSeleccionado() {
        int fila = adminUsuariosView.getTablaUsuarios().getSelectedRow();
        if (fila < 0) {
            adminUsuariosView.mostrarMensaje("Seleccione un usuario para eliminar.");
            return;
        }

        String username = (String) adminUsuariosView.getTablaUsuarios().getValueAt(fila, 0);

        if (usuario != null && username.equals(usuario.getUsername())) {
            adminUsuariosView.mostrarMensaje("No puede eliminarse a sí mismo.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(adminUsuariosView,
                "¿Está seguro de eliminar este usuario?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        if (usuarioDAO.eliminar(username)) {
            adminUsuariosView.mostrarMensaje("Usuario eliminado.");
            cargarUsuariosAdmin();
        } else {
            adminUsuariosView.mostrarMensaje("No se pudo eliminar el usuario.");
        }
    }

    public void cargarCarritosAdmin() {
        DefaultTableModel model = (DefaultTableModel) adminCarritosView.getTablaCarritos().getModel();
        model.setRowCount(0);
        List<Carrito> carritos = carritoDAO.listarTodos();

        for (Carrito c : carritos) {
            model.addRow(new Object[]{
                    c.getCodigo(),
                    c.getUsuario() != null ? c.getUsuario().getUsername() : "Desconocido",
                    c.getFechaCreacion().getTime(),
                    c.calcularTotal()
            });
        }
    }

    public void eliminarCarritoSeleccionado() {
        int fila = adminCarritosView.getTablaCarritos().getSelectedRow();
        if (fila < 0) {
            adminCarritosView.mostrarMensaje("Seleccione un carrito para eliminar.");
            return;
        }
        int codigo = (int) adminCarritosView.getTablaCarritos().getValueAt(fila, 0);

        int confirm = JOptionPane.showConfirmDialog(adminCarritosView,
                "¿Está seguro de eliminar este carrito?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        carritoDAO.eliminar(codigo);
        adminCarritosView.mostrarMensaje("Carrito eliminado.");
        cargarCarritosAdmin();
    }

    public Usuario getUsuarioAutenticado() {
        return usuario;
    }
}