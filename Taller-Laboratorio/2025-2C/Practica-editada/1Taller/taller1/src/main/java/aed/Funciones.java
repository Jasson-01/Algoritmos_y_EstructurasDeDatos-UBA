package aed;

class Funciones {

    /*** Primera parte: Funciones en java ***/

    int cuadrado(int x) {
        // COMPLETAR
        return x * x;
    }

    double distancia(double x, double y) {
        // COMPLETAR
        double op = x * x + y * y;
        return Math.sqrt(op);
    }

    boolean esPar(int n) {
        // COMPLETAR
        return n % 2 == 0;
    }

    boolean esBisiesto(int n) {
        // COMPLETAR
        if ((n % 4 == 0 && n % 100 != 0) || n % 400 == 0) {
            return true;
        }
        return false;
    }

    int factorialIterativo(int n) {
        // COMPLETAR
        int res = 1;
        for (int i = n; i > 0; i--) {
            res *= i;
        }
        return res;
    }

    int factorialRecursivo(int n) {
        // COMPLETAR
        int res = 1;
        if (n == 0) {
            res = 1;
        } else {
            res = n * factorialRecursivo(n - 1);
        }
        return res;
    }

    boolean esPrimo(int n) {
        // COMPLETAR
        if (n == 2) {
            return true;
        } else if (n < 2) {
            return false;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    int sumatoria(int[] numeros) {
        // COMPLETAR
        int acumulador = 0;
        for (int i = 0; i < numeros.length; i++) {
            acumulador += numeros[i];
        }
        return acumulador;
    }

    int busqueda(int[] numeros, int buscado) {
        // COMPLETAR
        int pos = 0;
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == buscado) {
                pos = i;
            }
        }
        return pos;
    }

    boolean tienePrimo(int[] numeros) {
        // COMPLETAR
        for (int i = 0; i < numeros.length; i++) {
            if (esPrimo(numeros[i])) {
                return true;
            }
        }
        return false;
    }

    boolean todosPares(int[] numeros) {
        // COMPLETAR
        for (int i = 0; i < numeros.length; i++) {
            if (!esPar(numeros[i])) {
                return false;
            }
        }
        return true;
    }

    boolean esPrefijo(String s1, String s2) {
        // COMPLETAR
        if (s1.length() > s2.length()) {
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            if (s2.charAt(i) != s1.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    boolean esSufijo(String s1, String s2) {
        // COMPLETAR
        if (s1.length() > s2.length()) {
            return false;
        }
        int longitudS2 = s2.length() - 1;
        for (int i = s1.length() - 1; i >= 0; i--) {
            if (s2.charAt(longitudS2) != s1.charAt(i)) {
                return false;
            }
            longitudS2--;
        }
        return true;
    }

    /*** Segunda parte: Debugging ***/

    boolean xor(boolean a, boolean b) {
        return (a || b) && (!(a && b));
    }

    boolean iguales(int[] xs, int[] ys) {
        boolean res = true;
        int j = 0;
        for (int i = 0; i <= xs.length - 1; i++) {
            if ((xs[i] != ys[j]) || (xs.length != ys.length)) {
                res = false;
            } else {
                j++;
            }
        }
        return res;
    }

    boolean ordenado(int[] xs) {
        boolean res = true;
        for (int i = 0; i < xs.length - 1; i++) {
            if (xs[i] > xs[i + 1]) {
                res = false;
            }
        }
        return res;
    }

    int maximo(int[] xs) {
        int res = xs[0]; // ojo
        for (int i = 0; i <= xs.length - 1; i++) {
            if (xs[i] > res)
                res = xs[i]; // ojo
        }
        return res;
    }

    boolean todosPositivos(int[] xs) {
        boolean res = true; // OJO
        for (int x : xs) {
            if (x > 0) {
                res = true;
            } else {
                return false; // OJO
            }
        }
        return res;
    }

}
