package it.unibs.ids.progetto;

import java.io.Serializable;
import java.util.ArrayList;


public class Albero implements Serializable {

    private static final long serialVersionUID = 1L;
    private NotLeaf radice;
    private ArrayList<Leaf> foglie;
    private Utente utente;

    /**
     * Costruttore della classe Albero.
     * 
     * @param radice Il nodo radice dell'albero
     * @throws RootTreeException Se il nodo specificato non è una radice
     */
    public Albero(NotLeaf radice) throws RootTreeException {
        if (!radice.isRoot()) throw new RootTreeException();
        this.foglie = new ArrayList<Leaf>();
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
            foglie.add((Leaf)albero);
        else {
            for (Nodo child : ((NotLeaf)albero).getChildren()) {
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
            for (Nodo nodoChild : ((NotLeaf)nodo).getChildren()) {
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
    public NotLeaf getRadice() {
        return radice;
    }

    /**
     * Restituisce l'elenco delle foglie dell'albero.
     * 
     * @return ArrayList<Nodo> L'elenco delle foglie dell'albero
     */
    public ArrayList<Leaf> getFoglie() {
        return foglie;
    }


}
