package aed;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class HandleTests {
    @Test
    void nuevo_sistema() {
        SistemaPedidos sistema = new SistemaPedidos();
        assertEquals("[]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("{}", sistema.obtenerPedidosOrdenadosPorId());
    }

    @Test
    void insertar_y_verificar_con_handle() {
        ABB<Integer> abb = new ABB<>();
        ABB<Integer>.HandleABB handle1 = abb.insertar(50);
        ABB<Integer>.HandleABB handle2 = abb.insertar(30);
        ABB<Integer>.HandleABB handle3 = abb.insertar(70);
        
        assertEquals(50, handle1.valor());
        assertEquals(30, handle2.valor());
        assertEquals(70, handle3.valor());
        
        assertTrue(abb.pertenece(50));
        assertTrue(abb.pertenece(30));
        assertTrue(abb.pertenece(70));
    }

    @Test
    void eliminar_elemento_con_handle() {
        ABB<Integer> abb = new ABB<>();
        ABB<Integer>.HandleABB handle1 = abb.insertar(50);
        abb.insertar(30);
        abb.insertar(70);
        
        assertTrue(abb.pertenece(50));
        handle1.eliminar();
        assertFalse(abb.pertenece(50));
        assertTrue(abb.pertenece(30));
        assertTrue(abb.pertenece(70));
    }

    @Test
    void eliminar_varios_elementos_con_handle() {
        ABB<Integer> abb = new ABB<>();
        ABB<Integer>.HandleABB handle1 = abb.insertar(50);
        ABB<Integer>.HandleABB handle2 = abb.insertar(30);
        ABB<Integer>.HandleABB handle3 = abb.insertar(70);
        ABB<Integer>.HandleABB handle4 = abb.insertar(20);
        ABB<Integer>.HandleABB handle5 = abb.insertar(60);
        
        assertEquals(5, abb.cardinal());
        
        handle2.eliminar();
        assertFalse(abb.pertenece(30));
        assertEquals(4, abb.cardinal());
        
        handle5.eliminar();
        assertFalse(abb.pertenece(60));
        assertEquals(3, abb.cardinal());
        
        assertTrue(abb.pertenece(50));
        assertTrue(abb.pertenece(20));
        assertTrue(abb.pertenece(70));
    }

    @Test
    void eliminar_raiz_con_handle() {
        ABB<Integer> abb = new ABB<>();
        ABB<Integer>.HandleABB handleRaiz = abb.insertar(50);
        abb.insertar(30);
        abb.insertar(70);
        
        handleRaiz.eliminar();
        assertFalse(abb.pertenece(50));
        assertTrue(abb.pertenece(30));
        assertTrue(abb.pertenece(70));
        assertEquals(2, abb.cardinal());
    }

    @Test
    void eliminar_minimo_con_handle() {
        ABB<Integer> abb = new ABB<>();
        abb.insertar(50);
        ABB<Integer>.HandleABB handleMin = abb.insertar(30);
        abb.insertar(70);
        
        handleMin.eliminar();
        assertFalse(abb.pertenece(30));
        assertEquals(50, abb.minimo());
    }

    @Test
    void eliminar_maximo_con_handle() {
        ABB<Integer> abb = new ABB<>();
        abb.insertar(50);
        abb.insertar(30);
        ABB<Integer>.HandleABB handleMax = abb.insertar(70);
        
        handleMax.eliminar();
        assertFalse(abb.pertenece(70));
        assertEquals(50, abb.maximo());
    }

    @Test
    void agregar_pedido_al_sistema() {
        SistemaPedidos sistema = new SistemaPedidos();
        Pedido p1 = new Pedido(100, 1, 1);
        
        sistema.agregarPedido(p1);
        
        assertEquals("[100]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("{100}", sistema.obtenerPedidosOrdenadosPorId());
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
        
        assertEquals("[50, 30, 70]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("{30, 50, 70}", sistema.obtenerPedidosOrdenadosPorId());
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
    void proximo_pedido_elimina_primero() {
        SistemaPedidos sistema = new SistemaPedidos();
        Pedido p1 = new Pedido(50, 1, 1);
        Pedido p2 = new Pedido(30, 2, 2);
        Pedido p3 = new Pedido(70, 3, 3);
        
        sistema.agregarPedido(p1);
        sistema.agregarPedido(p2);
        sistema.agregarPedido(p3);
        
        Pedido proximo = sistema.proximoPedido();
        
        assertEquals(p1, proximo);
        assertEquals("[30, 70]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("{30, 70}", sistema.obtenerPedidosOrdenadosPorId());
    }

    @Test
    void multiples_proximo_pedido_mantiene_coherencia() {
        SistemaPedidos sistema = new SistemaPedidos();
        Pedido p1 = new Pedido(100, 1, 1);
        Pedido p2 = new Pedido(50, 2, 2);
        Pedido p3 = new Pedido(150, 3, 3);
        Pedido p4 = new Pedido(25, 4, 4);
        
        sistema.agregarPedido(p1);
        sistema.agregarPedido(p2);
        sistema.agregarPedido(p3);
        sistema.agregarPedido(p4);
        
        assertEquals(p1, sistema.proximoPedido());
        assertEquals("[50, 150, 25]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("{25, 50, 150}", sistema.obtenerPedidosOrdenadosPorId());
        assertEquals(p4, sistema.pedidoMenorId());
        
        assertEquals(p2, sistema.proximoPedido());
        assertEquals("[150, 25]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("{25, 150}", sistema.obtenerPedidosOrdenadosPorId());
        assertEquals(p4, sistema.pedidoMenorId());
        
        assertEquals(p3, sistema.proximoPedido());
        assertEquals("[25]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("{25}", sistema.obtenerPedidosOrdenadosPorId());
        assertEquals(p4, sistema.pedidoMenorId());
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
        
        sistema.proximoPedido();
        sistema.proximoPedido();
        sistema.proximoPedido();
        
        assertEquals("[]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("{}", sistema.obtenerPedidosOrdenadosPorId());
    }

    @Test
    void agregar_despues_de_eliminar_mantiene_coherencia() {
        SistemaPedidos sistema = new SistemaPedidos();
        Pedido p1 = new Pedido(50, 1, 1);
        Pedido p2 = new Pedido(30, 2, 2);
        
        sistema.agregarPedido(p1);
        sistema.agregarPedido(p2);
        sistema.proximoPedido();
        
        Pedido p3 = new Pedido(70, 3, 3);
        Pedido p4 = new Pedido(10, 4, 4);
        sistema.agregarPedido(p3);
        sistema.agregarPedido(p4);
        
        assertEquals("[30, 70, 10]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("{10, 30, 70}", sistema.obtenerPedidosOrdenadosPorId());
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
        
        assertEquals("[28, 71, 17]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("{17, 28, 71}", sistema.obtenerPedidosOrdenadosPorId());
        
        sistema.agregarPedido(p4);
        sistema.agregarPedido(p5);
        
        assertEquals("[28, 71, 17, 261, 21]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("{17, 21, 28, 71, 261}", sistema.obtenerPedidosOrdenadosPorId());
        assertEquals(p3, sistema.pedidoMenorId());
        
        assertEquals(p1, sistema.proximoPedido());
        assertEquals("[71, 17, 261, 21]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("{17, 21, 71, 261}", sistema.obtenerPedidosOrdenadosPorId());
        
        assertEquals(p2, sistema.proximoPedido());
        assertEquals("[17, 261, 21]", sistema.obtenerPedidosEnOrdenDeLlegada());
        assertEquals("{17, 21, 261}", sistema.obtenerPedidosOrdenadosPorId());
        assertEquals(p3, sistema.pedidoMenorId());
    }
}