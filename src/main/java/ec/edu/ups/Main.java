package ec.edu.ups;

import ec.edu.ups.controlador.*;
import ec.edu.ups.dao.*;
import ec.edu.ups.dao.impl.*;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.*;
import ec.edu.ups.vista.Carrito.CarritoAnadirView;
import ec.edu.ups.vista.Producto.*;
import ec.edu.ups.vista.Usuario.*;

import javax.swing.*;

public class Main {
    private static UsuarioDao usuarioDAO = new UsuarioDAOMemoria();
    private static CarritoDAO carritoDAO = new CarritoDAOMemoria();
    private static ProductoDAO productoDAO = new ProductoDAOMemoria();

    private static LoginView loginView = new LoginView();
    private static AdminUsuariosView adminUsuariosView = new AdminUsuariosView();
    private static AdminCarritosView adminCarritosView = new AdminCarritosView();

    private static MenuPrincipalView principalView = new MenuPrincipalView();

    // Vistas Producto
    private static ProductoAnadirView productoAnadirView = new ProductoAnadirView();
    private static ProductoListaView productoListaView = new ProductoListaView();
    private static EliminarProductoView eliminarProductoView = new EliminarProductoView();
    private static ActualizarProductoView actualizarProductoView = new ActualizarProductoView();

    // Vista Carrito
    private static CarritoAnadirView carritoAnadirView = new CarritoAnadirView();

    // Controladores
    private static UsuarioController usuarioController;
    private static ProductoController productoController;
    private static CarritoController carritoController;

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            configurarLogin();
            mostrarLogin();
        });
    }

    private static void configurarLogin() {
        usuarioController = new UsuarioController(usuarioDAO, carritoDAO,
                loginView, adminUsuariosView, adminCarritosView, principalView);

        // Registro usa la misma ventana RegistroDeUsuariosView para crear usuario
        loginView.getBtnRegistrarse().addActionListener(e -> {
            RegistroDeUsuariosView registro = new RegistroDeUsuariosView();
            registro.setVisible(true);

            registro.getBtnRegistrar().addActionListener(ev -> {
                String username = registro.getTxtUsername().getText().trim();
                String pass = new String(registro.getTxtContrasenia().getPassword());
                String confirm = new String(registro.getTxtConfirmar().getPassword());

                String resultado = usuarioController.registrarUsuario(username, pass, confirm);
                registro.mostrarMensaje(resultado);
                if (resultado.equals("Usuario registrado correctamente.")) {
                    registro.dispose();
                }
            });

            registro.getBtnCancelar().addActionListener(ev2 -> registro.dispose());
        });

        // Inicializamos controladores de producto y carrito pero solo cuando el usuario inicia sesión
    }

    private static void mostrarLogin() {
        loginView.limpiarCampos();
        loginView.setVisible(true);
        principalView.setVisible(false);
    }

    public static void lanzarMenuPrincipal(Usuario usuario) {
        // Instanciamos controladores con las vistas ya creadas (se mantienen para todo el ciclo)
        productoController = new ProductoController(productoDAO, productoAnadirView,
                productoListaView, carritoAnadirView, eliminarProductoView, actualizarProductoView);

        carritoController = new CarritoController(carritoDAO, productoDAO, carritoAnadirView);

        // Agregar ventanas internas al escritorio principal (JDesktopPane)
        JDesktopPane desktop = principalView.getjDesktopPane();
        desktop.removeAll();
        desktop.add(productoAnadirView);
        desktop.add(productoListaView);
        desktop.add(carritoAnadirView);
        desktop.add(eliminarProductoView);
        desktop.add(actualizarProductoView);
        desktop.add(adminUsuariosView);
        desktop.add(adminCarritosView);

        // Ocultamos todas al inicio
        productoAnadirView.setVisible(false);
        productoListaView.setVisible(false);
        carritoAnadirView.setVisible(false);
        eliminarProductoView.setVisible(false);
        actualizarProductoView.setVisible(false);
        adminUsuariosView.setVisible(false);
        adminCarritosView.setVisible(false);

        // Aplicar restricciones por rol
        boolean esAdmin = usuario.getRol() == Rol.ADMINISTRADOR;
        principalView.aplicarRestriccionesPorRol(esAdmin);

        // Configurar listeners del menú principal para abrir ventanas internas
        principalView.getMenuItemCrearProducto().addActionListener(e -> principalView.abrirVentanaInterna(productoAnadirView));
        principalView.getMenuItemListarProducto().addActionListener(e -> {
            productoListaView.cargarDatos(productoDAO.listarTodos());
            principalView.abrirVentanaInterna(productoListaView);
        });
        principalView.getMenuItemBuscarProducto().addActionListener(e -> {
            productoListaView.cargarDatos(productoDAO.listarTodos());
            principalView.abrirVentanaInterna(productoListaView);
        });
        principalView.getMenuItemActualizarProducto().addActionListener(e -> principalView.abrirVentanaInterna(actualizarProductoView));
        principalView.getMenuItemEliminarProducto().addActionListener(e -> {
            eliminarProductoView.mostrarProductos(productoDAO.listarTodos());
            principalView.abrirVentanaInterna(eliminarProductoView);
        });

        principalView.getMenuItemCrearCarrito().addActionListener(e -> {
            carritoAnadirView.limpiarCampos();
            principalView.abrirVentanaInterna(carritoAnadirView);
        });
        principalView.getMenuItemListarCarrito().addActionListener(e -> {
            adminCarritosView.getBtnActualizar().doClick(); // para cargar datos
            principalView.abrirVentanaInterna(adminCarritosView);
        });
        principalView.getMenuItemEliminarCarrito().addActionListener(e -> {
            adminCarritosView.getBtnActualizar().doClick();
            principalView.abrirVentanaInterna(adminCarritosView);
        });

        principalView.getMenuItemCrearUsuario().addActionListener(e -> {
            RegistroDeUsuariosView registro = new RegistroDeUsuariosView();
            registro.setVisible(true);
            registro.getBtnRegistrar().addActionListener(ev -> {
                String username = registro.getTxtUsername().getText().trim();
                String pass = new String(registro.getTxtContrasenia().getPassword());
                String confirm = new String(registro.getTxtConfirmar().getPassword());

                String resultado = usuarioController.registrarUsuario(username, pass, confirm);
                registro.mostrarMensaje(resultado);
                if (resultado.equals("Usuario registrado correctamente.")) {
                    registro.dispose();
                }
            });
            registro.getBtnCancelar().addActionListener(ev2 -> registro.dispose());
        });
        principalView.getMenuItemListarUsuario().addActionListener(e -> {
            usuarioController.cargarUsuariosAdmin();
            principalView.abrirVentanaInterna(adminUsuariosView);
        });
        principalView.getMenuItemEliminarUsuario().addActionListener(e -> {
            usuarioController.cargarUsuariosAdmin();
            principalView.abrirVentanaInterna(adminUsuariosView);
        });

        principalView.getMenuItemCerrarSesion().addActionListener(e -> {
            principalView.dispose();
            usuarioController.cerrarSesion();
        });

        principalView.mostrarMensaje("Bienvenido: " + usuario.getUsername());
        principalView.setVisible(true);
    }
}