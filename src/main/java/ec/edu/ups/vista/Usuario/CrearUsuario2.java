package ec.edu.ups.vista.Usuario;
import ec.edu.ups.Util.MensajeInternacionalizacionHandler;
import javax.swing.*;

public class CrearUsuario2 extends JFrame {
    private JPanel panelPrincipal;
    private JTextField textField2;
    private JTextField textField4;
    private JTextField textField6;
    private JLabel llblP1;
    private JLabel lblP2;
    private JLabel lblP3;
    private JButton btnContinuar;

    public CrearUsuario2() {
        setContentPane(panelPrincipal);
        setTitle("Crear Usuario");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public void setTextField2(JTextField textField2) {
        this.textField2 = textField2;
    }

    public JTextField getTextField4() {
        return textField4;
    }

    public void setTextField4(JTextField textField4) {
        this.textField4 = textField4;
    }

    public JTextField getTextField6() {
        return textField6;
    }

    public void setTextField6(JTextField textField6) {
        this.textField6 = textField6;
    }

    public JLabel getLlblP1() {
        return llblP1;
    }

    public void setLlblP1(JLabel llblP1) {
        this.llblP1 = llblP1;
    }

    public JLabel getLblP2() {
        return lblP2;
    }

    public void setLblP2(JLabel lblP2) {
        this.lblP2 = lblP2;
    }

    public JLabel getLblP3() {
        return lblP3;
    }

    public void setLblP3(JLabel lblP3) {
        this.lblP3 = lblP3;
    }

    public JButton getBtnContinuar() {
        return btnContinuar;
    }

    public void setBtnContinuar(JButton btnContinuar) {
        this.btnContinuar = btnContinuar;
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    public void actualizarTextos(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.getMensaje("menu.usuario.crear")); // O puedes crear una clave como: usuario.crear.titulo

        llblP1.setText(mensajes.getMensaje("usuario.usuario"));        // Supongo que este campo es para el Username
        lblP2.setText(mensajes.getMensaje("usuario.contrasena"));      // Para la contraseña
        lblP3.setText(mensajes.getMensaje("usuario.carritos"));        // Supongo que es para Carritos o algo similar

        btnContinuar.setText(mensajes.getMensaje("usuario.continuar"));  // Botón de continuar
    }

}
