package aed;

public class Berretacoin {
    private ListaEnlazada<Bloque> bloques;
    private HandlerTransacciones heapTransacciones; 
    private HandlerUsuarios heapUsuarios; 


    public Berretacoin(int n_usuarios){
        this.bloques = new ListaEnlazada<>();
        this.heapUsuarios = new HandlerUsuarios(n_usuarios); //O(P)-Aclaracion en el constructor.+

    }

    public void agregarBloque(Transaccion[] transacciones){
        {   
            /*
            Objetivos:
            - Ir calculando el monto acumulado de las transacciones del bloque.
            - Armar la LE de transacciones del bloque.
            - Armar el heap de transacciones del bloque.
            - Actualizar el patrimonio de los usuarios involucrados en las transacciones.

            Todo esto en una complejidad de O(log P * nb).
            */

           
            ListaEnlazada<Transaccion> nuevoBloqueTxs = new ListaEnlazada<>(); //O(1)
            int montoAcumulado = 0; //0(1)                               

            for (Transaccion t : transacciones) { // O(nb)
                
                // Agregamos cada transacción al bloque
                nuevoBloqueTxs.agregarAtras(t); //O(1) Agregamos la transaccion al final del Bloque.
    
                // Actualizamos el patrimonio de los usuarios
                // O(log p)
                if (t.id_comprador() == 0) { // Transaccion de creacion
                    heapUsuarios.actualizarPatrimonio(t.id_vendedor(),t.monto());   
                } else {
                    heapUsuarios.actualizarPatrimonio(t.id_comprador(), -t.monto()); 
                    heapUsuarios.actualizarPatrimonio(t.id_vendedor(),t.monto()); 
                }

                // Calculo el monto acumulado de las transacciones del bloque
                if (t.id_comprador() != 0) { 
                    montoAcumulado += t.monto();
                }

            }

            // Hacer Heapify a nuevoBloqueTxs. O(nb)
             this.heapTransacciones = new HandlerTransacciones(nuevoBloqueTxs);
            

            // Agregamos el bloque a la lista de bloques
            int id_bloque = bloques.longitud(); //0(1)
            Bloque bloque = new Bloque(id_bloque, nuevoBloqueTxs, montoAcumulado); //O(1)
            bloques.agregarAtras(bloque); //O(1)
        }
    }

    
    public Transaccion txMayorValorUltimoBloque(){
        return heapTransacciones.verRaiz().valor(); //O(1)
    }

    /*
    Aca claramente me interesa buscar la informacion con el Heap para que sea O(1) (Por eso en el handle 
    verRaiz() queda igual!)
     */

    public Transaccion[] txUltimoBloque(){
        Transaccion[] lista = new Transaccion[bloques.obtenerUltimo().valor().transacciones().longitud()];
        ListaEnlazada<Transaccion>.Handle actual = bloques.obtenerUltimo().valor().transacciones().obtenerPrimero();  
        for(int i = 0; i < lista.length; i++){ //O(nb)
            lista[i] = actual.valor();
            actual = actual.siguiente();
        }
        return lista;
    }

    /*
    Aca me interesa usar el handle del ArrayList de LE que justamente me guarda la referencia a cada nodo ordenado por id de transaccion.
    Por eso puedo simplemente iterar y copiar elemento a elemento de la ArrayList de LE de transacciones.
    */

    public int maximoTenedor(){
        return heapUsuarios.verRaiz().id(); //O(1)
    }

    /*
    HeapHandleArray tambien en verRaiz queda igual que el heap 'generico' ya que es suficiente.
    */

    public int montoMedioUltimoBloque(){
        Bloque ultimo = bloques.obtenerUltimo().valor(); //O(1) 
        int res =  0;
        if(ultimo.transacciones().longitud()>1){
            int longitudBloque = ultimo.transacciones().longitud(); //O(1)
            if(bloques.longitud() < 3000){ //Si hay menos de 3000 bloques, no hay transacciones de creacion.
                res = ultimo.montoAcumulado()/(longitudBloque-1); //O(1)
            }else{ //Si hay mas de 3000 bloques, hay transacciones de creacion.
                res = ultimo.montoAcumulado()/(longitudBloque); //O(1)
            }
        }
        else{
            res = ultimo.montoAcumulado(); //Si no hay transacciones, el monto medio es 0.
        }
        return res; 
    }

    /*
    Es 0(1) porque aprovechamos que el bloque ya tiene el monto acumulado y la cantidad de transacciones.
    (Fuimos calculando el monto acumulado al agregar las transacciones al bloque).
    */

    public void hackearTx(){ 

        /* Buscamos el último bloque de la cadena */
        Bloque ultimoBloque = bloques.obtenerUltimo().valor(); //O(1)
        ListaEnlazada<Transaccion> trans = ultimoBloque.transacciones(); //O(1) - Tomamos su lista de transacciones

        /* Guardamos el valor de la raíz del handleLE en una variable */
        Transaccion transRaiz = heapTransacciones.verRaiz().valor();//O(1)
        
        /* Eliminamos la transacción de mayor valor */
        ListaEnlazada<Transaccion>.Handle eliminar = heapTransacciones.extraerRaiz(trans);/* O(log nb) - Esto elimina del heap y de la lista */

        /* Actualizamos el patrimonio de los usuarios involucrados en la transacción*/
        if (transRaiz.id_comprador() == 0) {
            heapUsuarios.actualizarPatrimonio(transRaiz.id_vendedor(), -transRaiz.monto()); // O(log P)
        } else {
            heapUsuarios.actualizarPatrimonio(transRaiz.id_comprador(), transRaiz.monto()); // O(log P)
            heapUsuarios.actualizarPatrimonio(transRaiz.id_vendedor(), -transRaiz.monto()); // O(log P)
        }

        /*Actualizamos último bloque*/

        trans.eliminarRapido(eliminar); //O(1) - Eliminamos la transacción de la lista de transacciones del último bloque.

        /* Actualizamos el monto total de la lista de transacciones del último bloque*/
        int nuevoMontoAcumulado = ultimoBloque.montoAcumulado(); // O(1)
        if (transRaiz.id_comprador() != 0) {
            nuevoMontoAcumulado -= transRaiz.monto();// O(1)
        }
        
        ultimoBloque.actualizar(trans, nuevoMontoAcumulado);// O(1)

    }
}
