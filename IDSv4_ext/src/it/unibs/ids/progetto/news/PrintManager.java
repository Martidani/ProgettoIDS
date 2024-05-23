package it.unibs.ids.progetto.news;

import java.util.Map.Entry;

import it.unibs.ids.progetto.Albero;
import it.unibs.ids.progetto.Comprensorio;
import it.unibs.ids.progetto.FattoriDiConversione;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Leaf;
import it.unibs.ids.progetto.Nodo;
import it.unibs.ids.progetto.NotLeaf;

public class PrintManager {
	
	
	  /**
     * Restituisce una rappresentazione testuale dell'albero.
     * 
     * @return Una stringa che rappresenta l'albero
     */
    public String toString(Albero albero) {
        StringBuffer bf = new StringBuffer();
        iterative(bf, albero.getRadice(), 1);
        return bf.toString();
    }

    /**
     * Metodo ausiliario ricorsivo per generare una rappresentazione testuale dell'albero.
     * 
     * @param bf Il buffer in cui aggiungere la rappresentazione
     * @param nodo Il nodo corrente
     * @param depth La profondità del nodo nella gerarchia
     */
    private static void iterative(StringBuffer bf, Nodo nodo, int depth) {
        String asterischi = "*".repeat(depth); // Genera una stringa di asterischi in base al grado di "figlio di"
        String blank = " ".repeat(depth + 1);
        if (nodo.isLeaf()) {
            bf.append(asterischi + " " + nodo.getNome());
        } else {
            bf.append(asterischi + NotLeafPrintManager.toString(blank,(NotLeaf)nodo));
            for (Nodo nodoChild : ((NotLeaf)nodo).getChildren()) {
                bf.append("\n");
                iterative(bf, nodoChild, depth + 1);
            }
        }
    }
    
    /**
     * Restituisce una rappresentazione testuale del comprensorio
     * con elencati i nomi dei comuni presenti.
     * 
     * @return String La rappresentazione testuale del comprensorio
     */
    public String toString(Comprensorio comprensorio) {
        StringBuffer bf = new StringBuffer();
        bf.append(comprensorio.getNome() + "\n");
        for (String comune : comprensorio.getComprensorio()) {
            bf.append(" - " + comune);
            bf.append("\n");
        }
        
        return bf.toString();
    }



	/**
	 * Genera una stringa delle relazioni di una foglia con le altre foglie.
	 * 
	 * @return Una stringa rappresentante le relazioni con le foglie.
	 */
	public static String toString(String nome, FattoriDiConversione fattori) {
		StringBuffer bf = new StringBuffer();
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
    
	
	/**
	 * Restituisce una rappresentazione testuale della geografia, includendo la lista dei comprensori.
	 * 
	 * @return Una stringa che rappresenta la geografia
	 */
	public String toString(Geografia geografia) {
		StringBuffer bf = new StringBuffer();
		for (Comprensorio comprensorio : geografia.getComprensori()) {
			bf.append(comprensorio.toString());
			bf.append("\n");
		}
		
		return bf.toString();
	}
	
	  
    /**
     * Restituisce una rappresentazione testuale della 
     * gerarchia (di tutti i suoi alberi).
     * 
     * @return Una stringa che rappresenta la gerarchia
     */
    public String toString(Gerarchia gerarchia) {
        StringBuilder builder = new StringBuilder();
        
        for (Nodo nodo : gerarchia.getRadici())  {
            builder.append("\n\n");
            try {
                builder.append(new Albero((NotLeaf) nodo).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return builder.toString();     
    }
    
    /**
     * Restituisce una rappresentazione testuale della 
     * gerarchia (di tutti i suoi alberi).
     * 
     * @param alberi La lista degli alberi di nodi
     * @return Una stringa che rappresenta la gerarchia
     */
    public String toStringRadici(Gerarchia gerarchia) {
        StringBuffer bf = new StringBuffer();
        
        for (Nodo nodo : gerarchia.getRadici())  {
            bf.append("\n* " + nodo.getNome());

        }
        return bf.toString();
            
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
