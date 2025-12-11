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
        return horario;
    }

    public Fecha fecha() {
        // Implementar
        return new Fecha(this.fecha);
    }

    public String mensaje() {
        // Implementar
        return mensaje;
    }

    // this es necesario cuando hay AMBIGÜEDAD (dos variables con el mismo nombre en
    // el mismo contexto).

    // ¿Por qué NO necesitas this aquí?Porque NO hay ambigüedad. No hay ninguna
    // variable local llamada mensaje, fecha o horario en este método.Java sabe
    // automáticamente que te refieres a los atributos de la clase:

    @Override
    public String toString() {
        // Implementar
        return mensaje + " @ " + fecha + " " + horario;
    }

    @Override
    public boolean equals(Object otro) {
        // Implementar
        if (otro == null) {
            return false;
        }

        boolean claseDistinta = otro.getClass() != this.getClass();
        if (claseDistinta) {
            return false;
        }

        Recordatorio otroRecordatorio = (Recordatorio) otro;

        // mensaje es un String, y NO debes usar == para comparar Strings. Debes usar
        // .equals():
        // fecha y horario: Son objetos, también debes usar .equals():

        return mensaje.equals(otroRecordatorio.mensaje) && fecha.equals(otroRecordatorio.fecha)
                && horario.equals(otroRecordatorio.horario);

    }

}

// --------------------------------------------------------------------------------

// mensaje == otroRecordatorio.mensaje // ❌ Compara referencias, no contenido
// mensaje.equals(otroRecordatorio.mensaje) // ✅ Compara contenido

// fecha == otroRecordatorio.fecha // ❌ Compara referencias, no contenido
// fecha.equals(otroRecordatorio.fecha) // ✅ Compara contenido

// horario == otroRecordatorio.horario // ❌ Compara referencias, no contenido
// horario.equals(otroRecordatorio.horario) // ✅ Compara contenido

// --------------------------------------------------------------------------------

// String s1 = new String("Hola");
// String s2 = new String("Hola");

// s1 == s2 // false ❌ (son objetos diferentes en memoria)
// s1.equals(s2) // true ✅ (tienen el mismo contenido)

// --------------------------------------------------------------------------------

// Fecha f1 = new Fecha(15, 3);
// Fecha f2 = new Fecha(15, 3);

// f1 == f2 // false ❌ (son objetos diferentes)
// f1.equals(f2) // true ✅ (tienen el mismo día y mes)

// ################################################################################

// Tipos primitivos: int, double, boolean, char, float etc. --> usan ==
// Tipos no primitivos: String, Fecha, Horario, etc. --> usan .equals()

// Línea 61: Llamadas a otros métodos:

// return mensaje.equals(otroRecordatorio.mensaje) // ← Llama al equals() de
// String
// && fecha.equals(otroRecordatorio.fecha) // ← Llama al equals() de Fecha
// && horario.equals(otroRecordatorio.horario); // ← Llama al equals() de
// Horario

// Explicación detallada:

// 1. mensaje.equals(otroRecordatorio.mensaje)
// Llama al método equals() de la clase String
// Este método está implementado por Java (no lo escribiste tú)
// Compara el contenido de los dos Strings

// 2. fecha.equals(otroRecordatorio.fecha)
// Llama al método equals() de la clase Fecha
// Este método lo implementaste tú en Fecha.java
// Compara dia y mes

// 3. horario.equals(otroRecordatorio.horario)
// Llama al método equals() de la clase Horario
// Este método lo implementaste tú en Horario.java
// Compara hora y minutos
