package Test;

class BusquedaBinariaThread extends Thread {
    private int[] arreglo;
    private int valorBuscado;
    private int inicio;
    private int fin;
    private int resultado = -1;

    public BusquedaBinariaThread(int[] arreglo, int valorBuscado, int inicio, int fin) {
        this.arreglo = arreglo;
        this.valorBuscado = valorBuscado;
        this.inicio = inicio;
        this.fin = fin;
    }

    public void run() {
        int i = inicio;
        int f = fin;

        while (i <= f) {
            int medio = (i + f) / 2;

            if (arreglo[medio] == valorBuscado) {
                resultado = medio;
                return;
            }

            if (valorBuscado < arreglo[medio]) {
                f = medio - 1;
            } else {
                i = medio + 1;
            }
        }
    }

    public int getResultado() {
        return resultado;
    }
}

public class TestBusquedaBinariaConcurrente {

    public static void main(String[] args) {
        int[] datos = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int valor = 13;

        int mitad = datos.length / 2;

        BusquedaBinariaThread hilo1 = new BusquedaBinariaThread(datos, valor, 0, mitad - 1);
        BusquedaBinariaThread hilo2 = new BusquedaBinariaThread(datos, valor, mitad, datos.length - 1);

        long tiempoInicio = System.nanoTime();

        hilo1.start();
        hilo2.start();

        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long tiempoFin = System.nanoTime();
        long duracion = tiempoFin - tiempoInicio;

        int resultado;
        if (hilo1.getResultado() != -1) {
            resultado = hilo1.getResultado();
        } else {
            resultado = hilo2.getResultado();
        }

        if (resultado != -1) {
            System.out.println("✔ Valor " + valor + " encontrado en la posición: " + resultado);
        } else {
            System.out.println("✘ Valor " + valor + " no encontrado.");
        }

        System.out.println("⏱ Tiempo de ejecución (concurrente): " + duracion + " nanosegundos");
    }
}
