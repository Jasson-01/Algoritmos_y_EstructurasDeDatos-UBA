package aed;

public class ListaEnlazada<T> {

    private Nodo primero;
    private Nodo ultimo;
    private int longitudLista;
    // Completar atributos privados

    private class Nodo {
        // Completar
        T valor;
        Nodo sig;
        Nodo ant;

        Nodo(T v) {
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

        if (primero == null) {
            ultimo = nuevo;
        } else {
            // Al viejo primer nodo le avisamos que ahora tiene a 'nuevo' antes que él
            primero.ant = nuevo;
        }
        primero = nuevo;
        longitudLista += 1;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        nuevo.sig = null; // el nuevo vendria a ser el ultimo nodo por eso el sig de nuevo es null

        if (ultimo == null) {
            nuevo.ant = null; // No hay nadie atrás de el
            primero = nuevo; // aqui primero y ultimo comparte el primer nodo ya que no hay mas
        } else {
            nuevo.ant = ultimo; // El anterior del nuevo es el viejo último(porque el viejo ultimo.sig apunta a
                                // null )
            ultimo.sig = nuevo; // El viejo último ahora engancha su sig al nuevo
        }
        ultimo = nuevo; // El control del último vagón pasa oficialmente a nuevo
        longitudLista += 1;
    }

    public T obtener(int i) {
        // Si el elemento esta en la primera mitad de la lista enlazada
        if (i < longitudLista / 2) {
            Nodo actual = primero;
            for (int j = 0; j < i; j++) {
                actual = actual.sig;
            }
            return actual.valor;
        } else { // Si el elemento esta en la segunda mitad de la lista enlazada
            Nodo actual = ultimo;
            for (int j = longitudLista - 1; j > i; j--) {
                actual = actual.ant;
            }
            return actual.valor;
        }
    }

    public void eliminar(int i) {
        // primero encontremos el Nodo a eliminar
        Nodo actual = null; // le damos un valor inicial por defecto, lo cambiamos en el "if"
        if (i < longitudLista / 2) {
            actual = primero;
            for (int j = 0; j < i; j++) {
                actual = actual.sig;
            }
        } else {
            actual = ultimo;
            for (int j = longitudLista - 1; j > i; j--) {
                actual = actual.ant;
            }
        }

        // Primer caso de Izquiera a derecha
        if (actual.ant == null) { // Si "actual" es el primer elemento(Nodo) de la lista
            primero = actual.sig;
        } else {
            actual.ant.sig = actual.sig; // AL Nodo de la izquierda le decimos que se saltee a "actual" por el que le
                                         // sigue.
        }

        if (actual.sig == null) { // Si "actual" es el ultimo elemento(Nodo) de la lista
            ultimo = actual.ant;
        } else {
            actual.sig.ant = actual.ant;
        }

        longitudLista -= 1;
    }

    public void modificarPosicion(int indice, T elem) {
        // Primero encontramos el Nodo a cambiar el valor
        Nodo actual = null; // le damos un valor inicial por defecto, lo cambiamos en el "if"
        if (indice < longitudLista / 2) {
            actual = primero;
            for (int j = 0; j < indice; j++) {
                actual = actual.sig;
            }
        } else {
            actual = ultimo;
            for (int j = longitudLista - 1; j > indice; j--) {
                actual = actual.ant;
            }
        }

        // 2do cambiamos el valor del nodo "actual"
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
                // Si es el último vagón, NO le ponemos coma al final
                elements += actual.valor;
            } else {
                // Si no es el último, le ponemos la coma y un espacio
                elements += actual.valor + ", ";
            }
            actual = actual.sig;
        }
        return "[" + elements + "]";
    }

    public class ListaIterador {
        // Completar atributos privados
        private Nodo dedito;

        public ListaIterador() { // Constructor
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
            // No es necesario ahora, pero si para mas adelante ;D
            if (dedito == null) {
                throw new java.util.NoSuchElementException("No hay más elementos para recorrer.");
            }
            // Con esto es suficiente ;D
            T valorActual = dedito.valor;
            dedito = dedito.sig;
            return valorActual;
        }

        public T anterior() {
            // No es necesario ahora, pero si para mas adelante ;D
            if (!hayAnterior()) {
                throw new java.util.NoSuchElementException();
            }

            T valorActual = null; // le damos un valor inicial por defecto, lo cambiamos en el "if"
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
