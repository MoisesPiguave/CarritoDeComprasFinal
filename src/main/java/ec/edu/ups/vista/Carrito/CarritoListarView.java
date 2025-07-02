package ec.edu.ups.vista.Carrito;

import ec.edu.ups.Util.MensajeInternacionalizacionHandler;
import ec.edu.ups.modelo.Carrito;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CarritoListarView extends JInternalFrame {
    private JPanel panel1;
    private JTable table1;
    private JButton btnDetalles;
    private JButton btnSalir;
    private DefaultTableModel modelo;

    public CarritoListarView() {
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

    public void cargarDatos(List<Carrito> lista) {
        modelo.setRowCount(0);
        for (Carrito carrito : lista) {
            modelo.addRow(new Object[]{
                    carrito.getCodigo(),
                    carrito.getUsuario() != null ? carrito.getUsuario().getUsername() : "N/A",
                    carrito.getProductos() != null ? carrito.getProductos().size() : 0
            });
        }
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

    public JButton getDetallesButton() {
        return btnDetalles;
    }

    public void setDetallesButton(JButton detallesButton) {
        this.btnDetalles = detallesButton;
    }

    public JButton getSalirButton() {
        return btnSalir;
    }

    public void setSalirButton(JButton salirButton) {
        this.btnSalir = salirButton;
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
        setTitle(mensajes.getMensaje("menu.carrito.listar")); // Título ventana

        // Actualizar columnas de la tabla
        String[] columnas = {
                mensajes.getMensaje("carrito.codigo"),
                mensajes.getMensaje("usuario.usuario"),       // "Usuario" o "Username"
                mensajes.getMensaje("carrito.total_productos") // "Total Products"
        };
        modelo.setColumnIdentifiers(columnas);

        // Botones
        btnDetalles.setText(mensajes.getMensaje("carrito.detalles")); // Detalles
        btnSalir.setText(mensajes.getMensaje("carrito.salir"));       // Salir / Exit
    }
}
