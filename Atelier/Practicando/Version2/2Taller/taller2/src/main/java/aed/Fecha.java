package aed;

public class Fecha {
    
    //Los private vendrian a ser el concepto de ENCAPSULAMIENTO
    //Pero en realidad, private en Java significa "accesible solo dentro de la misma clase". Es como una caja fuerte en tu casa: tú (la clase) puedes abrirla y usar lo que hay dentro, pero los demás (otras clases) no pueden acceder directamente. Los getters como dia() y mes() son como "ventanitas" controladas que permiten "ver" el contenido sin dar acceso total. Esto se llama encapsulamiento: proteges los datos internos, pero decides cómo compartirlos a través de métodos públicos. ¡Es una forma de mantener el control y la seguridad en el código!
    private int dia;
    private int mes;

    public Fecha(int dia, int mes) { //Constructor normal
        // Implementar
       this.dia = dia;
       this.mes = mes;
    }
 
    //Los metodos public vendrian a ser el concepto de ABSTRACCIÓN 
    public Fecha(Fecha fecha) { //Constructor por copia
        // Implementar
        this.dia = fecha.dia;
        this.mes = fecha.mes;
    }

    public Integer dia() {
        // Implementar
        return this.dia;
    }

    public Integer mes() {
        // Implementar
        return this.mes;
    }

    public String toString() {
        // Implementar
        return this.dia + "/" + this.mes;
    }

    @Override
    public boolean equals(Object otra) {
        // Implementar
        boolean otraEsNull = (otra == null);
        if(otraEsNull){
            return false;
        }

        boolean claseDistinta = otra.getClass() != this.getClass();

        if(claseDistinta){
            return false;
        }

        // casting -> cambiar el tipo
        Fecha otraFecha = (Fecha) otra;

        return this.dia == otraFecha.dia && this.mes == otraFecha.mes;
    }

    public void incrementarDia() {
        // Implementar
        int ultimoDiaDelMes = diasEnMes(this.mes);
        int diaActual = this.dia;
        int mesActual = this.mes;

        if( mesActual == 12 && diaActual < ultimoDiaDelMes){
            this.dia += 1;
        } else if (mesActual == 12 && diaActual == ultimoDiaDelMes) {
            this.dia = 1;
            this.mes = 1;
        } else if ( mesActual < 12 && diaActual < ultimoDiaDelMes){
            this.dia += 1;
        } else if (mesActual < 12 && diaActual == ultimoDiaDelMes){
            this.dia = 1;
            this.mes += 1;
        }
    }
    
    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
