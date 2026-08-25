package aed;

class ArregloRedimensionableDeRecordatorios {
    private Recordatorio[] misRecordatorios;
    private int longitud;

    public ArregloRedimensionableDeRecordatorios() {
        // Implementar
        this.misRecordatorios = new Recordatorio[0];
        this.longitud = 0;
    }

    public int longitud() {
        // Implementar
        return longitud;
    }

    public void agregarAtras(Recordatorio i) {
        // Implementar
        Recordatorio[] nuevaLista = new Recordatorio[longitud+1];
        for (int j=0; j< misRecordatorios.length; j++){
           nuevaLista[j] = misRecordatorios[j];
        }
        misRecordatorios = nuevaLista;
        nuevaLista[longitud] = i;
        longitud++;
    }

    public Recordatorio obtener(int i) {
        // Implementar
        return misRecordatorios[i];
    }

    public void quitarAtras() {
        // Implementar
        Recordatorio[] listaCorta = new Recordatorio[longitud-1];
        for (int j=0; j<misRecordatorios.length-1; j++){
            listaCorta[j] = misRecordatorios[j];
        }
        misRecordatorios = listaCorta;
        longitud--;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        // Implementar
        misRecordatorios[indice] = valor;
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        // Implementar
        this.longitud = vector.longitud;
        this.misRecordatorios = new Recordatorio[vector.longitud];

        for (int i=0; i<vector.longitud; i++){
            this.misRecordatorios[i] = vector.misRecordatorios[i];
        }
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        // Implementar
        ArregloRedimensionableDeRecordatorios copia = new ArregloRedimensionableDeRecordatorios(); //Creo el objeto vacio
        
        copia.longitud = this.longitud;
        copia.misRecordatorios = new Recordatorio[this.longitud];

        for(int i=0; i<this.longitud; i++){
            copia.misRecordatorios[i] = this.misRecordatorios[i];
        }

        return copia;
    }
}
