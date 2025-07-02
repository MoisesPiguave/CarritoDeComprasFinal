package ec.edu.ups.vista.Producto;
import ec.edu.ups.Util.MensajeInternacionalizacionHandler;
import javax.swing.*;

public class ProductoEliminarView extends JInternalFrame {
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    private JButton btnEliminar;
    private JButton btnSalir;
    private JPanel panelPrincipal;

    public ProductoEliminarView() {
        setContentPane(panelPrincipal);
        setTitle("Eliminar Producto");
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(400, 300);
        setLocation(30, 30);
        setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    public JLabel getLblPrecio() {
        return lblPrecio;
    }

    public void setLblPrecio(JLabel lblPrecio) {
        this.lblPrecio = lblPrecio;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public void setBtnSalir(JButton btnSalir) {
        this.btnSalir = btnSalir;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
        public void actualizarTextos(MensajeInternacionalizacionHandler mensajes) {
            setTitle(mensajes.getMensaje("menu.producto.eliminar")); // O producto.eliminar.titulo si tienes esa clave
            lblCodigo.setText(mensajes.getMensaje("producto.codigo"));
            lblNombre.setText(mensajes.getMensaje("producto.nombre"));
            lblPrecio.setText(mensajes.getMensaje("producto.precio"));
            btnEliminar.setText(mensajes.getMensaje("producto.eliminar"));
            btnSalir.setText(mensajes.getMensaje("carrito.salir"));
        }

    }
