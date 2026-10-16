package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> {
    // Agregar atributos privados del Conjunto
    private Nodo raiz;
    private int cardinal;
    private int altura;

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
        raiz = null;
        cardinal = 0;
        altura = 0;
    }

    public int cardinal() {
        return cardinal;
    }

    public T minimo() {
        Nodo puntero = raiz;

        if (raiz == null) {
            return null;
        }

        while (puntero.izq != null) {
            puntero = puntero.izq;
        }

        return puntero.valor;
    }

    public T maximo() {
        Nodo puntero = raiz;

        if (raiz == null) {
            return null;
        }

        while (puntero.der != null) {
            puntero = puntero.der;
        }

        return puntero.valor;
    }

    public void insertar(T elem) {
        Nodo actual = raiz;
        Nodo anterior = raiz;
        Nodo nuevo = new Nodo(elem);

        if (raiz == null) { // Si el arbol esta vacio
            raiz = nuevo;
            cardinal += 1;
            return;
        }

        // Si el arbol no esta vacio
        // me muevo por el arbol
        while (actual != null) {
            anterior = actual;
            if (nuevo.valor.compareTo(actual.valor) == 0) { // caso: si el nodo ya esta en el arbol
                return;
            } else if (nuevo.valor.compareTo(actual.valor) > 0) { // nuevo.valor > actual.valor
                actual = actual.der;
            } else { // nuevo.valor < actual.valor -> me muevo a la izq
                actual = actual.izq;
            }
        }

        // apunto el nuevo al nodo anterior
        nuevo.padre = anterior;
        // decido a que lado del nodo "anterior" va a ir el nuevo
        if (anterior.valor.compareTo(nuevo.valor) > 0) { // anterior.valor > nuevo.valor -> lado izq
            anterior.izq = nuevo;
        } else {
            anterior.der = nuevo;
        }
        cardinal += 1;
    }

    public boolean pertenece(T elem) {
        Nodo actual = raiz;

        if (raiz == null) {
            return false;
        }

        while (actual != null) {
            if (elem.compareTo(actual.valor) == 0) { // caso: si el nodo ya esta en el arbol
                return true;
            } else if (elem.compareTo(actual.valor) > 0) { // elem > actual.valor
                actual = actual.der;
            } else { // elem < actual.valor -> me muevo a la izq
                actual = actual.izq;
            }
        }
        return false;
    }

    // Metodos auxiliares del metodo eliminar()

    public boolean tieneUnSoloHijo(Nodo nodo) {
        if ((nodo.izq != null && nodo.der == null) || (nodo.izq == null && nodo.der != null)) {
            return true;
        }
        return false;
    }

    public boolean esHoja(Nodo nodo) {
        if (nodo.izq == null && nodo.der == null) {
            return true;
        }
        return false;
    }

    public Nodo minimoNodoDer(Nodo actual) {
        Nodo nodoDerecho = actual.der;
        while (nodoDerecho.izq != null) {
            nodoDerecho = nodoDerecho.izq;
        }
        return nodoDerecho; // Encontramos al nodo minimo, Aqui la variable nodoDerecho se connvierte en el
                            // nodo minimo que buscabamos.
    }

    public void eliminarNodoGeneral(Nodo actual) {

        if (esHoja(actual)) {
            if (actual == raiz) { // Si el nodo actual(el nodo a eliminar) es la raiz, Aqui compara Nodos No
                                   // variables de tipo T por eso funciona usar == (vendria a ser como comparar
                                   // direcciones :D)
                raiz = null;
            } else { // Busco en que lado esta el nodo (der o izq)
                int comparador = actual.valor.compareTo(actual.padre.valor);
                if (comparador < 0) {
                    actual.padre.izq = null;
                } else { // comparador > 0
                    actual.padre.der = null;
                }
            }
        }

        if (tieneUnSoloHijo(actual)) {
            if (actual == raiz) { // Si el nodo actual(el nodo a eliminar) es la raiz, Aqui compara Nodos, No
                                  // variables de tipo T por eso funciona usar == (vendria a ser como comparar
                                  // direcciones :D)
                if (actual.izq != null) { // El hijo izquierdo No es Null
                    raiz = actual.izq; // la raiz pasa a ser el hijo izquierdo
                    raiz.padre = null;
                } else { // El hijo derecho No es Null
                    raiz = actual.der; // la raiz pasa a ser el hijo derecho
                    raiz.padre = null;
                }
            } else { // Busco en que lado esta el nodo (der o izq)
                if (actual.izq != null) { // El hijo izquierdo No es Null
                    Nodo nieto = actual.izq;
                    nieto.padre = actual.padre; // El nieto apunta al abuelo
                    if (actual.padre.izq == actual) { // Le preguntás al abuelo si su brazo izquierdo sostenía al
                                                      // eliminado(actual)
                        actual.padre.izq = nieto; // El abuelo apunta al nieto
                    } else {
                        actual.padre.der = nieto;
                    }

                } else { // El hijo derecho No es Null
                    Nodo nieto = actual.der;
                    nieto.padre = actual.padre; // El nieto apunta al abuelo
                    if (actual.padre.izq == actual) { // Le preguntás al abuelo si su brazo izquierdo sostenía al
                                                      // eliminado(actual)
                        actual.padre.izq = nieto; // El abuelo apunta al nieto
                    } else {
                        actual.padre.der = nieto;
                    }
                }
            }
        }
    }

    public Nodo nodoAEliminar(T elem) { // parecido a pertenece()
        Nodo actual = raiz;

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

        if (actual == null) { // Si el arbol esta vacio
            return;
        }

        if (esHoja(actual) || tieneUnSoloHijo(actual)) {
            eliminarNodoGeneral(actual);
        }

        else {
            Nodo reemplazoMinimoDer = minimoNodoDer(actual);
            actual.valor = reemplazoMinimoDer.valor; // el valor del minimo lo pasamos al nodo.valor a reemplazar
            eliminarNodoGeneral(reemplazoMinimoDer); // eliminamos el nodo minimo que nos ayudo a reemplazar el nodo a eliminar
        }

        cardinal--;
    }

    public String toString() {
        String textoAImprimir = "{";
        ABB_Iterador iterador = iterador(); // no usamos new, porque ya lo usamos dentro de iterador()
        while (iterador.haySiguiente()) {
            T elementoActual = iterador.siguiente();
            textoAImprimir += elementoActual;

            if (iterador.haySiguiente()) {
                textoAImprimir += ",";
            }
        }

        textoAImprimir += "}";
        return textoAImprimir;
    }

    public class ABB_Iterador {
        private Nodo actual;

        public ABB_Iterador() {
            actual = raiz;

            if (actual != null) {
                while (actual.izq != null) {
                    actual = actual.izq; // comenzamos del valor minimo del arbol
                }
            }
        }

        public boolean haySiguiente() {
            if (actual != null) {
                return true;
            }
            return false;
        }

        public T siguiente() {
            T valorQuerido = actual.valor;

            if (actual.der != null) { // Si tiene hijo derecho
                actual = actual.der; // nos movemos al hijo derecho del actual
                while (actual.izq != null) { // si el hijo derecho de actual, su izquierda tiene hijo nos movemos en
                                             // ellas hasta su hijo mas inferior izquierda
                    actual = actual.izq; // Este es el siguiente Nodo
                }
            } else { // Si no , subimos por el padre
                while (actual.padre != null && actual.padre.der == actual) {
                    actual = actual.padre;
                }
                actual = actual.padre;
            }
            return valorQuerido;
        }
    }

    public ABB_Iterador iterador() {
        return new ABB_Iterador();
    }

}
