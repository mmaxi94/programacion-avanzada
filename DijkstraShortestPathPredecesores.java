// A Java program for Dijkstra's single source shortest path algorithm.
// The program is for adjacency matrix representation of the graph
import java.util.*;
import java.lang.*;
import java.io.*;

public class ShortestPathPredecesores {

	final static int INF = Integer.MAX_VALUE;
	int V;
	
	public ShortestPathPredecesores(int[][] graph) {
		this.V=graph.length;
	}

	public static void main(String[] args)
		{
			/* Let us create the example graph discussed above */
			int graph[][] = new int[][] { { INF, 7, 3, INF },
										{ INF, INF, INF, 2 },
										{ INF, 2, INF, 8 },
										{ INF, INF, INF, INF }};
			ShortestPathPredecesores t = new ShortestPathPredecesores(graph);
			t.dijkstra(graph, 0);
		}
		
	// Function that implements Dijkstra's single source shortest path
		// algorithm for a graph represented using adjacency matrix
		// representation
		void dijkstra(int graph[][], int src)
		{
			int distancia[] = new int[V]; // OUTPUT. Vector de costo minimo.
			//guarda la distancia mas corta desde el nodo origen "src" hacia el nodo "i"
			//guarda con true si un nodo ya fue visitado
			Boolean visitado[] = new Boolean[V];
			int[] predecesores = new int[V];
			Arrays.fill(predecesores, src);
			
			// Initialize all distances as INFINITE and visitado[] as false
			for (int i = 0; i < V; i++) {
				distancia[i] = INF;
				visitado[i] = false;
			}
			//actualiza en la iteracion inicial, el vector D con los valores de la fila de la matriz para el nodo
			//origen, o sea, src
			for (int w = 0; w < V; w++) {
				if(graph[src][w] != INF)
					distancia[w]=graph[src][w];
			}
			// Distance of source vertex from itself is always 0
			distancia[src] = 0;
			visitado[src]=true; //el true es que esta en CONJUNTO SOLUCION

			for (int count = 0; count < V - 1; count++) {
				
				int w = minDistance(distancia, visitado);
				visitado[w] = true;
				
				for (int i = 0; i < V; i++) {
					if((distancia[w]+graph[w][i]) < distancia[i] && !visitado[i]) {
						distancia[i] = distancia[w]+graph[w][i];	
						predecesores[i]=w;
					}
				}
			}
			printSolution(distancia);
			printPredecesores(predecesores);
			printCaminoAristas(predecesores,src,3);
		}
		
		
	// A utility function to find the vertex with minimum distance value,
	// from the set of vertices not yet included in shortest path tree
	int minDistance(int distancia[], Boolean visitado[])
	{
		// Initialize min value
		int min = INF;
		int min_index = -1;

		for (int i = 0; i < V; i++)
			if (visitado[i] == false && distancia[i] <= min) {
				min = distancia[i];
				min_index = i;
			}

		return min_index;
	}

	// A utility function to print the constructed distance array
	void printSolution(int dist[])
	{
		System.out.println("Vertex \t\t Distance from Source");
		for (int i = 0; i < V; i++)
			System.out.println(i + " \t\t " + dist[i]);
	}
	
	void printPredecesores(int[] vecPred) {
		System.out.println("Predecesores");
		for (int i = 0; i < V; i++)
			System.out.println(i + " : " + vecPred[i]);
	}
	
	void printCaminoAristas(int[] vecPred, int src, int des) {
		
		ArrayList<Integer> lista = new ArrayList<Integer>();
		System.out.println("Camino desde nodo origen "+src+" a destino "+des);
		lista.add(des);
		
		while(vecPred[des]!=src) {
			int aux=vecPred[des];
			lista.add(vecPred[des]);
			des=aux;	
		}
		lista.add(src);
		
		for (int i = lista.size()-1; i >=0 ; i--) {
			System.out.print(lista.get(i)+" ");
		}
	}
	
}
// This code is contributed by Aakash Hasija
