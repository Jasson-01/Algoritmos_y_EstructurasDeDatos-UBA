package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Agregar atributos privados del Conjunto
    private Nodo _raiz;
    private int _cardinal;
    private int _altura;

    private class Nodo {
        // Agregar atributos privados del Nodo
        T valor;
        Nodo izq;
        Nodo der;
        Nodo padre;

        // Crear Constructor del nodo
        Nodo(T v) {
            valor = v;
            izq = null;
            der = null;
            padre = null;
        }

    }

    public ABB() {
        _raiz = null;
        _cardinal = 0;
        _altura = 0;
    }

    public int cardinal() {
        return _cardinal;
    }

    public T minimo() {
        Nodo actual = _raiz;
        if (_raiz == null) {
            return null;
        }

        while (actual.izq != null) { // Al terminar el while, "actual" vendria a ser el Nodo que buscabamos osea, el
                                     // minimo ;D
            actual = actual.izq;
        }

        return actual.valor;

    }

    public T maximo() {
        Nodo actual = _raiz;
        if (_raiz == null) {
            return null;
        }
        while (actual.der != null) { // Al terminar el while, "actual" vendria a ser el Nodo que buscabamos osea, el
                                     // maximo ;D
            actual = actual.der;
        }
        return actual.valor;
    }

    public void insertar(T elem) {
        Nodo actual = _raiz;
        Nodo anterior = _raiz;
        Nodo nuevo = new Nodo(elem);
        int comparador = 0;

        if (_raiz == null) { // Si el arbol esta vacio
            _raiz = nuevo;
            _cardinal += 1;
            return;
        }

        // Si el arbol No esta vacio
        while (actual != null) { // Si el nodo "actual" llega a null significa que ya recorrio toda la lista
            comparador = elem.compareTo(actual.valor);
            anterior = actual;
            if (comparador == 0) { // Si tiene el mismo valor, no hacemos nada
                return;
            } else if (comparador < 0) { // Es menor, vamos a la izquierda
                actual = actual.izq;
            } else { // Es mayor, vamos a la derecha
                actual = actual.der;
            }
        }

        nuevo.padre = anterior;
        if (comparador < 0) {
            anterior.izq = nuevo;
        } else {
            anterior.der = nuevo;
        }

        _cardinal += 1;

    }

    public boolean pertenece(T elem) {
        Nodo actual = _raiz;

        while (actual != null) {
            int comparador = elem.compareTo(actual.valor); // Calculamos el entero, de la comparación del elemento que
                                                           // queremos encontrar "elem" con el Nodo actual donde estamos
                                                           // parados

            if (comparador == 0) {
                return true; // Lo pudimos encontrar!!! en que Nodo esta.
            } else if (comparador < 0) {
                actual = actual.izq; // Es menor, vamos a la izquierda
            } else {
                actual = actual.der; // Es mayor, vamos a la derecha
            }
        }
        return false;
    }

    public boolean esHoja(Nodo nodo) {
        if (nodo.izq == null && nodo.der == null) {
            return true;
        }
        return false;
    }

    public Nodo nodoAEliminar(T elem) {
        Nodo actual = _raiz;
        // El bucle viaja seguro mientras no se caiga del árbol

        while (actual != null) {
            int comparador = elem.compareTo(actual.valor);

            if (comparador == 0) {
                return actual;
            } else if (comparador < 0) {
                actual = actual.izq;
            } else {
                actual = actual.der;
            }
        }
        return actual;

    }

    public void eliminar(T elem) {
        Nodo actual = nodoAEliminar(elem);
        
        if (actual == null) {
            return;
        }
        
        // En esta etapa sabemos que el elem pertenece a un Nodo del Arbol
        // Caso: "El elemento es una hoja (no tiene hijos)"
        if (esHoja(actual)) {
            if (actual == _raiz) { // Si el nodo actual es la raiz
                _raiz = null;
            } else { // Busco en que lado esta el nodo (der o izq)
                int comparador = actual.valor.compareTo(actual.padre.valor);
                if (comparador < 0) {
                    actual.padre.izq = null;
                } else { // comparador > 0
                    actual.padre.der = null;
                }
            }
        }

    }

    public String toString() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public class ABB_Iterador {
        private Nodo _actual;

        public boolean haySiguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }

        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public ABB_Iterador iterador() {
        return new ABB_Iterador();
    }

}
