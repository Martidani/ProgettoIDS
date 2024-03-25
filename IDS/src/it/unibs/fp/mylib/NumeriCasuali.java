package it.unibs.fp.mylib;
import java.util.*;

/**
 * Classe per generare Numeri Casuali
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class NumeriCasuali {
	

	private static Random estrattore = new Random();
	
	/**
	 * Metodo per estrarre un numero int tra min e max
	 */
	public static int estraiIntero(int min, int max)
	{
		 int range = max + 1 - min;
		 int casual = estrattore.nextInt(range);
		 return casual + min;
	}
	
	/**
	 * Metodo per estrarre un numero double tra min e max
	 */
	public static double estraiDouble(double min, double max)
	{
	 double range = max - min;
	 double casual = estrattore.nextDouble();
	 
	 double posEstratto = range*casual;
	 
	 return posEstratto + min;
	}

}