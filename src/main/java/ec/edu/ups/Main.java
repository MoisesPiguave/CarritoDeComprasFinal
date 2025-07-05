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

    // DAOs
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
    private static DetallesDeCarritoView detallesDeCarritoView = new DetallesDeCarritoView();

    // Vistas Usuario
    private static CrearUsuario1View crearUsuario1View = new CrearUsuario1View();
    private static CrearUsuario2View crearUsuario2View = new CrearUsuario2View();
    private static RecuperarUsuaioVIew recuperarUsuarioView = new RecuperarUsuaioVIew();
    private static ListarUsuariosView listarUsuariosView = new ListarUsuariosView();
    private static EliminarUsuarioView eliminarUsuarioView = new EliminarUsuarioView();

    // Controladores
    private static UsuarioController usuarioController;
    private static ProductoController productoController;
    private static CarritoController carritoController;

    // Usuario logueado (sesión)
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
                crearUsuario1View,
                crearUsuario2View,
                recuperarUsuarioView,
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

        carritoController = new CarritoController(
                carritoDAO,
                productoDAO,
                carritoCrearView,
                carritoListarView,
                carritoActualizarView,
                carritoEliminarView,
                detallesDeCarritoView
        );

        configurarEventosLogin();
        configurarEventosMenuPrincipal();
    }

    private static void configurarEventosLogin() {
        loginView.setVisible(true);
    }

    public static void abrirMenuPrincipal(Usuario usuario) {
        usuarioLogueado = usuario;

        boolean esAdmin = usuario.getRol() == Rol.ADMINISTRADOR;
        boolean esUsuarioNormal = usuario.getRol() == Rol.USUARIO;

        menuPrincipalView.aplicarRestriccionesPorRol(esAdmin, esUsuarioNormal);

        menuPrincipalView.setVisible(true);
        loginView.setVisible(false);
    }

    private static void configurarEventosMenuPrincipal() {
        // Menú Usuarios
        menuPrincipalView.getMenuItemCrearUsuario().addActionListener(e -> crearUsuario1View.setVisible(true));

        menuPrincipalView.getMenuItemListarUsuario().addActionListener(e -> {
            usuarioController.cargarUsuariosEnTabla();
            menuPrincipalView.abrirVentanaInterna(listarUsuariosView);
        });

        menuPrincipalView.getMenuItemEliminarUsuario().addActionListener(e ->
                menuPrincipalView.abrirVentanaInterna(eliminarUsuarioView)
        );

        // Menú Productos
        menuPrincipalView.getMenuItemCrearProducto().addActionListener(e ->
                menuPrincipalView.abrirVentanaInterna(productoAnadirView)
        );

        menuPrincipalView.getMenuItemListarProducto().addActionListener(e -> {
            productoListarView.cargarDatos(productoDAO.listarTodos());
            menuPrincipalView.abrirVentanaInterna(productoListarView);
        });

        menuPrincipalView.getMenuItemActualizarProducto().addActionListener(e ->
                menuPrincipalView.abrirVentanaInterna(productoActualizarView)
        );

        menuPrincipalView.getMenuItemEliminarProducto().addActionListener(e ->
                menuPrincipalView.abrirVentanaInterna(productoEliminarView)
        );

        menuPrincipalView.getMenuItemBuscarProducto().addActionListener(e -> {
            productoListarView.cargarDatos(productoDAO.listarTodos());
            menuPrincipalView.abrirVentanaInterna(productoListarView);
        });

        // Menú Carrito
        menuPrincipalView.getMenuItemCrearCarrito().addActionListener(e ->
                menuPrincipalView.abrirVentanaInterna(carritoCrearView)
        );

        menuPrincipalView.getMenuItemListarCarrito().addActionListener(e -> {
            carritoListarView.cargarDatos(carritoDAO.listarTodos());
            menuPrincipalView.abrirVentanaInterna(carritoListarView);
        });

        menuPrincipalView.getMenuItemActualizarCarrito().addActionListener(e ->
                menuPrincipalView.abrirVentanaInterna(carritoActualizarView)
        );

        menuPrincipalView.getMenuItemEliminarCarrito().addActionListener(e ->
                menuPrincipalView.abrirVentanaInterna(carritoEliminarView)
        );

        // Menú Cerrar sesión
        menuPrincipalView.getMenuItemCerrarSesion().addActionListener(e -> cerrarSesion());
    }

    private static void mostrarLogin() {
        loginView.limpiarCampos();
        loginView.setVisible(true);
        menuPrincipalView.setVisible(false);
    }

    public static void cerrarSesion() {
        usuarioLogueado = null;
        menuPrincipalView.limpiarAlCerrarSesion();
        menuPrincipalView.setVisible(false);

        loginView.limpiarCampos();
        loginView.setVisible(true);
    }
}
