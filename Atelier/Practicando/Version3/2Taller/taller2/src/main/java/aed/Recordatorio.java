package aed;

public class Recordatorio {

    private String mensaje;
    private Fecha fecha;
    private Horario horario;

    //Como vamos a usar objetos que creamos anteriormente
    // Copia defensiva: guardo una nueva Fecha y un nuevo Horario
    // para que cambios externos no modifiquen este recordatorio.
    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        this.mensaje = mensaje;
        this.fecha = new Fecha(fecha);
        this.horario = new Horario(horario);
    }
    
    // Devuelve una copia para que quien recibe no pueda cambiar el estado interno
    public Horario horario() {
        return new Horario(horario);
    }
    
    // Devuelve una copia para que quien recibe no pueda cambiar el estado interno
    public Fecha fecha() {
        return new Fecha(fecha);
    }

    public String mensaje() {
        return mensaje;
    }

    @Override
    public String toString() {
        return mensaje + " @ " + fecha + " " + horario;
    }

    @Override
    public boolean equals(Object otro) {
        if (otro == null) {
            return false;
        }

        if (otro.getClass() != this.getClass()) {
            return false;
        }

        Recordatorio otroRecordatorio = (Recordatorio) otro;

        // Aqui no usamos "==" porque comprueban si dos referencias apuntan al mismo objeto (comparacion por referencias)
        // usamos .equals(...) porque comprueba si dos objetos son iguales en contenido (comparacion por valores o contenido)
        return mensaje.equals(otroRecordatorio.mensaje)
                && fecha.equals(otroRecordatorio.fecha)
                && horario.equals(otroRecordatorio.horario);
    }

}
