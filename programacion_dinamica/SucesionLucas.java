import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SucesionLucas {

    // Método de Lucas dinámico por tabulación
    public static int[] lucasTabulation(int n) {
        if (n == 0) return new int[]{2};
        if (n == 1) return new int[]{2, 1};

        int[] table = new int[n + 1];
        table[0] = 2;
        table[1] = 1;

        for (int i = 2; i <= n; i++) {
            table[i] = table[i - 1] + table[i - 2];
        }
        
        return table;
    }

    // Método de Lucas dinámico por memorización
    public static int lucasMemoization(int n, Map<Integer, Integer> memo) {
        if (n == 0) return 2;
        if (n == 1) return 1;

        if (!memo.containsKey(n)) {
            memo.put(n, lucasMemoization(n - 1, memo) + lucasMemoization(n - 2, memo));
        }

        return memo.get(n);
    }

    public static void main(String[] args) {
        int n = 10; // Número de términos de la sucesión

        // Calcular sucesión de Lucas usando tabulación
        int[] lucasTab = lucasTabulation(n);
        System.out.println("Secuencia de Lucas (Tabulación): " + Arrays.toString(lucasTab));

        // Calcular sucesión de Lucas usando memorización
        Map<Integer, Integer> memo = new HashMap<>();
        int[] lucasMem = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            lucasMem[i] = lucasMemoization(i, memo);
        }
        System.out.println("Secuencia de Lucas (Memorización): " + Arrays.toString(lucasMem));
    }
}

