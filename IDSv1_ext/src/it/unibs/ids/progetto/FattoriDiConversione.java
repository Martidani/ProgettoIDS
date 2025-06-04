package it.unibs.ids.progetto;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import it.unibs.ids.progetto.news.Leaf;


/**
 * Classe per la gestione dei fattori di conversione.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class FattoriDiConversione implements Serializable{

    private static final long serialVersionUID = 1L;
	private static final double MIN_FATTORECONVERSIONE = 0.5;
    private static final double MAX_FATTORECONVERSIONE = 2.0;
    
	private HashMap<Leaf, Double> fattori;

	
	
    public FattoriDiConversione() {
		this.fattori = new HashMap<Leaf, Double>();
	}
    


	
	public Set<Entry<Leaf,Double>> getFattori() {
		return fattori.entrySet();
	}




	/**
	 * Verifica se esiste un fattore di conversione con una data foglia.
	 * 
	 * @param foglia La foglia con cui verificare la relazione.
	 * @return true se esiste una relazione di conversione con la foglia, false altrimenti.
	 */
	public double fattoreFoglia(Leaf foglia) {
		if (fattori.containsKey(foglia))
			return fattori.get(foglia);
		return 0;
	}


	/**
	 * Aggiunge un fattore di conversione associato al nodo.
	 * 
	 * @param foglia  Il nodo foglia con cui associare il fattore.
	 * @param fattore Il fattore di conversione da aggiungere.
	 */
	public void addFattoreConversione(Leaf foglia, Double fattore) {
		fattori.put(foglia, fattore);
	}



    /**
     * Verifica se un dato fattore di conversione è valido.
     * 
     * @param fattore Il fattore di conversione da verificare
     * @return true se il fattore è compreso nell'intervallo consentito, false altrimenti
     */
    public static boolean verificaFattoreConversione(double fattore) {
        return (fattore >= MIN_FATTORECONVERSIONE && fattore <= MAX_FATTORECONVERSIONE);
    }

    /**
     * Aggiunge i fattori di conversione transitivi tra TUTTE le coppie di foglie nella gerarchia.
     * 
     * @param gerarchia La gerarchia su cui operare
     */
    public static void addTransitivoFattoreConversione(Gerarchia gerarchia) {
        
        for (Leaf nodo1 : gerarchia.getFoglie()) {
            for (Leaf nodo2 : gerarchia.getFoglie()) {
                if (!nodo1.equals(nodo2) && nodo1.getFattori().fattoreFoglia(nodo2) == 0) {
                    Double fattore = calcTransitivo(nodo1, nodo2, new ArrayList<>());
                    if (fattore != null) {
                    	nodo1.addFattoreConversione(nodo2, fattore);
                    }
                }
            }
        }
    }

    /**
     * Metodo per calcolare il fattore di conversione transitivo tra due nodi.
     * 
     * @param nodo1 Il primo nodo
     * @param nodo2 Il secondo nodo
     * @param visitati Lista dei nodi visitati durante il calcolo
     * @return Il fattore di conversione transitivo tra i due nodi, null se non è possibile calcolarlo
     */
    private static Double calcTransitivo(Leaf nodo1, Leaf nodo2, List<Leaf> visitati) {
    	FattoriDiConversione fact = nodo1.getFattori();
        if (nodo1.equals(nodo2)) {
            return 1.0;
        } else if (fact.fattoreFoglia(nodo2) != 0) {
            return fact.fattoreFoglia(nodo2);
        } else {
            if (fact.isEmpty())
                return null;
            for (Map.Entry<Leaf, Double> entry : fact.getFattori()) {
            	Leaf key = entry.getKey();
                if (!visitati.contains(key)) {
                    visitati.add(key);
                    Double val = calcTransitivo(key, nodo2, visitati);
                    if (val != null) {
                        return entry.getValue() * val;
                    }
                }
            }
        }
        return null; // Restituiamo null se non è possibile calcolare il fattore di conversione
    }
    
	private boolean isEmpty() {
		if (fattori.size()==0)
			return true;
		return false;
	}




	/**
	 * Genera una stringa delle relazioni di una foglia con le altre foglie.
	 * 
	 * @return Una stringa rappresentante le relazioni con le foglie.
	 */
	public String toString(String nome) {
		StringBuffer bf = new StringBuffer();
		for (Entry<Leaf, Double> fatt : getFattori()) {
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
