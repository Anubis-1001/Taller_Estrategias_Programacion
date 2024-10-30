import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Objeto {
    private double peso;
    private double valor;

    public Objeto(double peso, double valor) {
        this.peso = peso;
        this.valor = valor;
    }

    public double getPeso() {
        return peso;
    }

    public double getValor() {
        return valor;
    }

    public double valorPorPeso() {
        return valor / peso;
    }
}

class Container {
    private double pesoMaximo;
    private List<Objeto> objetos;
    private List<Object> cargaSeleccionada; 

    public Container(double pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
        this.objetos = new ArrayList<>();
        this.cargaSeleccionada = new ArrayList<>();
    }

    public void agregarObjeto(Objeto objeto) {
        objetos.add(objeto);
    }

    public void calcularCarga() {
        System.out.println("Heurística 1: Seleccionar el objeto más valioso");
        cargaSeleccionada = seleccionarPorValor();
        mostrarResultados();

        System.out.println("\nHeurística 2: Seleccionar el objeto más ligero");
        cargaSeleccionada = seleccionarPorPeso();
        mostrarResultados();

        System.out.println("\nHeurística 3: Seleccionar el objeto con mayor valor por unidad de peso");
        cargaSeleccionada = seleccionarPorValorPorPeso();
        mostrarResultados();
    }

    private List<Object> seleccionarPorValor() {
        objetos.sort(Comparator.comparingDouble(Objeto::getValor).reversed());
        return seleccionarObjetos();
    }

    private List<Object> seleccionarPorPeso() {
        objetos.sort(Comparator.comparingDouble(Objeto::getPeso));
        return seleccionarObjetos();
    }

    private List<Object> seleccionarPorValorPorPeso() {
        objetos.sort(Comparator.comparingDouble(Objeto::valorPorPeso).reversed());
        return seleccionarObjetos();
    }

    private List<Object> seleccionarObjetos() {
        double pesoActual = 0;
        List<Object> carga = new ArrayList<>();

        for (Objeto objeto : objetos) {
            if (pesoActual + objeto.getPeso() <= pesoMaximo) {
                carga.add(objeto);
                pesoActual += objeto.getPeso();
            } else {
                
                double fraccion = (pesoMaximo - pesoActual) / objeto.getPeso();
                carga.add(new Fraccion(objeto, fraccion));
                break;
            }
        }

        return carga;
    }

    private void mostrarResultados() {
        double valorTotal = 0;
        for (Object item : cargaSeleccionada) {
            if (item instanceof Objeto) {
                Objeto objeto = (Objeto) item;
                System.out.printf("Objeto de %.2f kg - Valor: %.2f%n", objeto.getPeso(), objeto.getValor());
                valorTotal += objeto.getValor();
            } else if (item instanceof Fraccion) {
                Fraccion fraccion = (Fraccion) item;
                double fraccionValor = fraccion.getFraccion() * fraccion.getObjeto().getValor();
                System.out.printf("Fracción de %.2f kg: %.2f - Valor: %.2f%n",
                        fraccion.getObjeto().getPeso(), fraccion.getFraccion(), fraccionValor);
                valorTotal += fraccionValor;
            }
        }
        System.out.printf("Valor total de la carga: %.2f%n", valorTotal);
    }

    private static class Fraccion {
        private Objeto objeto;
        private double fraccion;

        public Fraccion(Objeto objeto, double fraccion) {
            this.objeto = objeto;
            this.fraccion = fraccion;
        }

        public Objeto getObjeto() {
            return objeto;
        }

        public double getFraccion() {
            return fraccion;
        }
    }
}

public class MochilaFraccionaria {
    public static void main(String[] args) {
        Container container = new Container(50);

        
        container.agregarObjeto(new Objeto(10, 60));   
        container.agregarObjeto(new Objeto(20, 100));  
        container.agregarObjeto(new Objeto(30, 120));  

        
        container.calcularCarga();
    }
}

