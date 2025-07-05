package ec.edu.ups.Util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MensajeInternacionalizacionHandler {
    private ResourceBundle bundle;
    private Locale locale;

    public MensajeInternacionalizacionHandler() {
        this("es", "EC");
    }

    // Constructor con idioma y país
    public MensajeInternacionalizacionHandler(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("mensajes", locale);
    }

    public String getMensaje(String key) {
        return bundle.getString(key);
    }

    public void setLenguaje(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("mensajes", locale);
    }

    public void cambiarIdioma(String lenguaje, String pais) {
        setLenguaje(lenguaje, pais);
    }

    public Locale getLocale() {
        return locale;
    }

    // Método nuevo para asignar directamente Locale
    public void setLocale(Locale nuevoLocale) {
        this.locale = nuevoLocale;
        this.bundle = ResourceBundle.getBundle("mensajes", this.locale);
    }
}
