package aed;

public class ListaEnlazada<T> {
    // Completar atributos privados
    private Nodo primero;
    private Nodo ultimo;
    private int longitudLista;

    private class Nodo {
        // Completar
        T valor;
        Nodo sig;
        Nodo ant;
        Nodo (T v){
            valor = v;
        }
    }

    public ListaEnlazada() {
        primero = null;
        ultimo = null;
        longitudLista = 0;
    }

    public int longitud() {
        return longitudLista;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        nuevo.sig = primero;
        nuevo.ant = null;

        if ( primero == null) {
            ultimo = nuevo;
        } else {
            // Al viejo primer nodo le avisamos que ahora tiene a 'nuevo' antes que él
            primero.ant = nuevo;
        }
        primero = nuevo;
        longitudLista += 1;
    }

    public void agregarAtras(T elem) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public T obtener(int i) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public void eliminar(int i) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public void modificarPosicion(int indice, T elem) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        throw new UnsupportedOperationException("No implementada aun");
    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public class ListaIterador{
    	// Completar atributos privados

        public boolean haySiguiente() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
        
        public boolean hayAnterior() {
	        throw new UnsupportedOperationException("No implementada aun");
        }

        public T siguiente() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
        

        public T anterior() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public ListaIterador iterador() {
	    throw new UnsupportedOperationException("No implementada aun");
    }

}
