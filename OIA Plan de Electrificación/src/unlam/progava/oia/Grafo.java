package unlam.progava.oia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Grafo {

	//grafo no dirigido, conexo, ponderado
	static final int INF = Integer.MAX_VALUE;
	int[][] matriz_adyacencia;
	ArrayList<Integer> ciudades_centralElectrica;
	int costo_minimo;
	ArrayList<Arista> lista_out;


	public Grafo(int N_ciudades, int K_ciudades) {
		this.matriz_adyacencia = new int[N_ciudades][N_ciudades];
		this.ciudades_centralElectrica = new ArrayList<Integer>(K_ciudades);
		this.lista_out = new ArrayList<Arista>();
	}
	
	public void agregarCentralElectrica(int nro) {
		this.ciudades_centralElectrica.add(nro);
	}
	
	public void agregar_A_MatAdy(int valor, int i, int j) {
		if (i!=j)
			this.matriz_adyacencia[i][j]=valor;
	}
	
	public void algoritmoPrim() {

		//Lo resuelvo con el Algoritmo de PRIM y Matriz de adyacencia
		//basicamente, los nodos de las centrales electricas forman parte de mi conjunto solucion inicial
		//por lo tanto, se busca conectar el resto de las ciudades con al menos alguna de las centrales!
		int nro_edge; // number of edge
		int V = matriz_adyacencia.length;
		
		boolean[] selected = new boolean[V]; //vector de conjunto S y V-S
		Arrays.fill(selected, false);

		// set number of edge to 0
		nro_edge = 0;

		//seteo los nodos de centrales electricas en true en el S
		for (int i = 0; i < ciudades_centralElectrica.size(); i++) {
			selected[ciudades_centralElectrica.get(i)-1]=true;
		}

		// print for edge and weight
		System.out.println("Arista : Peso");

		boolean huboCambio;
		ArrayList<Integer> ciudad_SinEnergia_visitada = new ArrayList<Integer>();
		
		while (nro_edge < V - 1) {
			huboCambio=false;
			int min = INF;
			int x = 0; // row number
			int y = 0; // col number

			for (int i = 0; i < V; i++) {
				if (selected[i] == true && !esParteDeCiudadSinEnergia(i)) { //si el nodo es parte de las ciudades
					//sin energia que se conectaron recientemente, entonces no calcular nada.
					for (int j = 0; j < V; j++) {
						// not in selected and there is an edge
						if (!selected[j] && matriz_adyacencia[i][j] != 0) {
							if (min >= matriz_adyacencia[i][j]) { //si no pongo >= me sale la otra opcion de 0-1
								min = matriz_adyacencia[i][j];
								x = i;
								y = j;
								huboCambio=true;
							}
						}
					}
				}
			}
			if (huboCambio) {
				this.costo_minimo += min;
				System.out.println((x + 1) + " - " + (y + 1) + " :  " + matriz_adyacencia[x][y]);
				selected[y] = true;
				ciudad_SinEnergia_visitada.add(y);
				lista_out.add(new Arista(x,y,min));
			}
			nro_edge++;
			
		}
		System.out.println("costo minimo: "+costo_minimo);
		
		Collections.sort(lista_out);
	}
	
	public boolean esParteDeCiudadSinEnergia(int nro) {
		for (Integer integer : ciudades_centralElectrica) {
			if (integer==nro)
				return true;
		}
		return false;
	}
	
	public int getCosto_minimo() {
		return costo_minimo;
	}
	
	

	public ArrayList<Arista> getLista_out() {
		return lista_out;
	}
	
}
