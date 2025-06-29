package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.Producto.ProductoActualizarView;
import ec.edu.ups.vista.Producto.ProductoAnadirView;
import ec.edu.ups.vista.Producto.ProductoEliminarView;
import ec.edu.ups.vista.Producto.ProductoListarView;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductoController {

    private ProductoDAO productoDAO;
    private ProductoListarView productoListarView;

    public ProductoController(ProductoDAO productoDAO, ProductoAnadirView productoAnadirView, ProductoListarView productoListarView, ProductoEliminarView productoEliminarView, ProductoActualizarView productoActualizarView) {
        this.productoDAO = productoDAO;
        this.productoListarView = productoListarView;

        productoListarView.inicializarTabla();

        configurarEventos();
    }

    private void configurarEventos() {
        productoListarView.getListarButton().addActionListener(e -> listarProductos());

        productoListarView.getActualizarButton().addActionListener(e -> listarProductos());

        productoListarView.getSalirButton().addActionListener(e -> productoListarView.dispose());
    }

    private void listarProductos() {
        List<Producto> lista = productoDAO.listarTodos();
        DefaultTableModel modelo = productoListarView.getModelo();

        modelo.setRowCount(0); // Limpiar tabla antes de volver a llenar

        for (Producto p : lista) {
            modelo.addRow(new Object[]{
                    p.getCodigo(),
                    p.getNombre(),
                    p.getPrecio()
            });
        }

        productoListarView.mostrarMensaje("Productos cargados correctamente.");
    }
}
