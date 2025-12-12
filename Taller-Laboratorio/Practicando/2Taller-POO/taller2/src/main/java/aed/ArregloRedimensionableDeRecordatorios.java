package aed;

class ArregloRedimensionableDeRecordatorios {
    private Recordatorio[] arrayRecordatorio;
    private int longitud;

    public ArregloRedimensionableDeRecordatorios() {
        // Implementar
        this.arrayRecordatorio = new Recordatorio[longitud];
        this.longitud = 0;
    }

    public int longitud() {
        // Implementar
        return longitud;
    }

    public void agregarAtras(Recordatorio i) {
        // Implementar
        if (longitud == arrayRecordatorio.length) {
            Recordatorio[] arrayRecordatorio2 = new Recordatorio[longitud + 1];
            for (int j = 0; j < arrayRecordatorio.length; j++) {
                arrayRecordatorio2[j] = arrayRecordatorio[j];
            }
            arrayRecordatorio = arrayRecordatorio2;
        }
        arrayRecordatorio[longitud] = i;
        longitud++;
    }

    public Recordatorio obtener(int i) {
        // Implementar
        if (i < longitud && i >= 0) {
            return arrayRecordatorio[i];
        } else {
            return null;
        }
    }

    public void quitarAtras() {
        // Implementar
        if (longitud == 0) {
            return;
        }
        Recordatorio[] arraySinUltimoElemento = new Recordatorio[longitud - 1];
        for (int i = 0; i < arrayRecordatorio.length - 1; i++) {
            arraySinUltimoElemento[i] = arrayRecordatorio[i];
        }
        arrayRecordatorio = arraySinUltimoElemento;
        longitud--;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        // Implementar
        arrayRecordatorio[indice] = valor;
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {

        // Hace referencia al NUEVO objeto que estás creando en este momento.
        // Imagínalo así:
        // this = "YO" (El objeto nuevo que está naciendo ahora mismo).
        // vector = "ÉL/ELLA" (El objeto viejo que te pasaron para copiar).
        // Se lee: "A MI longitud (nuevo), ponle el mismo valor que tiene SU longitud
        // (viejo)."
        // Se lee: "A MI arrayRecordatorio (nuevo), ponle un nuevo arrayRecordatorio con
        // la misma longitud que el viejo."
        // Se lee: "Ahora, copia cada elemento del arrayRecordatorio viejo al nuevo
        // arrayRecordatorio nuevo."
        // Se lee: "Y listo, ya hiciste una copia del objeto viejo al nuevo."

        this.longitud = vector.longitud;
        this.arrayRecordatorio = new Recordatorio[this.longitud];
        for (int i = 0; i < this.longitud; i++) {
            this.arrayRecordatorio[i] = vector.arrayRecordatorio[i];
        }
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        ArregloRedimensionableDeRecordatorios nuevaLista = new ArregloRedimensionableDeRecordatorios();
        for (int i = 0; i < this.longitud; i++) {
            nuevaLista.agregarAtras(this.arrayRecordatorio[i]);
        }
        return nuevaLista;
    }
}

// 2 Formas mas para el metodo copiar()

// ####public ArregloRedimensionableDeRecordatorios copiar() {
// return new ArregloRedimensionableDeRecordatorios(this);
// }

// ####public ArregloRedimensionableDeRecordatorios copiar() {
// // 1. Creas el objeto vacío
// ArregloRedimensionableDeRecordatorios nuevaLista = new
// ArregloRedimensionableDeRecordatorios();

// // 2. Modificas sus atributos privados manualmente
// nuevaLista.longitud = this.longitud;
// nuevaLista.arrayRecordatorio = new Recordatorio[this.longitud];

// // 3. Copias los datos
// for (int i = 0; i < this.longitud; i++) {
// nuevaLista.arrayRecordatorio[i] = this.arrayRecordatorio[i];
// }

// return nuevaLista;
// }