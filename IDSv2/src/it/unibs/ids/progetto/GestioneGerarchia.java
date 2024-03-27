package it.unibs.ids.progetto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe per la gestione della gerarchia dei nodi.
 */
public class GestioneGerarchia implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Verifica se esiste un nodo radice con il dato nome.
     * 
     * @param nome Il nome del nodo radice da cercare
     * @param alberi La lista degli alberi di nodi
     * @return true se esiste un nodo radice con il nome specificato, false altrimenti
     */
    public static boolean verificaEsistenzaNomeRadice(String nome, ArrayList<Nodo> alberi) {
        for (Nodo nodo : alberi) {
            if (nodo.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Verifica se esiste un nodo non radice con il dato 
     * nome sotto il nodo radice specificato.
     * 
     * @param nome Il nome del nodo da cercare
     * @param radice Il nodo radice sotto il quale cercare
     * @return true se esiste un nodo con il nome specificato sotto il nodo radice, false altrimenti
     */
    public static boolean verificaEsistenzaNomeNonRadice(String nome, Nodo radice) {
        if (radice.isLeaf()) {
            return radice.getNome().equals(nome);
        } else {
            for (Nodo nodo : radice.getChildren()) {
                if (nodo.getNome().equals(nome) || verificaEsistenzaNomeNonRadice(nome, nodo)) {
                    return true;
                }
            }
            return false;
        }
    }
    
    /**
     * Controlla se una foglia è presente nella gerarchia.
     * 
     * @param foglia Il nodo foglia da cercare
     * @param foglie La lista delle foglie
     * @return true se la foglia è presente, false altrimenti
     */
    public static boolean checkFoglia(Nodo foglia, ArrayList<Nodo> foglie) {
        return foglie.contains(foglia);
    }
    
    /**
     * Restituisce una rappresentazione testuale della 
     * gerarchia (di tutti i suoi alberi).
     * 
     * @param alberi La lista degli alberi di nodi
     * @return Una stringa che rappresenta la gerarchia
     */
    public static String toString(ArrayList<Nodo> alberi) {
        StringBuffer bf = new StringBuffer();
        
        for (Nodo nodo : alberi)  {
            bf.append("\n\n");
            iterative(bf, nodo, 1);

        }
        return bf.toString();
            
    }
    
    /**
     * Restituisce una rappresentazione testuale della 
     * gerarchia (di tutti i suoi alberi).
     * 
     * @param alberi La lista degli alberi di nodi
     * @return Una stringa che rappresenta la gerarchia
     */
    public static String toStringR(ArrayList<Nodo> alberi) {
        StringBuffer bf = new StringBuffer();
        
        for (Nodo nodo : alberi)  {
            bf.append("\n* " + nodo.getNome());

        }
        return bf.toString();
            
    }
    
    /**
     * Metodo ausiliario ricorsivo per generare una rappresentazione 
     * testuale della gerarchia.
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
    public static Nodo visualizzaNodo(String nomeNodo, String root, List<Nodo> list) {
        
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