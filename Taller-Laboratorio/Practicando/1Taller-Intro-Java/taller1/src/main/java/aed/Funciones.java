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
        double res = Math.sqrt(x * x + y * y);
        return res;
    }

    boolean esPar(int n) {
        // COMPLETAR
        return n % 2 == 0;
    }

    boolean esBisiesto(int n) {
        // COMPLETAR
        if (n % 4 == 0 && n % 100 != 0 || n % 400 == 0) {
            return true;
        }
        return false;
    }

    int factorialIterativo(int n) {
        // COMPLETAR
        int res = 1;
        for (int i = n; i > 0; i--) {
            if (i == 1 || i == 0) {
                res *= 1;
            } else {
                res *= i;
            }
        }
        return res;
    }

    int factorialRecursivo(int n) {
        // COMPLETAR
        int res = 1;
        if (n == 0) {
            res *= 1;
        } else {
            res = n * factorialRecursivo(n - 1);
        }
        return res;
    }

    boolean esPrimo(int n) {
        // COMPLETAR
        if (n <= 1) {
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
        int res = 0;
        for (int i = 0; i < numeros.length; i++) {
            res += numeros[i];
        }
        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        // COMPLETAR
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == buscado) {
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
            if (numeros[i] % 2 != 0) {
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

        int j = s2.length() - 1;
        for (int i = s1.length() - 1; i >= 0; i--) {
            if (s1.charAt(i) != s2.charAt(j)) {
                return false;
            }
            j -= 1;
        }
        return true;
    }

    /*** Segunda parte: Debugging ***/

    boolean xor(boolean a, boolean b) {
        return (a || b) && !(a && b); // Lo que estaba mal fueron que no habia parentesis(Osea no habia orden en sus
                                      // operaciones)
    }

    boolean iguales(int[] xs, int[] ys) {
        boolean res = true;

        int j = 0;
        for (int i = 0; i < xs.length; i++) {
            if (xs[i] != ys[j] || xs.length != ys.length) {
                res = false;
            } else {
                j += 1; // el error era si alguno de los array era mas largo que el otro y para recorrer
                        // el otro arreglo use otro indice separado del indice del for
            }
        }
        return res;
    }

    boolean ordenado(int[] xs) {
        boolean res = true;
        for (int i = 0; i < xs.length - 1; i++) { // Tenia el problema de rangos cuando es i+1 y la longitud del arreglo
                                                  // xs
            if (xs[i] > xs[i + 1]) {
                res = false;
            }
        }
        return res;
    }

    int maximo(int[] xs) {
        int res = xs[0];
        for (int i = 0; i < xs.length; i++) {
            if (xs[i] > res) {
                res = xs[i]; // Aqui devolvia solo la posicion no el elemento maximo
            }
        }
        return res;
    }

    boolean todosPositivos(int[] xs) {
        boolean res = true;
        for (int x : xs) {
            if (x <= 0) { // Aqui habia un problema con la guarda
                res = false; 
            }
        }
        return res;
    }

}
