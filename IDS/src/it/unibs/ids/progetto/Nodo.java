package it.unibs.ids.progetto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import it.unibs.ids.progetto.news.LeafHasChildrenException;

/**
 * La classe Nodo rappresenta un nodo all'interno di un albero.
 * Ogni nodo può essere una foglia o una non foglia.
 * Se un nodo è una foglia, contiene i fattori di conversione verso altri nodi.
 * Se un nodo è una non foglia, può avere figli e contiene informazioni sul campo e sul dominio.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Nodo implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private String nome;
	private boolean isLeaf;
	private boolean isRoot;
	private List<Nodo> children;
	private HashMap<Nodo, Double> fattori;
	private String campo;
	private List<String[]> dominio;
	
	/**
	 * Costruttore non foglia
	 * 
	 * @param nome Il nome del nodo
	 * @param isRoot True se il nodo è la radice dell'albero, false altrimenti
	 * @param campo Il campo associato al nodo (valido solo per i nodi non foglia)
	 * @param dominio Il dominio associato al campo (valido solo per i nodi non foglia)
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
	 * Costruttore foglia
	 * Una foglia non può essere radice, in quanto una radice 
	 * deve avere necessariamente almeno un figlio
	 * 
	 * @param nome Il nome del nodo
	 */
	public Nodo(String nome) {
		this.nome = nome;
		this.isRoot = false;
		this.isLeaf = true;
		this.fattori = new HashMap<>();
	}

	
	    /**
     * Restituisce il nome del nodo.
     * @return Il nome del nodo.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome del nodo.
     * @param nome Il nome da impostare.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Verifica se il nodo è una foglia.
     * @return True se il nodo è una foglia, altrimenti False.
     */
    public boolean isLeaf() {
        return isLeaf;
    }

    /**
     * Imposta se il nodo è una foglia o meno.
     * @param isLeaf True se il nodo è una foglia, altrimenti False.
     */
    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * Verifica se il nodo è una radice.
     * @return True se il nodo è una radice, altrimenti False.
     */
    public boolean isRoot() {
        return isRoot;
    }

    /**
     * Imposta se il nodo è una radice o meno.
     * @param isRoot True se il nodo è una radice, altrimenti False.
     */
    public void setRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }

    /**
     * Restituisce la lista dei figli del nodo.
     * @return La lista dei figli del nodo.
     */
    public List<Nodo> getChildren() {
        return children;
    }

    /**
     * Imposta la lista dei figli del nodo.
     * @param children La lista dei figli da impostare.
     */
    public void setChildren(List<Nodo> children) {
        this.children = children;
    }

    /**
     * Restituisce i fattori di conversione associati al nodo.
     * @return Una mappa contenente i nodi e i relativi fattori di conversione.
     */
    public HashMap<Nodo, Double> getFattori() {
        return fattori;
    }

    /**
     * Aggiunge un fattore di conversione associato al nodo.
     * @param foglia Il nodo foglia con cui associare il fattore.
     * @param fattore Il fattore di conversione da aggiungere.
     */
    public void addFattori(Nodo foglia, Double fattore) {
        fattori.put(foglia,fattore);
    }

    /**
     * Restituisce il campo associato al nodo.
     * @return Il campo associato al nodo.
     */
    public String getCampo() {
        return campo;
    }

    /**
     * Imposta il campo associato al nodo.
     * @param campo Il campo da impostare.
     */
    public void setCampo(String campo) {
        this.campo = campo;
    }

    /**
     * Restituisce il dominio associato al nodo.
     * @return Il dominio associato al nodo.
     */
    public List<String[]> getDominio() {
        return dominio;
    }

    /**
     * Aggiunge un elemento al dominio del nodo.
     * @param valore Il valore da aggiungere al dominio.
     * @param descrizione La descrizione associata al valore.
     */
    public void addElementiDominio(String valore, String descrizione) {
        this.dominio.add(new String [] { valore, descrizione });
    }

    /**
     * Aggiunge un elemento al dominio del nodo.
     * @param valore Il valore da aggiungere al dominio.
     */
    public void addElementiDominio(String valore) {
        this.dominio.add(new String [] { valore });
    }

	/**
	 * Aggiunge un nodo figlio a questo nodo.
	 * 
	 * @param child Il nodo figlio da aggiungere
	 * @throws Exception Se il nodo è una foglia, non può avere figli
	 */
	public void addChild(Nodo child) throws LeafHasChildrenException {
		if (this.isLeaf) throw new LeafHasChildrenException();
		this.children.add(child);
	}

	/**
	 * Verifica se esiste un fattore di conversione con una data foglia.
	 * 
	 * @param foglia La foglia con cui verificare la relazione
	 * @return true se esiste una relazione di conversione con la foglia, false altrimenti
	 */
	public boolean esisteFoglia(Nodo foglia) {
		return this.getFattori().containsKey(foglia);
	}

	/**
	 * Restituisce il valore del fattore di conversione con un'altra foglia.
	 * 
	 * @param foglia La foglia con cui verificare la relazione
	 * @return Il valore della relazione di conversione
	 */
	public double valoreRelazione(Nodo foglia) {
		return this.getFattori().get(foglia);
	}

	/**
	 * Genera una stringa delle relazioni di una foglia con le altre foglie.
	 * 
	 * @return Una stringa rappresentante le relazioni con le foglie
	 */
	public String toStringF() {
		 StringBuffer bf = new StringBuffer();
		    for (Entry<Nodo, Double> fatt : fattori.entrySet()) {
		        Nodo key = fatt.getKey();
		        Double val = fatt.getValue();
		        if (!this.nome.equals(key.getNome())) {
		            // Formatta il double con un massimo di tre decimali
		            String formattedVal = String.format("%.3f", val);
		            bf.append(this.nome + " - " + key.getNome() + " - " + formattedVal);
		            bf.append("\n");
		        }
		    }
		 return bf.toString();
	}

	/**
	 * Genera una stringa del dominio associato al nodo.
	 * 
	 * @return Una stringa rappresentante il dominio associato al nodo
	 */
	public String toStringD() {
		StringBuffer bf = new StringBuffer();
		bf.append("dominio: [");
		for (String[] elem : this.dominio) {
			bf.append("(valore: " + elem[0].toString());
			if (elem.length>1)
				bf.append(", descrizione: " + elem[1].toString() + "\n");
			bf.append(")");
		}
		bf.append("]");
		return bf.toString();
	}

	/**
	 * Genera una rappresentazione testuale dei figli del nodo.
	 * 
	 * @return Una stringa rappresentante i figli del nodo
	 */
	public String toStringC() {
		StringBuffer bf = new StringBuffer();
		int num = this.children.size();
		if (num==1) 
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
	
	
}