package aed;
import java.util.ArrayList;


public class HandlerTransacciones {
    private Heap<ListaEnlazada<Transaccion>.Handle> heap;

    // Constructor
    public HandlerTransacciones(ListaEnlazada<Transaccion> lista){ // Construimos el heap a partir de una lista enlazada de transacciones. 
        heap = new Heap<ListaEnlazada<Transaccion>.Handle>(); // O(1)
        
        ArrayList<ListaEnlazada<Transaccion>.Handle> handles = new ArrayList<>();
        for(ListaEnlazada<Transaccion>.Handle i = lista.obtenerPrimero(); i != null; i = i.siguiente()){ // Hasta obtener un handle nulo - O(nb)
            ListaEnlazada<Transaccion>.Handle h = i; // O(1)
            handles.add(h); // O(1)
        }

        heap.heapify(handles); // O(nb) - Construye el heap a partir de los handles de las transacciones usando heapify.
    }

    // O(1)
    public ListaEnlazada<Transaccion>.Handle verRaiz(){
        return heap.verRaiz(); 
    }



    // Mantiene 0(log n) -> LO NECESITO PARA EL HACKEO.
    public ListaEnlazada<Transaccion>.Handle extraerRaiz( ListaEnlazada<Transaccion> t){

        return heap.extraerRaiz(); // O(log n) - Extrae la raiz del heap y elimina el nodo de la lista enlazada.
    }

}
