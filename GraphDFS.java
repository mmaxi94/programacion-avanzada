import java.util.*;
//https://favtutor.com/blogs/depth-first-search-java

public class GraphDFS {
	int V; // number of visitado
	LinkedList<Integer>[] adj; // adjacency list

	public GraphDFS(int V) {
		this.V = V;
		adj = new LinkedList[V];
		for (int i = 0; i < adj.length; i++)
			adj[i] = new LinkedList<Integer>();
	}

	void addEdge(int v, int w) {
            adj[v].add(w);  //adding an edge to the adjacency list (edges are bidirectional in this example)
        }

	void DFS(int n) {
		boolean visitado[] = new boolean[V];
		Stack<Integer> stack = new Stack<>();
		stack.push(n); // push root node to the stack
		int a = 0;
//hay forma de recorrerlo de izq a derecha?
		while (!stack.empty()) {
			n = stack.peek(); // extract the top element of the stack
			stack.pop(); // remove the top element from the stack

			if (visitado[n] == false) {
				System.out.print(n + " ");
				visitado[n] = true;
			}
			//for (int i = 0; i < adj[n].size(); i++) asi arranca de der a izq. abajo esta de izq a der el recorrido del arbol
			for (int i = adj[n].size() -1; i >=0; i--) // iterate through the linked list and then propagate to the next										// few visitado
			{
				a = adj[n].get(i);
				if (!visitado[a]) // only push those visitado to the stack which aren't in it already
				{
					stack.push(a); // push the top element to the stack
				}
			}
		}
	}

	public static void main(String[] args) {
		GraphDFS g = new GraphDFS(6);
		//ejemplo pagina

		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 0);
		g.addEdge(1, 3);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 1);
		g.addEdge(3, 2);
		g.addEdge(3, 4);
		g.addEdge(3, 5);
		g.addEdge(4, 3);
		g.addEdge(5, 3);
/*
		//ejemplo ejercicio metro oia
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 0);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 1);
		g.addEdge(2, 3);
		g.addEdge(3, 2);
		g.addEdge(3, 4);
		g.addEdge(3, 5);
		g.addEdge(4, 3);
		g.addEdge(4, 5);
		g.addEdge(5, 3);
		g.addEdge(5, 4);*/
		
		System.out.println("Following is the Depth First Traversal");
		g.DFS(0);
	}
}