package it.unibs.ids.progetto;
import java.io.Serializable;


/**
 * La classe Nodo rappresenta un nodo all'interno di un albero.
 * Ogni nodo può essere una foglia o una non foglia.
 * Se un nodo è una foglia, contiene i fattori di conversione verso altri nodi.
 * Se un nodo è una non foglia, può avere figli e contiene informazioni sul campo e sul dominio.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public abstract class Nodo implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String nome;
	protected String root;

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
	public abstract boolean isLeaf() ;

	/**
	 * Verifica se il nodo è una radice.
	 * 
	 * @return True se il nodo è una radice, altrimenti False.
	 */
	public abstract String root();

	

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
    
    
    /**
     * Verifica se esiste un nodo non radice con il dato nome sotto il nodo radice specificato.
     * 
     * @param nome Il nome del nodo da cercare
     * @param radice Il nodo radice sotto il quale cercare
     * @return true se esiste un nodo con il nome specificato sotto il nodo radice, false altrimenti
     */
    private static boolean verifica(String nome, Nodo radice) {
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
