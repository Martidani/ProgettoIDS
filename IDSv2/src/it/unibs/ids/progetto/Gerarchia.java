package it.unibs.ids.progetto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * La classe Gerarchia rappresenta l'insieme di tutti gli 
 * alberi nel sistema e gestisce le operazioni su di essi.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Gerarchia implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private static final double MIN_FATTORECONVERSIONE = 0.5;
	private static final double MAX_FATTORECONVERSIONE = 2.0;
	
	private ArrayList<Nodo> alberi; 
	private ArrayList<Nodo> foglie;

	/**
	 * Costruttore della classe Gerarchia.
	 */
	public Gerarchia() {
		this.alberi = new ArrayList<>();
		this.foglie = new ArrayList<Nodo>();
	}

	
	/**
	 * Restituisce l'elenco degli alberi presenti nella gerarchia.
	 * 
	 * @return Lista di alberi nella gerarchia
	 */
	public ArrayList<Nodo> getAlberi() {
		return alberi;
	}

	/**
	 * Aggiunge un albero alla gerarchia.
	 * 
	 * @param albero Il nodo radice dell'albero da aggiungere
	 * @throws LeafHasChildrenException 
	 * @throws Exception Se il nodo immesso non è una radice
	 */
	public void addAlbero(Nodo albero) throws LeafHasChildrenException  {
		if (!albero.isRoot()) 
			throw new LeafHasChildrenException();
		alberi.add(albero);
		addFoglie(albero);
	}
	
	/**
	 * Aggiunge le foglie dell'albero all'elenco delle foglie della gerarchia.
	 * 
	 * @param albero Il nodo radice dell'albero
	 */
	private void addFoglie(Nodo albero) {
		if (albero.isLeaf()) 
			foglie.add(albero);
		else {
			for (Nodo child : albero.getChildren()) {
				addFoglie(child);
			}
		}
	}
	
    /**
     * Aggiunge un fattore di conversione tra due nodi, 
     * e l'inverso del fattore stesso nella relazione inversa.
     * 
     * @param nodo1 Il primo nodo
     * @param nodo2 Il secondo nodo
     * @param fattore Il fattore di conversione da nodo1 a nodo2
     */
	public void addFattoreConversione(Nodo nodo1, Nodo nodo2, double fattore) {
        nodo1.addFattori(nodo2, fattore);
        nodo2.addFattori(nodo1, 1 / fattore); // Aggiunge il fattore inverso
	}
	
	/**
	 * Verifica se un dato fattore di conversione è valido.
	 * 
	 * @param fattore Il fattore di conversione da verificare
	 * @return true se il fattore è compreso nell'intervallo consentito, false altrimenti
	 */
	public boolean verificaFattoreConversione(double fattore) {
		return (fattore >= MIN_FATTORECONVERSIONE && fattore <= MAX_FATTORECONVERSIONE);
    }
	
	/**
	 * Aggiunge i fattori di conversione transitivi tra TUTTE le coppie di foglie nella gerarchia.
	 */
	public void addTransitivoFattoreConversione() {
	    for (Nodo nodo1 : this.foglie) {
	        for (Nodo nodo2 : this.foglie) {
	            if (!nodo1.equals(nodo2) && !nodo1.esisteFoglia(nodo2)) {
	                Double fattore = calcTransitivo(nodo1, nodo2, new ArrayList<>());
	                if (fattore != null) {
	                    nodo1.addFattori(nodo2, fattore);
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
	private Double calcTransitivo(Nodo nodo1, Nodo nodo2, List<Nodo> visitati) {
	    if (nodo1.equals(nodo2)) {
	        return 1.0;
	    } else if (nodo1.esisteFoglia(nodo2)) {
	        return nodo1.valoreRelazione(nodo2);
	    } else {
	        HashMap<Nodo, Double> foglieNodo1 = nodo1.getFattori();
	        if (foglieNodo1.isEmpty()) return null;
	        for (Map.Entry<Nodo, Double> entry : foglieNodo1.entrySet()) {
	            Nodo key = entry.getKey();
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
	

}