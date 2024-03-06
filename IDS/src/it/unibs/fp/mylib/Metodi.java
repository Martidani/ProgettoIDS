package it.unibs.fp.mylib;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

/**
 * classe contenente metodi di utilità generale
 * @author federico
 */
public class Metodi {

	/**
	 * Metodo per definire se un numero è primo
	 * @param x int
	 * @return boolean
	 */
	public static boolean isPrimo (int x) {
		int numDiv = 0, i;
		for (i = 1; i<=x; i++) {
			if (x%i==0) numDiv++;
		}
		if (numDiv > 2) return false;
		return true;
	}
	
	/**
	 * Metodo per definire se un numero è primo
	 * @param x long
	 * @return boolean
	 */
	public static boolean isPrimo (long n) {
		
		if (n==2) return true;
		
		if (n<=1 || n%2 == 0) return false;
		

		for (long i=3; i<=Math.sqrt(n); i+=2) {
			if (n%i ==0) return false;
		}
		
		return true;
	}
	
	
	/**
	 * Metodo per trovare il primo carattere MINUSCOLO in una Stringa
	 */
	public static char trovaPrimoCharMinuscolo(String x) {
		boolean found = false;
		char ch = '?';
		int position = 0;
		while (!found && position< x.length()) {
			ch = x.charAt (position);
			if (Character.isLowerCase (ch)) found = true;
			else position++; 
		}
		return ch;
	}
	
	/**
	 * Metodo per trovare il primo carattere MAIUSCOLO in una Stringa
	 */
	public static char trovaPrimoCharMaiuscolo(String x) {
		boolean found = false;
		char ch = '?';
		int position = 0;
		while (!found && position< x.length()) {
			ch = x.charAt (position);
			if (Character.isUpperCase (ch)) found = true;
			else position++; 
		}
		return ch;
	}
	
	/**
	 * Metodo per contare il numero di lettere maiuscole in una Stringa
	 */
	public static int contaMaiuscole(String x) {
		int upperCaseletters=0;
			for(int i = 0; i< x.length();i++) {
				char ch = x.charAt(i);
				if (Character.isUpperCase (ch) ) upperCaseletters++;
			}
		return upperCaseletters;
	}
	
	/**
	 * Metodo per contare il numero di lettere minuscole in una Stringa
	 */
	public static int contaMinuscole(String x) {
		int lowerCaseletters=0;
		for(int i = 0; i< x.length();i++) {
			char ch = x.charAt(i);
			if (Character.isLowerCase (ch) ) lowerCaseletters++;
		}
	return lowerCaseletters;
	}
	
	
	/**
	 * metodo per trovare il max in un array di double
	 * @param numeri
	 * @return
	 */
	public static double trovaMax (double numeri[]) {
		double max = numeri[0];
		for (int i=0; i < numeri.length; i++) 
			if (max < numeri[i]) 
				max = numeri[i];
		return max;
	}
	
	/**
	 * metodo per trovare il max in un array di int
	 * @param numeri
	 * @return
	 */
	public static int trovaMax (int numeri[]) {
		int max = numeri[0];
		for (int i=0; i < numeri.length; i++) 
			if (max < numeri[i]) 
				max = numeri[i];
		return max;
	}
	
	
	/**
	 * metodo per trovare il min in un array di double
	 * @param numeri
	 * @return
	 */
	public static double trovaMin (double numeri[]) {
		double min = numeri[0];
		for (int i=0; i < numeri.length; i++) 
			if (min > numeri[i]) 
				min = numeri[i];
		return min;
	}
	
	/**
	 * metodo per trovare il min in un array di int
	 * @param numeri
	 * @return
	 */
	public static int trovaMin (int numeri[]) {
		int min = numeri[0];
		for (int i=0; i < numeri.length; i++) 
			if (min > numeri[i]) 
				min = numeri[i];
		return min;
	}
	
	/**
	 * metodo per convertire Array in ArrayList
	 * @param parola
	 * @return
	 */
	public static ArrayList<String> conversioneArray (String [] parola) {
		ArrayList <String> parolaNew = new ArrayList <String>(Arrays.asList(parola));
		return parolaNew;
	}

	/**
	 * metodo per creare una funzione generica
	 * @param g2 obj Graphics
	 * @param xi x minima
	 * @param xmax x massima
	 * @param dx incremento x
	 * @param f Funzione (ex. sin, cos..)
	 */
	public static void plotFunction(Graphics2D g2, double xi, double xmax, 
			double dx, Function<Double, Double> f, Color colore) {
		g2.setColor(colore);
		g2.setStroke(new BasicStroke((float)(2./50.)));
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, (int)((xmax-xi)/dx)+1); 

		path.moveTo(xi, Math.sin(xi));
		for (double x = xi; x<=xmax; x+=dx) {
			path.lineTo(x, f.apply(x));

		}	
		g2.draw(path);
	}
}

