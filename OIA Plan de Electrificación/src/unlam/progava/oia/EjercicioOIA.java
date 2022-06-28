package unlam.progava.oia;

import java.io.FileNotFoundException;
import java.io.IOException;

public class EjercicioOIA {

	Grafo grafo;
	
	public static void main(String[] args) {
		EjercicioOIA ejercicio = new EjercicioOIA();
	
			ejercicio.leer("src/unlam/progava/oia/in/ciudades.in");
			ejercicio.resolver();
			ejercicio.escribir("src/unlam/progava/oia/out/tendido.out");
	}

	/**
	 * Este método se encarga de leer la entrada de la
	 * consigna desde el archivo.
	 * @throws FileNotFoundException 
	 */
	public void leer(String path) {
		try {
			grafo = GestorArchivo.leerArchivo(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	
	/**
	 * Este método se encarga de escribir la salida de la
	 * consigna en el archivo.
	 */
	public void escribir(String path) {
		try {
			GestorArchivo.escribirArchivo(path, grafo.getCosto_minimo(), grafo.lista_out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	
	/**
	 * Este método debe realizar la resolución del ejercicio.
	 * NO DEBE SITUARSE LA LOGICA AQUI, sino que se pueden
	 * utilizar clases adicionales, y los objetos creados en la
	 * etapa de lectura.
	 * Utilizar este mismo paquete para toda la resolución.
	 */
	public void resolver() {
		grafo.algoritmoPrim();
	}
}
