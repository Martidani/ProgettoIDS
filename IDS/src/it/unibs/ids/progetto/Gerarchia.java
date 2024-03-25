package it.unibs.ids.progetto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * La classe Gerarchia rappresenta la gerarchia degli alberi e gestisce le operazioni su di essa.
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
	 * @throws Exception Se il nodo immesso non è una radice
	 */
	public void addAlberi(Nodo albero) throws Exception {
		if (!albero.isRoot()) throw new Exception("Il nodo immesso non è radice");
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
	 * Verifica se esiste un nodo radice con il dato nome.
	 * 
	 * @param nome Il nome del nodo radice da cercare
	 * @return true se esiste un nodo radice con il nome specificato, false altrimenti
	 */
	public boolean verificaEsistenzaNomeRadice(String nome) {
		for (Nodo nodo : alberi) {
			if (nodo.getNome().equals(nome)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifica se esiste un nodo non radice con il dato nome sotto il nodo radice specificato.
	 * 
	 * @param nome Il nome del nodo da cercare
	 * @param radice Il nodo radice sotto il quale cercare
	 * @return true se esiste un nodo con il nome specificato sotto il nodo radice, false altrimenti
	 */
	public boolean verificaEsistenzaNomeNonRadice(String nome, Nodo radice) {
		if (radice.isLeaf()) {
			if (radice.getNome().equals(nome)) 
				return true;
		} else {
			for (Nodo nodo : radice.getChildren()) {
				if (nodo.getNome().equals(nome)) {
					return true;
				} else {
					verificaEsistenzaNomeNonRadice(nome, nodo);
				}
			}
		}
		return false;
	}
	
	/**
	 * Aggiunge un fattore di conversione tra due nodi, considerando anche l'inverso del fattore.
	 * 
	 * @param nodo1 Il primo nodo
	 * @param nodo2 Il secondo nodo
	 * @param fattore Il fattore di conversione da nodo1 a nodo2
	 * @throws Exception Se si cerca di aggiungere un fattore di conversione tra una foglia e un nodo non foglia
	 */
	public void aggiungiFattoreConversione(Nodo nodo1, Nodo nodo2, double fattore) throws Exception {
		addFattoreConversione(nodo1, nodo2, fattore);
		addInverso(nodo1, nodo2, fattore);
	}
	
	/**
	 * Aggiunge un fattore di conversione tra due nodi.
	 * 
	 * @param nodo1 Il primo nodo
	 * @param nodo2 Il secondo nodo
	 * @param fattore Il fattore di conversione da aggiungere
	 */
	private void addFattoreConversione(Nodo nodo1, Nodo nodo2, Double fattore) {
		nodo1.addFattori(nodo2, fattore);
	}
	
	/**
	 * Verifica se un dato fattore di conversione è valido.
	 * 
	 * @param fattore Il fattore di conversione da verificare
	 * @return true se il fattore è compreso nell'intervallo consentito, false altrimenti
	 */
	public boolean verificaFattoreConversione(double fattore) {
		if (fattore > MAX_FATTORECONVERSIONE || fattore < MIN_FATTORECONVERSIONE) 
			return false;
		
		return true;
	}
	
	/**
	 * Aggiunge i fattori di conversione transitivi tra tutte le coppie di foglie nella gerarchia.
	 */
	public void addTransitivoFattoreConversione() {
	    for (Nodo nodo1 : this.foglie) {
	        for (Nodo nodo2 : this.foglie) {
	            if (!nodo1.esisteFoglia(nodo2)) {
	                ArrayList<Nodo> visitati = new ArrayList<Nodo>();
	                nodo1.addFattori(nodo2, calcTransitivo(nodo1, nodo2, visitati));
	            }
	        }
	    }
	}
	
	/**
	 * Metodo privato per calcolare il fattore di conversione transitivo tra due nodi.
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
	            	Double val = entry.getValue();
		            return val * calcTransitivo(key, nodo2, visitati);
	            }
	        }
	    }
	    return null;
	}
	
	/**
	 * Aggiunge un fattore di conversione inverso tra due nodi.
	 * 
	 * @param nodo1 Il primo nodo
	 * @param nodo2 Il secondo nodo
	 * @param fattore Il fattore di conversione da nodo2 a nodo1
	 */
	private void addInverso(Nodo nodo1, Nodo nodo2, double fattore) {
		addFattoreConversione(nodo2, nodo1, 1 / fattore);
	}
	
	/**
	 * Controlla se una foglia è presente nella gerarchia.
	 * 
	 * @param foglia Il nodo foglia da cercare
	 * @return true se la foglia è presente, false altrimenti
	 */
	public boolean checkFoglia(Nodo foglia) {
		for (Nodo nodo : foglie) {
			if (nodo.equals(foglia)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Controlla se una foglia è presente nella lista specificata.
	 * 
	 * @param foglia Il nodo foglia da cercare
	 * @param foglieAttuali La lista di foglie in cui cercare
	 * @return true se la foglia è presente nella lista, false altrimenti
	 */
	public boolean checkFoglia(Nodo foglia, ArrayList<Nodo> foglieAttuali) {
		String nome = foglia.getNome();
		for (Nodo nodo : foglieAttuali) {
			if (nodo.getNome().equalsIgnoreCase(nome)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Restituisce una rappresentazione testuale della gerarchia.
	 * 
	 * @return Una stringa che rappresenta la gerarchia
	 */
	public String toString() {
		StringBuffer bf = new StringBuffer();
		
		for (Nodo nodo : alberi)  {
			bf.append("\n");
			iterative(bf, nodo, 1);

		}
		return bf.toString();
			
	}
	
	/**
	 * Metodo ausiliario ricorsivo per generare una rappresentazione testuale della gerarchia.
	 * 
	 * @param bf Il buffer in cui aggiungere la rappresentazione
	 * @param nodo Il nodo corrente
	 * @param depth La profondità del nodo nella gerarchia
	 */
	private void iterative(StringBuffer bf, Nodo nodo, int depth) {
		String asterischi = "*".repeat(depth); // Genera una stringa di asterischi in base al grado di "figlio di"
		String blank = " ".repeat(depth + 1);
		if (nodo.isLeaf()) {
			bf.append(asterischi + " " + nodo.getNome());
		} else {
			bf.append(asterischi + " " + nodo.getNome() + "\n" + blank + "campo: " + nodo.getCampo() + "\n" 
		+ blank + nodo.toStringD() + "\n" + blank + nodo.toStringC());
			for (Nodo nodoChild : nodo.getChildren()) {
				bf.append("\n");
				iterative(bf, nodoChild, depth + 1);
			}
		}
	}
	
	/**
	 * Restituisce il nodo corrispondente al nome specificato nella gerarchia.
	 * 
	 * @param nomeNodo Il nome del nodo da cercare
	 * @param root La radice della gerarchia
	 * @param list La lista di nodi in cui cercare
	 * @return Il nodo corrispondente al nome specificato, null se non trovato
	 */
	public Nodo visualizzaNodo(String nomeNodo, String root, List<Nodo> list) {
		
		for (Nodo nodo : list) {
			
			if (nodo.getNome().equals(root)) {
				
				for (Nodo nodoChild : nodo.getChildren()) {
					
					if (nodoChild.isLeaf()) {
						if (nodoChild.getNome().equals(nomeNodo))
							return nodoChild;
					} else 
							return visualizzaNodo(nomeNodo, nodoChild.getNome(), nodo.getChildren());
						
				}
				
			}
		}
		return null;
	}


}