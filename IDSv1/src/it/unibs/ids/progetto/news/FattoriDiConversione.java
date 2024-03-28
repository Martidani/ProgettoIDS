package it.unibs.ids.progetto.news;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Nodo;

/**
 * Classe per la gestione dei fattori di conversione.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class FattoriDiConversione {

    private static final double MIN_FATTORECONVERSIONE = 0.5;
    private static final double MAX_FATTORECONVERSIONE = 2.0;

    /**
     * Aggiunge un fattore di conversione tra due nodi e l'inverso del fattore stesso nella relazione inversa.
     * 
     * @param nodo1 Il primo nodo
     * @param nodo2 Il secondo nodo
     * @param fattore Il fattore di conversione da nodo1 a nodo2
     */
    public static void addFattoreConversione(Nodo nodo1, Nodo nodo2, double fattore) {
        nodo1.addFattori(nodo2, fattore);
        nodo2.addFattori(nodo1, 1 / fattore); // Aggiunge il fattore inverso
    }

    /**
     * Verifica se un dato fattore di conversione è valido.
     * 
     * @param fattore Il fattore di conversione da verificare
     * @return true se il fattore è compreso nell'intervallo consentito, false altrimenti
     */
    public static boolean verificaFattoreConversione(double fattore) {
        return (fattore >= MIN_FATTORECONVERSIONE && fattore <= MAX_FATTORECONVERSIONE);
    }

    /**
     * Aggiunge i fattori di conversione transitivi tra TUTTE le coppie di foglie nella gerarchia.
     * 
     * @param gerarchia La gerarchia su cui operare
     */
    public static void addTransitivoFattoreConversione(Gerarchia gerarchia) {
        ArrayList<Nodo> foglie = new ArrayList<Nodo>();
        for (Nodo nodo : gerarchia.getAlberi()) {
            Albero albero = null;
            try {
                albero = new Albero(nodo);
            } catch (RootTreeException e) {
                System.out.println(e.getMessage());
            }
            foglie.addAll(albero.getFoglie());
        }

        for (Nodo nodo1 : foglie) {
            for (Nodo nodo2 : foglie) {
                if (!nodo1.equals(nodo2) && nodo1.fattoreFoglia(nodo2) == 0) {
                    Double fattore = calcTransitivo(nodo1, nodo2, new ArrayList<>());
                    if (fattore != null) {
                        nodo1.addFattori(nodo2, fattore);
                    }
                }
            }
        }
    }

    /**
     * Metodo per calcolare il fattore di conversione transitivo tra due nodi.
     * 
     * @param nodo1 Il primo nodo
     * @param nodo2 Il secondo nodo
     * @param visitati Lista dei nodi visitati durante il calcolo
     * @return Il fattore di conversione transitivo tra i due nodi, null se non è possibile calcolarlo
     */
    private static Double calcTransitivo(Nodo nodo1, Nodo nodo2, List<Nodo> visitati) {
        if (nodo1.equals(nodo2)) {
            return 1.0;
        } else if (nodo1.fattoreFoglia(nodo2) != 0) {
            return nodo1.fattoreFoglia(nodo2);
        } else {
            HashMap<Nodo, Double> foglieNodo1 = nodo1.getFattori();
            if (foglieNodo1.isEmpty())
                return null;
            for (Map.Entry<Nodo, Double> entry : foglieNodo1.entrySet()) {
                Nodo key = entry.getKey();
                if (!visitati.contains(key)) {
                    visitati.add(key);
                    Double val = calcTransitivo(key, nodo2, visitati);
                    if (val != null) {
                        return entry.getValue() * val;
                    }
                }
            }
        }
        return null; // Restituiamo null se non è possibile calcolare il fattore di conversione
    }
}
