package aed;

interface Handle<T> extends Comparable<Handle<T>>{    
    /**
     * Devuelve el valor del elemento
     * 
     */
    public T valor();
    
    /**
     * Elimina al elemento de la colección
     * 
     */
    public void eliminar();

}
