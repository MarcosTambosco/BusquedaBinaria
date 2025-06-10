package Test;


public class TestBusquedaBinariaSecuencial {

    
    private static final int TAM = 1000000; 

    public static void main(String[] args) {

        // Creo el arreglo ordenado del 1 hasta TAM
        int[] datos = new int[TAM];
        for (int i = 0; i < TAM; i++) {
            datos[i] = i + 1;
        }

        
        int valor = 5;

       
        long tiempoInicio = System.nanoTime();

        // Ejecución de la búsqueda binaria de forma secuencial
        int resultado = busquedaBinaria(datos, valor);

        // Captura del tiempo final y cálculo de la duración
        long tiempoFin = System.nanoTime();
        long duracion = tiempoFin - tiempoInicio;

        
        if (resultado != -1) {
            System.out.println("✔ Valor " + valor + " encontrado en la posición: " + resultado);
        } else {
            System.out.println("✘ Valor " + valor + " no encontrado.");
        }

        // Muestra el tiempo de ejecución en nanosegundos
        System.out.println("⏱ Tiempo de ejecución (secuencial): " + duracion + " nanosegundos");
    }

    
    public static int busquedaBinaria(int[] arreglo, int valorBuscado) {
        int i = 0;
        int f = arreglo.length - 1;

        // Bucle de búsqueda binaria tradicional
        while (i <= f) {
            int medio = (i + f) / 2;

            // Si el elemento en la posición medio es el valor buscado, devuelve el índice
            if (arreglo[medio] == valorBuscado) {
                return medio;
            }

            // Si el valor buscado es menor, buscar en la mitad izquierda
            if (valorBuscado < arreglo[medio]) {
                f = medio - 1;
            } else {
                // Si es mayor, buscar en la mitad derecha
                i = medio + 1;
            }
        }

        // Si no se encuentra el valor, devuelve -1
        return -1;
    }
}
