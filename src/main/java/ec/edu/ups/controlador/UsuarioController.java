package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.Usuario.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UsuarioController {

    private UsuarioDAO usuarioDAO;

    private LoginView loginView;
    private CrearUsuario1View crearUsuario1View;
    private CrearUsuario2View crearUsuario2View;
    private RecuperarUsuaioVIew recuperarUsuarioView;
    private EliminarUsuarioView eliminarUsuarioView;
    private ListarUsuariosView listarUsuariosView;

    public UsuarioController(UsuarioDAO usuarioDAO,
                             LoginView loginView,
                             CrearUsuario1View crearUsuario1View,
                             CrearUsuario2View crearUsuario2View,
                             RecuperarUsuaioVIew recuperarUsuarioView,
                             EliminarUsuarioView eliminarUsuarioView,
                             ListarUsuariosView listarUsuariosView) {
        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.crearUsuario1View = crearUsuario1View;
        this.crearUsuario2View = crearUsuario2View;
        this.recuperarUsuarioView = recuperarUsuarioView;
        this.eliminarUsuarioView = eliminarUsuarioView;
        this.listarUsuariosView = listarUsuariosView;

        initListeners();
    }

    private void initListeners() {
        // Aquí agregas los listeners para cada vista, por ejemplo:

        // Listener para el botón continuar en crearUsuario1View
        crearUsuario1View.getBtnContinuar().addActionListener(e -> {
            try {
                String nombre = crearUsuario1View.getTxtNombre().getText();
                String telefono = crearUsuario1View.getTxtTelefono().getText();
                String username = crearUsuario1View.getTxtUsuario().getText();
                String correo = crearUsuario1View.getTxtCorreo().getText();
                String fechaStr = crearUsuario1View.getTxtFecha().getText();

                if (nombre.isEmpty() || telefono.isEmpty() || username.isEmpty() || correo.isEmpty() || fechaStr.isEmpty()) {
                    crearUsuario1View.mostrarMensaje("Debe llenar todos los campos.");
                    return;
                }

                if (telefono.length() != 10) {
                    crearUsuario1View.mostrarMensaje("El teléfono debe tener 10 dígitos.");
                    return;
                }

                if (!correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                    crearUsuario1View.mostrarMensaje("Correo inválido.");
                    return;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaNacimiento = sdf.parse(fechaStr);

                if (usuarioDAO.read(username) != null) {
                    crearUsuario1View.mostrarMensaje("El nombre de usuario ya existe.");
                    return;
                }

                // Aquí puedes guardar temporalmente el usuario para continuar registro
                Usuario usuarioTemp = new Usuario(nombre, telefono, username, correo, fechaNacimiento, null, ec.edu.ups.modelo.Rol.USUARIO);
                // Guardar usuarioTemp en algún atributo para pasar a crearUsuario2View (segundo paso)

                crearUsuario1View.mostrarMensaje("Datos válidos, continúa al siguiente paso.");
                crearUsuario1View.setVisible(false);
                crearUsuario2View.setVisible(true);

                // TODO: pasar usuarioTemp a crearUsuario2View o controlarlo desde aquí

            } catch (ParseException ex) {
                crearUsuario1View.mostrarMensaje("Formato de fecha inválido. Use dd/MM/yyyy");
            }
        });

        // Aquí agregas más listeners para login, recuperar usuario, eliminar, listar, etc.
    }

    public void cargarUsuariosEnTabla() {
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        DefaultTableModel modelo = (DefaultTableModel) listarUsuariosView.getTable1().getModel();
        modelo.setRowCount(0);

        for (Usuario u : usuarios) {
            Object[] fila = {
                    u.getNombre(),
                    u.getUsername(),
                    u.getTelefono(),
                    u.getCorreo()
            };
            modelo.addRow(fila);
        }
    }

    // Métodos para buscar, eliminar, actualizar usuarios, login, etc.
}
