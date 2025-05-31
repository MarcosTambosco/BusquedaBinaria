package Test;

public class TestBusquedaBinariaSecuencial {

    // Método de búsqueda binaria secuencial
    public static int busquedaBinaria(int[] arreglo, int valorBuscado) {
        int inicio = 0;
        int fin = arreglo.length - 1;

        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;

            if (arreglo[medio] == valorBuscado) {
                return medio;
            }

            if (valorBuscado < arreglo[medio]) {
                fin = medio - 1;
            } else {
                inicio = medio + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] datos = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int valor = 13;

        long tiempoInicio = System.nanoTime();
        int posicion = busquedaBinaria(datos, valor);
        long tiempoFin = System.nanoTime();
        long duracion = tiempoFin - tiempoInicio;

        if (posicion != -1) {
            System.out.println("✔ Valor " + valor + " encontrado en la posición: " + posicion);
        } else {
            System.out.println("✘ Valor " + valor + " no encontrado.");
        }

        System.out.println("⏱ Tiempo de ejecución: " + duracion + " nanosegundos");
    }
}
