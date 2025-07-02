package ec.edu.ups.vista.Carrito;

import ec.edu.ups.Util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DetallesDeCarritoView extends JInternalFrame {
    private JPanel panel1;
    private JTable table1;
    private JButton btnSalir;
    private JButton btnEliminar;
    private DefaultTableModel modelo;

    public DetallesDeCarritoView() {
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

    public JButton getSalirButton() {
        return btnSalir;
    }

    public void setSalirButton(JButton salirButton) {
        this.btnSalir = salirButton;
    }

    public JButton getEliminarButton() {
        return btnEliminar;
    }

    public void setEliminarButton(JButton eliminarButton) {
        this.btnEliminar = eliminarButton;
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

        String[] columnas = {
                mensajes.getMensaje("carrito.codigo"),
                mensajes.getMensaje("carrito.nombre"),
                mensajes.getMensaje("carrito.precio")
        };
        modelo.setColumnIdentifiers(columnas);

        btnSalir.setText(mensajes.getMensaje("carrito.salir"));
        btnEliminar.setText(mensajes.getMensaje("carrito.eliminar"));
    }
}
