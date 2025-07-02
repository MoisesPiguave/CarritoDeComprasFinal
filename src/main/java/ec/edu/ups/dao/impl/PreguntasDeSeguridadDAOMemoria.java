package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntaSeguridadDAO;

import java.util.ArrayList;
import java.util.List;

public class PreguntasDeSeguridadDAOMemoria implements PreguntaSeguridadDAO {

    private List<String> bancoPreguntas;

    public PreguntasDeSeguridadDAOMemoria() {
        bancoPreguntas = new ArrayList<>();
        cargarPreguntasIniciales();
    }

    private void cargarPreguntasIniciales() {
        bancoPreguntas.add("¿Cuál es el nombre de tu primera mascota?");
        bancoPreguntas.add("¿En qué ciudad naciste?");
        bancoPreguntas.add("¿Cuál es el nombre de tu escuela primaria?");
        bancoPreguntas.add("¿Cuál es tu comida favorita?");
        bancoPreguntas.add("¿Cuál es el segundo nombre de tu padre?");
        bancoPreguntas.add("¿Cuál fue tu primer trabajo?");
        bancoPreguntas.add("¿Cuál es tu color favorito?");
        bancoPreguntas.add("¿Cuál es el nombre de tu mejor amigo de la infancia?");
        bancoPreguntas.add("¿Cuál es tu deporte favorito?");
        bancoPreguntas.add("¿Cómo se llama tu madre?");
    }

    @Override
    public void agregarPregunta(String pregunta) {
        if (pregunta != null && !pregunta.trim().isEmpty()) {
            bancoPreguntas.add(pregunta.trim());
        }
    }

    @Override
    public List<String> obtenerTodasLasPreguntas() {
        return new ArrayList<>(bancoPreguntas);
    }

    @Override
    public String obtenerPreguntaPorIndice(int indice) {
        if (indice >= 0 && indice < bancoPreguntas.size()) {
            return bancoPreguntas.get(indice);
        }
        return null;
    }

    @Override
    public int totalPreguntas() {
        return bancoPreguntas.size();
    }
}
