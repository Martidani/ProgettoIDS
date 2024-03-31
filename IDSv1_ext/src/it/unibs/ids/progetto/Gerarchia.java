package it.unibs.ids.progetto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Gerarchia rappresenta l'insieme di tutti gli 
 * alberi nel sistema e gestisce le operazioni su di essi.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class Gerarchia implements Serializable {
	
	
    private static final long serialVersionUID = 1L;

    private ArrayList<Albero> alberi;
    
    private ArrayList<Nodo> radici; 
    private ArrayList<Nodo> foglie;
    
    private static Gerarchia gerarchia;
    //singleton
    public static Gerarchia getGerarchia() {
    	if (gerarchia == null)
    		gerarchia= new Gerarchia(); 
    	return gerarchia;
    }

    /**
     * Costruttore della classe Gerarchia.
     * Inizializza la gerarchia con liste vuote per gli alberi, le radici e le foglie.
     */
    private Gerarchia() {
        this.alberi = new ArrayList<>();
        this.radici = new ArrayList<>();
        this.foglie = new ArrayList<>();
    }
    
    /**
     * Restituisce una rappresentazione testuale della 
     * gerarchia (di tutti i suoi alberi).
     * 
     * @return Una stringa che rappresenta la gerarchia
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        for (Nodo nodo : radici)  {
            builder.append("\n\n");
            try {
                builder.append(new Albero(nodo).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return builder.toString();     
    }

    /**
     * Restituisce la lista delle foglie presenti nella gerarchia.
     * 
     * @return Lista di foglie
     */
    public ArrayList<Nodo> getFoglie() {
		return foglie;
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
    public Nodo visualizzaFoglia(String nomeNodo, String root) {
        return visualizza(nomeNodo, root, this.radici);
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
