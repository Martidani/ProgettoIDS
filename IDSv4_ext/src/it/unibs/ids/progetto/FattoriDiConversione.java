package it.unibs.ids.progetto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
    
	public Set<Entry<Leaf,Double>> getFattori() {
		return fattori.entrySet();
	}

	public FattoriDiConversione() {
		this.fattori = new HashMap<Leaf, Double>();
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
            	if (shouldAddTransitivoFattore(nodo1, nodo2)) {
                    addFattoreIfCalculated(nodo1, nodo2);
                }
            }
        }
    }
    
    private static boolean shouldAddTransitivoFattore(Leaf nodo1, Leaf nodo2) {
        return !nodo1.equals(nodo2) && nodo1.fattoreFoglia(nodo2) == 0;
    }

    private static void addFattoreIfCalculated(Leaf nodo1, Leaf nodo2) {
        Double fattore = calcTransitivo(nodo1, nodo2, new ArrayList<>());
        if (fattore != null) {
            nodo1.addFattoreConversione(nodo2, fattore);
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
    	
        if (nodo1.equals(nodo2)) {
            return getFattoreIdentità();
            
        } else if (nodo1.fattoreFoglia(nodo2) != 0) {
            return getFattoreDiretto(nodo1, nodo2);
            
        } else {
        	return getFattoreIndiretto(nodo1, nodo2, visitati);
        }
        
    }


	
	private static double getFattoreIdentità() {
		return 1.0;
	}

	private static double getFattoreDiretto(Leaf nodo1, Leaf nodo2) {
		return nodo1.fattoreFoglia(nodo2);
	}
	
    private static Double getFattoreIndiretto(Leaf nodo1, Leaf nodo2, List<Leaf> visitati) {
    	Set<Entry<Leaf,Double>> fact = nodo1.getFattori();
    	
        if (fact.isEmpty()) 
            return null;

        for (Map.Entry<Leaf, Double> entry : fact) {
            Leaf key = entry.getKey();
            if (!visitati.contains(key)) {
                visitati.add(key);
                Double val = calcTransitivo(key, nodo2, visitati);
                if (val != null) 
                    return entry.getValue() * val; 
            }
        }
        return null;
    }
	

}
