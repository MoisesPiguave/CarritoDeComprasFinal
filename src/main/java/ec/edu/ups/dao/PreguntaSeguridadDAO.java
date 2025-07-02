package ec.edu.ups.dao;

import java.util.List;

public interface PreguntaSeguridadDAO {

    void agregarPregunta(String pregunta);

    List<String> obtenerTodasLasPreguntas();

    String obtenerPreguntaPorIndice(int indice);

    int totalPreguntas();
}
