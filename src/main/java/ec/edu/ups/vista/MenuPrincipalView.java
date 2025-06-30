package ec.edu.ups.vista;

import ec.edu.ups.Util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class MenuPrincipalView extends JFrame {
    private MiJDesktopPane desktopPane;
    private MensajeInternacionalizacionHandler mensajeHandler;
    private JMenuBar menuBar;

    private JMenu menuUsuarios;
    private JMenu menuCarrito;
    private JMenu menuProducto;
    private JMenu menuIdioma;

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

    private JMenuItem menuItemEspanol;
    private JMenuItem menuItemIngles;
    private JMenuItem menuItemFrances;

    public MenuPrincipalView() {
        desktopPane = new MiJDesktopPane();

        mensajeHandler = new MensajeInternacionalizacionHandler();
        mensajeHandler.setLenguaje("es", "EC");

        menuBar = new JMenuBar();

        menuUsuarios = new JMenu();
        menuProducto = new JMenu();
        menuCarrito = new JMenu();
        menuIdioma = new JMenu();

        menuItemCrearUsuario = new JMenuItem();
        menuItemListarUsuario = new JMenuItem();
        menuItemEliminarUsuario = new JMenuItem();

        menuItemCrearProducto = new JMenuItem();
        menuItemListarProducto = new JMenuItem();
        menuItemEliminarProducto = new JMenuItem();
        menuItemActualizarProducto = new JMenuItem();
        menuItemBuscarProducto = new JMenuItem();

        menuItemCrearCarrito = new JMenuItem();
        menuItemListarCarrito = new JMenuItem();
        menuItemEliminarCarrito = new JMenuItem();
        menuItemActualizarCarrito = new JMenuItem();

        menuItemCerrarSesion = new JMenuItem();

        menuItemEspanol = new JMenuItem();
        menuItemIngles = new JMenuItem();
        menuItemFrances = new JMenuItem();

        menuIdioma.add(menuItemEspanol);
        menuIdioma.add(menuItemIngles);
        menuIdioma.add(menuItemFrances);

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
        menuBar.add(menuIdioma);

        setJMenuBar(menuBar);
        setContentPane(desktopPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        menuItemEspanol.addActionListener(e -> {
            mensajeHandler.cambiarIdioma("es", "EC");
            actualizarTextos();
        });

        menuItemIngles.addActionListener(e -> {
            mensajeHandler.cambiarIdioma("en", "US");
            actualizarTextos();
        });

        menuItemFrances.addActionListener(e -> {
            mensajeHandler.cambiarIdioma("fr", "FR");
            actualizarTextos();
        });

        actualizarTextos();

        // NO hacemos setVisible(true) aquí
    }

    private void actualizarTextos() {
        menuUsuarios.setText(mensajeHandler.getMensaje("menu.usuarios"));
        menuProducto.setText(mensajeHandler.getMensaje("menu.producto"));
        menuCarrito.setText(mensajeHandler.getMensaje("menu.carrito"));
        menuItemCerrarSesion.setText(mensajeHandler.getMensaje("menu.cerrar_sesion"));

        menuItemCrearUsuario.setText(mensajeHandler.getMensaje("menu.usuario.crear"));
        menuItemListarUsuario.setText(mensajeHandler.getMensaje("menu.usuario.listar"));
        menuItemEliminarUsuario.setText(mensajeHandler.getMensaje("menu.usuario.eliminar"));

        menuItemCrearProducto.setText(mensajeHandler.getMensaje("menu.producto.crear"));
        menuItemListarProducto.setText(mensajeHandler.getMensaje("menu.producto.listar"));
        menuItemEliminarProducto.setText(mensajeHandler.getMensaje("menu.producto.eliminar"));
        menuItemActualizarProducto.setText(mensajeHandler.getMensaje("menu.producto.actualizar"));
        menuItemBuscarProducto.setText(mensajeHandler.getMensaje("menu.producto.buscar"));

        menuItemCrearCarrito.setText(mensajeHandler.getMensaje("menu.carrito.crear"));
        menuItemListarCarrito.setText(mensajeHandler.getMensaje("menu.carrito.listar"));
        menuItemEliminarCarrito.setText(mensajeHandler.getMensaje("menu.carrito.eliminar"));
        menuItemActualizarCarrito.setText(mensajeHandler.getMensaje("menu.carrito.actualizar"));

        menuIdioma.setText(mensajeHandler.getMensaje("menu.idioma"));
        menuItemEspanol.setText(mensajeHandler.getMensaje("menu.idioma.es"));
        menuItemIngles.setText(mensajeHandler.getMensaje("menu.idioma.en"));
        menuItemFrances.setText(mensajeHandler.getMensaje("menu.idioma.fr"));

        setTitle(mensajeHandler.getMensaje("menu.titulo"));
    }

    public void aplicarRestriccionesPorRol(boolean esAdministrador, boolean esUsuario) {
        menuItemCrearUsuario.setEnabled(esAdministrador);
        menuItemListarUsuario.setEnabled(esAdministrador);
        menuItemEliminarUsuario.setEnabled(esAdministrador);

        menuItemCrearProducto.setEnabled(esAdministrador);
        menuItemListarProducto.setEnabled(esAdministrador);
        menuItemEliminarProducto.setEnabled(esAdministrador);
        menuItemActualizarProducto.setEnabled(esAdministrador);
        menuItemBuscarProducto.setEnabled(esAdministrador);

        boolean puedeManejarCarrito = esAdministrador || esUsuario;
        menuItemCrearCarrito.setEnabled(puedeManejarCarrito);
        menuItemListarCarrito.setEnabled(puedeManejarCarrito);
        menuItemActualizarCarrito.setEnabled(puedeManejarCarrito);
        menuItemEliminarCarrito.setEnabled(puedeManejarCarrito);
    }

    public void limpiarAlCerrarSesion() {
        desktopPane.removeAll();
        desktopPane.repaint();
    }

    public void abrirVentanaInterna(JInternalFrame ventana) {
        if (ventana.getParent() == null) {
            desktopPane.add(ventana);
        }
        ventana.setVisible(true);
        ventana.toFront();
        ventana.requestFocus();
    }

    // Getters para JMenuItems (si los necesitas)...

    public JMenuItem getMenuItemCrearUsuario() { return menuItemCrearUsuario; }
    public JMenuItem getMenuItemListarUsuario() { return menuItemListarUsuario; }
    public JMenuItem getMenuItemEliminarUsuario() { return menuItemEliminarUsuario; }
    public JMenuItem getMenuItemCrearProducto() { return menuItemCrearProducto; }
    public JMenuItem getMenuItemListarProducto() { return menuItemListarProducto; }
    public JMenuItem getMenuItemEliminarProducto() { return menuItemEliminarProducto; }
    public JMenuItem getMenuItemActualizarProducto() { return menuItemActualizarProducto; }
    public JMenuItem getMenuItemBuscarProducto() { return menuItemBuscarProducto; }
    public JMenuItem getMenuItemCrearCarrito() { return menuItemCrearCarrito; }
    public JMenuItem getMenuItemListarCarrito() { return menuItemListarCarrito; }
    public JMenuItem getMenuItemEliminarCarrito() { return menuItemEliminarCarrito; }
    public JMenuItem getMenuItemActualizarCarrito() { return menuItemActualizarCarrito; }
    public JMenuItem getMenuItemCerrarSesion() { return menuItemCerrarSesion; }
    public MiJDesktopPane getDesktopPane() { return desktopPane; }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
