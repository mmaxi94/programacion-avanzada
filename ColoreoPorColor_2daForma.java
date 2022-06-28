// A Java program to implement greedy algorithm for graph coloring
//https://www.geeksforgeeks.org/graph-coloring-set-2-greedy-algorithm/
import java.io.*;
import java.util.*;

// This class represents an undirected graph using adjacency list
public class ColoreoPorColor_2daForma
{
	private int V; // No. of vertices
	private LinkedList<Integer> adj[]; //Adjacency List

	//Constructor
	@SuppressWarnings("unchecked")
	public ColoreoPorColor_2daForma(int v)
	{
		V = v;
		adj = new LinkedList[v];
		for (int i=0; i<v; ++i)
			adj[i] = new LinkedList<Integer>();
	}

	//Function to add an edge into the graph
	void addEdge(int v,int w)
	{
		adj[v].add(w);
		adj[w].add(v); //Graph is undirected
	}

	// Assigns colors (starting from 0) to all vertices and
	// prints the assignment of colors
	void greedyColoring()
	{
		int result[] = new int[V]; //contiene los colores de cada nodo
		Arrays.fill(result, -1); //se los inicializa sin color
		result[0] = 0; // Assign the first color to first vertex
		// A temporary array to store the available colors. False
		// value of available[cr] would mean that the color cr is
		// assigned to one of its adjacent vertices
		boolean available[] = new boolean[V];	
		Arrays.fill(available, true); // Initially, all colors are available

		for (int u = 1; u < V; u++)
		{
			// Process all adjacent vertices and flag their colors
			// as unavailable	
			for (Integer elemento : adj[u]) {
				if (result[elemento] != -1) //si tiene un color
					available[result[elemento]] = false;//marco ese color como no disponible
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
		
		System.out.println("NC: "+getNC(result));
	}
	
	private int getNC(int[] result) {
		Set<Integer> set = new HashSet<Integer>();
		
		for (int i = 0; i < result.length; i++) {
			set.add(result[i]);
		}
		
		int tamanio = set.size();
		return tamanio;
	}

	// Driver method
	public static void main(String args[])
	{
		//Time Complexity: O(V^2 + E) in worst case.
		//NC=3
		ColoreoPorColor_2daForma g1 = new ColoreoPorColor_2daForma(5);
		g1.addEdge(0, 1);
		g1.addEdge(0, 2);
		g1.addEdge(1, 2);
		g1.addEdge(1, 3);
		g1.addEdge(2, 3);
		g1.addEdge(3, 4);
		System.out.println("Coloring of graph 1");
		g1.greedyColoring();

		//NC=4
		System.out.println();
		ColoreoPorColor_2daForma g2 = new ColoreoPorColor_2daForma(5);
		g2.addEdge(0, 1);
		g2.addEdge(0, 2);
		g2.addEdge(1, 2);
		g2.addEdge(1, 4);
		g2.addEdge(2, 4);
		g2.addEdge(4, 3);
		System.out.println("Coloring of graph 2 ");
		g2.greedyColoring();
	}
}
// This code is contributed by Aakash Hasija
