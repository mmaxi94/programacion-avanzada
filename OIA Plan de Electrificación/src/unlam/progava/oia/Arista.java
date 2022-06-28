package unlam.progava.oia;

public class Arista implements Comparable<Arista> {

	int nodoIzq;
	int nodorDer;
	int peso;
	
	public Arista(int nodoIzq, int nodorDer, int peso) {
		this.nodoIzq = nodoIzq;
		this.nodorDer = nodorDer;
		this.peso = peso;
	}
	
	public int getNodoIzq() {
		return nodoIzq;
	}

	public int getNodorDer() {
		return nodorDer;
	}

	public int getPeso() {
		return peso;
	}

	@Override
	public int compareTo(Arista o) {
		int valor=o.peso-this.peso;
		if (valor!=0)
			return valor;
		
		return o.nodoIzq-this.nodoIzq;
	}
}
