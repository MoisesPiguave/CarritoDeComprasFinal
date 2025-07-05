package ec.edu.ups.dao;

import ec.edu.ups.modelo.PreguntasDeSeguridad;

import java.util.List;

public interface PreguntasDeSeguridadDAO {
    void agregarPregunta(PreguntasDeSeguridad pregunta);
    List<PreguntasDeSeguridad> obtenerTodas();
}
