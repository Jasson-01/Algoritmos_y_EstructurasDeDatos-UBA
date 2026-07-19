package aed;
import java.util.ArrayList;

public class HandlerUsuarios {
    private Heap<Usuario> heap;  
    private Handle handle; 

    private class Handle{
        private ArrayList<Integer> handle;

        Handle(){
            handle = new ArrayList<>();
        }

        void add(int i){
            this.handle.add(i-1); // O(1)
        }

        void actualizarHandle(int idUsuario, int indice){
            this.handle.set(idUsuario,indice); // O(1)
        }
    }

    // Constructor
    public HandlerUsuarios(int n){
        heap = new Heap<Usuario>(); //O(1)
        handle = new Handle();

        for(int i = 1; i <= n; i++){ //O(P)

            /* Explicación: Si bien insertar usario a usuario en un heap es O(P log P),
             * como para este caso se insertan todos los usuarios con el mismo
             * patrimonio inicial, que es 0, y en orden de id creciente, se
             * logra tener una complejidad de O(P).
             */

            Usuario u = new Usuario(i);
            heap.insertar(u); 
            handle.add(i);
        }
    }
    
    public Usuario verRaiz(){
        return heap.verRaiz(); //O(1)
    }

    public void actualizarPatrimonio(int id, int monto){
        heap.get(handle.handle.get(id-1)).sumar(monto); //O(1) 
        actualizarHeap(id);//O(log P)
    }

    private void actualizarHeap(int id){ //O(log P)
        ArrayList<Tupla<Usuario>>  t = new ArrayList<Tupla<Usuario>>();
        int index = handle.handle.get(id-1); 
        t = heap.siftUp(index);//O(log P)
        
        for(int i = 0; i < t.size();i++){ //O(log P)
            handle.actualizarHandle(t.get(i).valor.id() - 1,t.get(i).indice);
        }

        t = heap.siftDown(index);//O(log P)
        for(int i = 0; i < t.size();i++){ //O(log P)
            handle.actualizarHandle(t.get(i).valor.id() - 1,t.get(i).indice);
        }
    }

}
