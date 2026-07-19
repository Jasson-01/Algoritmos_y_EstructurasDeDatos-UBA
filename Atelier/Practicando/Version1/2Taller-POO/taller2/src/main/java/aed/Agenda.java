package aed;

public class Agenda {
    private Fecha fechaActual;
    private ArregloRedimensionableDeRecordatorios recordatorios;

    public Agenda(Fecha fechaActual) {
        // Guardamos una COPIA de la fecha (por seguridad, para que nadie la cambie
        // desde fuera)
        this.fechaActual = new Fecha(fechaActual);
        // Inicializamos nuestra lista vacía
        this.recordatorios = new ArregloRedimensionableDeRecordatorios();
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        // Implementar
        recordatorios.agregarAtras(recordatorio);
    }

    @Override
    public String toString() {
        // Implementar
        String resultado = "" + fechaActual + "\n" + "=====\n";
        for (int i = 0; i < recordatorios.longitud(); i++) {
            // Tu objeto recordatorios es del tipo ArregloRedimensionable..., y ese tipo de
            // objeto no tiene un método .fecha(). Quien tiene fecha es el Recordatorio
            // individual que está guardado adentro.Es como tener una caja de chocolates
            // (recordatorios):No le puedes preguntar a la CAJA "¿De qué sabor eres?".Tienes
            // que obtener(i) el BOMBÓN (Recordatorio r) y preguntarle a ÉL "¿De qué sabor
            // eres?" (r.fecha()).
            Recordatorio r = recordatorios.obtener(i);
            if (r.fecha().equals(fechaActual)) {
                resultado = resultado + r.toString() + "\n";
            }
        }
        return resultado;
    }

    public void incrementarDia() {
        // Implementar
        fechaActual.incrementarDia();
    }

    public Fecha fechaActual() {
        // MAL (con Aliasing): Estas regalando la llave de tu casa
        // return this.fechaActual;
        // BIEN (Sin Aliasing): Entregas una copia nueva e independiente
        return new Fecha(this.fechaActual);
    }

}
