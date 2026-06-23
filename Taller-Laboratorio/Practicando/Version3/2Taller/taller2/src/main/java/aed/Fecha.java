package aed;

public class Fecha {
    public Fecha(int dia, int mes) {
        // Implementar
    }

    public Fecha(Fecha fecha) {
        // Implementar
    }

    public Integer dia() {
        // Implementar
        return -1;
    }

    public Integer mes() {
        // Implementar
        return -1;
    }

    public String toString() {
        // Implementar
        return "";
    }

    @Override
    public boolean equals(Object otra) {
        // Implementar
        return true;
    }

    public void incrementarDia() {
        // Implementar
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
