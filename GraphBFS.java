import java.io.*;
import java.util.*;

public class GraphBFS
{
    private int V;                              //number of visitado in the graph
    private LinkedList<Integer> adj[];              //adjacency list
    private Queue<Integer> queue;                   //maintaining a queue
 
   public GraphBFS(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; i++)
            adj[i] = new LinkedList<Integer>();
        
        queue = new LinkedList<Integer>();
    }

 
    void addEdge(int v,int w)
    {
        adj[v].add(w);                          //adding an edge to the adjacency list (edges are bidirectional in this example)
    }
 
    void BFS(int n)
    {

        boolean visitado[] = new boolean[V];       //initialize boolean array for holding the data
        int[] level = new int[V];
        
        for (int i = 0; i < level.length; i++) {
			level[i]=Integer.MAX_VALUE;
		}
        
        level[n]=0;
        int a = 0;
 
        visitado[n]=true;                  
        queue.add(n);                   //root node is added to the top of the queue
 
        while (queue.size() != 0)
        {
            n = queue.poll();             //remove the top element of the queue
            System.out.print(n+" ");           //print the top element of the queue
 
            for (int i = 0; i < adj[n].size(); i++)  //iterate through the linked list and push all neighbors into queue
            {
                a = adj[n].get(i);
                if (!visitado[a])                    //only insert visitado into queue if they have not been explored already
                {
                    visitado[a] = true;
                    queue.add(a);
                    level[a]=level[n]+1;
                }
            }  
        }
        
        mostrarDistancias(level);
    }
    
    public void mostrarDistancias(int[] level) {
    	System.out.println();
    	System.out.println("Nodo:\tLevel:");
    	for (int i = 0; i < level.length; i++) {
			System.out.println(" " +i+ "------> "+level[i]);
		}
    }

    public static void main(String args[])
    {
    	GraphBFS graph = new GraphBFS(6);
 //Time Complexity: O(V+E), where V is the number of nodes and E is the number of edges.
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);
        graph.addEdge(4, 5);
        graph.addEdge(3, 5);
        graph.addEdge(1, 2);
        graph.addEdge(1, 0);
        graph.addEdge(2, 1);
        graph.addEdge(5, 4);
        graph.addEdge(5, 3);
    	
    	/*graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 0);
		graph.addEdge(1, 3);
		graph.addEdge(2, 0);
		graph.addEdge(2, 3);
		graph.addEdge(3, 1);
		graph.addEdge(3, 2);
		graph.addEdge(3, 4);
		graph.addEdge(3, 5);
		graph.addEdge(4, 3);
		graph.addEdge(5, 3);*/
 
        System.out.println("The Breadth First Traversal of the graph is as follows :");
 
        graph.BFS(0);
    }
}