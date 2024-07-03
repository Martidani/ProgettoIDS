package it.unibs.ids.progetto.main.model;

import java.io.Serializable;
import it.unibs.ids.progetto.Comprensorio;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.servizi.DefaultInitializer;
import it.unibs.ids.progetto.servizi.FileManager;

/**
 * ModelGeografia gestisce le operazioni relative alla geografia all'interno dell'applicazione.
 */
public class ModelGeografia implements Serializable {

    private static final long serialVersionUID = 1L;
    private Geografia geografia;  // Oggetto che gestisce la geografia

    /**
     * Costruttore della classe ModelGeografia.
     * Carica la geografia da file, se disponibile, altrimenti inizializza con valori di default.
     */
    public ModelGeografia() {
        // Caricamento da file
        this.geografia = FileManager.caricaGeografia();
        
        if (geografia == null) {
            // Inizializzazione predefinita degli oggetti solo se non sono stati caricati da file
            geografia = DefaultInitializer.getDefaultInitializer().getGeografia();
        } else {
            System.out.println("Lettura da file: " + FileManager.getGeografiaFile());
        }
    }

    /**
     * Restituisce l'oggetto Geografia gestito dal modello.
     * @return Oggetto Geografia gestito dal modello
     */
    public Geografia getGeografia() {
        return geografia;
    }

    /**
     * Verifica l'esistenza di un comprensorio nella geografia dato il nome.
     * @param nome Nome del comprensorio da verificare
     * @return true se il comprensorio esiste, false altrimenti
     */
    public boolean verificaEsistenzaComprensorio(String nome) {
        return geografia.verificaEsistenzaComprensorio(nome);
    }

    /**
     * Aggiunge un comprensorio alla geografia.
     * @param comprensorio Comprensorio da aggiungere
     */
    public void addComprensorio(Comprensorio comprensorio) {
        geografia.addComprensorio(comprensorio);
    }

    /**
     * Salva lo stato attuale della geografia su file.
     */
    public void save() {
        FileManager.salvaSuFile(geografia);
    }

    /**
     * Cerca un comprensorio nella geografia dato il nome.
     * @param nome Nome del comprensorio da cercare
     * @return Comprensorio corrispondente al nome, se presente; altrimenti null
     */
    public Comprensorio cercaComprensorio(String nome) {
        return geografia.cercaComprensorio(nome);
    }

}
