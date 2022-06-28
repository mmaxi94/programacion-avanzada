package unlam.progava.oia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorArchivo {

	public static Grafo leerArchivo(String path) throws FileNotFoundException {
		Scanner archivo = new Scanner(new File(path));
		
		int cantNodos = archivo.nextInt();
		Grafo grafo = new Grafo(cantNodos);
		
		for (int i = 0; i < cantNodos; i++) {
			int nodo=archivo.nextInt()-1;
			int ady=archivo.nextInt()-1;
			while(ady != -2) {
				grafo.addEdge(nodo, ady);	
				ady=archivo.nextInt()-1;
			}
		}
		
		return grafo;
	}
	
	public static void escribirArchivo(String path, int cantConjuntoMax, ArrayList<Integer> conjuntoMax) throws IOException {
		FileWriter fw = new FileWriter(new File(path));
		PrintWriter pw = new PrintWriter(fw);
		
		pw.println(cantConjuntoMax);
		
		for (Integer ele : conjuntoMax) {
			pw.print(ele);
			pw.print(" ");
		}
		
		pw.close();
	}
	
}
