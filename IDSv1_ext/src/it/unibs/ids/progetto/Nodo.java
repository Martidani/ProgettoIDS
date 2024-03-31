package it.unibs.ids.progetto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Nodo rappresenta un nodo all'interno di un albero.
 * Ogni nodo può essere una foglia o una non foglia.
 * Se un nodo è una foglia, contiene i fattori di conversione verso altri nodi.
 * Se un nodo è una non foglia, può avere figli e contiene informazioni sul campo e sul dominio.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class Nodo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String nome;
	private boolean isLeaf;
	private boolean isRoot;
	private List<Nodo> children;
	private FattoriDiConversione fattori;
	private String campo;
	private List<String[]> dominio;
	
	/**
	 * Costruttore non foglia.
	 * 
	 * @param nome   Il nome del nodo.
	 * @param isRoot True se il nodo è la radice dell'albero, false altrimenti.
	 * @param campo  Il campo associato al nodo (valido solo per i nodi non foglia).
	 */
	public Nodo(String nome, boolean isRoot, String campo) {
		this.isRoot = isRoot;
		this.campo = campo;
		this.nome = nome;
		this.isLeaf = false;
		this.children = new ArrayList<>();
		this.dominio = new ArrayList<>();
	}

	/**
	 * Costruttore foglia.
	 * Una foglia non può essere radice, in quanto una radice 
	 * deve avere necessariamente almeno un figlio.
	 * 
	 * @param nome Il nome del nodo.
	 */
	public Nodo(String nome) {
		this.nome = nome;
		this.isRoot = false;
		this.isLeaf = true;
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
	 * Restituisce la lista dei figli del nodo.
	 * 
	 * @return La lista dei figli del nodo.
	 */
	public List<Nodo> getChildren() {
		return children;
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
     * Aggiunge un fattore di conversione tra due nodi e l'inverso del fattore stesso nella relazione inversa.
     * 
     * @param nodo2 Il secondo nodo
     * @param fattore Il fattore di conversione da nodo1 a nodo2
     */
    public void addFattoreConversione(Nodo nodo2, double fattore) {
        fattori.addFattoreConversione(nodo2, fattore);
    }
    
	/**
	 * Restituisce il campo associato al nodo.
	 * 
	 * @return Il campo associato al nodo.
	 */
	public String getCampo() {
		return campo;
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
		if (this.isLeaf)
			throw new LeafHasChildrenException();
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
	
    /**
     * Verifica se esiste un nodo non radice con il dato nome sotto il nodo radice specificato.
     * 
     * @param nome Il nome del nodo da cercare
     * @param radice Il nodo radice sotto il quale cercare
     * @return true se esiste un nodo con il nome specificato sotto il nodo radice, false altrimenti
     */
    public boolean verificaEsistenzaNome(String nome) {
    	return verifica(nome, this);
    }
    

    private static boolean verifica(String nome, Nodo radice) {
        if (radice.isLeaf()) {
            return radice.getNome().equals(nome);
        } else {
            for (Nodo nodo : radice.getChildren()) {
                if (nodo.getNome().equals(nome) || verifica(nome, nodo)) {
                    return true;
                }
            }
            return false;
        }
    }
    

}
