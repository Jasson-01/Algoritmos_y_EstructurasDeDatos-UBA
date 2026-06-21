package aed;

class Funciones {

    /*** Primera parte: Funciones en java ***/

    int cuadrado(int x) {
        // COMPLETAR
        int res = x * x;
        return res;
    }

    double distancia(double x, double y) {
        // COMPLETAR
        double distancia = Math.sqrt(x * x + y * y);
        return distancia;
    }

    boolean esPar(int n) {
        // COMPLETAR
        boolean res = n % 2 == 0;
        return res;
    }

    boolean esBisiesto(int n) {
        // COMPLETAR
        boolean res = (n % 4 == 0 && n % 100 != 0) || (n % 400 == 0);
        return res;
    }

    int factorialIterativo(int n) {
        // COMPLETAR
        int res = n;
        for (int i = n - 1; i > 0; i--) {
            res *= i;
        }

        if (n == 0) {
            res = 1;
        }

        return res;
    }

    int factorialRecursivo(int n) {
        // COMPLETAR
        int res;
        if (n > 0) {
            res = n * factorialRecursivo(n - 1);
        } else {
            res = 1;
        }
        return res;
    }

    boolean esPrimo(int n) {
        // COMPLETAR
        if (n == 0 || n == 1) {
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
        for (int i = 0; i < numeros.length; i++) {
            if (buscado == numeros[i]) {
                return i;
            }
        }
        return -1;
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
            if (numeros[i] % 2 == 1) {
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
            if (s1.charAt(i) != s2.charAt(i)) {
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

        for (int i = s1.length() - 1; i >= 0; i--) {
            if (s1.charAt(i) != s2.charAt(s2.length() - s1.length() + i)) {
                return false;
            }
        }
        return true;
    }

    /*** Segunda parte: Debugging ***/

    // El problema AQUI es el uso adecuado de los parentesis
    boolean xor(boolean a, boolean b) {
        return (a || b) && !(a && b);
    }

    // El problema AQUI esta en las longitudes de las listas
    boolean iguales(int[] xs, int[] ys) {
        boolean res = true;
        if (xs.length != ys.length) {
            return false;
        }

        for (int i = 0; i < xs.length; i++) {
            if (xs[i] != ys[i]) {
                res = false;
            }
        }
        return res;
    }

    // El problema AQUI es cuando la longitud de la lista es 1 y el rango de los
    // valos obtenidos por el for.
    boolean ordenado(int[] xs) {

        if (xs.length == 1) {
            return true;
        }

        boolean res = true;
        for (int i = 0; i < xs.length - 1; i++) {
            if (xs[i] > xs[i + 1]) {
                res = false;
            }
        }
        return res;
    }

    // El problema AQUI era que debia comenzar con el primer valor de la lista y no
    // con 0 y ademas el if debe se el valor de la lista en la posicion que le gano
    // en la condicion ;D.
    int maximo(int[] xs) {
        int res = xs[0];
        for (int i = 0; i < xs.length; i++) {
            if (xs[i] > res)
                res = xs[i];
        }
        return res;
    }

    // El problema AQUI es cuando la longitud es cero y tambien radicaba en que la
    // variable res se reasignaba en cada iteración del bucle, haciendo que su valor
    // final dependiera únicamente del último elemento del array.
    boolean todosPositivos(int[] xs) {
        boolean res = false;

        if (xs.length == 0) {
            return true;
        }

        for (int x : xs) {
            if (x > 0) {
                res = true;
            } else {
                return false;
            }
        }
        return res;
    }

}
