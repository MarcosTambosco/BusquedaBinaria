package Test;

import java.util.Arrays;
import java.util.Random;

// Clase que implementa el algoritmo de busqueda binaria
class BusquedaBinaria {

	// Metodo privado que realiza la busqueda binaria
	private static int busquedaBinaria(int[] arregloNumeros, int numeroBuscado) {
		int bajo = 0; // Indice inicial del arreglo
		int alto = arregloNumeros.length - 1; // Indice final del arreglo

		// Mientras el índice inicial sea menor o igual al final
		while (bajo <= alto) {
			int indiceMedio = (bajo + alto) / 2; // Calcula el indice del medio
			int numeroEnMedio = arregloNumeros[indiceMedio]; // Obtiene el numero en la posición media

			if (numeroBuscado == numeroEnMedio) {
				return indiceMedio; // Si el numero buscado es igual al número en el medio devuelve el indice
			}
			if (numeroBuscado < numeroEnMedio) {
				alto = indiceMedio - 1; // Si el numero buscado es menor, buscar en la mitad izquierda
			}
			if (numeroBuscado > numeroEnMedio) {
				bajo = indiceMedio + 1; // Si el numero buscado es mayor, buscar en la mitad derecha	
			}
		}

		return -1; // Si no se encuentra el número, devuelve -1
	}

	// Metodo principal que se ejecuta al correr el programa
	public static void main(String[] args) {

		Random random = new Random();

		int numeroABuscar = 25;
		int tamanioArray = 1000;

		// Crear un arreglo elementos con el tamaño enviado por parametro
		int[] arregloDeNumeros = new int[tamanioArray];

		for (int i = 0; i < arregloDeNumeros.length; i++) {
			arregloDeNumeros[i] = random.nextInt(1001); // Genera números de 0 a 50
		}

		Arrays.sort(arregloDeNumeros); // Ordenar el arreglo para poder aplicar búsqueda binaria

		System.out.println(Arrays.toString(arregloDeNumeros)); // Mostrar el arreglo por consola para verificar

		System.out.println("El numero a buscar es: " + numeroABuscar); // Mostrar el arreglo por consola para verificar

		long tiempoInicio = System.nanoTime(); // Guardar el tiempo al iniciar la operación

		int resultado = busquedaBinaria(arregloDeNumeros, numeroABuscar); // Busca el número "numeroABuscar"

		long tiempoFin = System.nanoTime(); // Guardar el tiempo al finalizar la operación

		long duracion = tiempoFin - tiempoInicio; // Calcular la duración total en nanosegundos

		if (resultado != -1) {
			System.out.println("Numero encontrado en la posición: " + resultado);
		} else {
			System.out.println("Numero no encontrado.");
		}

		System.out.println("⏱ Tiempo de ejecución: " + duracion + " nanosegundos"); // Mostrar el tiempo en consola
	}
}
