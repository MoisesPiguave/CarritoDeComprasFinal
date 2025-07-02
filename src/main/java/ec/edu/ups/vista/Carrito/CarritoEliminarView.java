package ec.edu.ups.vista.Carrito;

import ec.edu.ups.Util.MensajeInternacionalizacionHandler;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.Util.FormateadorUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Locale;

public class CarritoEliminarView extends JInternalFrame {
    private JPanel panel1;
    private JTable table1;
    private JTextField txtNombre;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnSalir;
    private JTextField txtSubtotal;
    private JTextField txtTotal;
    private JButton btnEliminarCarrito;
    private JLabel lblNombre;
    private JLabel lblSubtotal;
    private JLabel lblTotal;
    private DefaultTableModel modelo;

    public CarritoEliminarView() {
        setContentPane(panel1);
        setTitle("Carrito de Compras");
        setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        // Columnas incluyendo la fecha
        String[] columnas = {"Codigo", "Nombre", "Precio", "Cantidad", "Fecha"};
        modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(modelo);
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JTextField getTextField1() {
        return txtNombre;
    }

    public void setTextField1(JTextField textField1) {
        this.txtNombre = textField1;
    }

    public JButton getBuscarButton() {
        return btnBuscar;
    }

    public void setBuscarButton(JButton buscarButton) {
        this.btnBuscar = buscarButton;
    }

    public JButton getEliminarButton() {
        return btnEliminar;
    }

    public void setEliminarButton(JButton eliminarButton) {
        this.btnEliminar = eliminarButton;
    }

    public JButton getSalirButton() {
        return btnSalir;
    }

    public void setSalirButton(JButton salirButton) {
        this.btnSalir = salirButton;
    }

    public JTextField getTextField2() {
        return txtSubtotal;
    }

    public void setTextField2(JTextField textField2) {
        this.txtSubtotal = textField2;
    }

    public JTextField getTextField3() {
        return txtTotal;
    }

    public void setTextField3(JTextField textField3) {
        this.txtTotal = textField3;
    }

    public JButton getEliminarCarritoButton() {
        return btnEliminarCarrito;
    }

    public void setEliminarCarritoButton(JButton eliminarCarritoButton) {
        this.btnEliminarCarrito = eliminarCarritoButton;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void actualizarTextos(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.getMensaje("carrito.titulo")); // Título ventana

        // Actualizar columnas de la tabla
        String[] columnas = {
                mensajes.getMensaje("carrito.codigo"),
                mensajes.getMensaje("carrito.nombre"),
                mensajes.getMensaje("carrito.precio")
        };
        modelo.setColumnIdentifiers(columnas);

        // Botones
        btnBuscar.setText(mensajes.getMensaje("carrito.buscar"));
        btnEliminar.setText(mensajes.getMensaje("carrito.eliminar"));
        btnEliminarCarrito.setText(mensajes.getMensaje("carrito.eliminar"));
        btnSalir.setText(mensajes.getMensaje("carrito.salir"));
    }

    // Método para actualizar la tabla con precios formateados a moneda local
    public void actualizarTablaConMoneda(DefaultTableModel modelo, Carrito carrito) {
        modelo.setRowCount(0);
        for (ItemCarrito item : carrito.obtenerItems()) {
            String precioFormateado = FormateadorUtils.formatearMoneda(item.getProducto().getPrecio(), Locale.getDefault());
            modelo.addRow(new Object[]{
                    item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    precioFormateado
            });
        }
    }
}
