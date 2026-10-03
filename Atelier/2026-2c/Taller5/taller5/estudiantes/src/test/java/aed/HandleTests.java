package aed;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class HandleTests {
    @Test
    void nuevo_sistema() {
        SistemaPedidos sistema = new SistemaPedidos();
        assertEquals("[]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("[]", sistema.obtenerPedidosOrdenadosPorId());
    }

    @Test
    void insertar_y_verificar_con_handle() {
        ListaEnlazada<Integer> listaEnlazada = new ListaEnlazada<Integer>();
        Handle<Integer> handle1 = listaEnlazada.agregarAtras(50);
        Handle<Integer> handle2 = listaEnlazada.agregarAtras(30);
        Handle<Integer> handle3 = listaEnlazada.agregarAtras(70);
        
        assertEquals(50, handle1.valor());
        assertEquals(30, handle2.valor());
        assertEquals(70, handle3.valor());
        
        assertTrue(listaEnlazada.pertenece(50));
        assertTrue(listaEnlazada.pertenece(30));
        assertTrue(listaEnlazada.pertenece(70));
    }

    @Test
    void eliminar_elemento_con_handle() {
        ListaEnlazada<Integer> ls = new ListaEnlazada<Integer>();
        Handle<Integer> handle1 = ls.agregarAtras(50);
        ls.agregarAtras(30);
        ls.agregarAtras(70);
        
        assertTrue(ls.pertenece(50));
        assertEquals(3, ls.longitud());

        handle1.eliminar();

        assertFalse(ls.pertenece(50));
        assertTrue(ls.pertenece(30));
        assertTrue(ls.pertenece(70));
        assertEquals(2, ls.longitud());
    }

    @Test
    void eliminar_varios_elementos_con_handle() {
        ListaEnlazada<Integer> ls = new ListaEnlazada<Integer>();
        ls.agregarAtras(50);
        Handle<Integer> handle2 = ls.agregarAtras(30);
        ls.agregarAtras(70);
        ls.agregarAtras(20);
        Handle<Integer> handle5 = ls.agregarAtras(60);
        
        assertEquals(5, ls.longitud());
        
        handle2.eliminar();
        assertFalse(ls.pertenece(30));
        assertEquals(4, ls.longitud());
        
        handle5.eliminar();
        assertFalse(ls.pertenece(60));
        assertEquals(3, ls.longitud());
        
        assertTrue(ls.pertenece(50));
        assertTrue(ls.pertenece(20));
        assertTrue(ls.pertenece(70));
    }

    @Test
    void agregar_pedido_al_sistema() {
        SistemaPedidos sistema = new SistemaPedidos();
        Pedido p1 = new Pedido(100, 1, 1);
        
        sistema.agregarPedido(p1);
        
        assertEquals("[{id: 100, cliente: 1, producto: 1}]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("[{id: 100, cliente: 1, producto: 1}]", sistema.obtenerPedidosOrdenadosPorId());
        assertEquals(p1, sistema.pedidoMenorId());
    }

    @Test
    void agregar_multiples_pedidos_mantiene_orden() {
        SistemaPedidos sistema = new SistemaPedidos();
        Pedido p1 = new Pedido(50, 1, 1);
        Pedido p2 = new Pedido(30, 2, 2);
        Pedido p3 = new Pedido(70, 3, 3);
        
        sistema.agregarPedido(p1);
        sistema.agregarPedido(p2);
        sistema.agregarPedido(p3);
        
        assertEquals("[{id: 50, cliente: 1, producto: 1}, {id: 30, cliente: 2, producto: 2}, {id: 70, cliente: 3, producto: 3}]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("[{id: 30, cliente: 2, producto: 2}, {id: 50, cliente: 1, producto: 1}, {id: 70, cliente: 3, producto: 3}]", sistema.obtenerPedidosOrdenadosPorId());
    }

    @Test
    void pedido_menor_id_correcto() {
        SistemaPedidos sistema = new SistemaPedidos();
        Pedido p1 = new Pedido(50, 1, 1);
        Pedido p2 = new Pedido(30, 2, 2);
        Pedido p3 = new Pedido(70, 3, 3);
        Pedido p4 = new Pedido(10, 4, 4);
        
        sistema.agregarPedido(p1);
        sistema.agregarPedido(p2);
        sistema.agregarPedido(p3);
        sistema.agregarPedido(p4);
        
        assertEquals(p4, sistema.pedidoMenorId());
    }

    @Test
    void proximo_pedido_por_llegada_elimina_primero() {
        SistemaPedidos sistema = new SistemaPedidos();
        Pedido p1 = new Pedido(50, 1, 1);
        Pedido p2 = new Pedido(30, 2, 2);
        Pedido p3 = new Pedido(70, 3, 3);
        
        sistema.agregarPedido(p1);
        sistema.agregarPedido(p2);
        sistema.agregarPedido(p3);
        
        Pedido proximo = sistema.proximoPedidoPorLlegada();
        
        assertEquals(p1, proximo);
        assertEquals("[{id: 30, cliente: 2, producto: 2}, {id: 70, cliente: 3, producto: 3}]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("[{id: 30, cliente: 2, producto: 2}, {id: 70, cliente: 3, producto: 3}]", sistema.obtenerPedidosOrdenadosPorId());
    }

    @Test
    void proximo_pedido_por_id_elimina_segundo() {
        SistemaPedidos sistema = new SistemaPedidos();
        Pedido p1 = new Pedido(50, 1, 1);
        Pedido p2 = new Pedido(30, 2, 2);
        Pedido p3 = new Pedido(70, 3, 3);
        
        sistema.agregarPedido(p1);
        sistema.agregarPedido(p2);
        sistema.agregarPedido(p3);
        
        Pedido proximo = sistema.proximoPedidoPorId();
        
        assertEquals(p2, proximo);
        assertEquals("[{id: 50, cliente: 1, producto: 1}, {id: 70, cliente: 3, producto: 3}]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("[{id: 50, cliente: 1, producto: 1}, {id: 70, cliente: 3, producto: 3}]", sistema.obtenerPedidosOrdenadosPorId());
    }

    @Test
    void multiples_proximo_pedido_por_llegada_mantiene_coherencia() {
        SistemaPedidos sistema = new SistemaPedidos();
        Pedido p1 = new Pedido(100, 1, 1);
        Pedido p2 = new Pedido(50, 2, 2);
        Pedido p3 = new Pedido(150, 3, 3);
        Pedido p4 = new Pedido(25, 4, 4);
        
        sistema.agregarPedido(p1);
        sistema.agregarPedido(p2);
        sistema.agregarPedido(p3);
        sistema.agregarPedido(p4);
        
        assertEquals(p1, sistema.proximoPedidoPorLlegada());
        assertEquals("[{id: 50, cliente: 2, producto: 2}, {id: 150, cliente: 3, producto: 3}, {id: 25, cliente: 4, producto: 4}]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("[{id: 25, cliente: 4, producto: 4}, {id: 50, cliente: 2, producto: 2}, {id: 150, cliente: 3, producto: 3}]", sistema.obtenerPedidosOrdenadosPorId());
        assertEquals(p4, sistema.pedidoMenorId());
        
        assertEquals(p2, sistema.proximoPedidoPorLlegada());
        assertEquals("[{id: 150, cliente: 3, producto: 3}, {id: 25, cliente: 4, producto: 4}]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("[{id: 25, cliente: 4, producto: 4}, {id: 150, cliente: 3, producto: 3}]", sistema.obtenerPedidosOrdenadosPorId());
        assertEquals(p4, sistema.pedidoMenorId());
        
        assertEquals(p3, sistema.proximoPedidoPorLlegada());
        assertEquals("[{id: 25, cliente: 4, producto: 4}]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("[{id: 25, cliente: 4, producto: 4}]", sistema.obtenerPedidosOrdenadosPorId());
        assertEquals(p4, sistema.pedidoMenorId());
    }

    
    @Test
    void multiples_proximo_pedido_por_id_mantiene_coherencia() {
        SistemaPedidos sistema = new SistemaPedidos();
        Pedido p1 = new Pedido(100, 1, 1);
        Pedido p2 = new Pedido(50, 2, 2);
        Pedido p3 = new Pedido(150, 3, 3);
        Pedido p4 = new Pedido(25, 4, 4);
        
        sistema.agregarPedido(p1);
        sistema.agregarPedido(p2);
        sistema.agregarPedido(p3);
        sistema.agregarPedido(p4);
        
        assertEquals(p4, sistema.proximoPedidoPorId());
        assertEquals("[{id: 100, cliente: 1, producto: 1}, {id: 50, cliente: 2, producto: 2}, {id: 150, cliente: 3, producto: 3}]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("[{id: 50, cliente: 2, producto: 2}, {id: 100, cliente: 1, producto: 1}, {id: 150, cliente: 3, producto: 3}]", sistema.obtenerPedidosOrdenadosPorId());
        assertEquals(p2, sistema.pedidoMenorId());
        
        assertEquals(p2, sistema.proximoPedidoPorId());
        assertEquals("[{id: 100, cliente: 1, producto: 1}, {id: 150, cliente: 3, producto: 3}]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("[{id: 100, cliente: 1, producto: 1}, {id: 150, cliente: 3, producto: 3}]", sistema.obtenerPedidosOrdenadosPorId());
        assertEquals(p1, sistema.pedidoMenorId());
        
        assertEquals(p1, sistema.proximoPedidoPorId());
        assertEquals("[{id: 150, cliente: 3, producto: 3}]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("[{id: 150, cliente: 3, producto: 3}]", sistema.obtenerPedidosOrdenadosPorId());
        assertEquals(p3, sistema.pedidoMenorId());
    }
    
    @Test
    void multiples_proximo_pedido_mezclado_mantiene_coherencia() {
        SistemaPedidos sistema = new SistemaPedidos();
        Pedido p1 = new Pedido(100, 1, 1);
        Pedido p2 = new Pedido(50, 2, 2);
        Pedido p3 = new Pedido(150, 3, 3);
        Pedido p4 = new Pedido(25, 4, 4);
        
        sistema.agregarPedido(p1);
        sistema.agregarPedido(p2);
        sistema.agregarPedido(p3);
        sistema.agregarPedido(p4);
        
        assertEquals(p1, sistema.proximoPedidoPorLlegada());
        assertEquals("[{id: 50, cliente: 2, producto: 2}, {id: 150, cliente: 3, producto: 3}, {id: 25, cliente: 4, producto: 4}]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("[{id: 25, cliente: 4, producto: 4}, {id: 50, cliente: 2, producto: 2}, {id: 150, cliente: 3, producto: 3}]", sistema.obtenerPedidosOrdenadosPorId());
        assertEquals(p4, sistema.pedidoMenorId());
        
        assertEquals(p4, sistema.proximoPedidoPorId());
        assertEquals("[{id: 50, cliente: 2, producto: 2}, {id: 150, cliente: 3, producto: 3}]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("[{id: 50, cliente: 2, producto: 2}, {id: 150, cliente: 3, producto: 3}]", sistema.obtenerPedidosOrdenadosPorId());
        assertEquals(p2, sistema.pedidoMenorId());
        
        assertEquals(p2, sistema.proximoPedidoPorLlegada());
        assertEquals("[{id: 150, cliente: 3, producto: 3}]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("[{id: 150, cliente: 3, producto: 3}]", sistema.obtenerPedidosOrdenadosPorId());
        assertEquals(p3, sistema.pedidoMenorId());
    }

    @Test
    void eliminar_todos_los_pedidos() {
        SistemaPedidos sistema = new SistemaPedidos();
        Pedido p1 = new Pedido(50, 1, 1);
        Pedido p2 = new Pedido(30, 2, 2);
        Pedido p3 = new Pedido(70, 3, 3);
        
        sistema.agregarPedido(p1);
        sistema.agregarPedido(p2);
        sistema.agregarPedido(p3);
        
        sistema.proximoPedidoPorLlegada();
        sistema.proximoPedidoPorLlegada();
        sistema.proximoPedidoPorLlegada();
        
        assertEquals("[]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("[]", sistema.obtenerPedidosOrdenadosPorId());
    }

    @Test
    void agregar_despues_de_eliminar_mantiene_coherencia() {
        SistemaPedidos sistema = new SistemaPedidos();
        Pedido p1 = new Pedido(50, 1, 1);
        Pedido p2 = new Pedido(30, 2, 2);
        
        sistema.agregarPedido(p1);
        sistema.agregarPedido(p2);
        sistema.proximoPedidoPorLlegada();
        
        Pedido p3 = new Pedido(70, 3, 3);
        Pedido p4 = new Pedido(10, 4, 4);
        sistema.agregarPedido(p3);
        sistema.agregarPedido(p4);
        
        assertEquals("[{id: 30, cliente: 2, producto: 2}, {id: 70, cliente: 3, producto: 3}, {id: 10, cliente: 4, producto: 4}]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("[{id: 10, cliente: 4, producto: 4}, {id: 30, cliente: 2, producto: 2}, {id: 70, cliente: 3, producto: 3}]", sistema.obtenerPedidosOrdenadosPorId());
        assertEquals(p4, sistema.pedidoMenorId());
    }

    @Test
    void caso_complejo_intercalado() {
        SistemaPedidos sistema = new SistemaPedidos();
        Pedido p1 = new Pedido(28, 1, 1);
        Pedido p2 = new Pedido(71, 2, 2);
        Pedido p3 = new Pedido(17, 3, 3);
        Pedido p4 = new Pedido(261, 4, 4);
        Pedido p5 = new Pedido(21, 5, 5);
        
        sistema.agregarPedido(p1);
        sistema.agregarPedido(p2);
        sistema.agregarPedido(p3);
        
        assertEquals("[{id: 28, cliente: 1, producto: 1}, {id: 71, cliente: 2, producto: 2}, {id: 17, cliente: 3, producto: 3}]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("[{id: 17, cliente: 3, producto: 3}, {id: 28, cliente: 1, producto: 1}, {id: 71, cliente: 2, producto: 2}]", sistema.obtenerPedidosOrdenadosPorId());
        
        sistema.agregarPedido(p4);
        sistema.agregarPedido(p5);
        
        assertEquals("[{id: 28, cliente: 1, producto: 1}, {id: 71, cliente: 2, producto: 2}, {id: 17, cliente: 3, producto: 3}, {id: 261, cliente: 4, producto: 4}, {id: 21, cliente: 5, producto: 5}]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("[{id: 17, cliente: 3, producto: 3}, {id: 21, cliente: 5, producto: 5}, {id: 28, cliente: 1, producto: 1}, {id: 71, cliente: 2, producto: 2}, {id: 261, cliente: 4, producto: 4}]", sistema.obtenerPedidosOrdenadosPorId());
        assertEquals(p3, sistema.pedidoMenorId());
        
        assertEquals(p1, sistema.proximoPedidoPorLlegada());
        assertEquals("[{id: 71, cliente: 2, producto: 2}, {id: 17, cliente: 3, producto: 3}, {id: 261, cliente: 4, producto: 4}, {id: 21, cliente: 5, producto: 5}]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("[{id: 17, cliente: 3, producto: 3}, {id: 21, cliente: 5, producto: 5}, {id: 71, cliente: 2, producto: 2}, {id: 261, cliente: 4, producto: 4}]", sistema.obtenerPedidosOrdenadosPorId());
        
        assertEquals(p3, sistema.proximoPedidoPorId());
        assertEquals("[{id: 71, cliente: 2, producto: 2}, {id: 261, cliente: 4, producto: 4}, {id: 21, cliente: 5, producto: 5}]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("[{id: 21, cliente: 5, producto: 5}, {id: 71, cliente: 2, producto: 2}, {id: 261, cliente: 4, producto: 4}]", sistema.obtenerPedidosOrdenadosPorId());
        assertEquals(p5, sistema.pedidoMenorId());

        assertEquals(p5, sistema.proximoPedidoPorId());
        assertEquals("[{id: 71, cliente: 2, producto: 2}, {id: 261, cliente: 4, producto: 4}]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("[{id: 71, cliente: 2, producto: 2}, {id: 261, cliente: 4, producto: 4}]", sistema.obtenerPedidosOrdenadosPorId());
        assertEquals(p2, sistema.pedidoMenorId());
    }
}