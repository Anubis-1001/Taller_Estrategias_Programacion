public class Mochila {

    // Método para resolver el problema de la mochila
    public static int mochila(int W, int[] pesos, int[] valores, int n) {
        int[][] K = new int[n + 1][W + 1];

        // Construir la tabla de programación dinámica
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (pesos[i - 1] <= w)
                    K[i][w] = Math.max(valores[i - 1] + K[i - 1][w - pesos[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }
        
        // Retornar el valor máximo que cabe en la mochila de capacidad W
        return K[n][W];
    }

    public static void main(String[] args) {
        int[] valores = {2, 5, 10, 14, 15}; // Valores de los elementos
        int[] pesos = {1, 3, 4, 5, 7}; // Pesos de los elementos
        int W = 8; // Peso máximo de la mochila
        int n = valores.length; // Número de elementos

        int maxValor = mochila(W, pesos, valores, n);
        System.out.println("El valor máximo que se puede obtener es: " + maxValor);
    }
}

