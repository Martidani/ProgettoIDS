package it.unibs.ids.progetto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * La classe FattoriDiConversione gestisce i fattori di conversione tra diverse foglie
 * all'interno di una gerarchia, permettendo la conversione di valori tra foglie diverse
 * tramite fattori diretti e transitivi.
 */
public class FattoriDiConversione implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final double MIN_FATTORECONVERSIONE = 0.5;
    public static final double MAX_FATTORECONVERSIONE = 2.0;

    private HashMap<Leaf, Double> fattori;

    /**
     * Restituisce il set di fattori di conversione.
     * @return un set di Entry contenente le foglie e i relativi fattori di conversione.
     */
    public Set<Entry<Leaf, Double>> getFattori() {
        return fattori.entrySet();
    }

    /**
     * Costruttore della classe FattoriDiConversione.
     * Inizializza una nuova mappa di fattori di conversione.
     */
    public FattoriDiConversione() {
        this.fattori = new HashMap<Leaf, Double>();
    }

    /**
     * Restituisce il fattore di conversione per una data foglia.
     * @param foglia la foglia di cui si vuole conoscere il fattore di conversione.
     * @return il fattore di conversione associato alla foglia, o 0 se non presente.
     */
    public double fattoreFoglia(Leaf foglia) {
        if (fattori.containsKey(foglia))
            return fattori.get(foglia);
        return 0;
    }

    /**
     * Aggiunge un nuovo fattore di conversione per una data foglia.
     * @param foglia la foglia a cui associare il fattore di conversione.
     * @param fattore il fattore di conversione da associare alla foglia.
     */
    public void addFattoreConversione(Leaf foglia, Double fattore) {
        fattori.put(foglia, fattore);
    }

    /**
     * Verifica se un dato fattore di conversione è valido.
     * @param fattore il fattore di conversione da verificare.
     * @return true se il fattore è compreso tra MIN_FATTORECONVERSIONE e MAX_FATTORECONVERSIONE, false altrimenti.
     */
    public static boolean verificaFattoreConversione(double fattore) {
        return (fattore >= MIN_FATTORECONVERSIONE && fattore <= MAX_FATTORECONVERSIONE);
    }

    /**
     * Aggiunge i fattori di conversione transitivi tra tutte le foglie di una gerarchia.
     * @param gerarchia la gerarchia contenente le foglie.
     */
    public static void addTransitivoFattoreConversione(Gerarchia gerarchia) {
        for (Leaf nodo1 : gerarchia.getFoglie()) {
            for (Leaf nodo2 : gerarchia.getFoglie()) {
                if (shouldAddTransitivoFattore(nodo1, nodo2)) {
                    addFattoreIfCalculated(nodo1, nodo2);
                }
            }
        }
    }

    /**
     * Determina se è necessario aggiungere un fattore di conversione transitivo tra due nodi.
     * @param nodo1 il primo nodo.
     * @param nodo2 il secondo nodo.
     * @return true se è necessario aggiungere il fattore di conversione, false altrimenti.
     */
    private static boolean shouldAddTransitivoFattore(Leaf nodo1, Leaf nodo2) {
        return !nodo1.equals(nodo2) && nodo1.fattoreFoglia(nodo2) == 0;
    }

    /**
     * Aggiunge un fattore di conversione calcolato tra due nodi.
     * @param nodo1 il primo nodo.
     * @param nodo2 il secondo nodo.
     */
    private static void addFattoreIfCalculated(Leaf nodo1, Leaf nodo2) {
        Double fattore = calcTransitivo(nodo1, nodo2, new ArrayList<>());
        if (fattore != null) {
            nodo1.addFattoreConversione(nodo2, fattore);
        }
    }

    /**
     * Calcola il fattore di conversione transitivo tra due nodi.
     * @param nodo1 il primo nodo.
     * @param nodo2 il secondo nodo.
     * @param visitati la lista dei nodi già visitati per evitare cicli.
     * @return il fattore di conversione transitivo, o null se non esiste.
     */
    private static Double calcTransitivo(Leaf nodo1, Leaf nodo2, List<Leaf> visitati) {
        if (nodo1.equals(nodo2)) {
            return getFattoreIdentità();
        } else if (nodo1.fattoreFoglia(nodo2) != 0) {
            return getFattoreDiretto(nodo1, nodo2);
        } else {
            return getFattoreIndiretto(nodo1, nodo2, visitati);
        }
    }

    /**
     * Restituisce il fattore di identità (1.0).
     * @return il fattore di identità.
     */
    private static double getFattoreIdentità() {
        return 1.0;
    }

    /**
     * Restituisce il fattore di conversione diretto tra due nodi.
     * @param nodo1 il primo nodo.
     * @param nodo2 il secondo nodo.
     * @return il fattore di conversione diretto.
     */
    private static double getFattoreDiretto(Leaf nodo1, Leaf nodo2) {
        return nodo1.fattoreFoglia(nodo2);
    }

    /**
     * Calcola il fattore di conversione indiretto tra due nodi.
     * @param nodo1 il primo nodo.
     * @param nodo2 il secondo nodo.
     * @param visitati la lista dei nodi già visitati per evitare cicli.
     * @return il fattore di conversione indiretto, o null se non esiste.
     */
    private static Double getFattoreIndiretto(Leaf nodo1, Leaf nodo2, List<Leaf> visitati) {
        Set<Entry<Leaf, Double>> fact = nodo1.getFattori();
        if (fact.isEmpty())
            return null;

        for (Map.Entry<Leaf, Double> entry : fact) {
            Leaf key = entry.getKey();
            if (!visitati.contains(key)) {
                visitati.add(key);
                Double val = calcTransitivo(key, nodo2, visitati);
                if (val != null)
                    return entry.getValue() * val;
            }
        }
        return null;
    }
}

