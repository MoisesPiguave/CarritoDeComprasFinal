package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntasDeSeguridadDAO;
import ec.edu.ups.modelo.PreguntasDeSeguridad;

import java.util.ArrayList;
import java.util.List;

public class PreguntasDeSeguridadDAOMemoria implements PreguntasDeSeguridadDAO {

    private List<PreguntasDeSeguridad> bancoPreguntas;

    public PreguntasDeSeguridadDAOMemoria() {
        this.bancoPreguntas = new ArrayList<>();
        cargarPreguntasIniciales();
    }

    private void cargarPreguntasIniciales() {
        bancoPreguntas.add(new PreguntasDeSeguridad("¿Cuál es el nombre de tu primera mascota?", ""));
        bancoPreguntas.add(new PreguntasDeSeguridad("¿En qué ciudad naciste?", ""));
        bancoPreguntas.add(new PreguntasDeSeguridad("¿Cuál es tu color favorito?", ""));
        bancoPreguntas.add(new PreguntasDeSeguridad("¿Cuál es el nombre de tu escuela primaria?", ""));
        bancoPreguntas.add(new PreguntasDeSeguridad("¿Cómo se llama tu mejor amigo de infancia?", ""));
        bancoPreguntas.add(new PreguntasDeSeguridad("¿Cuál es tu comida favorita?", ""));
        bancoPreguntas.add(new PreguntasDeSeguridad("¿Cuál fue tu primer número de teléfono?", ""));
        bancoPreguntas.add(new PreguntasDeSeguridad("¿Cuál es tu deporte favorito?", ""));
        bancoPreguntas.add(new PreguntasDeSeguridad("¿Qué nombre tenía tu primer juguete?", ""));
        bancoPreguntas.add(new PreguntasDeSeguridad("¿Cuál es el segundo nombre de tu madre?", ""));
    }

    @Override
    public void agregarPregunta(PreguntasDeSeguridad pregunta) {
        bancoPreguntas.add(pregunta);
    }

    @Override
    public List<PreguntasDeSeguridad> obtenerTodas() {
        return new ArrayList<>(bancoPreguntas);
    }
}
