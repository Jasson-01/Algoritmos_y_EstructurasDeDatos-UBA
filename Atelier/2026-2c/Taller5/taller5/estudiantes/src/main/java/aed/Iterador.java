package aed;

public interface Iterador<T> {
    boolean haySiguiente();
    boolean hayAnterior();
    T siguiente();
    T anterior();
}
