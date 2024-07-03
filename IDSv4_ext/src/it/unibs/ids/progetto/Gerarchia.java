package it.unibs.ids.progetto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Gerarchia rappresenta l'insieme di tutti gli 
 * alberi nel sistema e gestisce le operazioni su di essi.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Gerarchia implements Serializable {
	
	
    private static final long serialVersionUID = 1L;

    private ArrayList<Albero> alberi;
    
    private ArrayList<Nodo> radici; 
    private ArrayList<Leaf> foglie;

    /**
     * Costruttore della classe Gerarchia.
     * Inizializza la gerarchia con liste vuote per gli alberi, le radici e le foglie.
     */
    public Gerarchia() {
        this.alberi = new ArrayList<>();
        this.radici = new ArrayList<>();
        this.foglie = new ArrayList<>();
    }
    
 
    /**
     * Restituisce la lista delle foglie
     * @return lista foglie
     */
    public ArrayList<Leaf> getFoglie() {
		return foglie;
	}
    /**
     * Restituisce la lista dei nodi radice
     * @return lista radici
     */
    public ArrayList<Nodo> getRadici() {
		return radici;
	}

	/**
     * Aggiunge un albero alla gerarchia.
     * 
     * @param albero Il nodo radice dell'albero da aggiungere
     */
    public void addAlbero(Albero albero) {
    	alberi.add(albero);
        radici.add(albero.getRadice());
        foglie.addAll(albero.getFoglie());
    }
    
    /**
     * Verifica se esiste un nodo radice con il dato nome nella gerarchia.
     * 
     * @param nome Il nome del nodo radice da cercare
     * @return true se esiste un nodo radice con il nome specificato, false altrimenti
     */
    public boolean verificaEsistenzaNomeRadice(String nome) {
        for (Nodo albero : radici) {
            if (albero.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Restituisce il nodo corrispondente al nome specificato nella gerarchia.
     * 
     * @param nomeNodo Il nome del nodo da cercare
     * @param root La radice della gerarchia
     * @return Il nodo corrispondente al nome specificato, null se non trovato
     */
    public Leaf visualizzaFoglia(String nomeNodo, String root) {
        return (Leaf)visualizza(nomeNodo, root, this.radici);
    }
    
    public NotLeaf visualizzaRadice(String root) {   
        for (Nodo nodo : this.radici) 
            if (nodo.getNome().equals(root)) 
            	return (NotLeaf)nodo;
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
                for (Nodo nodoChild : ((NotLeaf)nodo).getChildren()) {
                    if (nodoChild.isLeaf()) {
                        if (nodoChild.getNome().equals(nomeNodo))
                            return nodoChild;
                    } else {
                        Nodo foundNode = visualizza(nomeNodo, nodoChild.getNome(), ((NotLeaf)nodo).getChildren());
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
