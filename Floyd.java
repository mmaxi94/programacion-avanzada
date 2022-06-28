// A Java program for Floyd Warshall All Pairs Shortest
//https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/
// Path algorithm.
import java.util.*;
import java.lang.*;
import java.io.*;


public class Floyd
{
	final static int INF = 99999;
	int V;
	
	public Floyd(int graph[][]) {
		this.V=graph.length;
	}
	
	public static void main (String[] args)
	{
		//INPUT: GRAFO PONDERADO DIRIGIDO EN MAT DE ADY. aca pongo las distancias dentro
		//DIAG PCPAL EN 0
		int graph[][] = { {0, 8, 5},
						  {3, 0, INF},
						  {INF, 2, 0}
						};
		
		Floyd a = new Floyd(graph);

		// OUTPUT: MATRIZ DE COSTOS MINIMO
		a.floydWarshall(graph);
	}

	void floydWarshall(int graph[][])
	{	
		int dist[][] = new int[V][V]; //MATRIZ DE COSTOS MINIMOS
		int i, j, k;

		/*inicializo mi matriz F0 (de costos minimos)*/
		for (i = 0; i < V; i++)
			for (j = 0; j < V; j++)
				dist[i][j] = graph[i][j];

		//'k' es el numeros de nodos e iteraciones que voy a hacer para recorrer cada matriz F
		//siempre voy a estar parado sobre la matriz anterior, por eso arranca con k=0 xq es la inicial
		for (k = 0; k < V; k++)
		{
			//recorro filas de matriz
			for (i = 0; i < V; i++)
			{
				//recorro las columnas
				for (j = 0; j < V; j++)
				{
					//para setear el valor de F1 [i,j], pregunto si la suma en el 'k' anterior es menor a lo que tengo
					//ahora mismo. si es menor, entonces reasigno, sino, no hago nada
					if ((dist[i][k] + dist[k][j] < dist[i][j]) && (i!=j))
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
		// Print the shortest distance matrix
		printSolution(dist);
		//ej: del nodo 1 al 2, la distancia mas corta es 8
	}

	void printSolution(int dist[][])
	{
		System.out.println("Distancias mas cortas "+
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

