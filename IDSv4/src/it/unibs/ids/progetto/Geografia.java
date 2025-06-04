package it.unibs.ids.progetto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe Geografia rappresenta l'insieme di tutti i comprensori del sistema.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Geografia implements Serializable {

    private static final long serialVersionUID = 1L;
    // Lista dei comprensori geografici nel sistema
    private ArrayList<Comprensorio> geografia;
    
    /**
     * Costruttore della classe Geografia.
     * Inizializza una nuova lista di comprensori.
     */
    public Geografia() {
        this.geografia = new ArrayList<>();
    }
    
    
    /**
     * Aggiunge un comprensorio alla lista dei comprensori geografici nel sistema.
     * 
     * @param comprensorio Il comprensorio da aggiungere
     */
    public void addComprensorio(Comprensorio comprensorio) {
        this.geografia.add(comprensorio);
    }
    
    /**
     * Restituisce la lista dei comprensori geografici nel sistema.
     * 
     * @return La lista dei comprensori geografici
     */
    public ArrayList<Comprensorio> getGeografia() {
        return geografia;
    }
    
    /**
     * Cerca un comprensorio nella geografia basandosi sul nome.
     * 
     * @param nome Il nome del comprensorio da cercare
     * @return Il comprensorio corrispondente al nome, null se non trovato
     */
    public Comprensorio cercaComprensorio(String nome) {
        for (Comprensorio comprensorio : geografia) {
            if (comprensorio.getNome().equals(nome))
                return comprensorio;
        }
        return null;
    }
    
    /**
     * Verifica l'esistenza di un comprensorio nella geografia basandosi sul nome.
     * 
     * @param nome Il nome del comprensorio da verificare
     * @return true se il comprensorio esiste, false altrimenti
     */
    public boolean verificaEsistenzaComprensorio(String nome) {
        return cercaComprensorio(nome) != null;
    }
    
    /**
     * Restituisce una rappresentazione testuale della geografia, includendo la lista dei comprensori.
     * 
     * @return Una stringa che rappresenta la geografia
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Comprensorio comprensorio : geografia) {
            sb.append(comprensorio.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
