package ec.edu.ups.vista.Producto;
import ec.edu.ups.Util.MensajeInternacionalizacionHandler;
import javax.swing.*;

public class ProductoAnadirView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton guardarButton;
    private JButton salirButton;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblPrecio;

    public ProductoAnadirView() {
        setContentPane(panelPrincipal);
        setTitle("Agregar Producto");
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(400, 300);
        setLocation(30, 30);
        setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTextField1() {
        return txtCodigo;
    }

    public void setTextField1(JTextField textField1) {
        this.txtCodigo = textField1;
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

    public JButton getGuardarButton() {
        return guardarButton;
    }

    public void setGuardarButton(JButton guardarButton) {
        this.guardarButton = guardarButton;
    }

    public JButton getSalirButton() {
        return salirButton;
    }

    public void setSalirButton(JButton salirButton) {
        this.salirButton = salirButton;
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    public void actualizarTextos(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.getMensaje("producto.titulo"));
        lblCodigo.setText(mensajes.getMensaje("producto.codigo"));
        lblNombre.setText(mensajes.getMensaje("producto.nombre"));
        lblPrecio.setText(mensajes.getMensaje("producto.precio"));
        guardarButton.setText(mensajes.getMensaje("producto.guardar"));
        salirButton.setText(mensajes.getMensaje("carrito.salir")); // O crea menu.producto.salir si quieres
    }

}