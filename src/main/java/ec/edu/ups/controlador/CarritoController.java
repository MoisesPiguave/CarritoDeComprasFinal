package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.Carrito.CarritoAnadirView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CarritoController {

    private final CarritoDAO carritoDAO;
    private final ProductoDAO productoDAO;
    private final CarritoAnadirView carritoAnadirView;
    private Carrito carrito;

    public CarritoController(CarritoDAO carritoDAO,
                             ProductoDAO productoDAO,
                             CarritoAnadirView carritoAnadirView) {
        this.carritoDAO = carritoDAO;
        this.productoDAO = productoDAO;
        this.carritoAnadirView = carritoAnadirView;
        this.carrito = new Carrito();
        configurarEventos();
        cargarProductos();
        mostrarTotales();
    }

    private void configurarEventos() {
        carritoAnadirView.getBtnBuscar().addActionListener(e -> buscarProductoPorNombre());
        carritoAnadirView.getBtnAnadir().addActionListener(e -> agregarProductoAlCarrito());
        carritoAnadirView.getBtnGuardar().addActionListener(e -> guardarCarrito());
        // Si tienes botones eliminar o actualizar, configura aquí sus eventos también
    }

    private void buscarProductoPorNombre() {
        // Cambiado para usar el txtNombre en lugar de txtBuscarNombre
        String nombre = carritoAnadirView.getTxtNombre().getText().trim();
        List<Producto> productos = productoDAO.buscarPorNombre(nombre);
        carritoAnadirView.mostrarProductosBuscados(productos);
    }

    private void agregarProductoAlCarrito() {
        String codigoTexto = carritoAnadirView.getTxtCodigo().getText();
        String cantidadTexto = carritoAnadirView.getTxtCantidad();

        if (codigoTexto.isEmpty() || cantidadTexto.isEmpty()) {
            carritoAnadirView.mostrarMensaje("Ingrese código y cantidad");
            return;
        }

        if (!esNumeroEntero(codigoTexto) || !esNumeroEntero(cantidadTexto)) {
            carritoAnadirView.mostrarMensaje("Código y cantidad deben ser números enteros");
            return;
        }

        int codigo = Integer.parseInt(codigoTexto);
        int cantidad = Integer.parseInt(cantidadTexto);

        if (cantidad <= 0) {
            carritoAnadirView.mostrarMensaje("Cantidad debe ser mayor a cero");
            return;
        }

        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto == null) {
            carritoAnadirView.mostrarMensaje("Producto no encontrado");
            return;
        }

        carrito.agregarProducto(producto, cantidad);
        cargarProductos();
        mostrarTotales();
    }

    private void guardarCarrito() {
        if (carrito.obtenerItems().isEmpty()) {
            carritoAnadirView.mostrarMensaje("El carrito está vacío");
            return;
        }
        carritoDAO.crear(carrito);
        carritoAnadirView.mostrarMensaje("Carrito guardado correctamente");
        carrito = new Carrito();
        cargarProductos();
        mostrarTotales();
    }

    private void cargarProductos() {
        DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        modelo.setRowCount(0);
        for (ItemCarrito item : carrito.obtenerItems()) {
            modelo.addRow(new Object[]{
                    item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    item.getProducto().getPrecio(),
                    item.getCantidad(),
                    item.getProducto().getPrecio() * item.getCantidad()
            });
        }
    }

    private void mostrarTotales() {
        double subtotal = carrito.calcularSubtotal();
        double iva = subtotal * 0.12;
        double total = subtotal + iva;

        carritoAnadirView.getTxtSubtotal().setText(String.format("%.2f", subtotal));
        carritoAnadirView.getTxtIva().setText(String.format("%.2f", iva));
        carritoAnadirView.getTxtTotal().setText(String.format("%.2f", total));
    }

    private boolean esNumeroEntero(String texto) {
        if (texto.isEmpty()) return false;
        for (int i = 0; i < texto.length(); i++) {
            if (!Character.isDigit(texto.charAt(i))) return false;
        }
        return true;
    }
}