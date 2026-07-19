package aed;

class ArregloRedimensionableDeRecordatorios {
    private int longitud;
    private Recordatorio[] arrayRecordatorios;

    public ArregloRedimensionableDeRecordatorios() {
        // Implementar
        this.longitud = 0;
        this.arrayRecordatorios = new Recordatorio[0];
    }

    public int longitud() {
        // Implementar
        return longitud;
    }

    public void agregarAtras(Recordatorio i) {
        // Implementar
        Recordatorio[] newArrayRecordatorios = new Recordatorio[longitud + 1];
        for (int j = 0; j < arrayRecordatorios.length; j++) {
            newArrayRecordatorios[j] = arrayRecordatorios[j];
        }
        arrayRecordatorios = newArrayRecordatorios;
        newArrayRecordatorios[longitud] = i;
        longitud++;
    }

    public Recordatorio obtener(int i) {
        // Implementar
        return arrayRecordatorios[i];
    }

    public void quitarAtras() {
        // Implementar
        Recordatorio[] newArrayRecordatorios2 = new Recordatorio[longitud - 1];
        for (int j = 0; j < arrayRecordatorios.length - 1; j++) {
            newArrayRecordatorios2[j] = arrayRecordatorios[j];
        }
        arrayRecordatorios = newArrayRecordatorios2;
        longitud--;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        // Implementar
        arrayRecordatorios[indice] = valor;
    }

    // Si además quisieras evitar aliasing entre los objetos Recordatorio también,
    // entonces necesitarías copiar cada Recordatorio por separado, pero la consigna
    // que mencionas solo pide que no haya aliasing entre los dos arreglos (copia
    // superficial).
    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        // Implementar
        this.longitud = vector.longitud;
        this.arrayRecordatorios = new Recordatorio[vector.longitud];
        for (int i = 0; i < vector.longitud; i++) {
            this.arrayRecordatorios[i] = vector.arrayRecordatorios[i];
        }
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        // Implementar
        ArregloRedimensionableDeRecordatorios copia = new ArregloRedimensionableDeRecordatorios(); // creo el objeto vacio
        copia.longitud = this.longitud;
        copia.arrayRecordatorios = new Recordatorio[this.longitud];
        for (int i = 0; i < this.longitud; i++) {    
           copia.arrayRecordatorios[i] = this.arrayRecordatorios[i]; // O(n^2)   
        }
        return copia;
    }
    
}

// *** Nota ***

// EJM (CONSTRUCTOR POR COPIA)
// Si además quieres que los Recordatorio sean distintos objetos independientes,
// usa la copia profunda como:
// public
// ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios
// vector) {
// this.longitud = vector.longitud;
// this.arrayRecordatorios = new Recordatorio[vector.longitud];
// for (int i = 0; i < vector.longitud; i++) {
// Recordatorio original = vector.arrayRecordatorios[i];
// this.arrayRecordatorios[i] = new Recordatorio(
// original.mensaje(),
// original.fecha(),
// original.horario()
// );
// }
// }

// EJM (metodo copiar())
// Formas mas eficientes para implementar el metodo copiar:

// 1)
// public ArregloRedimensionableDeRecordatorios copiar() {
// return new ArregloRedimensionableDeRecordatorios(this);
// }