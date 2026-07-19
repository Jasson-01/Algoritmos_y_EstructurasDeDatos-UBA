package aed;

public class SistemaPedidos {
    /* COMPLETAR atributos privados: ¿cómo se representan los pedidos? */
    ABB<Pedido> arbolDePedidos; // Arbol de datos(Pedidos)
    ListaEnlazada<ABB<Pedido>.HandleABB> direccionesDePedidos; // lista de direcciones

    public SistemaPedidos(){
        arbolDePedidos = new ABB<>();
        direccionesDePedidos = new ListaEnlazada<>();
    }

    public void agregarPedido(Pedido pedido){
        //Insertamos el pedido en el arbol y guardamos su dirección en el Handle
       ABB<Pedido>.HandleABB direccionDelPedidoNuevo = arbolDePedidos.insertar(pedido); // Hace dos acciones

       //Agregamos la direccion del nuevo pedido al final de la lista Enlanzada
       direccionesDePedidos.agregarAtras(direccionDelPedidoNuevo);
    }
    
    //proximoPedido -> Es el que mas tiempo lleva esperando (Osea vendria a ser el primer Nodo en nuestra lista enlazada)
    public Pedido proximoPedido(){
        // Primero obtenemos la direccion del pedido
        ABB<Pedido>.HandleABB direccionDelPedidoProximo = direccionesDePedidos.obtener(0);

        // Buscamos el pedido en el arbol usando el Handle
        Pedido pedido = direccionDelPedidoProximo.valor();

        //Eliminamos el Pedido del arbol
        direccionDelPedidoProximo.eliminar();
        //Lo elimino tambien de la lista de Handles
        direccionesDePedidos.eliminar(0);

        return pedido;
    }

    public Pedido pedidoMenorId(){
        return arbolDePedidos.minimo();
    }

    public String obtenerPedidosEnOrdenDeLlegada(){
        // return direccionesDePedidos.toString();
        String res = "[";
        for(int i=0; i<direccionesDePedidos.longitud(); i++){
             ABB<Pedido>.HandleABB direccionDelPedidoActual = direccionesDePedidos.obtener(i);
             if(i == direccionesDePedidos.longitud() - 1){
                res += direccionDelPedidoActual.valor(); 
             } else {
                res += direccionDelPedidoActual.valor() + ", ";
             }
        }
        res += "]";
        return res;
    }

    public String obtenerPedidosOrdenadosPorId(){
        return arbolDePedidos.toString();
    }
}