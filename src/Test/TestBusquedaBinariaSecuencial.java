package Test;
// Clase que implementa el algoritmo de busqueda binaria
class BusquedaBinaria {

    // Metodo privado que realiza la busqueda binaria
    private static int busquedaBinaria(int[] arregloNumeros, int numeroBuscado) {
        int bajo = 0;                            // Indice inicial del arreglo
        int alto = arregloNumeros.length - 1;    // Indice final del arreglo

        // Mientras el índice inicial sea menor o igual al final
        while (bajo <= alto) {
            int indiceMedio = (bajo + alto) / 2;           // Calcula el indice del medio
            int numeroEnMedio = arregloNumeros[indiceMedio]; // Obtiene el numero en la posición media

            if (numeroBuscado == numeroEnMedio) {
                return indiceMedio;    // Si el numero buscado es igual al número en el medio devuelve el indice
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

        int[] arregloDeNumeros = {2, 3, 6, 8, 9, 13, 20}; // Arreglo de números ordenados
        
        int resultado = busquedaBinaria(arregloDeNumeros, 13); // Busca el número 13

        if (resultado != -1) {
            System.out.println("Numero encontrado en la posición: " + resultado);
        } else {
            System.out.println("Numero no encontrado.");
        }
    }
}
