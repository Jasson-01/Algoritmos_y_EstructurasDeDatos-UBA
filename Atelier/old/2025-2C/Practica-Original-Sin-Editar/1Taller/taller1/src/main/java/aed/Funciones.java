package aed;

class Funciones {

/***  Primera parte: Funciones en java ***/

    int cuadrado(int x) {
        // COMPLETAR
        return 0;
    }

    double distancia(double x, double y) {
        // COMPLETAR
        return 0.0;
    }

    boolean esPar(int n) {
        // COMPLETAR
        return false;
    }

    boolean esBisiesto(int n) {
        // COMPLETAR
        return false;
    }

    int factorialIterativo(int n) {
        // COMPLETAR
        return 0;
    }

    int factorialRecursivo(int n) {
        // COMPLETAR
        return 0;
    }

    boolean esPrimo(int n) {
        // COMPLETAR
        return false;
    }

    int sumatoria(int[] numeros) {
        // COMPLETAR
        return 0;
    }

    int busqueda(int[] numeros, int buscado) {
        // COMPLETAR
        return 0;
    }

    boolean tienePrimo(int[] numeros) {
        // COMPLETAR
        return false;
    }

    boolean todosPares(int[] numeros) {
        // COMPLETAR
        return false;
    }

    boolean esPrefijo(String s1, String s2) {
        // COMPLETAR
        return false;
    }

    boolean esSufijo(String s1, String s2) {
        // COMPLETAR
        return false;
    }

/***  Segunda parte: Debugging ***/

    boolean xor(boolean a, boolean b) {
        return a || b && !(a && b);
    }

    boolean iguales(int[] xs, int[] ys) {
        boolean res = true;

        for (int i = 0; i < xs.length; i++) {
            if (xs[i] != ys[i]) {
                res = false;
            }
        }
        return res;
    }

    boolean ordenado(int[] xs) {
        boolean res = true;
        for (int i = 0; i < xs.length; i++) {
            if (xs[i] > xs [i+1]) {
                res = false;
            }
        }
        return res;
    }

    int maximo(int[] xs) {
        int res = 0;
        for (int i = 0; i <= xs.length; i++) {
            if (xs[i] > res) res = i;
        }
        return res;
    }

    boolean todosPositivos(int[] xs) {
        boolean res = false;
        for (int x : xs) {
            if (x > 0) {
                res = true;
            } else {
                res = false;
            }
        }
        return res;
    }

}
