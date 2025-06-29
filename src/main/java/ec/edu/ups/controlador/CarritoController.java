package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.Carrito.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CarritoController {

    private final CarritoCrearView carritoCrearView;
    private final CarritoActualizarView carritoActualizarView;
    private final CarritoEliminarView carritoEliminarView;
    private final CarritoListarView carritoListarView;
    private final DetallesDeCarritoView detallesDeCarritoView;

    private final CarritoDAO carritoDAO;
    private final ProductoDAO productoDAO;

    private Carrito carritoEnCreacion;

    public CarritoController(CarritoDAO carritoDAO, ProductoDAO productoDAO,
                             CarritoCrearView carritoCrearView, CarritoListarView carritoListarView,
                             CarritoActualizarView carritoActualizarView, CarritoEliminarView carritoEliminarView,
                             DetallesDeCarritoView detallesDeCarritoView) {
        this.carritoDAO = carritoDAO;
        this.productoDAO = productoDAO;
        this.carritoCrearView = carritoCrearView;
        this.carritoActualizarView = carritoActualizarView;
        this.carritoEliminarView = carritoEliminarView;
        this.carritoListarView = carritoListarView;
        this.detallesDeCarritoView = detallesDeCarritoView;
        configurarEventos();
        carritoEnCreacion = null;
    }


    private void configurarEventos() {

        carritoCrearView.getAgregarButton().addActionListener(e -> agregarProductoACarritoCrear());
        carritoCrearView.getCrearButton().addActionListener(e -> finalizarCreacionCarrito());
        carritoCrearView.getSalirButton().addActionListener(e -> carritoCrearView.setVisible(false));


        carritoActualizarView.getActualizarButton().addActionListener(e -> agregarOActualizarProductoEnCarritoActualizar());
        carritoActualizarView.getSalirButton().addActionListener(e -> carritoActualizarView.setVisible(false));


        carritoEliminarView.getBuscarButton().addActionListener(e -> buscarCarritoEnEliminar());
        carritoEliminarView.getEliminarButton().addActionListener(e -> eliminarProductoEnCarritoEliminar());
        carritoEliminarView.getEliminarCarritoButton().addActionListener(e -> eliminarCarritoCompleto());
        carritoEliminarView.getSalirButton().addActionListener(e -> carritoEliminarView.setVisible(false));


        carritoListarView.getSalirButton().addActionListener(e -> carritoListarView.setVisible(false));
        carritoListarView.getDetallesButton().addActionListener(e -> abrirDetallesDeCarrito());


        detallesDeCarritoView.getEliminarButton().addActionListener(e -> eliminarProductoEnDetalles());
        detallesDeCarritoView.getSalirButton().addActionListener(e -> detallesDeCarritoView.setVisible(false));
    }



    private void agregarProductoACarritoCrear() {
        String nombreProducto = carritoCrearView.getTextField1().getText().trim();
        String cantidadTexto = carritoCrearView.getTextField2().getText().trim();

        if (nombreProducto.isEmpty() || cantidadTexto.isEmpty()) {
            carritoCrearView.mostrarMensaje("Ingrese nombre de producto y cantidad");
            return;
        }
        if (!esNumero(cantidadTexto)) {
            carritoCrearView.mostrarMensaje("Cantidad inválida");
            return;
        }
        int cantidad = Integer.parseInt(cantidadTexto);

        Producto producto = buscarProductoPorNombre(nombreProducto);
        if (producto == null) {
            carritoCrearView.mostrarMensaje("Producto no encontrado");
            return;
        }

        if (carritoEnCreacion == null) {
            carritoEnCreacion = new Carrito();
        }

        agregarOActualizarProducto(carritoEnCreacion, producto, cantidad);
        actualizarTablaCarrito(carritoCrearView.getModelo(), carritoEnCreacion);

        carritoCrearView.mostrarMensaje("Producto agregado/actualizado");
        limpiarCamposCarritoCrear();
    }

    private void finalizarCreacionCarrito() {
        if (carritoEnCreacion == null || carritoEnCreacion.estaVacio()) {
            carritoCrearView.mostrarMensaje("Carrito vacío, agregue productos antes de crear");
            return;
        }
        carritoDAO.crear(carritoEnCreacion);

        double subtotal = carritoEnCreacion.calcularSubtotal();
        double total = carritoEnCreacion.calcularTotal();

        carritoCrearView.mostrarMensaje("Carrito creado exitosamente\nSubtotal: $" + subtotal + "\nTotal con IVA: $" + total);

        carritoEnCreacion = null;
        limpiarTabla(carritoCrearView.getModelo());
        limpiarCamposCarritoCrear();
    }

    private void limpiarCamposCarritoCrear() {
        carritoCrearView.getTextField1().setText("");
        carritoCrearView.getTextField2().setText("");
    }



    private void agregarOActualizarProductoEnCarritoActualizar() {
        String nombreProducto = carritoActualizarView.getTextField1().getText().trim();
        String cantidadTexto = carritoActualizarView.getTextField2().getText().trim();

        if (nombreProducto.isEmpty() || cantidadTexto.isEmpty()) {
            carritoActualizarView.mostrarMensaje("Ingrese nombre de producto y cantidad");
            return;
        }
        if (!esNumero(cantidadTexto)) {
            carritoActualizarView.mostrarMensaje("Cantidad inválida");
            return;
        }
        int cantidad = Integer.parseInt(cantidadTexto);

        Producto producto = buscarProductoPorNombre(nombreProducto);
        if (producto == null) {
            carritoActualizarView.mostrarMensaje("Producto no encontrado");
            return;
        }

        if (carritoEnCreacion == null) {
            carritoActualizarView.mostrarMensaje("No hay carrito seleccionado para actualizar");
            return;
        }

        agregarOActualizarProducto(carritoEnCreacion, producto, cantidad);
        actualizarTablaCarrito(carritoActualizarView.getModelo(), carritoEnCreacion);

        carritoActualizarView.mostrarMensaje("Producto agregado o actualizado");
        limpiarCamposCarritoActualizar();
    }

    private void limpiarCamposCarritoActualizar() {
        carritoActualizarView.getTextField1().setText("");
        carritoActualizarView.getTextField2().setText("");
    }



    private void buscarCarritoEnEliminar() {
        String codigoTexto = carritoEliminarView.getTextField1().getText().trim();

        if (!esNumero(codigoTexto)) {
            carritoEliminarView.mostrarMensaje("Ingrese código válido");
            return;
        }

        int codigo = Integer.parseInt(codigoTexto);
        Carrito carrito = carritoDAO.buscarPorCodigo(codigo);

        if (carrito == null) {
            carritoEliminarView.mostrarMensaje("Carrito no encontrado");
            limpiarTabla(carritoEliminarView.getModelo());
            return;
        }

        carritoEnCreacion = carrito;
        actualizarTablaCarrito(carritoEliminarView.getModelo(), carritoEnCreacion);
    }

    private void eliminarProductoEnCarritoEliminar() {
        int filaSeleccionada = carritoEliminarView.getTable1().getSelectedRow();
        if (filaSeleccionada < 0) {
            carritoEliminarView.mostrarMensaje("Seleccione un producto para eliminar");
            return;
        }
        int codigoProducto = (int) carritoEliminarView.getModelo().getValueAt(filaSeleccionada, 0);

        carritoEnCreacion.eliminarProducto(codigoProducto);
        carritoDAO.actualizar(carritoEnCreacion);
        actualizarTablaCarrito(carritoEliminarView.getModelo(), carritoEnCreacion);
        carritoEliminarView.mostrarMensaje("Producto eliminado");

        if (carritoEnCreacion.estaVacio()) {
            carritoEliminarView.mostrarMensaje("Carrito vacío");
        }
    }

    private void eliminarCarritoCompleto() {
        if (carritoEnCreacion == null) {
            carritoEliminarView.mostrarMensaje("No hay carrito seleccionado");
            return;
        }
        int resp = JOptionPane.showConfirmDialog(carritoEliminarView,
                "¿Está seguro que desea eliminar todo el carrito?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            carritoDAO.eliminar(carritoEnCreacion.getCodigo());
            carritoEnCreacion = null;
            limpiarTabla(carritoEliminarView.getModelo());
            carritoEliminarView.mostrarMensaje("Carrito eliminado");
        }
    }



    public void listarCarritos() {
        List<Carrito> carritos = carritoDAO.listarTodos();
        DefaultTableModel modelo = carritoListarView.getModelo();
        limpiarTabla(modelo);
        for (Carrito c : carritos) {
            modelo.addRow(new Object[]{
                    c.getCodigo(),
                    "Usuario: " + (c.getUsuario() != null ? c.getUsuario().getUsername() : "N/A"),
                    c.calcularTotal()
            });
        }
    }

    private void abrirDetallesDeCarrito() {
        int fila = carritoListarView.getTable1().getSelectedRow();
        if (fila < 0) {
            carritoListarView.mostrarMensaje("Seleccione un carrito");
            return;
        }
        int codigoCarrito = (int) carritoListarView.getModelo().getValueAt(fila, 0);
        Carrito carrito = carritoDAO.buscarPorCodigo(codigoCarrito);
        if (carrito == null) {
            carritoListarView.mostrarMensaje("Carrito no encontrado");
            return;
        }

        actualizarTablaCarrito(detallesDeCarritoView.getModelo(), carrito);
        detallesDeCarritoView.setVisible(true);
    }



    private void eliminarProductoEnDetalles() {
        int filaSeleccionada = detallesDeCarritoView.getTable1().getSelectedRow();
        if (filaSeleccionada < 0) {
            detallesDeCarritoView.mostrarMensaje("Seleccione un producto para eliminar");
            return;
        }
        int codigoProducto = (int) detallesDeCarritoView.getModelo().getValueAt(filaSeleccionada, 0);
        int resp = JOptionPane.showConfirmDialog(detallesDeCarritoView,
                "¿Está seguro que desea eliminar este producto?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            // Buscamos el carrito actual en detalles por código en la tabla padre
            int filaCarrito = carritoListarView.getTable1().getSelectedRow();
            if (filaCarrito < 0) {
                detallesDeCarritoView.mostrarMensaje("Error: no se encontró carrito seleccionado");
                return;
            }
            int codigoCarrito = (int) carritoListarView.getModelo().getValueAt(filaCarrito, 0);
            Carrito carrito = carritoDAO.buscarPorCodigo(codigoCarrito);
            if (carrito == null) {
                detallesDeCarritoView.mostrarMensaje("Carrito no encontrado");
                return;
            }

            carrito.eliminarProducto(codigoProducto);
            carritoDAO.actualizar(carrito);
            actualizarTablaCarrito(detallesDeCarritoView.getModelo(), carrito);
            detallesDeCarritoView.mostrarMensaje("Producto eliminado");
        }
    }



    private void limpiarTabla(DefaultTableModel modelo) {
        modelo.setRowCount(0);
    }

    private boolean esNumero(String texto) {
        if (texto == null || texto.isEmpty()) return false;
        for (char c : texto.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private Producto buscarProductoPorNombre(String nombre) {
        List<Producto> encontrados = productoDAO.buscarPorNombre(nombre);
        for (Producto p : encontrados) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    private void agregarOActualizarProducto(Carrito carrito, Producto producto, int cantidadNueva) {
        List<ItemCarrito> items = carrito.obtenerItems();
        boolean encontrado = false;
        for (ItemCarrito item : items) {
            if (item.getProducto().getCodigo() == producto.getCodigo()) {
                item.setCantidad(cantidadNueva);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            carrito.agregarProducto(producto, cantidadNueva);
        }
    }

    private void actualizarTablaCarrito(DefaultTableModel modelo, Carrito carrito) {
        limpiarTabla(modelo);
        for (ItemCarrito item : carrito.obtenerItems()) {
            modelo.addRow(new Object[]{
                    item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    item.getProducto().getPrecio(),
                    item.getCantidad()
            });
        }
    }
}
