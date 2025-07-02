package ec.edu.ups.vista.Carrito;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import java.util.Locale;
import ec.edu.ups.Util.MensajeInternacionalizacionHandler;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoCrearView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTable table1;
    private JTextField txtNombre;
    private JButton btnAgregar;
    private JTextField txtSubtotal;
    private JTextField txtTotal;
    private JTextField txtFecha;
    private JButton btnCrear;
    private JButton btnSalir;
    private JLabel lblNombre;
    private JLabel lblCantidad;
    private JLabel lblSubtotal;
    private JLabel lblTotal;
    private JTextField txtCantidad;
    private JLabel lblFecha;
    private DefaultTableModel modelo;

    public CarritoCrearView() {
        setContentPane(panelPrincipal);
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

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public void setBtnAgregar(JButton btnAgregar) {
        this.btnAgregar = btnAgregar;
    }

    public JTextField getTxtSubtotal() {
        return txtSubtotal;
    }

    public void setTxtSubtotal(JTextField txtSubtotal) {
        this.txtSubtotal = txtSubtotal;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public void setTxtTotal(JTextField txtTotal) {
        this.txtTotal = txtTotal;
    }

    public JTextField getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(JTextField txtFecha) {
        this.txtFecha = txtFecha;
    }

    public JButton getBtnCrear() {
        return btnCrear;
    }

    public void setBtnCrear(JButton btnCrear) {
        this.btnCrear = btnCrear;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public void setBtnSalir(JButton btnSalir) {
        this.btnSalir = btnSalir;
    }

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    public JLabel getLblCantidad() {
        return lblCantidad;
    }

    public void setLblCantidad(JLabel lblCantidad) {
        this.lblCantidad = lblCantidad;
    }

    public JLabel getLblSubtotal() {
        return lblSubtotal;
    }

    public void setLblSubtotal(JLabel lblSubtotal) {
        this.lblSubtotal = lblSubtotal;
    }

    public JLabel getLblTotal() {
        return lblTotal;
    }

    public void setLblTotal(JLabel lblTotal) {
        this.lblTotal = lblTotal;
    }

    public JTextField getTxtCantidad() {
        return txtCantidad;
    }

    public void setTxtCantidad(JTextField txtCantidad) {
        this.txtCantidad = txtCantidad;
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

    public JLabel getLblFecha() {
        return lblFecha;
    }

    public void setLblFecha(JLabel lblFecha) {
        this.lblFecha = lblFecha;
    }

    public void actualizarTextos(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.getMensaje("carrito.titulo"));

        String[] columnas = {
                mensajes.getMensaje("carrito.codigo"),
                mensajes.getMensaje("carrito.nombre"),
                mensajes.getMensaje("carrito.precio"),
                mensajes.getMensaje("carrito.cantidad")
        };
        modelo.setColumnIdentifiers(columnas);

        btnAgregar.setText(mensajes.getMensaje("carrito.agregar"));
        btnCrear.setText(mensajes.getMensaje("carrito.crear"));
        btnSalir.setText(mensajes.getMensaje("carrito.salir"));
    }



    public void actualizarTablaConMoneda(DefaultTableModel modelo, Carrito carrito) {
        modelo.setRowCount(0);
        for (ItemCarrito item : carrito.obtenerItems()) {
            String precioFormateado = ec.edu.ups.Util.FormateadorUtils.formatearMoneda(item.getProducto().getPrecio(), Locale.getDefault());
            modelo.addRow(new Object[]{
                    item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    precioFormateado,
                    item.getCantidad()
            });
        }
    }
}
