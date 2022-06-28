// A Java program for Warshall Warshall All Pairs Shortest
// Path algorithm.
import java.util.*;
import java.lang.*;
import java.io.*;


public class Warshall //es como el Floyd pero sin pesos. quiero saber si hay camino entre cual par de nodos
{
	final static int INF = 99999;
	int V;
	
	public Warshall(int graph[][]) {
		this.V=graph.length;
	}
	
	public static void main (String[] args)
	{
		//INPUT: GRAFO NO PONDERADO DIRIGIDO EN MAT DE ADY. aca pongo 1 "hay camino" o 0 "no hay camino"
		//DIAG PCPAL EN 0
		int graph[][] = { {0, 1, 1},
						{1, 0, 0},
						{0, 1, 0}
						};
		
		Warshall a = new Warshall(graph);

		// OUTPUT: MATRIZ DE COSTOS MINIMO
		a.floydWarshall(graph);
	}

	void floydWarshall(int graph[][])
	{	
		int dist[][] = new int[V][V]; //MATRIZ DE SALIDA, que sera la inicial
		int i, j, k;

		/*inicializo mi matriz A0*/
		for (i = 0; i < V; i++)
			for (j = 0; j < V; j++)
				dist[i][j] = graph[i][j];

		//'k' es el numeros de nodos e iteraciones que voy a hacer para recorrer cada matriz A
		//siempre voy a estar parado sobre la matriz anterior, por eso arranca con k=0 xq es la inicial
		for (k = 0; k < V; k++)
		{
			//recorro filas de matriz
			for (i = 0; i < V; i++)
			{
				//recorro las columnas
				for (j = 0; j < V; j++)
				{
					//para setear el valor de A1 [i,j], pregunto si existe un 1 en alguna de las sig cond
					//quiero ver si los ceros se convierten en 1..
					if (((dist[i][k]==1 && dist[k][j]==1) || ( dist[i][j])==1) && (i!=j))
						dist[i][j] = 1;
				}
			}
		}
		// Print the shortest distance matrix
		printSolution(dist);
		//ej: del nodo 0 al nodo 1 y 2, hay camino
	}

	void printSolution(int dist[][])
	{
		System.out.println("Indica si hay camino o no "+
						"entre cualquier par de vertices");
		for (int i=0; i<V; ++i)
		{
			for (int j=0; j<V; ++j)
			{
				if (dist[i][j]==INF)
					System.out.print("INF ");
				else
					System.out.print(dist[i][j]+" ");
			}
			System.out.println();
		}
	}	
}

