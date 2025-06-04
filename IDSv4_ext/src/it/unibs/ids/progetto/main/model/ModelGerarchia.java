package it.unibs.ids.progetto.main.model;

import java.io.Serializable;
import it.unibs.ids.progetto.Albero;
import it.unibs.ids.progetto.FattoriDiConversione;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Leaf;
import it.unibs.ids.progetto.NotLeaf;
import it.unibs.ids.progetto.servizi.DefaultInitializer;
import it.unibs.ids.progetto.servizi.FileManager;

/**
 * ModelGerarchia gestisce le operazioni relative alla gerarchia all'interno dell'applicazione.
 */
public class ModelGerarchia implements Serializable {

    private static final long serialVersionUID = 1L;
    private Gerarchia gerarchia;  // Oggetto che gestisce la gerarchia

    /**
     * Costruttore della classe ModelGerarchia.
     * Carica la gerarchia da file, se disponibile, altrimenti inizializza con valori di default.
     */
    public ModelGerarchia() {
        // Caricamento da file
        this.gerarchia = FileManager.caricaGerarchia();
        
        if (gerarchia == null) {
            // Inizializzazione predefinita degli oggetti solo se non sono stati caricati da file
            gerarchia = DefaultInitializer.getDefaultInitializer().getGerarchia();
        } else {
            System.out.println("Lettura da file: " + FileManager.getGerarchiaFile());
        }
    }

    /**
     * Restituisce l'oggetto Gerarchia gestito dal modello.
     * @return Oggetto Gerarchia gestito dal modello
     */
    public Gerarchia getGerarchia() {
        return gerarchia;
    }

    /**
     * Aggiunge un albero alla gerarchia.
     * @param albero Albero da aggiungere
     */
    public void addAlbero(Albero albero) {
        gerarchia.addAlbero(albero);
    }

    /**
     * Verifica l'esistenza di una radice nella gerarchia dato il nome della radice.
     * @param radice Nome della radice da verificare
     * @return true se la radice esiste, false altrimenti
     */
    public boolean verificaEsistenzaNomeRadice(String radice) {
        return gerarchia.verificaEsistenzaNomeRadice(radice);
    }

    /**
     * Aggiunge un fattore di conversione transitivo alla gerarchia.
     * Questo metodo utilizza un metodo statico della classe FattoriDiConversione.
     */
    public void addTransitivoFattoreConversione() {
        FattoriDiConversione.addTransitivoFattoreConversione(gerarchia);
    }

    /**
     * Visualizza una foglia nella gerarchia dato il nome della foglia e il nome della radice.
     * @param foglia Nome della foglia da visualizzare
     * @param radice Nome della radice sotto cui cercare la foglia
     * @return Oggetto Leaf corrispondente alla foglia, se presente; altrimenti null
     */
    public Leaf visualizzaFoglia(String foglia, String radice) {
        return gerarchia.visualizzaFoglia(foglia, radice);
    }

    /**
     * Salva lo stato attuale della gerarchia su file.
     */
    public void save() {
        FileManager.salvaSuFile(gerarchia);
    }

    /**
     * Visualizza una radice nella gerarchia dato il nome della radice.
     * @param nomeRadice Nome della radice da visualizzare
     * @return Oggetto NotLeaf corrispondente alla radice, se presente; altrimenti null
     */
    public NotLeaf visualizzaRadice(String nomeRadice) {
        return gerarchia.visualizzaRadice(nomeRadice);
    }

}
