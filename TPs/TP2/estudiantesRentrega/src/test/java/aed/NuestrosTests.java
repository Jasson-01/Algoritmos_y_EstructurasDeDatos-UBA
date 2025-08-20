package aed;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;


public class NuestrosTests {
    
/*
'Cuidado en el test compararUsers que están dependiendo de que se corra el
test anterior para que den bien los resultados. No hay garantía de que se 
corra antes el otro'

CONSULTA:
Esto es porque compararUsers() depende de user que lo estamos creando en montoUser(),
y en general es una mala práctica que un test dependa de otro no?

// Tests sobre Usuarios
    Usuario user = new Usuario(1);
    @Test
    public void montoUser(){

        user.sumar(10);
        assertEquals(10, user.patrimonio());

        user.sumar(-5);
        assertEquals(5, user.patrimonio());;
    }

    @Test
    public void compararUsers(){
        Usuario user_2 = new Usuario(2);
        Usuario user_3 = new Usuario(3);

        assertEquals(user_3.compareTo(user),-1);
        assertEquals(user_2.compareTo(user_3),1);
        assertEquals(user_2.compareTo(user),-1);
        assertEquals(user.compareTo(user_2),1);

        assertTrue(user.equals(user));
        assertFalse(user.equals(user_2));
    }

ABAJO NUEVO TEST QUE SOLUCIONA ESTO Y ADEMAS LE AGREGUE TESTEO ESTE NUEVO
CAMBIO EN COMPARETO.
*/

    // Tests sobre Usuarios

    @Test
    public void testCompararUsuarios_PatrimonioDistinto() {
        Usuario u1 = new Usuario(1); // patrimonio 0
        Usuario u2 = new Usuario(2); // patrimonio 0

        u1.sumar(100);
        u2.sumar(200);

        // u2 > u1
        assertTrue(u2.compareTo(u1) > 0);
        assertTrue(u1.compareTo(u2) < 0);
    }

    @Test
    public void testCompararUsuarios_PatrimonioIgual_IdDistinto() {
        Usuario u1 = new Usuario(1);
        Usuario u2 = new Usuario(2);

        u1.sumar(100);
        u2.sumar(100);

        // patrimonio igual, ID 1 < 2 → u1.compareTo(u2) = 1 (porque ID menor pierde en el desempate)
        assertEquals(1, u1.compareTo(u2));
        assertEquals(-1, u2.compareTo(u1));
    }

    @Test
    public void testCompararUsuarios_Iguales() {
        Usuario u1 = new Usuario(1);
        Usuario u2 = new Usuario(1); // mismo ID

        u1.sumar(100);
        u2.sumar(100);

        // Simula misma instancia lógica
        assertEquals(0, u1.compareTo(u2));
        assertEquals(0, u2.compareTo(u1));
    }

    @Test
    public void testReflexividadYSimetria() {
        Usuario u = new Usuario(42);
        u.sumar(500);

        // Reflexividad
        assertEquals(0, u.compareTo(u));

        // Simetría con igual usuario
        Usuario copia = new Usuario(42);
        copia.sumar(500);
        assertEquals(0, u.compareTo(copia));
        assertEquals(0, copia.compareTo(u));
    }


// Tests sobre Transacciones

@Test
public void testCompararTransacciones_MontoDistinto() {
    Transaccion t1 = new Transaccion(1, 100, 2, 3); // id = 1, monto = 100
    Transaccion t2 = new Transaccion(2, 200, 3, 4); // id = 2, monto = 200

    assertTrue(t2.compareTo(t1) > 0); // 200 > 100
    assertTrue(t1.compareTo(t2) < 0); // 100 < 200
}

@Test
public void testCompararTransacciones_MontoIgual_IdDistinto() {
    Transaccion t1 = new Transaccion(1, 100, 2, 3);
    Transaccion t2 = new Transaccion(2, 100, 3, 4);

    // Mismo monto, id = 1 vs 2
    assertTrue(t2.compareTo(t1) > 0);
    assertTrue(t1.compareTo(t2) < 0);
}

@Test
public void testCompararTransacciones_Iguales() {
    Transaccion t1 = new Transaccion(42, 300, 5, 6);
    Transaccion t2 = new Transaccion(42, 300, 5, 6); // misma id y monto

    assertEquals(0, t1.compareTo(t2));
    assertEquals(0, t2.compareTo(t1));
}

@Test
public void testTransReflexividadYSimetria() {
    Transaccion t = new Transaccion(99, 500, 1, 2);

    // Reflexividad
    assertEquals(0, t.compareTo(t));

    // Simetría
    Transaccion copia = new Transaccion(99, 500, 1, 2);
    assertEquals(0, t.compareTo(copia));
    assertEquals(0, copia.compareTo(t));
}

    Transaccion trans = new Transaccion(0, 0, 1, 1);
    Transaccion trans_2 = new Transaccion(1,1,2,1);
    Transaccion trans_3 = new Transaccion(2, 2, 1, 2);

// Tests sobre Bloques

    Bloque bloque = new Bloque(0, new ListaEnlazada<Transaccion>(), 0);

    @Test
    public void testActualizarBloque() {
        assertEquals(0, bloque.montoAcumulado());
        ListaEnlazada<Transaccion> nuevaLE = new ListaEnlazada<Transaccion>();
        nuevaLE.agregarAtras(trans);
        nuevaLE.agregarAtras(trans_2);
        nuevaLE.agregarAtras(trans_3);

        int nuevoMonto = 0;

        ListaEnlazada<Transaccion>.Handle actual = nuevaLE.obtenerPrimero();
        for(int i = 0; i < nuevaLE.longitud(); i++){
            if(actual.valor().id_comprador() != 0){
              nuevoMonto += actual.valor().monto();
            }
        }
        bloque.actualizar(nuevaLE, nuevoMonto);
        assertEquals(nuevoMonto, bloque.montoAcumulado());
        assertEquals(nuevaLE, bloque.transacciones());

    }

// Tests para el Heap
    @Test
    public void testInsertarYVerRaiz() {
        Heap<Integer> heap = new Heap<>();
        heap.insertar(4);
        assertEquals(4, heap.verRaiz());

        heap.insertar(10);
        assertEquals(10, heap.verRaiz());

        heap.insertar(3);
        assertEquals(10, heap.verRaiz());

        heap.insertar(8);
        assertEquals(10, heap.verRaiz());

        heap.insertar(15);
        assertEquals(15, heap.verRaiz());
    }

    @Test
    public void testExtraerRaiz() {
        Heap<Integer> heap = new Heap<>();
        heap.insertar(50);
        heap.insertar(40);
        heap.insertar(20);
        heap.insertar(30);
        heap.insertar(15);
        heap.insertar(5);
        heap.insertar(3);
        heap.insertar(10);

        // Estado inicial: raíz debe ser 50
        assertEquals(50, heap.verRaiz());

        int extraido = heap.extraerRaiz();
        assertEquals(50, extraido);
        assertEquals(40, heap.verRaiz());

        extraido = heap.extraerRaiz();
        assertEquals(40, extraido);
        assertEquals(30, heap.verRaiz());
    }

    @Test
    public void testHeapConUnSoloElemento() {
        Heap<Integer> heap = new Heap<>();
        heap.insertar(7);
        assertEquals(7, heap.verRaiz());

        int extraido = heap.extraerRaiz();
        assertEquals(7, extraido);
        assertNull(heap.verRaiz());
    }

    @Test
    public void testHeapVacio() {
        Heap<Integer> heap = new Heap<>();
        assertNull(heap.verRaiz());
        assertNull(heap.extraerRaiz());
    }



}

