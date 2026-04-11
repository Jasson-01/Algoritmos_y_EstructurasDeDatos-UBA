package aed;

public class Recordatorio {
    private String mensaje;
    private Fecha fecha;
    private Horario horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        // Implementar
        this.mensaje = mensaje;
        this.fecha = new Fecha(fecha);
        this.horario = horario;
    }

    public Horario horario() {
        // Implementar
        return this.horario;
    }

    public Fecha fecha() {
        // Implementar
        return new Fecha(this.fecha);
    }

    public String mensaje() {
        // Implementar
        return this.mensaje;
    }

    @Override
    public String toString() {
        // Implementar
        return mensaje + " " + "@" + " " + fecha + " " + horario;
    }

    @Override
    public boolean equals(Object otro) {
        // Implementar
        //Algunos chequeos burocraticos
        boolean otroEsNull = (otro == null);
        if(otroEsNull){
            return false;
        }

        boolean claseDistinta = otro.getClass() != this.getClass();
        if(claseDistinta){
            return false;
        }

        // casting -> cambiar el tipo
        Recordatorio otroRecordatorio = (Recordatorio) otro;

        return mensaje.equals(otroRecordatorio.mensaje) && fecha.equals(otroRecordatorio.fecha) && horario.equals(otroRecordatorio.horario);

    }

}
