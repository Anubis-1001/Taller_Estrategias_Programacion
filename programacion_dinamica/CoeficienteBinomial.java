import java.util.HashMap;
import java.util.Map;

public class CoeficienteBinomial {

    public static void main(String[] args) {
        int n = 8; // Nivel del triángulo de Pascal a calcular
        int k = 3;
        // Ejecutar y obtener resultados de tabulación
        System.out.println("Método de Tabulación:");
        int[][] resultadoTabulacion = calcularCoeficienteBinomialTabulacion(n);
        imprimirResultadoTabulacion(resultadoTabulacion, n, k);

        // Ejecutar y obtener resultados de memoización
        System.out.println("\nMétodo de Memoización:");
        Map<String, Integer> memo = new HashMap<>(); // Tabla para memoización
        Map<String, Integer> resultadoMemoizacion = calcularCoeficienteBinomialMemoizacion(n, memo);
        imprimirResultadoMemoizacion(resultadoMemoizacion, n, k);
    }

    // Método de Tabulación (Bottom-Up) que devuelve un array bidimensional
    public static int[][] calcularCoeficienteBinomialTabulacion(int n) {
        int[][] coeficientesBinomiales = new int[n + 1][n + 1];

        // Construir la tabla usando tabulación
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    coeficientesBinomiales[i][j] = 1;
                } else {
                    coeficientesBinomiales[i][j] = coeficientesBinomiales[i - 1][j - 1] + coeficientesBinomiales[i - 1][j];
                }
            }
        }
        return coeficientesBinomiales;
    }

    // Método de Memoización (Top-Down) que devuelve un HashMap con los resultados
    public static Map<String, Integer> calcularCoeficienteBinomialMemoizacion(int n, Map<String, Integer> memo) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                coeficienteBinomial(i, j, memo); // Llenar el HashMap con los resultados
            }
        }
        return memo;
    }

    // Método recursivo para calcular coeficientes binomiales con memoización
    private static int coeficienteBinomial(int n, int k, Map<String, Integer> memo) {
        // Condiciones base
        if (k == 0 || k == n) {
            return 1;
        }

        // Crear una clave única para el par (n, k)
        String clave = n + "," + k;

        // Verificar si el valor ya fue calculado
        if (memo.containsKey(clave)) {
            return memo.get(clave);
        }

        // Calcular y almacenar en la tabla de memoización
        int resultado = coeficienteBinomial(n - 1, k - 1, memo) + coeficienteBinomial(n - 1, k, memo);
        memo.put(clave, resultado);

        return resultado;
    }

    // Método para imprimir el resultado de la tabulación en el formato deseado
    private static void imprimirResultadoTabulacion(int[][] resultado, int n, int k) {
        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == n && j == k) {
                    System.out.print("|" + resultado[i][j] + "| ");
                } else {
                    System.out.print(resultado[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // Método para imprimir el resultado de memoización en el formato deseado
    private static void imprimirResultadoMemoizacion(Map<String, Integer> resultado, int n, int k) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                String clave = i + "," + j;
                int valor = resultado.getOrDefault(clave, 1);
                if (i == n && j == k ) {
                    System.out.print("|" + valor + "| ");
                } else {
                    System.out.print(valor + " ");
                }
            }
            System.out.println();
        }
    }
}

