package it.unibs.ids.progetto.news;

import java.io.Serializable;

import it.unibs.ids.progetto.LeafHasChildrenException;

public abstract class Nodo implements Serializable {
	
	protected String nome;
	protected boolean isLeaf;
	protected boolean isRoot;

	/**
	 * Restituisce il nome del nodo.
	 * 
	 * @return Il nome del nodo.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Verifica se il nodo è una foglia.
	 * 
	 * @return True se il nodo è una foglia, altrimenti False.
	 */
	public boolean isLeaf() {
		return isLeaf;
	}

	/**
	 * Verifica se il nodo è una radice.
	 * 
	 * @return True se il nodo è una radice, altrimenti False.
	 */
	public boolean isRoot() {
		return isRoot;
	}




	/**
	 * Genera una stringa del dominio associato al nodo.
	 * 
	 * @return Una stringa rappresentante il dominio associato al nodo.
	 */
	public abstract String toString(String blank);

	
	   /**
     * Verifica se esiste un nodo non radice con il dato nome sotto il nodo radice specificato.
     * 
     * @param nome Il nome del nodo da cercare
     * @param radice Il nodo radice sotto il quale cercare
     * @return true se esiste un nodo con il nome specificato sotto il nodo radice, false altrimenti
     * @throws LeafHasChildrenException 
     */
    public boolean verificaEsistenzaNome(String nome) throws LeafHasChildrenException {
    	return verifica(nome, this);
    }
    

    private static boolean verifica(String nome, Nodo radice) throws LeafHasChildrenException {
        if (radice.isLeaf()) {
            return radice.getNome().equals(nome);
        } else {
            for (Nodo nodo : ((NotLeaf)radice).getChildren()) {
                if (nodo.getNome().equals(nome) || verifica(nome, nodo)) {
                    return true;
                }
            }
            return false;
        }
    }
	

}
