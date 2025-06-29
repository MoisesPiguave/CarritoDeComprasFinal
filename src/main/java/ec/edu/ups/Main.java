package ec.edu.ups;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.MenuPrincipalView;
import ec.edu.ups.vista.Carrito.*;
import ec.edu.ups.vista.Producto.*;
import ec.edu.ups.vista.Usuario.*;

import javax.swing.*;

public class Main {

    private static UsuarioDAO usuarioDAO = new UsuarioDAOMemoria();
    private static ProductoDAO productoDAO = new ProductoDAOMemoria();
    private static CarritoDAO carritoDAO = new CarritoDAOMemoria();

    // Vistas principales
    private static LoginView loginView = new LoginView();
    private static MenuPrincipalView menuPrincipalView = new MenuPrincipalView();

    // Vistas Producto
    private static ProductoAnadirView productoAnadirView = new ProductoAnadirView();
    private static ProductoListarView productoListarView = new ProductoListarView();
    private static ProductoActualizarView productoActualizarView = new ProductoActualizarView();
    private static ProductoEliminarView productoEliminarView = new ProductoEliminarView();

    // Vistas Carrito
    private static CarritoCrearView carritoCrearView = new CarritoCrearView();
    private static CarritoListarView carritoListarView = new CarritoListarView();
    private static CarritoActualizarView carritoActualizarView = new CarritoActualizarView();
    private static CarritoEliminarView carritoEliminarView = new CarritoEliminarView();

    // Vistas Usuario (admin)
    private static CrearUsuarioView crearUsuarioView = new CrearUsuarioView();
    private static CrearUsuario2 crearUsuario2View = new CrearUsuario2();
    private static ListarUsuariosView listarUsuariosView = new ListarUsuariosView();
    private static EliminarUsuarioView eliminarUsuarioView = new EliminarUsuarioView();

    // Controladores
    private static UsuarioController usuarioController;
    private static ProductoController productoController;
    private static CarritoController carritoController;

    private static Usuario usuarioLogueado;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            inicializarControladores();
            mostrarLogin();
        });
    }

    private static void inicializarControladores() {
        usuarioController = new UsuarioController(
                usuarioDAO,
                loginView,
                crearUsuarioView,
                crearUsuario2View,
                null,
                null,
                eliminarUsuarioView,
                listarUsuariosView
        );

        productoController = new ProductoController(
                productoDAO,
                productoAnadirView,
                productoListarView,
                productoEliminarView,
                productoActualizarView
        );
        DetallesDeCarritoView detallesDeCarritoView = new DetallesDeCarritoView();

        carritoController = new CarritoController(carritoDAO, productoDAO, carritoCrearView,
                carritoListarView, carritoActualizarView,
                carritoEliminarView, detallesDeCarritoView);
        configurarEventosLogin();
        configurarEventosMenuPrincipal();
    }

    private static void configurarEventosLogin() {
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
                loginView.setVisible(false);
                abrirMenuPrincipalSegunRol(usuario);
            }
        });

        loginView.getBtnRegistrarse().addActionListener(e -> {
            crearUsuarioView.setVisible(true);
            loginView.setVisible(false);
        });
    }

    private static void configurarEventosMenuPrincipal() {
        // Usuarios
        menuPrincipalView.getMenuItemCrearUsuario().addActionListener(e -> crearUsuarioView.setVisible(true));

        menuPrincipalView.getMenuItemListarUsuario().addActionListener(e -> {
            usuarioController.cargarUsuariosEnTabla();
            listarUsuariosView.setVisible(true);
        });

        menuPrincipalView.getMenuItemEliminarUsuario().addActionListener(e -> eliminarUsuarioView.setVisible(true));

        // Productos
        menuPrincipalView.getMenuItemCrearProducto().addActionListener(e -> productoAnadirView.setVisible(true));

        menuPrincipalView.getMenuItemListarProducto().addActionListener(e -> {
            productoListarView.cargarDatos(productoDAO.listarTodos());
            productoListarView.setVisible(true);
        });

        menuPrincipalView.getMenuItemActualizarProducto().addActionListener(e -> productoActualizarView.setVisible(true));

        menuPrincipalView.getMenuItemEliminarProducto().addActionListener(e -> productoEliminarView.setVisible(true));

        menuPrincipalView.getMenuItemBuscarProducto().addActionListener(e -> {
            productoListarView.cargarDatos(productoDAO.listarTodos());
            productoListarView.setVisible(true);
        });

        // Carrito
        menuPrincipalView.getMenuItemCrearCarrito().addActionListener(e -> carritoCrearView.setVisible(true));

        menuPrincipalView.getMenuItemListarCarrito().addActionListener(e -> {
            carritoListarView.cargarDatos(carritoDAO.listarTodos());
            carritoListarView.setVisible(true);
        });

        menuPrincipalView.getMenuItemActualizarCarrito().addActionListener(e -> carritoActualizarView.setVisible(true));

        menuPrincipalView.getMenuItemEliminarCarrito().addActionListener(e -> carritoEliminarView.setVisible(true));

        // Cerrar sesión
        menuPrincipalView.getMenuItemCerrarSesion().addActionListener(e -> {
            menuPrincipalView.dispose();
            usuarioController.cerrarSesion();
        });
    }


    private static void abrirMenuPrincipalSegunRol(Usuario usuario) {
        boolean esAdmin = usuario.getRol() == Rol.ADMINISTRADOR;
        menuPrincipalView.aplicarRestriccionesPorRol(esAdmin);
        menuPrincipalView.setVisible(true);
    }

    private static void mostrarLogin() {
        loginView.limpiarCampos();
        loginView.setVisible(true);
        menuPrincipalView.setVisible(false);
    }

    private static void cerrarSesion() {
        usuarioLogueado = null;
        menuPrincipalView.setVisible(false);
        loginView.limpiarCampos();
        loginView.setVisible(true);
    }
}
