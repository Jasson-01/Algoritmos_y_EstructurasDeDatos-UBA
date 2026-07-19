package aed;

//import java.util.*;

//import aed.ABB.ABB_Iterador;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Agregar atributos privados del Conjunto
    private Nodo _raiz;
    private int _cardinal;

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
        Nodo anterior = _raiz; // Vamos a usarlo como el padre del Nodo a insertar
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

    // *** Aqui comienzan los metodos auxiliares del metodo eliminar() ***

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
            if (actual == _raiz) { // Si el nodo actual(el nodo a eliminar) es la raiz, Aqui compara Nodos No
                                   // variables de tipo T por eso funciona usar == (vendria a ser como comparar
                                   // direcciones :D)
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

        if (tieneUnSoloHijo(actual)) {
            if (actual == _raiz) { // Si el nodo actual(el nodo a eliminar) es la raiz, Aqui compara Nodos, No
                                   // variables de tipo T por eso funciona usar == (vendria a ser como comparar
                                   // direcciones :D)
                if (actual.izq != null) { // El hijo izquierdo No es Null
                    _raiz = actual.izq; // la raiz pasa a ser el hijo izquierdo
                    _raiz.padre = null;
                } else { // El hijo derecho No es Null
                    _raiz = actual.der; // la raiz pasa a ser el hijo derecho
                    _raiz.padre = null;
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

        if (actual == null) { // Si el arbol esta vacio
            return; // No existe, devolvemos asi como esta, sin avanzar mas en el codigo (salimos
                    // del metodo)
        }

        // Eligimos entre el caso 1 o el caso 2
        if (esHoja(actual) || tieneUnSoloHijo(actual)) {
            eliminarNodoGeneral(actual);
        }
        // Si no es uno de los casos anteriores, entonces es el caso 3 por descarte
        else {
            Nodo reemplazoMinimoDer = minimoNodoDer(actual);
            actual.valor = reemplazoMinimoDer.valor; // el valor del minimo lo pasamos al nodo.valor a reemplazar
            eliminarNodoGeneral(reemplazoMinimoDer); // eliminamos el nodo minimo que nos ayudo a reemplazar el nodo a
                                                     // eliminar
        }

        _cardinal--;
    }

    public String toString() {
        String textoAImprimir = "{";

        // Creamos nuestro propio iterador que se ubica en el arbol actual
        ABB_Iterador iterador = this.iterador();

        // Recorremos el arbol usando el iterador
        while (iterador.haySiguiente()) {
            T elemActual = iterador.siguiente();
            textoAImprimir += elemActual;

            //  Si hay más elementos después de este, agregamos la coma
            if (iterador.haySiguiente()) {
                textoAImprimir += ",";
            }
        }

        textoAImprimir += "}";
        return textoAImprimir;
    }

    // ** Segunda forma
    // public void inOrder(Nodo actual, ArrayList<T> lista){
    // if (actual == null){
    // return;
    // }
    // inOrder(actual.izq, lista);
    // lista.add(actual.valor);
    // inOrder(actual.der, lista);
    // }

    // public String toString() {
    // ArrayList<T> listaDeElementos = new ArrayList<>(); // Es mejor usar ArrayList
    // que Arreglos
    // Nodo actual = _raiz;
    // inOrder(actual, listaDeElementos);

    // String textoAImprimir = "{";
    // for(int i=0; i<listaDeElementos.size(); i++){
    // if( i == listaDeElementos.size() - 1 ){
    // T elem = listaDeElementos.get(i);
    // textoAImprimir += elem;
    // } else {
    // T elem = listaDeElementos.get(i);
    // textoAImprimir += elem + ",";
    // }
    // }
    // textoAImprimir += "}";
    // return textoAImprimir;
    // }

    // haySiguiente(): Devuelve true si todavía quedan números por visitar.
    // siguiente(): Devuelve el número actual y avanza el control remoto al próximo
    // elemento en orden.

    public class ABB_Iterador implements Iterador<T> {
        private Nodo actual;

        public ABB_Iterador() {
            actual = _raiz;

            if (actual != null) {
                while (actual.izq != null) {
                    actual = actual.izq;
                }
            }
            // Si actual es NULL -> ¿Cuál es la magia de esto? Que cuando el usuario del
            // iterador intente arrancar y pregunte: "Che, ¿haySiguiente()?", tu método va a
            // ver que actual es null y le va a devolver false
        }

        public boolean haySiguiente() {
            if (actual != null) {
                return true;
            }
            return false;
        }

        public T siguiente() {
            T valorQuerido = actual.valor; // Devuelvo el valor del nodo querido

            // Ahora me muevo al siguiente Nodo
            if (actual.der != null) { // Si hay nodo derecho -> Subo por el lado izquierdo (menor -> padre -> mayor)
                actual = actual.der;
                while (actual.izq != null) {
                    actual = actual.izq;
                }
            } else { // Subo por el lado derecho -> mi padre ya fue visitado ( el valor del padre es
                     // menor que el hijo) -> apuntamos al abuelo
                while (actual.padre != null && actual.padre.der == actual) { // Me dice: ¿De qué brazo de mi papá vengo
                                                                             // colgado?"
                    actual = actual.padre; // Sigo subiendo porque el padre ya fue visitado
                }
                // Al salir del while significa que ahora actaul es el hijo izquierdo
                actual = actual.padre;
            }
            return valorQuerido;
        }
    }

    public ABB_Iterador iterador() {
        return new ABB_Iterador();
    }

}
