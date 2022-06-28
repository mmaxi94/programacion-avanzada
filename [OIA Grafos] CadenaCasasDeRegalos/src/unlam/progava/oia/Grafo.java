package unlam.progava.oia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Grafo {

	private List<Integer>[] listaAdy;
	private int cantConjuntoMax;
	private ArrayList<Integer> conjuntoMax;
	private int cantNodos;
	
	public Grafo(int cantNodos) {
		this.listaAdy=new LinkedList[cantNodos];
		
		for (int i = 0; i < cantNodos; i++) {
			this.listaAdy[i] = new LinkedList<Integer>();
		}
		this.cantNodos=cantNodos;
		this.conjuntoMax = new ArrayList<Integer>();
	}
	
	public void addEdge(int src, int des) {
		this.listaAdy[src].add(des);
		this.listaAdy[des].add(src);
	}
	
	public void ejecutar() {
		colorear();
	}
	
	public void colorear() {
		int V=cantNodos;
		int result[] = new int[V];
		// Initialize all vertices as unassigned
		Arrays.fill(result, -1);
		// Assign the first color to first vertex
		result[0] = 0;
		// A temporary array to store the available colors. False
		// value of available[cr] would mean that the color cr is
		// assigned to one of its adjacent vertices
		boolean available[] = new boolean[V];	
		// Initially, all colors are available
		Arrays.fill(available, true);
		// Assign colors to remaining V-1 vertices
		for (int u = 1; u < V; u++)
		{
			// Process all adjacent vertices and flag their colors
			// as unavailable	
			for (Integer elemento : listaAdy[u]) {
				if (result[elemento] != -1)
					available[result[elemento]] = false;
			}
			// Find the first available color
			int cr;
			for (cr = 0; cr < V; cr++){
				if (available[cr])
					break;
			}
			result[u] = cr; // Assign the found color
			// Reset the values back to true for the next iteration
			Arrays.fill(available, true);
		}

		// print the result
		for (int u = 0; u < V; u++)
			System.out.println("Vertex " + u + " ---> Color "
								+ result[u]);
		
		calcularOutput(result);
	}
	
	public void calcularOutput(int[] result) {
		
		HashMap<Integer, Integer> mapa = new HashMap<Integer,Integer>();
		
		for (int i = 0; i < result.length; i++) {
			if(mapa.containsKey(result[i])) {
				int valor = mapa.get(result[i]);
				mapa.put(result[i], valor+1);
			}
			else
				mapa.put(result[i], 1);
		}
		
		int max=-9999;
		int ColorMax=-1;
		for (int ele : mapa.keySet()) {
			if(mapa.get(ele) >max) {
				max=mapa.get(ele);
				ColorMax=ele;
			}
		}	
		this.cantConjuntoMax=max;
		
		for (int i = 0; i < result.length; i++) {
			if(result[i]==ColorMax)
				this.conjuntoMax.add(i+1);
		}
	}

	public ArrayList<Integer> getConjuntoMax() {
		return conjuntoMax;
	}

	public int getCantConjuntoMax() {
		return cantConjuntoMax;
	}
}
