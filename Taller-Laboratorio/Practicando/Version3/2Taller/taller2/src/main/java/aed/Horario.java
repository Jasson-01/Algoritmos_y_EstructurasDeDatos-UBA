package aed;

public class Horario {
    private int hora;
    private int minutos;

    public Horario(int hora, int minutos) {
        this.hora = hora;
        this.minutos = minutos;
    }

    public Horario(Horario horario) {
        this.hora = horario.hora();
        this.minutos = horario.minutos();
    }

    public int hora() {
        return hora;
    }

    public int minutos() {
        // Implementar
        return minutos;
    }

    @Override
    public String toString() {
        // Implementar
        return hora + ":" + minutos;
    }

    @Override
    public boolean equals(Object otro) {
        // Implementar
        boolean otroEsNull = (otro == null);
        if (otroEsNull) {
            return false;
        }

        boolean claseDistinta = otro.getClass() != this.getClass();
        if (claseDistinta) {
            return false;
        }

        Horario otroHorario = (Horario) otro;
        return hora == otroHorario.hora && minutos == otroHorario.minutos;
    }

}
