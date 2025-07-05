package ec.edu.ups.controlador;

import ec.edu.ups.dao.PreguntasDeSeguridadDAO;
import ec.edu.ups.modelo.PreguntasDeSeguridad;
import ec.edu.ups.vista.Usuario.CrearUsuario2View;

import javax.swing.*;
import java.util.List;

public class PreguntasDeSeguridadController {

    private final PreguntasDeSeguridadDAO preguntasDAO;

    public PreguntasDeSeguridadController(PreguntasDeSeguridadDAO preguntasDAO) {
        this.preguntasDAO = preguntasDAO;
    }

    /**
     * Carga las preguntas de seguridad en los tres combo boxes
     * del CrearUsuario2View para que el usuario elija.
     */
    public void cargarPreguntasEnCombos(CrearUsuario2View vista) {
        List<PreguntasDeSeguridad> preguntasDisponibles = preguntasDAO.obtenerTodas();

        JComboBox<String>[] combos = new JComboBox[]{
                vista.getCbx1(),
                vista.getCbx2(),
                vista.getCbx3()
        };

        for (JComboBox<String> combo : combos) {
            combo.removeAllItems();
            for (PreguntasDeSeguridad pregunta : preguntasDisponibles) {
                combo.addItem(pregunta.getPregunta());
            }
        }
    }
}
