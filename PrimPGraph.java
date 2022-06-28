
import java.util.Arrays;

public class PGraph {

	int costo_minimo = 0;

	public void Prim(int G[][], int V) {

		int INF = 9999999;
		int no_edge; 

		boolean[] selected = new boolean[V];

		Arrays.fill(selected, false);

		no_edge = 0;

		selected[0] = true;

		System.out.println("Arista : Peso");

		while (no_edge < V - 1) {

			int min = INF;
			int x = 0; 
			int y = 0; 

			for (int i = 0; i < V; i++) {
				if (selected[i] == true) {
					for (int j = 0; j < V; j++) {
						if (!selected[j] && G[i][j] != 0) {
							if (min > G[i][j]) {
								min = G[i][j];
								x = i;
								y = j;
							}
						}
					}
				}
			}	
			this.costo_minimo += min;
			System.out.println((x + 1) + " - " + (y + 1) + " :  " + G[x][y]);
			selected[y] = true;
			no_edge++;
		}
	}

	public int getCosto_minimo() {
		return costo_minimo;
	}

	public static void main(String[] args) {
		PGraph g = new PGraph();

		int[][] G = { { 0, 6, 1, 5, 0, 0 }, 
					  { 6, 0, 5, 0, 3, 0 }, 
				      { 1, 5, 0, 5, 8, 4 }, 
				      { 5, 0, 5, 0, 0, 2 },
				      { 0, 3, 8, 0, 0, 6 }, 
				      { 0, 0, 4, 2, 6, 0 } };

		int V = G.length;

		g.Prim(G, V);
		System.out.println("Costo minimo: " + g.getCosto_minimo());
	}
}