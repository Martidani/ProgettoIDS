package it.unibs.ids.progetto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Gerarchia implements Serializable {
	
	private static final double MIN_FATTORECONVERSIONE = 0.5;
	private static final double MAX_FATTORECONVERSIONE = 2.0;
	/**
	 * 
	 * Attributi 
	 */
	
	private ArrayList<Nodo> alberi; 
	private ArrayList<Nodo> foglie;
	//private ArrayList<Nodo>

	public Gerarchia() {
		this.alberi = new ArrayList<>();
		this.foglie = new ArrayList<Nodo>();
		
	}
	
	

	public ArrayList<Nodo> getAlberi() {
		return alberi;
	}

	public void addAlberi(Nodo albero) throws Exception {
		if (!albero.isRoot()) throw new Exception("Il nodo immesso non è radice");
		
		alberi.add(albero);
		
		addFoglie(albero);
	}
	
	private void addFoglie(Nodo albero) {
		
		if (albero.isLeaf()) 
			foglie.add(albero);
		else {
			for (Nodo child : albero.getChildren()) {
				addFoglie(child);
			}
		}
	}
	
	public boolean verificaEsistenzaNomeRadice(String nome) {
		for (Nodo nodo : alberi) {
			if (nodo.getNome().equals(nome)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean verificaEsistenzaNomeNonRadice(String nome,Nodo radice) {
		
		if (radice.isLeaf()) {
			if (radice.getNome().equals(nome)) 
				return true;
		}else {
		
			for (Nodo nodo : radice.getChildren()) {
				if (nodo.getNome().equals(nome)) {
					return true;
				}else {
					verificaEsistenzaNomeNonRadice(nome, nodo);
				}
			}
		
		}
		
		return false;
	}
	
	
	public void aggiungiFattoreConversione(Nodo nodo1, Nodo nodo2, double fattore) throws Exception {
		
		
		addFattoreConversione(nodo1, nodo2, fattore);
		addInverso(nodo1, nodo2, fattore);
		

		
	}
	
	public boolean verificaFattoreConversione(double fattore) {
		if (fattore > MAX_FATTORECONVERSIONE || fattore < MIN_FATTORECONVERSIONE) 
			return false;
		
		return true;
	}
	
	private void addFattoreConversione(Nodo nodo1, Nodo nodo2, Double fattore) {
		nodo1.addFattori(nodo2,fattore);
	}


	
	public void addTransitivoFattoreConversione() {
		
		for (Nodo nodo1 : this.foglie) {
			for (Nodo nodo2 : this.foglie) {
				if (!nodo1.esisteFoglia(nodo2)) {
					ArrayList<Nodo> visitati = new ArrayList<Nodo>();
					nodo1.addFattori(nodo2, calcTransitivo(nodo1,nodo2,visitati));
				}

			}
			
		}
		
	}
	
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
		            return val*calcTransitivo(key, nodo2, visitati);
	            }
	            
	            
	        }
	    }
	    return null;
	}
	
	
	private void addInverso(Nodo nodo1, Nodo nodo2, double fattore) {
		addFattoreConversione(nodo2, nodo1, 1/fattore);
	}
	
	
	
	public boolean checkFoglia(Nodo foglia) {
		for (Nodo nodo : foglie) {
			if (nodo.equals(foglia)) {
				return true;
			}
		}
		return false;
	}
	public boolean checkFoglia(Nodo foglia, ArrayList<Nodo> foglieAttuali) {
		String nome = foglia.getNome();
		for (Nodo nodo : foglieAttuali) {
			if (nodo.getNome().equalsIgnoreCase(nome)) {
				return true;
			}
		}
		return false;
	}
	

	
	
	public String toString() {
		StringBuffer bf = new StringBuffer();
		
		for (Nodo nodo : alberi)  {
			bf.append("\n");
			iterative(bf, nodo, 1);

		}
		return bf.toString();
			
	}
	
	private void iterative(StringBuffer bf, Nodo nodo, int depth) {
		String asterischi = "*".repeat(depth); // Genera una stringa di asterischi in base al grado di "figlio di"
		String blank = " ".repeat(depth+1);
		if (nodo.isLeaf()) {
			bf.append(asterischi + " " + nodo.getNome());
		} else {
			bf.append(asterischi + " " + nodo.getNome() + "\n" + blank + "campo: " + nodo.getCampo() + "\n" 
		+ blank + nodo.toStringD() + "\n" + blank + nodo.toStringC());
			for (Nodo nodoChild : nodo.getChildren()) {
				bf.append("\n");
				iterative(bf, nodoChild, depth+1);

			}
		}
		
	}
	
	
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
