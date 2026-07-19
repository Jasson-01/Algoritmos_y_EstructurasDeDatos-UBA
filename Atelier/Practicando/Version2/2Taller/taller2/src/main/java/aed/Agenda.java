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
        String fecha = fechaActual.dia() + "/" + fechaActual.mes() + "\n";
        String separador = "=====" + "\n";
        String recordatorios = "";

        // Recorremos el arreglo de recordatorios
        for (int i = 0; i < conjRecordatorios.longitud(); i++) {
            Recordatorio recordatorioActual = conjRecordatorios.obtener(i);
            if (recordatorioActual.fecha().equals(this.fechaActual)) {
                recordatorios += recordatorioActual.toString() + "\n";
            }

        }
        return fecha + separador + recordatorios;
    }

    public void incrementarDia() {
        // Implementar
        fechaActual.incrementarDia();
    }

    public Fecha fechaActual() {
        // Implementar
        return new Fecha(this.fechaActual);
    }

}
