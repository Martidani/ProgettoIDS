package it.unibs.ids.progetto.printer;

import java.util.Map.Entry;

import it.unibs.ids.progetto.FattoriDiConversione;
import it.unibs.ids.progetto.Leaf;
import it.unibs.ids.progetto.Nodo;

public class PrinterLeaf{
	
	  
  	/**
  	 * Genera una stringa delle relazioni di una foglia con le altre foglie.
  	 * 
  	 * @return Una stringa rappresentante le relazioni con le foglie.
  	 */
  	public static String toStringFactors(Nodo nodo) {
  		return toString((nodo.getNome()),((Leaf) nodo).getFattori());
  			
  	}

  	
  	/**
  	 * Genera una stringa del dominio associato al nodo.
  	 * 
  	 * @return Una stringa rappresentante il dominio associato al nodo.
  	 */
  	public static String toString(String blank,Nodo nodo) {
  		StringBuffer bf = new StringBuffer();
  		bf.append(" " + nodo.getNome() + "\n" );
  		
  		return bf.toString();
  	}
  	

  	 public static String toNavigationString(Nodo nodo) {
  	    	StringBuilder b = new StringBuilder();
  	    	b.append(" nome: " + nodo.getNome());

  	    	b.append(" |foglia!|");
  	    	return b.toString();
  	 }


 	/**
 	 * Genera una stringa delle relazioni di una foglia con le altre foglie.
 	 * 
 	 * @return Una stringa rappresentante le relazioni con le foglie.
 	 */
 	public static String toString(String nome, FattoriDiConversione fattori) {
 		StringBuffer bf = new StringBuffer();
 		bf.append("\n");
 		for (Entry<Leaf, Double> fatt : fattori.getFattori()) {
 			Leaf key = fatt.getKey();
 			Double val = fatt.getValue();
 			if (!nome.equals(key.getNome())) {
 				// Formatta il double con un massimo di tre decimali
 				String formattedVal = String.format("%.3f", val);
 				bf.append(nome + " - " + key.getNome() + " - " + formattedVal);
 				bf.append("\n");
 			}
 		}
 		return bf.toString();
 	}

}
