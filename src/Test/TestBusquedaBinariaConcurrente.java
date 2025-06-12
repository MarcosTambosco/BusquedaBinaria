package Test;

import java.util.Arrays;
import java.util.Random;

// Clase hilo que realiza la busqueda binaria en un rango del arreglo
class BusquedaThread extends Thread {
    private int[] arreglo;        // Arreglo sobre el que se hace la busqueda
    private int numeroBuscado;    // Numero a buscar
    private int inicio;           // Inicio del rango
    private int fin;              // Fin del rango
    private int resultado = -1;   // Resultado (-1 si no se encuentra)

    // Constructor del hilo
    public BusquedaThread(int[] arreglo, int numeroBuscado, int inicio, int fin) {
        this.arreglo = arreglo;
        this.numeroBuscado = numeroBuscado;
        this.inicio = inicio;
        this.fin = fin;
    }

    // Metodo que ejecuta el hilo
    @Override
    public void run() {
        int bajo = inicio;
        int alto = fin;

        // Algoritmo de busqueda binaria normal dentro del rango asignado
        while (bajo <= alto) {
            int medio = (bajo + alto) / 2;
            int numeroEnMedio = arreglo[medio];

            if (numeroBuscado == numeroEnMedio) {
                resultado = medio; // Si lo encuentra, guarda el resultado
                return;            // Finaliza el hilo
            }
            if (numeroBuscado < numeroEnMedio) {
                alto = medio - 1;  // Busca en la mitad izquierda
            } else {
                bajo = medio + 1;  // Busca en la mitad derecha
            }
        }
    }

    // Metodo para obtener el resultado de la busqueda
    public int getResultado() {
        return resultado;
    }
}

public class TestBusquedaBinariaConcurrente {

    public static void main(String[] args) {
        Random random = new Random();

        int tamanioArray = 1000;                   // Tamaño del arreglo
        int numeroABuscar = random.nextInt(51);  // Numero aleatorio de 0 a 50

        int[] arregloDeNumeros = new int[tamanioArray]; // Creamos el arreglo

        // Llenamos el arreglo con numeros aleatorios de 0 a 50
        for (int i = 0; i < arregloDeNumeros.length; i++) {
            arregloDeNumeros[i] = random.nextInt(51);
        }

        Arrays.sort(arregloDeNumeros); // Ordenamos el arreglo (requisito para busqueda binaria)

        // Mostramos el arreglo por consola
        System.out.println(Arrays.toString(arregloDeNumeros));
        System.out.println("Numero a buscar: " + numeroABuscar);

        // Dividimos el arreglo en 2 partes
        int medio = arregloDeNumeros.length / 2;

        // Creo los dos hilos
        BusquedaThread hilo1 = new BusquedaThread(arregloDeNumeros, numeroABuscar, 0, medio - 1);
        BusquedaThread hilo2 = new BusquedaThread(arregloDeNumeros, numeroABuscar, medio, arregloDeNumeros.length - 1);

        long tiempoInicio = System.nanoTime(); // Inicio del tiempo

        // Iniciamos los 2 hilos
        hilo1.start();
        hilo2.start();

        // Esperamos que los hilos terminen
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Revisamos si alguno encontro el numero
        int resultado = -1;
        if (hilo1.getResultado() != -1) {
            resultado = hilo1.getResultado();
        } else if (hilo2.getResultado() != -1) {
            resultado = hilo2.getResultado();
        }

        long tiempoFin = System.nanoTime();      // Fin del tiempo
        long duracion = tiempoFin - tiempoInicio; // Duracion total

        // Mostramos el resultado
        if (resultado != -1) {
            System.out.println("Numero encontrado en la posicion: " + resultado);
        } else {
            System.out.println("Numero no encontrado.");
        }

        System.out.println("Tiempo de ejecucion: " + duracion + " nanosegundos");
    }
}
