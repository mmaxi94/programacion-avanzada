
import java.util.*;
import java.lang.*;
import java.io.*;

public class MST {

	private static final int V = 6;
	int costo_minimo = 0;

	int minKey(int key[], Boolean mstSet[])
	{
		int min = Integer.MAX_VALUE, min_index = -1;

		for (int v = 0; v < V; v++)
			if (mstSet[v] == false && key[v] < min) {
				min = key[v];
				min_index = v;
			}

		return min_index;
	}

	void printMST(int parent[], int graph[][])
	{
		System.out.println("Edge \tWeight");
		for (int i = 1; i < V; i++) {
			System.out.println(parent[i]+1 + " - " + (i+1) + "\t" + graph[i][parent[i]]);
			this.costo_minimo+=graph[i][parent[i]];
		}
	}


	void primMST(int graph[][])
	{

		int parent[] = new int[V];


		int key[] = new int[V];


		Boolean mstSet[] = new Boolean[V];


		for (int i = 0; i < V; i++) {
			key[i] = Integer.MAX_VALUE;
			mstSet[i] = false;
		}


		key[0] = 0; 

		parent[0] = -1; 

		for (int count = 0; count < V - 1; count++) {

			int u = minKey(key, mstSet);

			mstSet[u] = true;


			for (int v = 0; v < V; v++)


				if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
					parent[v] = u;
					key[v] = graph[u][v];	
				}
		}


		printMST(parent, graph);
	}
	
	public int getCosto_minimo() {
		return costo_minimo;
	}

	public static void main(String[] args)
	{

//O(V^2). 
		
		 
		MST t = new MST();
		int graph[][] = new int[][] { { 0, 6, 1, 5, 0, 0 }, 
									  { 6, 0, 5, 0, 3, 0 }, 
									  { 1, 5, 0, 5, 8, 4 }, 
									  { 5, 0, 5, 0, 0, 2 },
									  { 0, 3, 8, 0, 0, 6 }, 
									  { 0, 0, 4, 2, 6, 0 } };


		t.primMST(graph);
		System.out.println("Costo minimo: " + t.getCosto_minimo());
	}
}
