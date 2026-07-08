package aed;

import aed.ListaEnlazada.ListaIterador;

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
        Nodo actual;
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
    
    // 2da forma:
//     public void eliminar(int i) {
//     if (i == 0) {
//         // CASO 1: Eliminar el primero
//         primero = primero.sig;
//         if (primero != null) {
//             primero.ant = null; // El nuevo primero no tiene a nadie atrás
//         } else {
//             ultimo = null; // Si se quedó vacía, último también es null
//         }
//     } 
//     else if (i == longitudLista - 1) {
//         // CASO 2: Eliminar el último
//         ultimo = ultimo.ant;
//         if (ultimo != null) {
//             ultimo.sig = null; // El nuevo último no tiene a nadie adelante
//         } else {
//             primero = null; // Si se quedó vacía
//         }
//     } 
//     // CASOS GENERALES (Nodos del medio)
//     else if (i < longitudLista / 2) {
//         Nodo actual = primero;
//         Nodo prev = primero;
//         for (int j = 0; j < i; j++) {
//             prev = actual;
//             actual = actual.sig;
//         }
//         prev.sig = actual.sig;     // Puente de ida
//         actual.sig.ant = prev;     // ¡Acá está el cambio! Puente de vuelta
//     } 
//     else {
//         Nodo actual = ultimo;
//         Nodo post = ultimo;
//         for (int j = longitudLista - 1; j > i; j--) {
//             post = actual;
//             actual = actual.ant;
//         }
//         post.ant = actual.ant;     // Puente de vuelta
//         actual.ant.sig = post;     // ¡Acá está el cambio! Puente de ida
//     }

//     longitudLista -= 1; // Se resta una sola vez al final
//   }


    public void modificarPosicion(int indice, T elem) {
        
        // Primero encontramos el Nodo a cambiar el valor
        Nodo actual;
        if ( indice < longitudLista / 2) {
            actual = primero;
            for(int j=0; j<indice; j++) {
               actual = actual.sig;
            }
        } else {
            actual = ultimo;
            for(int j = longitudLista - 1; j>indice; j--){
               actual = actual.ant;
            }
        }

        // 2do cambiamos el valor del nodo "actual"
        actual.valor = elem;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        Nodo actual = lista.primero; 
        while ( actual != null){
            agregarAtras(actual.valor);
            actual = actual.sig;
        }
    }

    @Override
    public String toString() {
        
        if(primero == null){
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

    // DATO PRO:
    // Tu solución con String += está perfecta para el taller y va a pasar. Pero si estuviéramos procesando listas con millones de datos, concatenar Strings con el signo + adentro de un bucle es lento porque Java crea un objeto String nuevo en el Heap en cada vuelta. Para optimizar eso, en materias más avanzadas se usa una herramienta de Java llamada "StringBuilder", que es una cajita que va acumulando el texto de forma ultra eficiente. ¡Te lo tiro como dato por si querés investigarlo después!

    public class ListaIterador {
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
