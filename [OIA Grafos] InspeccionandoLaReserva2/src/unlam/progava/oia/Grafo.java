package unlam.progava.oia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Grafo {

	private int cantNodos;
	private int cantAristas;
	private int nodoInicial;
	private int nodoLlegada;
	private List<Integer>[] listaAdj;
	
	public List<Integer>[] getListaAdj() {
		return listaAdj;
	}

	private int cantRecorridos;

	
	@SuppressWarnings("unchecked")
	public Grafo(int cantNodos, int cantAristas,int nodoInicial,int nodoLlegada) {
		this.cantNodos=cantNodos;
		this.cantAristas= cantAristas;
		this.nodoInicial=nodoInicial;
		this.nodoLlegada=nodoLlegada;
		
		listaAdj = new LinkedList[cantNodos];
		
		for (int i = 0; i < cantNodos; i++) {
			listaAdj[i]= new LinkedList<Integer>();
		}
	}
	
	public void addEdge(int src, int des) {
		this.listaAdj[src].add(des);
	}
	
	public void resolver() {
		BFS bfs = new BFS(this, this.nodoInicial, this.nodoLlegada);
		
		this.cantRecorridos = bfs.ejecutarBFS();
		
	}
	

	public int getCantRecorridos() {
		return cantRecorridos;
	}


}
