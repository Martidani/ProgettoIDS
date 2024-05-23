package it.unibs.ids.progetto.news;

import java.io.Serializable;
import java.util.ArrayList;

import it.unibs.ids.progetto.Nodo;
import it.unibs.ids.progetto.Utente;
import it.unibs.ids.progetto.news.ecccezioni.RootTreeException;

/**
 * Questa classe rappresenta un albero gerarchico, composto da nodi, utilizzato per la gestione delle notizie.
 * Ogni albero ha una radice, foglie e un utente associato.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class Albero implements Serializable {

    private static final long serialVersionUID = 1L;
    private Nodo radice;
    private ArrayList<Nodo> foglie;
    private Utente utente;

    /**
     * Costruttore della classe Albero.
     * 
     * @param radice Il nodo radice dell'albero
     * @throws RootTreeException Se il nodo specificato non è una radice
     */
    public Albero(Nodo radice) throws RootTreeException {
    	if (radice.root()!=null) throw new RootTreeException();
        this.foglie = new ArrayList<Nodo>();
        this.radice = radice;
        addFoglie(radice);
    }

    /**
     * Restituisce l'utente associato all'albero.
     * 
     * @return Utente L'utente associato all'albero
     */
    public Utente getUtente() {
        return utente;
    }

    /**
     * Imposta l'utente associato all'albero.
     * 
     * @param utente L'utente da impostare
     */
    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    /**
     * Aggiunge le foglie dell'albero all'elenco delle foglie dell'albero.
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
     * Restituisce una rappresentazione testuale dell'albero.
     * 
     * @return Una stringa che rappresenta l'albero
     */
    public String toString() {
        StringBuffer bf = new StringBuffer();
        iterative(bf, this.radice, 1);
        return bf.toString();
    }

    /**
     * Metodo ausiliario ricorsivo per generare una rappresentazione testuale dell'albero.
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
            bf.append(asterischi + nodo.toString(blank));
            for (Nodo nodoChild : nodo.getChildren()) {
                bf.append("\n");
                iterative(bf, nodoChild, depth + 1);
            }
        }
    }

    /**
     * Restituisce la radice dell'albero.
     * 
     * @return Nodo La radice dell'albero
     */
    public Nodo getRadice() {
        return radice;
    }

    /**
     * Restituisce l'elenco delle foglie dell'albero.
     * 
     * @return ArrayList<Nodo> L'elenco delle foglie dell'albero
     */
    public ArrayList<Nodo> getFoglie() {
        return foglie;
    }

}
