package aed;

public class ListaEnlazada<T> {
    private Nodo primero;
    private Nodo ultimo;
    private int longitud;
    // Completar atributos privados

    private class Nodo {
        // Completar
        T valor;
        Nodo sig;
        Nodo ant;

        Nodo(T v){
            valor = v;
        }
    }

    public ListaEnlazada() {
        primero = null;
        ultimo = null;
        longitud = 0;
    }

    public int longitud() {
        return longitud;
    }

    public void agregarAdelante(T elem) {

        Nodo nuevo = new Nodo(elem);
        nuevo.sig = primero;
        nuevo.ant = null;

        if (primero == null) {
            ultimo = nuevo;
        } else {
            
            primero.ant = nuevo;
        }
        primero = nuevo;
        longitud += 1;
    }

    public void agregarAtras(T elem) {

        Nodo nuevo = new Nodo(elem);
        nuevo.sig = null;

        if (ultimo == null) {
            nuevo.ant = null; 
            primero = nuevo; 
        } else {
            nuevo.ant = ultimo; 
            ultimo.sig = nuevo; 
        }
        ultimo = nuevo; 
        longitud += 1;
    }

    public T obtener(int i) {
        
        if (i < longitud / 2) {
            Nodo actual = primero;
            for (int j = 0; j < i; j++) {
                actual = actual.sig;
            }
            return actual.valor;
        } else { 
            Nodo actual = ultimo;
            for (int j = longitud - 1; j > i; j--) {
                actual = actual.ant;
            }
            return actual.valor;
        }
    }

    public void eliminar(int i) {
        
        //Busco el nodo a eliminar
        Nodo actual = null; 
        if (i < longitud / 2) {
            actual = primero;
            for (int j = 0; j < i; j++) {
                actual = actual.sig;
            }
        } else {
            actual = ultimo;
            for (int j = longitud - 1; j > i; j--) {
                actual = actual.ant;
            }
        }
        if (actual.ant == null) { // esta parte recorre de izquierda a derecha de la lista 
            primero = actual.sig;
        } else {
            actual.ant.sig = actual.sig; 
        }
        if (actual.sig == null) {  // Y esta de derecha a izquierda
            ultimo = actual.ant;
        } else {
            actual.sig.ant = actual.ant;
        }
        longitud -= 1;
    }

    public void modificarPosicion(int indice, T elem) {
        // Primero encontramos el Nodo a cambiar el valor
        Nodo actual = null; // le damos un valor inicial por defecto, lo cambiamos en el "if"
        if (indice < longitud / 2) {
            actual = primero;
            for (int j = 0; j < indice; j++) {
                actual = actual.sig;
            }
        } else {
            actual = ultimo;
            for (int j = longitud - 1; j > indice; j--) {
                actual = actual.ant;
            }
        }
        actual.valor = elem;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {

        Nodo actual = lista.primero;

        while (actual != null) {
            agregarAtras(actual.valor);
            actual = actual.sig;
        }
    }
    
    @Override
    public String toString() {

        if (primero == null) {
            return "[]";
        }
        Nodo actual = primero;

        String elements = "";
        while (actual != null) {

            if (actual.sig == null) {
                elements += actual.valor;
            } else {
                elements += actual.valor + ", ";
            }
            actual = actual.sig;
        }
        return "[" + elements + "]";
    }
    

    public class ListaIterador{
    	// Completar atributos privados
        private Nodo dedito;

        public ListaIterador(){
             dedito = primero;
        }

        public boolean haySiguiente() {
	        return dedito != null;
        }
        
        public boolean hayAnterior() {

	        if (primero == null) {
                return false;
            }

            if (dedito == primero) {
                return false;
            }
            return true;
        }

        public T siguiente() {

            T valorActual = dedito.valor;
            dedito = dedito.sig;
            return valorActual;
        }
        

        public T anterior() {

            T valorActual = null;

            if (dedito == null) {
                valorActual = ultimo.valor;
                dedito = ultimo;
            } else {
                valorActual = dedito.ant.valor;
                dedito = dedito.ant;
            }
            return valorActual;
        }
    }

    public ListaIterador iterador() {
	    return new ListaIterador();
    }

}

