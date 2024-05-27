package it.unibs.fp.mylib;
import java.util.*;

/**
 * Classe per generare Numeri Casuali e Stringhe Casuali
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class Estrattore {
public static Random estrattore = new Random();
	
	/**
	 * Metodo per estrarre un numero intero casuale compreso tra min e max (inclusi).
	 * 
	 * @param min il valore minimo dell'intervallo (incluso)
	 * @param max il valore massimo dell'intervallo (incluso)
	 * @return un numero intero casuale compreso tra min e max
	 */
	public static int estraiIntero(int min, int max)
	{
		 int range = max + 1 - min;
		 int casual = estrattore.nextInt(range);
		 return casual + min;
	}
	
	/**
	 * Metodo per estrarre un numero double casuale compreso tra min e max.
	 * 
	 * @param min il valore minimo dell'intervallo
	 * @param max il valore massimo dell'intervallo
	 * @return un numero double casuale compreso tra min e max
	 */
	public static double estraiDouble(double min, double max)
	{
	 double range = max - min;
	 double casual = estrattore.nextDouble();
	 
	 double posEstratto = range*casual;
	 
	 return posEstratto + min;
	}

	/**
	 * Metodo per estrarre una stringa di una certa lunghezza, 
	 * composta da caratteri presi da una lista di caratteri fornita.
	 * 
	 * @param lista la lista di caratteri da cui estrarre la stringa
	 * @param length la lunghezza della stringa da generare
	 * @return una stringa casuale di lunghezza specificata, composta da caratteri della lista fornita
	 */
	public static String estraiString(String lista, int length) {
		StringBuilder identificatore = new StringBuilder();
		Random rand = new Random();
		
		for (int i = 0; i < length; i++) {
			identificatore.append(lista.charAt(rand.nextInt(lista.length())));
		}
		
		return identificatore.toString();
	}
}
