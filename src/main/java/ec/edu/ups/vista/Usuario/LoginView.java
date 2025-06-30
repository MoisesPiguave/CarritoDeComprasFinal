package ec.edu.ups.vista.Usuario;
import ec.edu.ups.Util.MensajeInternacionalizacionHandler;
import javax.swing.*;

public class LoginView extends JFrame {
    private JPanel panelPrincipal;
    private JLabel lblUsuario;
    private JLabel lblContrasena;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton btnIniciarSesion;
    private JButton btnRegistrarse;
    private JButton btnRecuperar;

    public  LoginView(){
        setContentPane(panelPrincipal);
        setTitle("Iniciar Sesion");
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

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public void setPasswordField1(JPasswordField passwordField1) {
        this.passwordField1 = passwordField1;
    }

    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    public void setBtnIniciarSesion(JButton btnIniciarSesion) {
        this.btnIniciarSesion = btnIniciarSesion;
    }

    public JButton getBtnRegistrarse() {
        return btnRegistrarse;
    }

    public void setBtnRegistrarse(JButton btnRegistrarse) {
        this.btnRegistrarse = btnRegistrarse;
    }

    public JButton getBtnRecuperar() {
        return btnRecuperar;
    }

    public void setBtnRecuperar(JButton btnRecuperar) {
        this.btnRecuperar = btnRecuperar;
    }
    public void limpiarCampos() {
        textField1.setText("");
        passwordField1.setText("");
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    public void actualizarTextos(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.getMensaje("login.titulo")); // Título de la ventana

        lblUsuario.setText(mensajes.getMensaje("login.usuario"));         // Label Usuario
        lblContrasena.setText(mensajes.getMensaje("login.contrasena"));   // Label Contraseña

        btnIniciarSesion.setText(mensajes.getMensaje("login.boton.iniciar"));   // Botón Iniciar Sesión
        btnRegistrarse.setText(mensajes.getMensaje("login.boton.registrarse")); // Botón Registrarse
        btnRecuperar.setText(mensajes.getMensaje("login.boton.recuperar"));     // Botón Recuperar
    }

}
