package aed;

class ArregloRedimensionableDeRecordatorios {
    private Recordatorio[] arrayRecordatorios;
    private int longitud;

    public ArregloRedimensionableDeRecordatorios() {
        // Implementar
        this.arrayRecordatorios = new Recordatorio[0];
        this.longitud = 0;
    }

    public int longitud() {
        // Implementar
        return longitud;
    }

    public void agregarAtras(Recordatorio i) {
        // Implementar
        Recordatorio[] nuevoArray = new Recordatorio[longitud + 1];
        for(int j=0; j<arrayRecordatorios.length; j++){
            nuevoArray[j] = arrayRecordatorios[j];
        }
        arrayRecordatorios = nuevoArray;
        arrayRecordatorios[longitud] = i;
        longitud++;
    }

    public Recordatorio obtener(int i) {
        // Implementar
        return arrayRecordatorios[i];
    }

    public void quitarAtras() {
        // Implementar
        Recordatorio[] nuevoRecordatorios2 = new Recordatorio[longitud-1];
        for(int j=0; j<arrayRecordatorios.length-1; j++){
             nuevoRecordatorios2[j] = arrayRecordatorios[j];
        }
        arrayRecordatorios = nuevoRecordatorios2;
        longitud--;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        // Implementar
        arrayRecordatorios[indice] = valor;
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        // Implementar
        this.arrayRecordatorios = vector.arrayRecordatorios.clone();
        this.longitud =  vector.longitud;
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        // Implementar
        ArregloRedimensionableDeRecordatorios copiaArray = new ArregloRedimensionableDeRecordatorios(); //Aqui creo el objeto vacío
        for(int j=0; j<longitud; j++){
            copiaArray.agregarAtras(arrayRecordatorios[j]);
        }
        return copiaArray;
    }
}

//2DA FORMA PARA EL METODO copiar()
/*
public ArregloRedimensionableDeRecordatorios copiar() {
    ArregloRedimensionableDeRecordatorios copiaArray = new ArregloRedimensionableDeRecordatorios();
    copiaArray.arrayRecordatorios = this.arrayRecordatorios.clone();
    copiaArray.longitud = this.longitud;
    return copiaArray;
}


// USANDO EL CONSTRUCTOR POR COPIA

public ArregloRedimensionableDeRecordatorios copiar() {
    return new ArregloRedimensionableDeRecordatorios(this);
}

*/
