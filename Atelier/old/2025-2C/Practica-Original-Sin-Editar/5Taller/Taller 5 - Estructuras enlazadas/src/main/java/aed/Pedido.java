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

    @Override
    public int compareTo(Pedido otroPedido){
        return this._id - otroPedido._id;
    }

    @Override
    public String toString(){
        return String.valueOf(_id);
    }
}