package aed;
import java.util.ArrayList;

public class SistemaPedidos {
    /*Completar con los atributos privados*/
    private ListaEnlazada<Pedido> pedidos; //  Pedidos por orden de llegada, donde se guardan los pedidos
    private ArrayList<Handle<Pedido>> pedidosPorIdReferencias; // Pedidos ordenados por ID
    
    public SistemaPedidos(){
         pedidos = new ListaEnlazada<>();
         pedidosPorIdReferencias = new ArrayList<>();
    }
    
    //Agregamos a la lista de pedidos el nuevo pedido, y enviamos su handle al metodo agregarOrdenado
    public void agregarPedido(Pedido pedido){
        Handle<Pedido> direccionDelPedidoNuevo = pedidos.agregarAtras(pedido);
        agregarOrdenado(direccionDelPedidoNuevo);
    }
    
    // Ingresa un handle y lo agregamos al arrayList de handles ordenado por su ID, ordenados de mayor a menor
    private void agregarOrdenado(Handle<Pedido> p){
        int i = 0;
        while (i < pedidosPorIdReferencias.size() && pedidosPorIdReferencias.get(i).valor().id() > p.valor().id()){
            i++;
        }
        pedidosPorIdReferencias.add(i,p);   
    }
    //pedidosPorIdReferencias.get(i).valor().id() , de donde vienen valor() y id() ? Son dos métodos de clases distintas, encadenados:
    // - pedidosPorIdReferencias.get(i) obtiene un Handle<Pedido> del ArrayList.
    // - .valor() viene de la interfaz Handle<T>. En HandleLE, devuelve el dato del nodo al 
    //            que  apunta; en este caso, un Pedido.
    // - .id() viene de la clase Pedido y devuelve el identificador de ese pedido.
    // Por eso, la expresión completa devuelve el ID del pedido apuntado por ese handle:


    
    // devuelve y elimina el pedido con menor ID, el arrayList esta ordenado de mayor a menor, para devolver el menor pedido en O(1), porque si el menor ID esta en la pos cero entonces tendriamos que desplazar los demas una posicion a la izquierda. 
    public Pedido proximoPedidoPorId(){
        Handle<Pedido> handleBuscado = pedidosPorIdReferencias.get(pedidosPorIdReferencias.size()-1);// primero guardo el handle pedido
        Pedido pedido = handleBuscado.valor(); //obtengo el pedido 
        
        handleBuscado.eliminar(); // lo elimina de pedidos
        pedidosPorIdReferencias.remove(pedidosPorIdReferencias.size()-1); // lo elimino del array

        return pedido;
    }
    
    // parecido al anterior pero aca modificamos la listaEnlazada(pedidos)
    public Pedido proximoPedidoPorLlegada(){
        Pedido pedidoQuerido = pedidos.obtenerPrimero(); // obtengo el primer pedido
        int i = 0; 
        while ( i < pedidosPorIdReferencias.size() && pedidosPorIdReferencias.get(i).valor().compareTo(pedidoQuerido) != 0){ // busco y elimino
            i++;
        }
        Handle<Pedido> handleBuscado = pedidosPorIdReferencias.get(i); // obtengo el handle del array
        handleBuscado.eliminar(); /// lo elimina de pedidos
        pedidosPorIdReferencias.remove(i); // lo elimina del array
        return pedidoQuerido;
    }
    // pedidosPorIdReferencias.get(i) obtiene el handle en la posición i
    // .valor() obtiene el Pedido al que apunta.

    public Pedido pedidoMenorId(){
        Handle<Pedido> pedidoQuerido = pedidosPorIdReferencias.get(pedidosPorIdReferencias.size()-1); // Primero busco el handle en el array
        return pedidoQuerido.valor();
    }

    public String obtenerPedidosEnOrdenDeLlegada(){
        String res = "[";
        Iterador<Pedido> iterador = pedidos.iterador();

        while(iterador.haySiguiente()){

            res += iterador.siguiente();    

            if(iterador.haySiguiente()){
                res +=  ", ";
            } 
            
        } 
        return res + "]";
    }

    public String obtenerPedidosOrdenadosPorId(){
        String res = "[";
        for(int i=pedidosPorIdReferencias.size()-1; i>=0; i--){
            if( i == 0 ){
                res += pedidosPorIdReferencias.get(i).valor();
            } else {
                res += pedidosPorIdReferencias.get(i).valor() + ", ";
            }
        }
        return  res + "]"; 
    }
}
