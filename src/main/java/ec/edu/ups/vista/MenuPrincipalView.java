package ec.edu.ups.vista;

import javax.swing.*;

public class MenuPrincipalView extends JFrame {
    private JMenuBar menuBar;

    private JMenu menuProducto;
    private JMenu menuCarrito;
    private JMenu menuUsuario;

    private JMenuItem menuItemCrearUsuario;
    private JMenuItem menuItemListarUsuario;
    private JMenuItem menuItemEliminarUsuario;

    private JMenuItem menuItemCrearProducto;
    private JMenuItem menuItemListarProducto;
    private JMenuItem menuItemEliminarProducto;
    private JMenuItem menuItemActualizarProducto;
    private JMenuItem menuItemBuscarProducto;

    private JMenuItem menuItemCrearCarrito;
    private JMenuItem menuItemListarCarrito;
    private JMenuItem menuItemEliminarCarrito;

    private JMenuItem menuItemCerrarSesion;

    private JDesktopPane jDesktopPane;

    public MenuPrincipalView() {
        jDesktopPane = new JDesktopPane();
        menuBar = new JMenuBar();


        menuUsuario = new JMenu("Usuarios");
        menuProducto = new JMenu("Producto");
        menuCarrito = new JMenu("Carrito");


        menuItemCrearUsuario = new JMenuItem("Crear Usuario");
        menuItemListarUsuario = new JMenuItem("Listar Usuarios");
        menuItemEliminarUsuario = new JMenuItem("Eliminar Usuario");


        menuItemCrearProducto = new JMenuItem("Crear Producto");
        menuItemListarProducto = new JMenuItem("Listar Productos");
        menuItemEliminarProducto = new JMenuItem("Eliminar Producto");
        menuItemActualizarProducto = new JMenuItem("Actualizar Producto");
        menuItemBuscarProducto = new JMenuItem("Buscar Producto");


        menuItemCrearCarrito = new JMenuItem("Crear Carrito");
        menuItemListarCarrito = new JMenuItem("Listar Carritos");
        menuItemEliminarCarrito = new JMenuItem("Eliminar Carrito");


        menuItemCerrarSesion = new JMenuItem("Cerrar Sesión");


        menuUsuario.add(menuItemCrearUsuario);
        menuUsuario.add(menuItemListarUsuario);
        menuUsuario.add(menuItemEliminarUsuario);
        menuUsuario.addSeparator();
        menuUsuario.add(menuItemCerrarSesion);

        menuProducto.add(menuItemCrearProducto);
        menuProducto.add(menuItemListarProducto);
        menuProducto.add(menuItemEliminarProducto);
        menuProducto.add(menuItemActualizarProducto);
        menuProducto.add(menuItemBuscarProducto);

        menuCarrito.add(menuItemCrearCarrito);
        menuCarrito.add(menuItemListarCarrito);
        menuCarrito.add(menuItemEliminarCarrito);


        menuBar.add(menuUsuario);
        menuBar.add(menuProducto);
        menuBar.add(menuCarrito);

        setJMenuBar(menuBar);
        setContentPane(jDesktopPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sistema de Carrito de Compras En Línea");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void abrirVentanaInterna(JInternalFrame ventana) {
        if (ventana.getParent() == null) {
            jDesktopPane.add(ventana);
        }
        ventana.setVisible(true);
        ventana.toFront();
        ventana.requestFocus();
    }


    public JMenuItem getMenuItemCrearUsuario() {
        return menuItemCrearUsuario;
    }

    public JMenuItem getMenuItemListarUsuario() {
        return menuItemListarUsuario;
    }

    public JMenuItem getMenuItemEliminarUsuario() {
        return menuItemEliminarUsuario;
    }

    public JMenuItem getMenuItemCrearProducto() {
        return menuItemCrearProducto;
    }

    public JMenuItem getMenuItemListarProducto() {
        return menuItemListarProducto;
    }

    public JMenuItem getMenuItemEliminarProducto() {
        return menuItemEliminarProducto;
    }

    public JMenuItem getMenuItemActualizarProducto() {
        return menuItemActualizarProducto;
    }

    public JMenuItem getMenuItemBuscarProducto() {
        return menuItemBuscarProducto;
    }

    public JMenuItem getMenuItemCrearCarrito() {
        return menuItemCrearCarrito;
    }

    public JMenuItem getMenuItemListarCarrito() {
        return menuItemListarCarrito;
    }

    public JMenuItem getMenuItemEliminarCarrito() {
        return menuItemEliminarCarrito;
    }

    public JMenuItem getMenuItemCerrarSesion() {
        return menuItemCerrarSesion;
    }

    public JDesktopPane getjDesktopPane() {
        return jDesktopPane;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void aplicarRestriccionesPorRol(boolean esAdministrador) {
        if (esAdministrador) {

            menuItemCrearUsuario.setEnabled(true);
            menuItemListarUsuario.setEnabled(true);
            menuItemEliminarUsuario.setEnabled(true);

            menuItemCrearProducto.setEnabled(true);
            menuItemListarProducto.setEnabled(true);
            menuItemEliminarProducto.setEnabled(true);
            menuItemActualizarProducto.setEnabled(true);
            menuItemBuscarProducto.setEnabled(true);

            menuItemCrearCarrito.setEnabled(true);
            menuItemListarCarrito.setEnabled(true);
            menuItemEliminarCarrito.setEnabled(true);

        } else {
            menuItemCrearUsuario.setEnabled(false);
            menuItemListarUsuario.setEnabled(false);
            menuItemEliminarUsuario.setEnabled(false);

            menuItemCrearProducto.setEnabled(false);
            menuItemListarProducto.setEnabled(true);
            menuItemEliminarProducto.setEnabled(false);
            menuItemActualizarProducto.setEnabled(false);
            menuItemBuscarProducto.setEnabled(true);

            menuItemCrearCarrito.setEnabled(true);
            menuItemListarCarrito.setEnabled(true);
            menuItemEliminarCarrito.setEnabled(false);
        }
    }
}