package it.unibs.ids.progetto;

import java.io.Serializable;

public class Leaf extends Nodo implements Serializable {

	private static final long serialVersionUID = 1L;
	private FattoriDiConversione fattori;
	

	/**
	 * Costruttore foglia.
	 * Una foglia non può essere radice, in quanto una radice 
	 * deve avere necessariamente almeno un figlio.
	 * 
	 * @param nome Il nome del nodo.
	 */
	public Leaf(String nome) {
		this.nome = nome;
		this.fattori = new FattoriDiConversione();
	}

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
		return true;
	}

	/**
	 * Restituisce il dominio associato al nodo.
	 * 
	 * @return Il dominio associato al nodo.
	 */
	public FattoriDiConversione getFattori() {
		return fattori;
	}
	
	/**
	 * Verifica se il nodo è una radice.
	 * 
	 * @return True se il nodo è una radice, altrimenti False.
	 */
	public boolean isRoot() {
		return false;
	}

	/**
     * Aggiunge un fattore di conversione tra due nodi e l'inverso del fattore stesso nella relazione inversa.
     * 
     * @param nodo2 Il secondo nodo
     * @param fattore Il fattore di conversione da nodo1 a nodo2
     */
    public void addFattoreConversione(Leaf nodo2, double fattore) {
        fattori.addFattoreConversione(nodo2, fattore);
    }
  

    public double fattoreFoglia(Leaf foglia) {
    	return this.fattori.fattoreFoglia(foglia);
    }
    
	/**
	 * Genera una stringa delle relazioni di una foglia con le altre foglie.
	 * 
	 * @return Una stringa rappresentante le relazioni con le foglie.
	 */
	public String toStringFactors() {
		return fattori.toString(this.nome);
	}

	
	/**
	 * Genera una stringa del dominio associato al nodo.
	 * 
	 * @return Una stringa rappresentante il dominio associato al nodo.
	 */
	public String toString(String blank) {
		StringBuffer bf = new StringBuffer();
		bf.append(" " + nome + "\n" );
		
		return bf.toString();
	}
	

	 public String toNavigationString() {
	    	StringBuilder b = new StringBuilder();
	    	b.append(" nome: " + this.nome);

	    	b.append(" |foglia!|");
	    	return b.toString();
	    }

	
}
