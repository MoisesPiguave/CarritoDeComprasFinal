package ec.edu.ups.vista.Carrito;
import ec.edu.ups.Util.MensajeInternacionalizacionHandler;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoEliminarView extends JInternalFrame {
    private JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JButton buscarButton;
    private JButton eliminarButton;
    private JButton salirButton;
    private JTextField textField2;
    private JTextField textField3;
    private JButton eliminarCarritoButton;
    private DefaultTableModel modelo;

    public CarritoEliminarView() {
        setContentPane(panel1);
        setTitle("Carrito de Compras");
        setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        String[] columnas = {"Codigo", "Nombre", "Precio"};
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
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public void setBuscarButton(JButton buscarButton) {
        this.buscarButton = buscarButton;
    }

    public JButton getEliminarButton() {
        return eliminarButton;
    }

    public void setEliminarButton(JButton eliminarButton) {
        this.eliminarButton = eliminarButton;
    }

    public JButton getSalirButton() {
        return salirButton;
    }

    public void setSalirButton(JButton salirButton) {
        this.salirButton = salirButton;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public void setTextField2(JTextField textField2) {
        this.textField2 = textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public void setTextField3(JTextField textField3) {
        this.textField3 = textField3;
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

    public JButton getEliminarCarritoButton() {
        return eliminarCarritoButton;
    }

    public void setEliminarCarritoButton(JButton eliminarCarritoButton) {
        this.eliminarCarritoButton = eliminarCarritoButton;
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
        buscarButton.setText(mensajes.getMensaje("carrito.buscar")); // Si tienes esta clave
        eliminarButton.setText(mensajes.getMensaje("carrito.eliminar"));
        eliminarCarritoButton.setText(mensajes.getMensaje("carrito.eliminar")); // Si tiene botón extra
        salirButton.setText(mensajes.getMensaje("carrito.salir"));
    }

}
