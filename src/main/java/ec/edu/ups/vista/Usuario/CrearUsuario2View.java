package ec.edu.ups.vista.Usuario;
import ec.edu.ups.Util.MensajeInternacionalizacionHandler;
import javax.swing.*;

public class CrearUsuario2View extends JFrame {
    private JLabel lblUsuario;
    private JLabel lblContrasena;
    private JTextField txtUsuario;
    private JPasswordField pswContrasena;
    private JPasswordField pswConfirmar;
    private JButton btnCancelar;
    private JButton btnContinuar;
    private JPanel panelPrincipal;
    private JComboBox cbx1;
    private JComboBox cbx2;
    private JComboBox cbx3;
    private JPasswordField txtP1;
    private JTextField txtP2;
    private JTextField txtP3;
    private JLabel lblP1;
    private JLabel lblP2;
    private JLabel lblP3;
    private JButton salirButton;

    public CrearUsuario2View() {
        setContentPane(panelPrincipal);
        setTitle("Crear Usuario");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
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

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public JPasswordField getPswContrasena() {
        return pswContrasena;
    }

    public void setPswContrasena(JPasswordField pswContrasena) {
        this.pswContrasena = pswContrasena;
    }

    public JPasswordField getPswConfirmar() {
        return pswConfirmar;
    }

    public void setPswConfirmar(JPasswordField pswConfirmar) {
        this.pswConfirmar = pswConfirmar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public JButton getBtnContinuar() {
        return btnContinuar;
    }

    public void setBtnContinuar(JButton btnContinuar) {
        this.btnContinuar = btnContinuar;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JComboBox getCbx1() {
        return cbx1;
    }

    public void setCbx1(JComboBox cbx1) {
        this.cbx1 = cbx1;
    }

    public JComboBox getCbx2() {
        return cbx2;
    }

    public void setCbx2(JComboBox cbx2) {
        this.cbx2 = cbx2;
    }

    public JComboBox getCbx3() {
        return cbx3;
    }

    public void setCbx3(JComboBox cbx3) {
        this.cbx3 = cbx3;
    }

    public JPasswordField getTxtP1() {
        return txtP1;
    }

    public void setTxtP1(JPasswordField txtP1) {
        this.txtP1 = txtP1;
    }

    public JTextField getTxtP2() {
        return txtP2;
    }

    public void setTxtP2(JTextField txtP2) {
        this.txtP2 = txtP2;
    }

    public JTextField getTxtP3() {
        return txtP3;
    }

    public void setTxtP3(JTextField txtP3) {
        this.txtP3 = txtP3;
    }

    public JLabel getLblP1() {
        return lblP1;
    }

    public void setLblP1(JLabel lblP1) {
        this.lblP1 = lblP1;
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

    public JButton getSalirButton() {
        return salirButton;
    }

    public void setSalirButton(JButton salirButton) {
        this.salirButton = salirButton;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    public void actualizarTextos(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.getMensaje("usuario.crear.titulo"));  // Título de la ventana

        lblUsuario.setText(mensajes.getMensaje("usuario.usuario"));          // Username
        lblContrasena.setText(mensajes.getMensaje("usuario.contrasena"));    // Password

        btnContinuar.setText(mensajes.getMensaje("usuario.continuar"));      // Botón Continuar
        btnCancelar.setText(mensajes.getMensaje("usuario.cancelar"));        // Botón Cancelar
    }


}
