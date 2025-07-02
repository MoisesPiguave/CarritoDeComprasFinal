package ec.edu.ups.vista.Producto;
import ec.edu.ups.Util.MensajeInternacionalizacionHandler;
import javax.swing.*;

public class ProductoActualizarView extends JInternalFrame{
    private JLabel lblCodigo;
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JButton btnActualizar;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JPanel panelPrincipal;
    private JButton btnSalir;
    private JLabel lblNombre;
    private JLabel lblPrecio;

    public ProductoActualizarView(){
        setContentPane(panelPrincipal);
        setTitle("Actualizar Producto");
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(400, 300);
        setLocation(30, 30);
        setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
    }

    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public JTextField getTextField1() {
        return txtCodigo;
    }

    public void setTextField1(JTextField textField1) {
        this.txtCodigo = textField1;
    }

    public JButton getBuscarButton() {
        return btnBuscar;
    }

    public void setBuscarButton(JButton buscarButton) {
        this.btnBuscar = buscarButton;
    }

    public JButton getActualizarButton() {
        return btnActualizar;
    }

    public void setActualizarButton(JButton actualizarButton) {
        this.btnActualizar = actualizarButton;
    }

    public JTextField getTextField2() {
        return txtNombre;
    }

    public void setTextField2(JTextField textField2) {
        this.txtNombre = textField2;
    }

    public JTextField getTextField3() {
        return txtPrecio;
    }

    public void setTextField3(JTextField textField3) {
        this.txtPrecio = textField3;
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
        setTitle(mensajes.getMensaje("menu.producto.actualizar")); // O producto.actualizar.titulo si prefieres
        lblCodigo.setText(mensajes.getMensaje("producto.codigo"));
        lblNombre.setText(mensajes.getMensaje("producto.nombre"));
        lblPrecio.setText(mensajes.getMensaje("producto.precio"));
        btnBuscar.setText(mensajes.getMensaje("producto.buscar"));
        btnActualizar.setText(mensajes.getMensaje("producto.actualizar"));
        btnSalir.setText(mensajes.getMensaje("carrito.salir"));
    }



}
