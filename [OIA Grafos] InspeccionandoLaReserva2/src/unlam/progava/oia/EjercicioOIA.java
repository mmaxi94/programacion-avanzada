package unlam.progava.oia;

public class EjercicioOIA {

	Grafo grafo;
	
	public static void main(String[] args) {
		EjercicioOIA ejercicio = new EjercicioOIA();
		
		ejercicio.leer("src/unlam/progava/oia/in/reserva.in");
		ejercicio.resolver();
		ejercicio.escribir("src/unlam/progava/oia/out/reserva.out");
	}

	/**
	 * Este método se encarga de leer la entrada de la
	 * consigna desde el archivo.
	 */
	public void leer(String path) {
		grafo = GestorArchivo.leerArchivo(path);
	}
	
	/**
	 * Este método se encarga de escribir la salida de la
	 * consigna en el archivo.
	 */
	public void escribir(String path) {
		GestorArchivo.escribirArchivo(path, grafo.getCantRecorridos());
	}
	
	/**
	 * Este método debe realizar la resolución del ejercicio.
	 * NO DEBE SITUARSE LA LOGICA AQUI, sino que se pueden
	 * utilizar clases adicionales, y los objetos creados en la
	 * etapa de lectura.
	 * Utilizar este mismo paquete para toda la resolución.
	 */
	public void resolver() {
		grafo.resolver();
	}
}
