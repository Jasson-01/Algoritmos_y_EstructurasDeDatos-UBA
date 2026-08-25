package aed;

public class Horario {
    private int hora;
    private int minutos;

    public Horario(int hora, int minutos) {
        // Implementar
        this.hora = hora;
        this.minutos = minutos;
    }

    public int hora() {
        // Implementar
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
        boolean otraEsNull = (otro == null);
        if(otraEsNull){
            return false;
        }

        boolean claseDistinta = otro.getClass() != this.getClass();

        if(claseDistinta){
            return false;
        }

        //cating -> cambiar el tipo
        Horario otroHorario = (Horario) otro;

        return hora == otroHorario.hora && minutos == otroHorario.minutos;
    }

}