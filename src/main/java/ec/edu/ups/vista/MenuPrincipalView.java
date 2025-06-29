package ec.edu.ups.vista;

import javax.swing.*;

public class MenuPrincipalView extends JFrame {
    private JMenuBar menuBar;

    private JMenu menuUsuarios;
    private JMenu menuCarrito;
    private JMenu menuProducto;

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
    private JMenuItem menuItemActualizarCarrito;

    private JMenuItem menuItemCerrarSesion;

    private JDesktopPane jDesktopPane;

    public MenuPrincipalView() {
        jDesktopPane = new JDesktopPane();
        menuBar = new JMenuBar();

        menuUsuarios = new JMenu("Usuarios");
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
        menuItemActualizarCarrito = new JMenuItem("Actualizar Carrito");

        menuItemCerrarSesion = new JMenuItem("Cerrar Sesión");

        menuUsuarios.add(menuItemCrearUsuario);
        menuUsuarios.add(menuItemListarUsuario);
        menuUsuarios.add(menuItemEliminarUsuario);
        menuUsuarios.addSeparator();
        menuUsuarios.add(menuItemCerrarSesion);

        menuProducto.add(menuItemCrearProducto);
        menuProducto.add(menuItemListarProducto);
        menuProducto.add(menuItemEliminarProducto);
        menuProducto.add(menuItemActualizarProducto);
        menuProducto.add(menuItemBuscarProducto);

        menuCarrito.add(menuItemCrearCarrito);
        menuCarrito.add(menuItemListarCarrito);
        menuCarrito.add(menuItemActualizarCarrito);
        menuCarrito.add(menuItemEliminarCarrito);

        menuBar.add(menuUsuarios);
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

    public void aplicarRestriccionesPorRol(boolean esAdministrador) {
        menuItemCrearUsuario.setEnabled(esAdministrador);
        menuItemListarUsuario.setEnabled(esAdministrador);
        menuItemEliminarUsuario.setEnabled(esAdministrador);

        menuItemCrearProducto.setEnabled(esAdministrador);
        menuItemListarProducto.setEnabled(esAdministrador);
        menuItemEliminarProducto.setEnabled(esAdministrador);
        menuItemActualizarProducto.setEnabled(esAdministrador);
        menuItemBuscarProducto.setEnabled(esAdministrador);

        menuItemCrearCarrito.setEnabled(esAdministrador);
        menuItemListarCarrito.setEnabled(esAdministrador);
        menuItemActualizarCarrito.setEnabled(esAdministrador);
        menuItemEliminarCarrito.setEnabled(esAdministrador);
    }

    public void limpiarAlCerrarSesion() {
        jDesktopPane.removeAll();
        jDesktopPane.repaint();
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

    public JMenuItem getMenuItemActualizarCarrito() {
        return menuItemActualizarCarrito;
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
}
