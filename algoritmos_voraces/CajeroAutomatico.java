import java.util.HashMap;
import java.util.Map;

public class CajeroAutomatico {

    public static Map<Integer, Integer> dispensarDinero(int montoSolicitado, Map<Integer, Integer> billetes) {
        
        if (montoSolicitado % 10000 != 0) {
            throw new IllegalArgumentException("El monto solicitado debe ser múltiplo de 10,000.");
        }

        Map<Integer, Integer> billetesEntregados = new HashMap<>();
        billetesEntregados.put(100000, 0);
        billetesEntregados.put(50000, 0);
        billetesEntregados.put(20000, 0);
        billetesEntregados.put(10000, 0);

        
        for (int denominacion : new int[]{100000, 50000, 20000, 10000}) {
            
            int maxBilletes = Math.min(montoSolicitado / denominacion, billetes.get(denominacion));

            
            montoSolicitado -= maxBilletes * denominacion;
            billetesEntregados.put(denominacion, maxBilletes);
        }

        
        if (montoSolicitado > 0) {
            throw new IllegalArgumentException("No hay suficiente cantidad de billetes para entregar el monto solicitado.");
        }

        
        return billetesEntregados;
    }

    public static void main(String[] args) {
	
	Map<Integer, Integer> billetes = new HashMap<>();
        billetes.put(100000, 50);
        billetes.put(50000, 100);
        billetes.put(20000, 200);
        billetes.put(10000, 300);


        try {
            Map<Integer, Integer> resultado = dispensarDinero(390000, billetes);
            System.out.println("Billetes entregados:");
            for (Map.Entry<Integer, Integer> entry : resultado.entrySet()) {
                System.out.println("Denominación " + entry.getKey() + ": " + entry.getValue() + " billetes");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
	
	try {
            Map<Integer, Integer> resultado = dispensarDinero(890000, billetes);
            System.out.println("Billetes entregados:");
            for (Map.Entry<Integer, Integer> entry : resultado.entrySet()) {
                System.out.println("Denominación " + entry.getKey() + ": " + entry.getValue() + " billetes");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}

