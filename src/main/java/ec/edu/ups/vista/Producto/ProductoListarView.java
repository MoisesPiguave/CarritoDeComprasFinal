package ec.edu.ups.vista.Producto;
import ec.edu.ups.Util.MensajeInternacionalizacionHandler;
import ec.edu.ups.modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductoListarView extends JInternalFrame {
    private JTable tblProducto;
    private JPanel panelPrincipal;
    private JButton btnsalir;
    private JButton btnActualizar;
    private DefaultTableModel modelo;

    public ProductoListarView() {
        setContentPane(panelPrincipal);
        setTitle("Listado de Productos");
        setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        String[] columnas = {"Codigo", "Nombre", "Precio"};
        modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        inicializarTabla();
    }

    public void inicializarTabla() {
        if (tblProducto != null) {
            tblProducto.setModel(modelo);
        } else {
            System.err.println("tblProducto es null. Revisa la carga del .form");
        }
    }

    // ✅ Método nuevo que te hacía falta
    public void cargarDatos(List<Producto> lista) {
        modelo.setRowCount(0); // Limpiar tabla

        for (Producto p : lista) {
            modelo.addRow(new Object[]{
                    p.getCodigo(),
                    p.getNombre(),
                    p.getPrecio()
            });
        }
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public JTable getTblProducto() {
        return tblProducto;
    }


    public JButton getSalirButton() {
        return btnsalir;
    }

    public JButton getActualizarButton() {
        return btnActualizar;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    public void actualizarTextos(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.getMensaje("menu.producto.listar"));
        btnsalir.setText(mensajes.getMensaje("carrito.salir"));
        btnActualizar.setText(mensajes.getMensaje("producto.actualizar"));
        // También puedes actualizar las cabeceras si quieres:
        String[] columnas = {
                mensajes.getMensaje("producto.codigo"),
                mensajes.getMensaje("producto.nombre"),
                mensajes.getMensaje("producto.precio")
        };
        modelo.setColumnIdentifiers(columnas);
    }

}