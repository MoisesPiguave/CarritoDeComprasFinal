package ec.edu.ups.vista.Usuario;
import ec.edu.ups.Util.MensajeInternacionalizacionHandler;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EliminarUsuarioView extends JInternalFrame {
    private JTextField txtUsuario;
    private JButton btnBuscar;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel lblUsuario;
    private JLabel lblContrasena;
    private JLabel lblCarrito;
    private JLabel lblUsuario1;
    private JPanel panelPrincipal;
    private JTable tblUsuario;
    private JButton btnSalir;
    private JButton btnEliminar;
    private DefaultTableModel modelo;

    public EliminarUsuarioView() {
        setContentPane(panelPrincipal);
        setTitle("Eliminar Usuario");
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(600, 400);

        // Columnas incluyendo la fecha
        String[] columnas = { "Nombre", "Usuario", "Correo", "Fecha de nacimiento"};
        modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblUsuario.setModel(modelo);
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
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

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    public JLabel getLblContrasena() {
        return lblContrasena;
    }

    public void setLblContrasena(JLabel lblContrasena) {
        this.lblContrasena = lblContrasena;
    }

    public JLabel getLblCarrito() {
        return lblCarrito;
    }

    public void setLblCarrito(JLabel lblCarrito) {
        this.lblCarrito = lblCarrito;
    }

    public JLabel getLblUsuario1() {
        return lblUsuario1;
    }

    public void setLblUsuario1(JLabel lblUsuario1) {
        this.lblUsuario1 = lblUsuario1;
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
        setTitle(mensajes.getMensaje("menu.usuario.eliminar"));  // Título de la ventana

        lblUsuario.setText(mensajes.getMensaje("usuario.usuario"));          // Username
        lblContrasena.setText(mensajes.getMensaje("usuario.contrasena"));    // Password
        lblCarrito.setText(mensajes.getMensaje("usuario.carritos"));         // Carts

        btnBuscar.setText(mensajes.getMensaje("producto.buscar"));           // Botón Buscar (puedes cambiar la clave si prefieres otra)
    }

}