package aed;

public class Agenda {
    private Fecha fechaActual;
    private ArregloRedimensionableDeRecordatorios conjRecordatorios;

    public Agenda(Fecha fechaActual) { // Constructor por copia
        // Implementar
        this.fechaActual = new Fecha(fechaActual);
        this.conjRecordatorios = new ArregloRedimensionableDeRecordatorios();
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        // Implementar
        conjRecordatorios.agregarAtras(recordatorio);
    }

    @Override
    public String toString() {
        // Implementar
        String toDo = "";
        for (int i = 0; i < conjRecordatorios.longitud(); i++) {
            Recordatorio recordatorio = conjRecordatorios.obtener(i);
            if (recordatorio.fecha().equals(this.fechaActual)) {
                toDo += recordatorio.toString() + "\n";
            }
        }

        return fechaActual.dia() + "/" + fechaActual.mes() + "\n" + "=====" + "\n" + toDo;
    }

    public void incrementarDia() {
        // Implementar
        fechaActual.incrementarDia();
    }

    public Fecha fechaActual() {
        // Implementar
        return new Fecha(fechaActual);
    }

}
