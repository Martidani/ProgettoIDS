package it.unibs.ids.progetto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.unibs.ids.progetto.eccezioni.LeafHasChildrenException;


public class NotLeaf extends Nodo implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Nodo> children;
	private String campo;
	private List<String[]> dominio;
	
	/**
	 * Costruttore non foglia.
	 * 
	 * @param nome   Il nome del nodo.
	 * @param isRoot True se il nodo è la radice dell'albero, false altrimenti.
	 * @param campo  Il campo associato al nodo (valido solo per i nodi non foglia).
	 */
	public NotLeaf(String nome, String root, String campo) {
		this.root = root;
		this.campo = campo;
		this.nome = nome;
		this.children = new ArrayList<>();
		this.dominio = new ArrayList<>();
	}


	/**
	 * Restituisce la lista dei figli del nodo.
	 * 
	 * @return La lista dei figli del nodo.
	 */
	public List<Nodo> getChildren() {
		return children;
	}
    
	/**
	 * Restituisce il campo associato al nodo.
	 * 
	 * @return Il campo associato al nodo.
	 */
	public String getCampo() {
		return campo;
	}
	
	public String root() {
		return root;
	}

	/**
	 * Restituisce il dominio associato al nodo.
	 * 
	 * @return Il dominio associato al nodo.
	 */
	public List<String[]> getDominio() {
		return dominio;
	}

	/**
	 * Restituisce il valore del dominio associato all'i-esimo figlio.
	 * 
	 * @param i L'indice del figlio.
	 * @return Il valore del dominio associato al nodo.
	 */
	public String getDominio(int i) {
		String[] ret = this.dominio.get(i - 1);
		return ret[0];
	}

	/**
	 * Aggiunge una coppia valore:descrizione al dominio del nodo.
	 * 
	 * @param valore       Il valore da aggiungere al dominio.
	 * @param descrizione  La descrizione associata al valore.
	 */
	public void addElementiDominio(String valore, String descrizione) {
		this.dominio.add(new String[] { valore, descrizione });
	}

	/**
	 * Aggiunge un valore al dominio del nodo.
	 * 
	 * @param valore Il valore da aggiungere al dominio.
	 */
	public void addElementiDominio(String valore) {
		this.dominio.add(new String[] { valore });
	}

	/**
	 * Aggiunge un nodo figlio a questo nodo.
	 * 
	 * @param child Il nodo figlio da aggiungere.
	 * @throws LeafHasChildrenException Se il nodo è una foglia, non può avere figli.
	 */
	public void addChild(Nodo child) {
		this.children.add(child);
	}

	/**
	 * Restituisce false in quanto non foglia
	 */
	@Override
	public boolean isLeaf() {
		return false;
	}
	
}
	
