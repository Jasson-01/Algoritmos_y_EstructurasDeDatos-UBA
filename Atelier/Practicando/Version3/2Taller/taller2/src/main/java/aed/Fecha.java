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
        dia = fecha.dia;
        mes = fecha.mes;
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
        return dia() + "/" + mes();
    }

    @Override
    public boolean equals(Object otra) {
        // Implementar
        
        // 2. Si el otro objeto es nulo(otraEsNull) o no es de la clase Fecha(claseDistinta), no son iguales
        boolean otraEsNull = (otra == null);
        if(otraEsNull){
            return false;
        }

        boolean claseDistinta = otra.getClass() != this.getClass();
        if(claseDistinta){
            return false;
        }
        
        // cambio el tipo de dato
        Fecha otraFecha = (Fecha) otra;
        
         // 4. Comparamos los atributos clave
        return dia == otraFecha.dia && mes == otraFecha.mes;
    }

    public void incrementarDia() {
        // Implementar
        //  System.out.println(diasEnMes(6)); --> res = 30
        // ojo: cuando el mes era "12" y era un ">" -> daba false e iba al else
        if ( mes >= 12 && dia >= diasEnMes(mes)) {
           mes = 1;
           dia = 1;
        } else {
            if ( (diasEnMes(mes) == 28 && mes == 2) || (diasEnMes(mes) == 30 && dia == 30) || (diasEnMes(mes) == 31 && dia == 31) ){
                dia = 1;
                mes += 1;
            } else {
               dia += 1;
            }
        }

    }
        // dia -> 28 mes -> 6

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        // OJO, a mes se le resta 1 por eso el array comienza con 1 no con 0.
        return dias[mes - 1];
    }

}
