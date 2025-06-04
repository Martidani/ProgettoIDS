package it.unibs.ids.progetto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe Comprensorio rappresenta un insieme di comuni.
 * Gli oggetti di questa classe contengono una lista di comuni
 * all'interno di un determinato territorio.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Comprensorio implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nome; // Nome del comprensorio
    private ArrayList<String> comprensorio; // Lista dei comuni nel comprensorio
    
    /**
     * Costruttore della classe Comprensorio.
     * Crea un nuovo oggetto Comprensorio con una lista vuota di comuni.
     * 
     * @param nome Il nome del comprensorio.
     */
    public Comprensorio(String nome) {
        this.comprensorio = new ArrayList<>();
        this.nome = nome;
    }

    /**
     * Restituisce il nome del comprensorio.
     * 
     * @return Il nome del comprensorio.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome del comprensorio.
     * 
     * @param nome Il nome del comprensorio da impostare.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce la lista di comuni contenuti nel comprensorio.
     * 
     * @return La lista di comuni nel comprensorio.
     */
    public ArrayList<String> getComprensorio() {
        return comprensorio;
    }

    /**
     * Aggiunge un nuovo comune alla lista del comprensorio.
     * 
     * @param luogo Il nome del comune da aggiungere.
     */
    public void addComune(String luogo) {
        this.comprensorio.add(luogo);
    }
    
    /**
     * Restituisce una rappresentazione testuale del comprensorio
     * con elencati i nomi dei comuni presenti.
     * 
     * @return Una stringa che rappresenta il comprensorio.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.nome).append("\n");
        for (String comune : comprensorio) {
            sb.append(" - ").append(comune).append("\n");
        }
        return sb.toString();
    }
}
