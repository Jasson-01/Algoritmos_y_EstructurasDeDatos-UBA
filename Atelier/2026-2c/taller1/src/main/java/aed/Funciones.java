package aed;

class Funciones {

/***  Primera parte: Funciones en java ***/

    int cuadrado(int x) {
        // COMPLETAR
        return x*x;
    }

    double distancia(double x, double y) {
        // COMPLETAR
        return Math.sqrt(x*x + y*y);
    }

    boolean esPar(int n) {
        // COMPLETAR
        return n % 2 == 0;
    }

    boolean esBisiesto(int n) {
        // COMPLETAR
        // if((n % 4 == 0 && n % 100 != 0) || ( n % 400 == 0)) {
        //    return true;
        // }
        //return false;

        return (n % 4 == 0 && n % 100 != 0) || ( n % 400 == 0); // Forma usando sin ciclos y condicionales.
    }

    int factorialIterativo(int n) {
        // COMPLETAR
        int res = n;
        if(n==1 || n==0){
            res=1;
        } else {
           for(int i=n-1; i>1; i--){
              res *= i;
           } 
        }
        return res;
    }

    int factorialRecursivo(int n) {
        // COMPLETAR
        int res;
        if(n==1 || n==0){
            res = 1;
        } else {
            res = n * factorialRecursivo(n-1);
        }
        return res;
    }

    boolean esPrimo(int n) {
        // COMPLETAR
        boolean res = true;
        
        if(n<2){
            res = false;
        }

        for(int d=2; d<n; d++){
           if( n % d == 0){
              res = false;
           }
        }
        return res;
    }

    int sumatoria(int[] numeros) {
        // COMPLETAR
        int res = 0;
        for(int i=0; i<numeros.length; i++){
            res += numeros[i];
        }
        return res;
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
            if( esPrimo(numeros[i])){
                return true;
            }
        }
        return false;
    }

    boolean todosPares(int[] numeros) {
        // COMPLETAR
        for(int i=0; i<numeros.length; i++){
            if(numeros[i] % 2 != 0){
                return false;
            }
        }
        return true;
    }
    
    //s1 es prefijo de s2
    boolean esPrefijo(String s1, String s2) {
        // COMPLETAR
        if( s1.length() > s2.length()){
            return false;
        }

        for(int i=0; i<s1.length(); i++){
            if( s1.charAt(i) != s2.charAt(i)){
                  return false;
            }
        }

        return true;
    }
    
    //s1 es sufijo de s2
    boolean esSufijo(String s1, String s2) {
        // COMPLETAR
        if(s1.length() == s2.length()){
            return true;
        }

        if(s1.length() > s2.length()){
            return false;
        }

        int j = s2.length()-1;
        for(int i=s1.length()-1; i>=0; i--){
            if( s1.charAt(i) != s2.charAt(j)){
                return false;
            }
            --j;
        }

        return true;
    }

/***  Segunda parte: Debugging ***/

    boolean xor(boolean a, boolean b) {
        return (a || b) && !(a && b); // el error aqui estaba en que le faltaba añadir parentesis antes del &&
    }

    boolean iguales(int[] xs, int[] ys) {
        boolean res = true;

        if( xs.length != ys.length) { //puedo añadir codigo ? error -> longitudes distintas
            return false;
        }

        for (int i = 0; i < xs.length; i++) {
            if (xs[i] != ys[i]) {
                res = false;
            }
        }
        return res;
    }

    boolean ordenado(int[] xs) {
        boolean res = true;
        
        for (int i = 0; i < xs.length - 1; i++) { // Faltaba añadir el -1 en la condicional
            if (xs[i] > xs [i+1]) { 
                res = false;
            }
        }
        return res;
    }

    int maximo(int[] xs) {
        int res = xs[0]; // error -> faltaba inicializar con el primer elemento de la lista.
        for (int i = 0; i < xs.length; i++) {
            if (xs[i] > res) res = xs[i]; // error -> faltaba devolver el elemento, porque antes devolvia solo el indice.
        }
        return res;
    }

    boolean todosPositivos(int[] xs) {
        boolean res = false;
        
        if( xs.length == 0) { // codigo añadido, error -> longitud 0
            res = true;
        }

        for (int x : xs) {
            if (x > 0) {
                res = true;
            } else {
                return false; // Cuando encuentre el false, debe salir de la función
            }
        }
        return res;
    }

}