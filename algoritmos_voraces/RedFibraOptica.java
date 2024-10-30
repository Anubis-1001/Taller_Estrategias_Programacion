import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Clase que representa una conexión entre dos municipios
class Conexion implements Comparable<Conexion> {
    int origen, destino, costo;

    public Conexion(int origen, int destino, int costo) {
        this.origen = origen;
        this.destino = destino;
        this.costo = costo;
    }

    @Override
    public int compareTo(Conexion otra) {
        return this.costo - otra.costo; // Ordenar por costo
    }
}

// Clase para representar la red de fibra óptica y aplicar el Algoritmo de Kruskal
class RedFibraOptica {
    private int municipios; // Número de municipios
    private List<Conexion> conexiones; // Lista de todas las conexiones posibles

    public RedFibraOptica(int municipios) {
        this.municipios = municipios;
        conexiones = new ArrayList<>();
    }

    public void agregarConexion(int origen, int destino, int costo) {
        conexiones.add(new Conexion(origen, destino, costo));
    }

    // Encuentra el representante (o raíz) del conjunto de un nodo
    private int encontrar(int[] padres, int i) {
        if (padres[i] != i) {
            padres[i] = encontrar(padres, padres[i]);
        }
        return padres[i];
    }

    // Une dos conjuntos
    private void unir(int[] padres, int[] rango, int x, int y) {
        int raizX = encontrar(padres, x);
        int raizY = encontrar(padres, y);

        if (rango[raizX] < rango[raizY]) {
            padres[raizX] = raizY;
        } else if (rango[raizX] > rango[raizY]) {
            padres[raizY] = raizX;
        } else {
            padres[raizY] = raizX;
            rango[raizX]++;
        }
    }

    // Algoritmo de Kruskal para encontrar el Árbol de Recubrimiento Mínimo
    public List<Conexion> kruskalARM() {
        List<Conexion> arm = new ArrayList<>(); // Lista para el Árbol de Recubrimiento Mínimo
        Collections.sort(conexiones);           // Ordena las conexiones por costo

        int[] padres = new int[municipios];
        int[] rango = new int[municipios];

        // Inicializa los conjuntos de cada municipio
        for (int i = 0; i < municipios; i++) {
            padres[i] = i;
            rango[i] = 0;
        }

        for (Conexion conexion : conexiones) {
            int raizOrigen = encontrar(padres, conexion.origen);
            int raizDestino = encontrar(padres, conexion.destino);

            // Si no forman un ciclo, agregamos la conexión al ARM
            if (raizOrigen != raizDestino) {
                arm.add(conexion);
                unir(padres, rango, raizOrigen, raizDestino);
            }
        }

        return arm;
    }

    // Método principal para demostrar el ARM y su costo total
    public static void main(String[] args) {
        int numMunicipios = 5; // Número de municipios
        RedFibraOptica red = new RedFibraOptica(numMunicipios);

        // Ejemplo de conexiones con sus costos
        red.agregarConexion(0, 1, 10);
        red.agregarConexion(0, 2, 6);
        red.agregarConexion(0, 3, 5);
        red.agregarConexion(1, 3, 15);
        red.agregarConexion(2, 3, 4);

        List<Conexion> arm = red.kruskalARM();
        int costoTotal = 0;

        System.out.println("Conexiones en el Árbol de Recubrimiento Mínimo:");
        for (Conexion conexion : arm) {
            System.out.println("Municipio " + conexion.origen + " - Municipio " + conexion.destino + " con costo " + conexion.costo);
            costoTotal += conexion.costo;
        }

        System.out.println("Costo total de la instalación de la red: " + costoTotal + " pesos colombianos");
    }
}

