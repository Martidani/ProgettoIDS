package it.unibs.ids.progetto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gerarchia {
	
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
		
	}
	
	

	public ArrayList<Nodo> getAlberi() {
		return alberi;
	}

	public void addAlberi(Nodo albero) throws Exception {
		if (albero.isRoot()) throw new Exception("Il nodo immesso non è radice");
		alberi.add(albero);
		addFoglie(albero);
	}
	
	private void addFoglie(Nodo albero) {
		
		if (albero.getChildren() == null) 
			foglie.add(albero);
		else {
			for (Nodo child : albero.getChildren()) {
				addFoglie(child);
			}
		}
	}
	
	
	public void aggiungiFattoreConversione(Nodo nodo1, Nodo nodo2, double fattore) throws Exception {
		if (!verificaFattoreConversione(fattore)) throw new 
		Exception ("Fattore di conversione sbagliato, deve essere 0.5<__<2.0 ");
		
		addFattoreConversione(nodo1, nodo2, fattore);
		addInverso(nodo1, nodo2, fattore);
		addTransitivoFattoreConversione(nodo1, nodo2, fattore);
		
	}
	
	private boolean verificaFattoreConversione(double fattore) {
		if (fattore >= MAX_FATTORECONVERSIONE || fattore <= MIN_FATTORECONVERSIONE) 
			return false;
		
		return true;
	}
	
	private void addFattoreConversione(Nodo nodo1, Nodo nodo2, Double fattore) {
		nodo1.addFattori(nodo2,fattore);
	}

	private void addTransitivoFattoreConversione(Nodo nodo1, Nodo nodo2, double fattore) {
		
		for (Nodo nodo3 : this.foglie) {
			if (!nodo1.esisteFoglia(nodo3)) {
				nodo1.addFattori(nodo3, calcTransitivo(nodo1,nodo2,nodo3,fattore));
				
			}
			
		}
		
	}
	
	private Double calcTransitivo(Nodo nodo1,Nodo nodo2, Nodo nodo3,double fattore) {
		
		if (nodo1.equals(nodo3)) {
			return 1.0;
		}
		if (nodo2.esisteFoglia(nodo3)) {
			return fattore * nodo2.valoreRelazione(nodo3);
		}else {
			HashMap<Nodo, Double> foglieNodo2 = nodo2.getFattori();
			for (Map.Entry<Nodo, Double> entry : foglieNodo2.entrySet()) {
				Nodo key = entry.getKey();
				Double val = entry.getValue();
				calcTransitivo(nodo2, key, nodo3, val);
				
			}
			
		}
		
		return null;	
	}
	
	
	
	private void addInverso(Nodo nodo1, Nodo nodo2, double fattore) {
		addFattoreConversione(nodo2, nodo1, 1/fattore);
		addTransitivoFattoreConversione(nodo2, nodo1, 1/fattore);
	}
	
	


}
