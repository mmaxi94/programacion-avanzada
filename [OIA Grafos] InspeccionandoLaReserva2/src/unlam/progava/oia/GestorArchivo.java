package unlam.progava.oia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GestorArchivo {

	public static Grafo leerArchivo(String path) {
		
		Scanner archivo = null;
		Grafo grafo = null;
		try {
			archivo = new Scanner(new File(path));
			int cantNodos= archivo.nextInt();
			int cantAristas=archivo.nextInt();
			
			grafo = new Grafo(cantNodos,cantAristas,0,cantNodos-1);
			
			for (int i = 0; i < cantAristas; i++) {
				int src=archivo.nextInt()-1;
				int des=archivo.nextInt()-1;
				grafo.addEdge(src, des);
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			archivo.close();
		}
		return grafo;
	}
	
	public static void escribirArchivo(String path, int cantRecorridos) {
		
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter(new File(path));
			pw = new PrintWriter(fw);
			
			pw.print(cantRecorridos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (fw != null)
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
		}
		
	}
}
