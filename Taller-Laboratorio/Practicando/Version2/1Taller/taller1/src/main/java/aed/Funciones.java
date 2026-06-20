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
        if (n > 0){
            res = n * factorialRecursivo( n - 1 );
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

        for (int i=2; i<n; i++){
           if(n % i == 0){
             return false;
           }  
        }
        return true;
    }

    int sumatoria(int[] numeros) {
        // COMPLETAR
        int acumulador = 0;
        for(int i=0; i < numeros.length; i++){
            acumulador += numeros[i];
        }
        return acumulador;
    }

    int busqueda(int[] numeros, int buscado) {
        // COMPLETAR
        for(int i=0; i<numeros.length; i++){
            if( buscado == numeros[i]){
                return i;
            }
        }
        return -1;
    }

    boolean tienePrimo(int[] numeros) {
        // COMPLETAR
        for(int i=0; i<numeros.length; i++){
            if(esPrimo(numeros[i])){
                return true;
            }
        }
        return false;
    }

    boolean todosPares(int[] numeros) {
        // COMPLETAR
        for(int i=0; i<numeros.length; i++){
            if(numeros[i] % 2 == 1){
                return false;
            }
        }
        return true;
    }

    boolean esPrefijo(String s1, String s2) {
        // COMPLETAR
        if(s1.length() > s2.length()){
            return false;
        }

        for(int i=0; i<s1.length(); i++){
            if ( s1.charAt(i) != s2.charAt(i)){
                return false;
            }
        }
        return true;
    }

    boolean esSufijo(String s1, String s2) {
        // COMPLETAR
        if(s1.length() > s2.length()){
            return false;
        } 

        for(int i=s1.length()-1; i>=0; i--){
            if(s1.charAt(i) != s2.charAt(s2.length() - s1.length() + i)){
                return false;
            }
        }
        return true;
    }

    /*** Segunda parte: Debugging ***/

    boolean xor(boolean a, boolean b) {
        return (a || b) && !(a && b);
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
            if (xs[i] > xs[i + 1]) {
                res = false;
            }
        }
        return res;
    }

    int maximo(int[] xs) {
        int res = 0;
        for (int i = 0; i <= xs.length; i++) {
            if (xs[i] > res)
                res = i;
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
