package ec.edu.ups.vista.Usuario;
import ec.edu.ups.Util.MensajeInternacionalizacionHandler;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListarUsuariosView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JButton lblListar;
    private JTable table1;
    private JScrollPane tblUsuarios;
    private DefaultTableModel modelo;
public ListarUsuariosView() {

    setContentPane(panelPrincipal);
    setTitle("Listar Usuarios");
    setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
    setSize(500, 500);
    setClosable(true);
    setIconifiable(true);
    setResizable(true);

    modelo = new DefaultTableModel();
    Object[] columnas = {"Usuario", "Contraseña", "Carritos"};
    modelo.setColumnIdentifiers(columnas);
    table1.setModel(modelo);

}

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JButton getLblListar() {
        return lblListar;
    }

    public void setLblListar(JButton lblListar) {
        this.lblListar = lblListar;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JScrollPane getTblUsuarios() {
        return tblUsuarios;
    }

    public void setTblUsuarios(JScrollPane tblUsuarios) {
        this.tblUsuarios = tblUsuarios;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    public void actualizarTextos(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.getMensaje("menu.usuario.listar"));
        lblListar.setText(mensajes.getMensaje("menu.usuario.listar")); // Si lblListar es un botón o label de título
        // Si tienes columnas en una tabla:
        String[] columnas = {
                mensajes.getMensaje("usuario.usuario"),
                mensajes.getMensaje("usuario.contrasena")
                // Agrega más columnas si las tienes
        };
        modelo.setColumnIdentifiers(columnas);
    }


}
