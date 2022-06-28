package unlam.progava.oia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorArchivo {

	public static Grafo leerArchivo(String path) throws FileNotFoundException {
		
		Scanner archivo = new Scanner(new File(path));
		
		int N_ciudades= archivo.nextInt();
		int K_ciudades = archivo.nextInt();
		
		Grafo g = new Grafo(N_ciudades, K_ciudades);
		
		for (int i = 0; i < K_ciudades; i++) {
			g.agregarCentralElectrica(archivo.nextInt());
		}
		
		for (int i = 0; i < N_ciudades; i++) {
			for (int j = 0; j < N_ciudades; j++) {
				g.agregar_A_MatAdy(archivo.nextInt(), i, j);
			}
		}
		
		return g;
		
	}
	
	public static void escribirArchivo(String path, int costo_minimo, ArrayList<Arista> lista) throws IOException {
		
		FileWriter fw = new FileWriter(new File(path));
		PrintWriter pw = new PrintWriter(fw);
		
		pw.println(costo_minimo);
		
		for (Arista elemento : lista) {
			pw.print(elemento.getNodorDer()+1);
			pw.print(" ");
			pw.println(elemento.getNodoIzq()+1);
		}
		
		pw.close();
	}
}
