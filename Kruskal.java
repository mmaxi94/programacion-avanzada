// Kruskal's algorithm in Java

import java.util.*;

public class Graph {
	
	int vertices, edges;
	Edge edge[];
	int costo_minimo=0;

	// Graph creation
	Graph(int v, int e) {
		vertices = v;
		edges = e;
		edge = new Edge[edges];
		for (int i = 0; i < e; ++i)
			edge[i] = new Edge();
	}
	
	int find(subset parent[], int i) {
		if (parent[i-1].parent != i)
			parent[i-1].parent = find(parent, parent[i-1].parent);
		return parent[i-1].parent;
	}

	void Union(subset parent[], int x, int y) { //setea el referente minimo para cada nodo
		//rank es la cantidad de veces que es minimo de algun otro nodo un cierto nodo
		int xroot = find(parent, x);
		int yroot = find(parent, y);

		if (parent[xroot-1].rank < parent[yroot-1].rank)
			parent[xroot-1].parent = yroot;
		else if (parent[xroot-1].rank > parent[yroot-1].rank)
			parent[yroot-1].parent = xroot;
		else {
			parent[yroot-1].parent = xroot;
			parent[xroot-1].rank++;
		}
	}

	// Applying Krushkal Algorithm
	void KruskalAlgo() {
		Edge result[] = new Edge[vertices];
		int e = 0;
		int i = 0;
		for (i = 0; i < vertices; ++i)
			result[i] = new Edge();

		// Sorting the edges
		Arrays.sort(edge);
		subset parent[] = new subset[vertices]; //vector de referentes/padre
		for (i = 0; i < vertices; ++i)
			parent[i] = new subset();

		for (int v = 0; v < vertices; ++v) {
			parent[v].parent = v+1;
			parent[v].rank = 0;
		}
		i = 0;
		while (e < vertices - 1) {
			Edge next_edge = new Edge();
			next_edge = edge[i++]; //guardo el sig par de la lista ordenada
			int x = find(parent, next_edge.src); //busca el padre raiz de cada nodo
			int y = find(parent, next_edge.dest);
			if (x != y) { //si los padres de cada par de nodos son distintos..si son iguales no puedo hacer nada, sino haria un ciclo
				result[e++] = next_edge; //guardo en result, el par definitivo
				Union(parent, x, y); //setea el referente minimo en el vector de referentes
			}
		}
		for (i = 0; i < e; ++i) {
			System.out.println(result[i].src + " - " + result[i].dest + ": " + result[i].weight);
			costo_minimo+=result[i].weight;
		}
		System.out.println("Costo minimo: "+this.costo_minimo);	
		
	}

	
	class Edge implements Comparable<Edge> {
		int src, dest, weight;

		public int compareTo(Edge compareEdge) {
			return this.weight - compareEdge.weight;
		}
	};

	// Union
	class subset {
		int parent, rank;
	};

	public static void main(String[] args) { //Time Complexity: O(ElogE)
		int vertices = 6; // Number of vertices
		int edges = 10; // Number of edges
		Graph G = new Graph(vertices, edges);

		G.edge[0].src = 1;
		G.edge[0].dest = 3;
		G.edge[0].weight = 1;

		G.edge[1].src = 4;
		G.edge[1].dest = 6;
		G.edge[1].weight = 2;

		G.edge[2].src = 2;
		G.edge[2].dest = 5;
		G.edge[2].weight = 3;

		G.edge[3].src = 3;
		G.edge[3].dest = 6;
		G.edge[3].weight = 4;

		G.edge[4].src = 3;
		G.edge[4].dest = 4;
		G.edge[4].weight = 5;

		G.edge[5].src = 1;
		G.edge[5].dest = 4;
		G.edge[5].weight = 5;

		G.edge[6].src = 2;
		G.edge[6].dest = 3;
		G.edge[6].weight = 5;

		G.edge[7].src = 1;
		G.edge[7].dest = 2;
		G.edge[7].weight = 6;
		
		G.edge[8].src = 5;
		G.edge[8].dest = 6;
		G.edge[8].weight = 6;
		
		G.edge[9].src = 3;
		G.edge[9].dest = 5;
		G.edge[9].weight = 8;
		G.KruskalAlgo();
	}
	
	/*Time Complexity: O(ElogE) or O(ElogV). Sorting of edges takes O(ELogE) time. 
	 * After sorting, we iterate through all edges and apply the find-union algorithm. 
	 * The find and union operations can take at most O(LogV) time. 
	 * So overall complexity is O(ELogE + ELogV) time. 
	 * The value of E can be at most O(V2), so O(LogV) is O(LogE) the same. 
	 * Therefore, the overall time complexity is O(ElogE) or O(ElogV)*/
}