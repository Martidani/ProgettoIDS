package it.unibs.ids.progetto.news;

import it.unibs.ids.progetto.Leaf;
import it.unibs.ids.progetto.Nodo;

public class LeafPrintManager{
	
	  
  	/**
  	 * Genera una stringa delle relazioni di una foglia con le altre foglie.
  	 * 
  	 * @return Una stringa rappresentante le relazioni con le foglie.
  	 */
  	public static String toStringFactors(Nodo nodo) {
  		return PrintManager.toString((nodo.getNome()),((Leaf) nodo).getFattori());
  			
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


}
