package aed;

public class Pedido implements Comparable<Pedido>{
    private int _id;
    private int _cliente;
    private int _producto;

    Pedido(int id, int cliente, int producto){
        _id = id;
        _cliente = cliente;
        _producto = producto;
    }

    int id(){
        return _id;
    }

    @Override
    public int compareTo(Pedido otroPedido){
        int diff_id = this._id - otroPedido._id;
        int diff_cliente = this._cliente - otroPedido._cliente;
        int diff_producto = this._producto - otroPedido._producto;
        int diff_pedido = (diff_id != 0 ? diff_id : (diff_cliente != 0 ? diff_cliente : diff_producto ) );
        return diff_pedido;
    }

    @Override
    public String toString(){
        return "{id: " + String.valueOf(_id) + ", cliente: " + String.valueOf(_cliente) + ", producto: " + String.valueOf(_producto) + "}";
    }

    public Pedido copy(){
        Pedido nuevo = new Pedido(this._id, this._cliente, this._producto);
        return nuevo;
    }
}