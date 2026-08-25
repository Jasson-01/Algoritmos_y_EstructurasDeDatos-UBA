package aed;

public class Agenda {
    private Fecha fecha;
    private ArregloRedimensionableDeRecordatorios misRecordatorios;

    public Agenda(Fecha fechaActual) {
        // Implementar
        this.fecha = new Fecha(fechaActual);
        this.misRecordatorios = new ArregloRedimensionableDeRecordatorios();
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        // Implementar
        misRecordatorios.agregarAtras(recordatorio);
    }

    @Override
    public String toString() {
        // Implementar
        String recordatoriosActuales = "";
        for(int i=0; i<misRecordatorios.longitud(); i++){
            Recordatorio recordatorioElem = misRecordatorios.obtener(i); 
            if( recordatorioElem.fecha().equals(fecha)){
                recordatoriosActuales += recordatorioElem.toString() + "\n";
            }
        }

        return fecha.toString() + "\n" + "=====" + "\n" + recordatoriosActuales;
    }

    public void incrementarDia() {
        // Implementar
        fecha.incrementarDia();
    }

    public Fecha fechaActual() {
        // Implementar
        return new Fecha(fecha);
    }

}
