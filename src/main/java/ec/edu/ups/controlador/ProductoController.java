package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.Carrito.CarritoAnadirView;
import ec.edu.ups.vista.Producto.ActualizarProductoView;
import ec.edu.ups.vista.Producto.EliminarProductoView;
import ec.edu.ups.vista.Producto.ProductoAnadirView;
import ec.edu.ups.vista.Producto.ProductoListaView;

import javax.swing.*;
import java.util.List;

public class ProductoController {

    private final ProductoAnadirView productoAnadirView;
    private final ProductoListaView productoListaView;
    private final CarritoAnadirView carritoAnadirView;
    private final EliminarProductoView eliminarProductoView;
    private final ActualizarProductoView actualizarProductoView;
    private final ProductoDAO productoDAO;

    public ProductoController(ProductoDAO productoDAO,
                              ProductoAnadirView productoAnadirView,
                              ProductoListaView productoListaView,
                              CarritoAnadirView carritoAnadirView,
                              EliminarProductoView eliminarProductoView,
                              ActualizarProductoView actualizarProductoView) {

        this.productoDAO = productoDAO;
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.carritoAnadirView = carritoAnadirView;
        this.eliminarProductoView = eliminarProductoView;
        this.actualizarProductoView = actualizarProductoView;

        configurarEventosEnVistas();
    }

    private void configurarEventosEnVistas() {
        productoAnadirView.getBtnAceptar().addActionListener(e -> guardarProducto());

        JButton btnActualizar = productoAnadirView.getBtnActualizar();
        if (btnActualizar != null) {
            btnActualizar.addActionListener(e -> {
                String codigoTexto = productoAnadirView.getTxtCodigo().getText().trim();
                String nombre = productoAnadirView.getTxtNombre().getText().trim();
                String precioTexto = productoAnadirView.getTxtPrecio().getText().trim();

                if (!esNumero(codigoTexto)) {
                    productoAnadirView.mostrarMensaje("Código inválido");
                    return;
                }
                if (nombre.isEmpty() || precioTexto.isEmpty()) {
                    productoAnadirView.mostrarMensaje("Complete todos los campos");
                    return;
                }
                if (!esNumeroDecimal(precioTexto)) {
                    productoAnadirView.mostrarMensaje("Precio inválido");
                    return;
                }
                int codigo = Integer.parseInt(codigoTexto);
                double precio = Double.parseDouble(precioTexto);

                actualizarProducto(codigo, nombre, precio);
            });
        }

        eliminarProductoView.getBtnEliminar().addActionListener(e -> {
            int fila = eliminarProductoView.getTblProductos().getSelectedRow();
            if (fila < 0) {
                eliminarProductoView.mostrarMensaje("Seleccione un producto para eliminar");
                return;
            }
            int codigo = (int) eliminarProductoView.getModelo().getValueAt(fila, 0);

            int opcion = eliminarProductoView.confirmarEliminacion();
            if (opcion == JOptionPane.YES_OPTION) {
                eliminarProducto(codigo);
                listarProductosEnEliminarView();
            }
        });

        eliminarProductoView.getBtnBuscar().addActionListener(e -> {
            String texto = eliminarProductoView.getTxtBuscar().getText().trim();
            if (texto.isEmpty()) {
                eliminarProductoView.mostrarMensaje("Ingrese código o nombre para buscar");
                return;
            }

            if (esNumero(texto)) {
                int codigo = Integer.parseInt(texto);
                Producto producto = productoDAO.buscarPorCodigo(codigo);
                eliminarProductoView.mostrarProducto(producto);
            } else {
                List<Producto> productos = productoDAO.buscarPorNombre(texto);
                eliminarProductoView.mostrarProductos(productos);
            }
        });

        productoListaView.getBtnBuscar().addActionListener(e -> buscarProducto());

        productoListaView.getBtnListar().addActionListener(e -> listarProductos());

        carritoAnadirView.getBtnBuscar().addActionListener(e -> buscarProductoPorCodigo());

        // Eventos para actualizarProductoView (sin tabla)
        actualizarProductoView.getBtnBuscar().addActionListener(e -> {
            String codigoTexto = actualizarProductoView.getTxtBuscar().getText().trim();
            if (!esNumero(codigoTexto)) {
                actualizarProductoView.mostrarMensaje("Ingrese un código válido");
                return;
            }
            int codigo = Integer.parseInt(codigoTexto);
            Producto producto = productoDAO.buscarPorCodigo(codigo);
            if (producto == null) {
                actualizarProductoView.mostrarMensaje("Producto no encontrado");
                actualizarProductoView.getTxtNombre().setText("");
                actualizarProductoView.getTxtPrecio().setText("");
            } else {
                actualizarProductoView.getTxtNombre().setText(producto.getNombre());
                actualizarProductoView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
            }
        });

        actualizarProductoView.getBtnActualizar().addActionListener(e -> {
            String codigoTexto = actualizarProductoView.getTxtBuscar().getText().trim();
            String nuevoNombre = actualizarProductoView.getTxtNombre().getText().trim();
            String precioTexto = actualizarProductoView.getTxtPrecio().getText().trim();

            if (!esNumero(codigoTexto)) {
                actualizarProductoView.mostrarMensaje("Ingrese un código válido para actualizar");
                return;
            }
            if (nuevoNombre.isEmpty() || precioTexto.isEmpty()) {
                actualizarProductoView.mostrarMensaje("Complete todos los campos antes de actualizar");
                return;
            }
            if (!esNumeroDecimal(precioTexto)) {
                actualizarProductoView.mostrarMensaje("Precio inválido");
                return;
            }

            int codigo = Integer.parseInt(codigoTexto);
            double nuevoPrecio = Double.parseDouble(precioTexto);

            actualizarProducto(codigo, nuevoNombre, nuevoPrecio);
        });
    }

    private void guardarProducto() {
        String codigoTexto = productoAnadirView.getTxtCodigo().getText().trim();
        String nombre = productoAnadirView.getTxtNombre().getText().trim();
        String precioTexto = productoAnadirView.getTxtPrecio().getText().trim();

        if (!esNumero(codigoTexto)) {
            productoAnadirView.mostrarMensaje("Código inválido");
            return;
        }
        if (nombre.isEmpty() || precioTexto.isEmpty()) {
            productoAnadirView.mostrarMensaje("Complete todos los campos");
            return;
        }
        if (!esNumeroDecimal(precioTexto)) {
            productoAnadirView.mostrarMensaje("Precio inválido");
            return;
        }

        int codigo = Integer.parseInt(codigoTexto);
        double precio = Double.parseDouble(precioTexto);

        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoAnadirView.mostrarMensaje("Producto guardado correctamente");
        productoAnadirView.limpiarCampos();
        productoAnadirView.mostrarProductos(productoDAO.listarTodos());
    }

    public void actualizarProducto(int codigo, String nuevoNombre, double nuevoPrecio) {
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto != null) {
            producto.setNombre(nuevoNombre);
            producto.setPrecio(nuevoPrecio);
            productoDAO.actualizar(producto);
            actualizarProductoView.mostrarMensaje("Producto actualizado correctamente");
        } else {
            actualizarProductoView.mostrarMensaje("Producto no encontrado");
        }
    }

    public void eliminarProducto(int codigo) {
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto != null) {
            productoDAO.eliminar(codigo);
            eliminarProductoView.mostrarMensaje("Producto eliminado correctamente");
        } else {
            eliminarProductoView.mostrarMensaje("Producto no encontrado");
        }
    }

    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();
        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(productosEncontrados);
    }

    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }

    private void listarProductosEnEliminarView() {
        List<Producto> productos = productoDAO.listarTodos();
        eliminarProductoView.mostrarProductos(productos);
    }




    private void buscarProductoPorCodigo() {
        String codigoTexto = carritoAnadirView.getTxtCodigo().getText();
        if (!esNumero(codigoTexto)) {
            carritoAnadirView.mostrarMensaje("Código inválido");
            return;
        }

        int codigo = Integer.parseInt(codigoTexto);
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto == null) {
            carritoAnadirView.mostrarMensaje("No se encontró el producto");
            carritoAnadirView.getTxtNombre().setText("");
            carritoAnadirView.getTxtPrecio().setText("");
        } else {
            carritoAnadirView.getTxtNombre().setText(producto.getNombre());
            carritoAnadirView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        }
    }

    private boolean esNumero(String texto) {
        if (texto.isEmpty()) return false;
        for (char c : texto.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private boolean esNumeroDecimal(String texto) {
        if (texto.isEmpty()) return false;
        boolean puntoEncontrado = false;
        for (char c : texto.toCharArray()) {
            if (c == '.') {
                if (puntoEncontrado) return false; // solo un punto
                puntoEncontrado = true;
            } else if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}