package ec.edu.ups.vista.Usuario;

import javax.swing.*;

public class RecuperarUsuaioVIew extends JFrame {
    private JPanel panelPrincipal;
    private JLabel lblPreguntas;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JButton btnContinuar;

    public RecuperarUsuaioVIew(){
        setContentPane(panelPrincipal);
        setTitle("Recuperar Cuenta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
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

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
