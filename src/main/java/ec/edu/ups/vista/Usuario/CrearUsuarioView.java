package ec.edu.ups.vista.Usuario;

import javax.swing.*;
import javax.swing.plaf.basic.BasicRadioButtonMenuItemUI;

public class CrearUsuarioView extends JFrame {
    private JLabel lblUsuario;
    private JLabel lblContrasena;
    private JTextField txtUsuario;
    private JPasswordField pswContrasena;
    private JPasswordField pswConfirmar;
    private JButton btnCancelar;
    private JButton btnContinuar;
    private JPanel panelPrincipal;

    public CrearUsuarioView() {
        setContentPane(panelPrincipal);
        setTitle("Crear Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
