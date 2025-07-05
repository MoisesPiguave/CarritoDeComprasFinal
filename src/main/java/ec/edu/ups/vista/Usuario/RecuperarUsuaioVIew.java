package ec.edu.ups.vista.Usuario;

import ec.edu.ups.Util.MensajeInternacionalizacionHandler;
import javax.swing.*;

public class RecuperarUsuaioVIew extends JFrame {
    private JPanel panelPrincipal;
    private JLabel lblConfirmar;
    private JLabel lblContrasena;
    private JLabel lblRespuesta;
    private JLabel lblPregunta;
    private JTextField txtPregunta;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JLabel lblPreguntas;
    private JComboBox comboBox1;
    private JTextField txtRespuesta;
    private JButton btnSalir;
    private JButton btnConfirmar;
    private JTextField txtUsuario;
    private JLabel lblUsuario;
    private JButton buscarButton;
    private JButton btnContinuar;

    public RecuperarUsuaioVIew(){
        setContentPane(panelPrincipal);
        setTitle("Recuperar Cuenta");
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

    public JLabel getLblConfirmar() {
        return lblConfirmar;
    }

    public void setLblConfirmar(JLabel lblConfirmar) {
        this.lblConfirmar = lblConfirmar;
    }

    public JLabel getLblContrasena() {
        return lblContrasena;
    }

    public void setLblContrasena(JLabel lblContrasena) {
        this.lblContrasena = lblContrasena;
    }

    public JLabel getLblRespuesta() {
        return lblRespuesta;
    }

    public void setLblRespuesta(JLabel lblRespuesta) {
        this.lblRespuesta = lblRespuesta;
    }

    public JLabel getLblPregunta() {
        return lblPregunta;
    }

    public void setLblPregunta(JLabel lblPregunta) {
        this.lblPregunta = lblPregunta;
    }

    public JTextField getTxtPregunta() {
        return txtPregunta;
    }

    public void setTxtPregunta(JTextField txtPregunta) {
        this.txtPregunta = txtPregunta;
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

    public JLabel getLblPreguntas() {
        return lblPreguntas;
    }

    public void setLblPreguntas(JLabel lblPreguntas) {
        this.lblPreguntas = lblPreguntas;
    }

    public JComboBox getComboBox1() {
        return comboBox1;
    }

    public void setComboBox1(JComboBox comboBox1) {
        this.comboBox1 = comboBox1;
    }

    public JTextField getTxtRespuesta() {
        return txtRespuesta;
    }

    public void setTxtRespuesta(JTextField txtRespuesta) {
        this.txtRespuesta = txtRespuesta;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public void setBtnSalir(JButton btnSalir) {
        this.btnSalir = btnSalir;
    }

    public JButton getBtnConfirmar() {
        return btnConfirmar;
    }

    public void setBtnConfirmar(JButton btnConfirmar) {
        this.btnConfirmar = btnConfirmar;
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public void setBuscarButton(JButton buscarButton) {
        this.buscarButton = buscarButton;
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
        setTitle(mensajes.getMensaje("recuperar.titulo"));  // Título ventana

        lblPreguntas.setText(mensajes.getMensaje("recuperar.pregunta"));  // Label pregunta

        btnContinuar.setText(mensajes.getMensaje("recuperar.boton.continuar")); // Botón continuar
    }
}
