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
	public Leaf(String nome, String root) {
		this.nome = nome;
		this.root = root;
		this.fattori = new FattoriDiConversione();
		this.fattori.addFattoreConversione(this, 1.0);
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
	public String root() {
		return root;
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
  
	
}
