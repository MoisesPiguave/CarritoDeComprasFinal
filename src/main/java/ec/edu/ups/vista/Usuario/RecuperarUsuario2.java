package ec.edu.ups.vista.Usuario;
import ec.edu.ups.Util.MensajeInternacionalizacionHandler;
import javax.swing.*;

public class RecuperarUsuario2 extends JFrame {
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton continuarButton;
    private JPanel panelPrincipal;

    public RecuperarUsuario2() {
        setContentPane(panelPrincipal);
        setTitle("Recuperar Cuenta");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public void setPasswordField1(JPasswordField passwordField1) {
        this.passwordField1 = passwordField1;
    }

    public JPasswordField getPasswordField2() {
        return passwordField2;
    }

    public void setPasswordField2(JPasswordField passwordField2) {
        this.passwordField2 = passwordField2;
    }

    public JButton getContinuarButton() {
        return continuarButton;
    }

    public void setContinuarButton(JButton continuarButton) {
        this.continuarButton = continuarButton;
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
        setTitle(mensajes.getMensaje("recuperar.titulo"));
        continuarButton.setText(mensajes.getMensaje("recuperar.boton.continuar"));
    }


}
