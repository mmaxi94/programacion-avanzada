package unlam.progava.oia;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

	private Grafo grafo;
	private int nodoInicial;
	private int nodoLlegada;
	private List<Integer>[] listaAdj;
	
	public BFS(Grafo g, int nodoInicial, int nodoLlegada) {
		this.grafo=g;
		this.nodoInicial=nodoInicial;
		this.nodoLlegada=nodoLlegada;
		this.listaAdj=this.grafo.getListaAdj();
	}
	
	public int ejecutarBFS() {
		int contador=0;
		
		Queue<List<Integer>> ColaCaminos = new LinkedList<>();
		
		List<Integer> rutaActual = new LinkedList<>();
		
		rutaActual.add(this.nodoInicial);
		ColaCaminos.add(rutaActual);
		
		while(!ColaCaminos.isEmpty()) {
			
			rutaActual = ColaCaminos.poll();
			int last=rutaActual.get(rutaActual.size()-1);
			
			if(last==this.nodoLlegada)
				contador++;
			
			for (int i = 0; i < listaAdj[last].size(); i++) {
				int adyacenteUltimoNodo=listaAdj[last].get(i);
				
				if(!fueVisitado(adyacenteUltimoNodo,rutaActual)) {
					List<Integer> nuevaRuta = new LinkedList<>(rutaActual);
					nuevaRuta.add(adyacenteUltimoNodo);
					ColaCaminos.add(nuevaRuta);
				}
			}
			
		}
		
		return contador;
	}
	
	public boolean fueVisitado(int adyacenteUltimoNodo, List<Integer> rutaActual) {
		
		for (int i = 0; i < rutaActual.size(); i++) {
			if(adyacenteUltimoNodo==rutaActual.get(i))
				return true;
		}
		return false;
	}
}
