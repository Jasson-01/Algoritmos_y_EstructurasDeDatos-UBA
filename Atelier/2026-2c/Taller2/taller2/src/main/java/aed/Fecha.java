package aed;

public class Fecha {
    private int dia;
    private int mes;

    public Fecha(int dia, int mes) {
        // Implementar
        this.dia = dia;
        this.mes = mes;
    }

    public Fecha(Fecha fecha) {
        // Implementar
        this.dia = fecha.dia;
        this.mes = fecha.mes;
    }

    public Integer dia() {
        // Implementar
        return dia;
    }

    public Integer mes() {
        // Implementar
        return mes;
    }

    public String toString() {
        // Implementar
        return dia + "/" + mes;
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

        //cating -> cambiar el tipo
        Fecha otraFecha = (Fecha) otra;

        return dia == otraFecha.dia && mes == otraFecha.mes;
    }

    public void incrementarDia() {
        // Implementar
        if( diasEnMes(mes) == 31 && mes >= 12){ // Para diciembre
            dia = 1;
            mes = 1;
        } else { // Para cualquier otro mes
            if ( (diasEnMes(mes) == 28 && mes == 2 && dia < 28) || ( diasEnMes(mes) == 30 && dia < 30 ) || (diasEnMes(mes) == 31 && dia < 31)) { 
                dia += 1;
            } else {
                mes += 1;
                dia = 1;
            }
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