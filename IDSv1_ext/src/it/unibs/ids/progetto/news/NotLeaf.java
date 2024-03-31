package it.unibs.ids.progetto.news;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import it.unibs.ids.progetto.LeafHasChildrenException;


public class NotLeaf extends Nodo implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean isRoot;
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
	public NotLeaf(String nome, boolean isRoot, String campo) {
		this.isRoot = isRoot;
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
	
	public boolean isRoot() {
		return isRoot;
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
	public void addChild(Nodo child) throws LeafHasChildrenException {
		this.children.add(child);
	}



	/**
	 * Genera una stringa del dominio associato al nodo.
	 * 
	 * @return Una stringa rappresentante il dominio associato al nodo.
	 */
	public String toString(String blank) {
		StringBuffer bf = new StringBuffer();
		bf.append(" " + nome + "\n" );
		bf.append(blank + "campo: " + campo + "\n" );
		bf.append(blank + toStringDomain() + "\n" );
		bf.append(blank + toStringChildren());
		
		return bf.toString();
	}
	

	/**
	 * Genera una stringa del dominio associato al nodo.
	 * 
	 * @return Una stringa rappresentante il dominio associato al nodo.
	 */
	private String toStringDomain() {
		StringBuffer bf = new StringBuffer();
		bf.append("dominio: [");
		for (String[] elem : this.dominio) {
			bf.append("(valore: " + elem[0].toString());
			if (elem.length > 1)
				bf.append(", descrizione: " + elem[1].toString());
			bf.append(")");
		}
		bf.append("]");
		return bf.toString();
	}

	/**
	 * Genera una rappresentazione testuale dei figli del nodo.
	 * 
	 * @return Una stringa rappresentante i figli del nodo.
	 */
	private String toStringChildren() {
		StringBuffer bf = new StringBuffer();
		int num = this.children.size();
		if (num == 1)
			bf.append(num + " figlio: [");
		else
			bf.append(num + " figli: [");
		for (Nodo nodo : this.children) {
			bf.append("(" + nodo.getNome());
			bf.append(")");
		}
		bf.append("]");
		return bf.toString();
	}


	@Override
	public boolean isLeaf() {
		return false;
	}
	
}
	
