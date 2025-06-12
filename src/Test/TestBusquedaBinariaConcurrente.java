package Test;

import java.util.Arrays;
import java.util.Random;

// Clase para realizar busqueda binaria concurrente usando threads
class BusquedaBinariaConcurrente {

    // Clase interna que extiende Thread y realiza busqueda binaria en un rango del arreglo
    static class BusquedaThread extends Thread {
        private int[] arreglo;       // Arreglo completo donde se buscara
        private int numeroBuscado;   // Numero que queremos encontrar
        private int inicio;          // Indice inicial del rango para este thread
        private int fin;             // Indice final del rango para este thread
        private int resultado = -1;  // Resultado: posicion encontrada o -1 si no encontro

        // Constructor que recibe arreglo, numero buscado y rango donde buscar
        public BusquedaThread(int[] arreglo, int numeroBuscado, int inicio, int fin) {
            this.arreglo = arreglo;
            this.numeroBuscado = numeroBuscado;
            this.inicio = inicio;
            this.fin = fin;
        }

        // Metodo para obtener el resultado despues de ejecutar el thread
        public int getResultado() {
            return resultado;
        }

        // Metodo run que se ejecuta cuando inicia el thread
        @Override
        public void run() {
            int bajo = inicio; // indice inicial para la busqueda binaria
            int alto = fin;    // indice final para la busqueda binaria

            // Mientras el indice inicial sea menor o igual al final
            while (bajo <= alto) {
                int medio = (bajo + alto) / 2;     // calculamos el punto medio
                int numMedio = arreglo[medio];     // obtenemos el valor en la posicion media

                if (numMedio == numeroBuscado) {  // si encontramos el numero buscado
                    resultado = medio;             // guardamos la posicion encontrada
                    return;                       // terminamos el thread
                }
                if (numeroBuscado < numMedio) {  // si el numero buscado es menor que el medio
                    alto = medio - 1;             // buscamos en la mitad izquierda
                } else {                         // si es mayor
                    bajo = medio + 1;             // buscamos en la mitad derecha
                }
            }

            resultado = -1; // si no se encontro el numero en el rango, resultado es -1
        }
    }

    // Metodo principal donde se ejecuta el programa
    public static void main(String[] args) {
        Random random = new Random(); // instancia para generar numeros aleatorios

        int numeroABuscar = 25;       // numero que queremos buscar en el arreglo
        int tamanioArray = 10;        // tamaño del arreglo a generar

        int[] arregloDeNumeros = new int[tamanioArray]; // creamos el arreglo

        // llenamos el arreglo con numeros aleatorios entre 0 y 50
        for (int i = 0; i < arregloDeNumeros.length; i++) {
            arregloDeNumeros[i] = random.nextInt(51);
        }

        Arrays.sort(arregloDeNumeros); // ordenamos el arreglo para busqueda binaria

        System.out.println(Arrays.toString(arregloDeNumeros)); // mostramos arreglo en consola
        System.out.println("El numero a buscar es: " + numeroABuscar); // mostramos numero a buscar

        int cantidadThreads = 2; // cantidad de threads para dividir la busqueda

        // calculamos tamaño del rango que manejara cada thread
        int rango = (int) Math.ceil((double) tamanioArray / cantidadThreads);

        BusquedaThread[] threads = new BusquedaThread[cantidadThreads]; // arreglo de threads

        long tiempoInicio = System.nanoTime(); // tiempo de inicio para medir ejecucion

        // creamos y arrancamos cada thread con su rango definido
        for (int i = 0; i < cantidadThreads; i++) {
            int inicio = i * rango; // inicio del rango para este thread
            int fin = Math.min(inicio + rango - 1, tamanioArray - 1); // fin del rango

            threads[i] = new BusquedaThread(arregloDeNumeros, numeroABuscar, inicio, fin);
            threads[i].start(); // arrancamos el thread
        }

        int resultado = -1; // variable para guardar resultado final

        // esperamos que todos los threads terminen
        try {
            for (BusquedaThread thread : threads) {
                thread.join(); // esperamos que termine el thread
                if (thread.getResultado() != -1) { // si encontro el numero
                    resultado = thread.getResultado(); // guardamos la posicion
                    // aca podriamos romper el ciclo si queremos, pero esperamos a todos
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace(); // mostramos error si algo interrumpe la espera
        }

        long tiempoFin = System.nanoTime(); // tiempo final para medir ejecucion
        long duracion = tiempoFin - tiempoInicio; // calculamos duracion en nanosegundos

        if (resultado != -1) { // si se encontro el numero
            System.out.println("Numero encontrado en la posicion: " + resultado);
        } else { // si no se encontro
            System.out.println("Numero no encontrado.");
        }

        System.out.println("⏱ Tiempo de ejecucion concurrente: " + duracion + " nanosegundos");
    }
}
