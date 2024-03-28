package it.unibs.ids.progetto.news;
import java.io.Serializable;
import java.util.ArrayList;

import it.unibs.ids.progetto.Nodo;

public class Albero implements Serializable {

	private static final long serialVersionUID = 1L;
	private Nodo radice;
	private ArrayList<Nodo> foglie;
	
	/**
	 * Costruttore della classe Gerarchia.
	 * @throws Exception 
	 */
	public Albero(Nodo radice) throws Exception {
		if (!radice.isRoot()) throw new Exception();
		this.foglie = new ArrayList<Nodo>();
		this.radice = radice;
		addFoglie(radice);
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
     * Restituisce una rappresentazione testuale della 
     * gerarchia (di tutti i suoi alberi).
     * 
     * @param alberi La lista degli alberi di nodi
     * @return Una stringa che rappresenta la gerarchia
     */
    public String toString() {
        StringBuffer bf = new StringBuffer();
        iterative(bf, this.radice, 1);

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


	public Nodo getRadice() {
		return radice;
	}


	public ArrayList<Nodo> getFoglie() {
		return foglie;
	}
	
 
    /**
     * Verifica se esiste un nodo non radice con il dato 
     * nome sotto il nodo radice specificato.
     * 
     * @param nome Il nome del nodo da cercare
     * @param radice Il nodo radice sotto il quale cercare
     * @return true se esiste un nodo con il nome specificato sotto il nodo radice, false altrimenti
     */
    public static boolean verificaEsistenzaNome(String nome, Nodo radice) {
        if (radice.isLeaf()) {
            return radice.getNome().equals(nome);
        } else {
            for (Nodo nodo : radice.getChildren()) {
                if (nodo.getNome().equals(nome) || verificaEsistenzaNome(nome, nodo)) {
                    return true;
                }
            }
            return false;
        }
    }
    

}
