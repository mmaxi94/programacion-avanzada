// A Java program to implement greedy algorithm for graph coloring
//https://www.geeksforgeeks.org/graph-coloring-set-2-greedy-algorithm/
import java.io.*;
import java.util.*;

// This class represents an undirected graph using adjacency list
public class WelshPowell
{
	private int V; // No. of vertices
	private LinkedList<Integer> adj[]; //Adjacency List


	//Constructor
	@SuppressWarnings("unchecked")
	public WelshPowell(int v)
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

	
	void ordenarDescendente() {
		LinkedList<Integer> aux;
		
		for (int i = 0; i < adj.length -1; i++) {
			for (int j = 0; j < adj.length -1; j++) {
				if (adj[j].size() < adj[j+1].size()) {
					aux = adj[j];
					adj[j] = adj[j+1];
					adj[j+1] = aux;
				}
			}
		}
	}

	// Assigns colors (starting from 0) to all vertices and
	// prints the assignment of colors
	void greedyColoring()
	{

		ordenarDescendente();
		
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
			
			for (Integer elemento : adj[u]) {
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
	}

	// Driver method
	public static void main(String args[])
	{
		//Time Complexity: O(V^2 + E) in worst case.
		//NC=3
		WelshPowell g1 = new WelshPowell(5);
		g1.addEdge(0, 1);
		g1.addEdge(0, 2);
		g1.addEdge(1, 2);
		g1.addEdge(1, 3);
		g1.addEdge(2, 3);
		g1.addEdge(3, 4);
		System.out.println("Coloring of graph 1");
		g1.greedyColoring();

		//NC=3
		System.out.println();
		WelshPowell g2 = new WelshPowell(5);
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
