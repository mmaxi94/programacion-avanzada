package coloreo;

import java.io.FileNotFoundException;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		ColoreoGrafo coloreo = new ColoreoGrafo("grafo.in");
		coloreo.secuencialAleatorio();
		coloreo.matula();
		coloreo.wellshPowell();
		
		if(ProgramaProbador.probador("grafo.in", "coloreoWellshPowell.out"))
			System.out.println("El coloreo es correcto");
		else
			System.out.println("El coloreo No fue correcto");
		
		
	}
	

}
