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
        if (otra == null){
            return false;
        }

        boolean claseDistinta = otra.getClass() != this.getClass();
        if(claseDistinta){
            return false;
        }
        // casting -> cambiar el tipo
        Fecha otraFecha = (Fecha) otra;
        //                ↑↑↑↑↑↑↑
        //                "Convierte 'otra' de Object a Fecha"

        return this.dia == otraFecha.dia && this.mes == otraFecha.mes;
        // this.dia == otraFecha.dia
        //   ↑           ↑
        //   |           └─ El día del objeto que recibimos como parámetro
        //   └──────────── El día del objeto actual (this)

    }

    public void incrementarDia() {
        // Implementar
        this.dia++;
        if(this.dia > diasEnMes(this.mes)){
           this.dia = 1;
           if( this.mes == 12){
              this.mes = 1;
           } else {
              this.mes += 1;
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
