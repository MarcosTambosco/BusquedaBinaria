package Test;

// Clase que extiende Thread para realizar la búsqueda binaria en paralelo
class BusquedaBinariaThread extends Thread {
    private int[] arreglo;       // Arreglo sobre el que se realiza la búsqueda
    private int valorBuscado;    // Valor que se desea encontrar
    private int inicio;          
    private int fin;             
    private int resultado = -1;  // Variable para guardar el índice donde se encuentra el valor (-1 si no se encuentra)

    // Constructor
    public BusquedaBinariaThread(int[] arreglo, int valorBuscado, int inicio, int fin) {
        this.arreglo = arreglo;
        this.valorBuscado = valorBuscado;
        this.inicio = inicio;
        this.fin = fin;
    }

    // Inicializo el hilo
    public void run() {
        int i = inicio;
        int f = fin;

        // Bucle de busqueda binaria tradicional
        while (i <= f) {
            int medio = (i + f) / 2;

            // Si el elemento en la posición medio es el valor buscado, lo guarda y termina
            if (arreglo[medio] == valorBuscado) {
                resultado = medio;
                return;
            }

            // Si el valor buscado es menor, buscar en la mitad izquierda
            if (valorBuscado < arreglo[medio]) {
                f = medio - 1;
            } else {
                // Si es mayor, buscar en la mitad derecha
                i = medio + 1;
            }
        }
    }

    // Método para obtener el resultado de la búsqueda al finalizar el hilo
    public int getResultado() {
        return resultado;
    }
}

// Clase principal donde se prueba la búsqueda binaria concurrente
public class TestBusquedaBinariaConcurrente {

    public static void main(String[] args) {
       
        int[] datos = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};

        
        int valor = 13;

        // División del arreglo en dos mitades para asignar a cada hilo
        int mitad = datos.length / 2;

        // Creación de dos hilos, uno para la primera mitad, otro para la segunda mitad
        BusquedaBinariaThread hilo1 = new BusquedaBinariaThread(datos, valor, 0, mitad - 1);
        BusquedaBinariaThread hilo2 = new BusquedaBinariaThread(datos, valor, mitad, datos.length - 1);

        // Captura del tiempo de inicio
        long tiempoInicio = System.nanoTime();

        // Inicio de ambos hilos
        hilo1.start();
        hilo2.start();

        // Espera a que ambos hilos terminen su ejecución
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Captura del tiempo final y cálculo de la duración
        long tiempoFin = System.nanoTime();
        long duracion = tiempoFin - tiempoInicio;

        // Determina cuál de los hilos encontró el valor (si alguno lo encontró)
        int resultado;
        if (hilo1.getResultado() != -1) {
            resultado = hilo1.getResultado();
        } else {
            resultado = hilo2.getResultado();
        }

        // Muestra por pantalla el resultado de la búsqueda
        if (resultado != -1) {
            System.out.println("✔ Valor " + valor + " encontrado en la posición: " + resultado);
        } else {
            System.out.println("✘ Valor " + valor + " no encontrado.");
        }

        // Muestra el tiempo de ejecución en nanosegundos
        System.out.println("⏱ Tiempo de ejecución (concurrente): " + duracion + " nanosegundos");
    }
}
