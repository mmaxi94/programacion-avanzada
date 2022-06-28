package coloreo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ColoreoGrafo {
	
	protected MatrizSimetrica matrizAdyacencia;
	private Nodo[] nodos;
	private int cantAristas;
	private int cantNodos;
	
	public ColoreoGrafo(String archEntrada){
		
		try {
			Scanner entrada = new Scanner(new File(archEntrada));
			this.cantNodos = entrada.nextInt();
			this.cantAristas = entrada.nextInt();
			this.matrizAdyacencia = new MatrizSimetrica(this.cantNodos);
			this.nodos = new Nodo[this.cantNodos];
			
			for(int i = 0; i < this.cantNodos; i++) //Creo todos los nodos.
				nodos[i] = new Nodo(i);
			
			for(int i = 0; i < this.cantAristas; i++){
				int nodo1 = entrada.nextInt() - 1;
				int nodo2 = entrada.nextInt() - 1;
				this.nodos[nodo1].aumentarGrado();
				this.nodos[nodo2].aumentarGrado();
				
				this.matrizAdyacencia.setFC(nodo1, nodo2, true);
			}		
			
			entrada.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void secuencialAleatorio(){
		mesclarArray();
		imprimirSolucion(colorear(), "coloreoSecuencialAleatorio.out");
	}
	
	public void matula(){
		mesclarArray();
		ordernarAscendente();
		imprimirSolucion(colorear(), "coloreoMatula.out");
	}
	
	public void wellshPowell(){
		mesclarArray();
		ordernarDescendente();
		imprimirSolucion(colorear(), "coloreoWellshPowell.out");
	}
	
	public int colorear(){
		
		limpiarColores();
		
		int cantColores = 0;
		
		for(int i = 0; i < this.cantNodos; i++){
			
			int color = 0;
			boolean pintado = false;
			
			while(!pintado){
				color++;
				nodos[i].setColor(color);
				pintado = true;
				for(int j = 0; j < this.cantNodos; j++){
					if(this.matrizAdyacencia.getFC(nodos[i].getNroNodo(), nodos[j].getNroNodo()) && 
							nodos[i].getColor() == nodos[j].getColor()) {
						pintado = false;
						break;
					}
				}
			}
			
			if(nodos[i].getColor() > cantColores)
				cantColores = nodos[i].getColor();
		}
		
		System.out.println(cantColores);
		
		for(int i = 0; i < this.cantNodos; i++)
			System.out.println((nodos[i].getNroNodo() + 1)+" "+nodos[i].getGrado()+" "+nodos[i].getColor());
		
		return cantColores;
		
	}
	
	private void limpiarColores(){
		
		for(int i = 0; i < this.cantNodos; i++)
			nodos[i].setColor(0);
	}
	
	private void ordernarAscendente(){
		
		int j = 0;
		Nodo aux;
		boolean cambio = true;
		while(cambio){
			cambio = false;
			j++;
			for(int i = 0; i < this.cantNodos-j; i++)
				if(this.nodos[i].getGrado() > this.nodos[i+1].getGrado()){
					aux = this.nodos[i];
					this.nodos[i] = this.nodos[i+1];
					this.nodos[i+1] = aux;
					cambio = true;
				}
			
		}
	}
	
	private void ordernarDescendente(){
		
		int j = 0;
		Nodo aux;
		boolean cambio = true;
		while(cambio){
			cambio = false;
			j++;
			for(int i = 0; i < this.cantNodos-j; i++)
				if(this.nodos[i].getGrado() < this.nodos[i+1].getGrado()){
					aux = this.nodos[i];
					this.nodos[i] = this.nodos[i+1];
					this.nodos[i+1] = aux;
					cambio = true;
				}
			
		}
	}
	
	private void mesclarArray() {
	    
	    Random rnd = ThreadLocalRandom.current();
	    for (int i = nodos.length - 1; i > 0; i--){
	    	int index = rnd.nextInt(i + 1);
	    	Nodo aux = nodos[index];
	    	this.nodos[index] = nodos[i];
	    	this.nodos[i] = aux;
	    }
	}
	
	private void imprimirSolucion(int cantColores, String ruta){
		
		try {
			PrintWriter salida = new PrintWriter(new FileWriter(ruta));
			salida.println(this.cantNodos+" "+cantColores);
			
			for(int i = 0; i < this.cantNodos; i++)
				salida.println((nodos[i].getNroNodo() + 1)+" "+nodos[i].getColor());
			
			salida.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
