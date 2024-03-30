package it.unibs.ids.progetto;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe Comprensorio rappresenta un insieme di comuni.
 * Gli oggetti di questa classe contengono una lista di comuni
 * all'interno di un determinato territorio.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class Comprensorio implements Serializable {

    // Numero di versione per la serializzazione
    private static final long serialVersionUID = 1L;
    
    // Lista dei comuni nel comprensorio
    private ArrayList<String> comprensorio;
    
    /**
     * Costruttore della classe Comprensorio.
     * Crea un nuovo oggetto Comprensorio con una lista vuota di comuni.
     */
    public Comprensorio() {
        this.comprensorio = new ArrayList<>();
    }

    /**
     * Restituisce la lista di comuni contenuti nel comprensorio.
     * 
     * @return ArrayList<String> La lista di comuni
     */
    public ArrayList<String> getComprensorio() {
        return comprensorio;
    }

    /**
     * Aggiunge un nuovo comune alla lista del comprensorio.
     * 
     * @param luogo Il nome del comune da aggiungere
     */
    public void addComune(String luogo) {
        this.comprensorio.add(luogo);
    }
    
    /**
     * Restituisce una rappresentazione testuale del comprensorio
     * con elencati i nomi dei comuni presenti.
     * 
     * @return String La rappresentazione testuale del comprensorio
     */
    @Override
    public String toString() {
        // Costruisce una rappresentazione testuale del comprensorio
        StringBuilder builder = new StringBuilder();
        for (String comune : comprensorio) {
            builder.append(" - ").append(comune).append("\n");
        }
        
        return builder.toString();
    }
}
