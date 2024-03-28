package it.unibs.ids.progetto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.unibs.ids.progetto.news.Albero;


/**
 * La classe Gerarchia rappresenta l'insieme di tutti gli 
 * alberi nel sistema e gestisce le operazioni su di essi.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Gerarchia implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	
	private ArrayList<Nodo> alberi; 

	/**
	 * Costruttore della classe Gerarchia.
	 */
	public Gerarchia() {
		this.alberi = new ArrayList<>();

	}
	
    /**
     * Restituisce una rappresentazione testuale della 
     * gerarchia (di tutti i suoi alberi).
     * 
     * @param alberi La lista degli alberi di nodi
     * @return Una stringa che rappresenta la gerarchia
     */
    public String toString() {
        StringBuffer bf = new StringBuffer();
        
        for (Nodo nodo : alberi)  {
            bf.append("\n\n");
            try {
				bf.append( new Albero(nodo) .toString());
			} catch (Exception e) {

				
				e.printStackTrace();
			}

        }
        return bf.toString();
            
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
	public void addAlbero(Albero albero)   {

		alberi.add(albero.getRadice());

	}
	
    /**
     * Verifica se esiste un nodo radice con il dato nome.
     * 
     * @param nome Il nome del nodo radice da cercare
     * @param alberi La lista degli alberi di nodi
     * @return true se esiste un nodo radice con il nome specificato, false altrimenti
     */
    public boolean verificaEsistenzaNomeRadice(String nome) {
        for (Nodo albero : alberi) 
            if (albero.getNome().equals(nome)) 
                return true;
            
        
        return false;
    }

    /**
     * Restituisce il nodo corrispondente al nome specificato nella gerarchia.
     * 
     * @param nomeNodo Il nome del nodo da cercare
     * @param root La radice della gerarchia
     * @param list La lista di nodi in cui cercare
     * @return Il nodo corrispondente al nome specificato, null se non trovato
     */
    public Nodo visualizzaNodo(String nomeNodo, String root) {
    	Nodo nodo = visualizza(nomeNodo, root, this.alberi);
    	if (nodo == null)
    		return nodo;
        return null;
    }
    
    /**
     * Restituisce il nodo corrispondente al nome specificato nella gerarchia.
     * 
     * @param nomeNodo Il nome del nodo da cercare
     * @param root La radice della gerarchia
     * @param list La lista di nodi in cui cercare
     * @return Il nodo corrispondente al nome specificato, null se non trovato
     */
    private static Nodo visualizza(String nomeNodo, String root, List<Nodo> list) {
        for (Nodo nodo : list) {
            if (nodo.getNome().equals(root)) {
                for (Nodo nodoChild : nodo.getChildren()) {
                    if (nodoChild.isLeaf()) {
                        if (nodoChild.getNome().equals(nomeNodo))
                            return nodoChild;
                    } else {
                        Nodo foundNode = visualizza(nomeNodo, nodoChild.getNome(), nodo.getChildren());
                        if (foundNode != null) {
                            return foundNode;
                        }
                    }
                }
            }
        }
        return null;
    }
}
