package it.unibs.ids.progetto;
import java.io.Serializable;

/**
 * La classe Configuratore rappresenta un utente con privilegi di configurazione.
 * Estende la classe astratta Utente.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class Configuratore extends Utente implements Serializable {

    // Numero di versione per la serializzazione
    private static final long serialVersionUID = 1L;
    
    // Tipo di utente: configuratore
    public static final char TIPOUTENTE = 'c';
    
    /**
     * Costruttore della classe Configuratore.
     * Crea un nuovo configuratore con le credenziali specificate.
     * 
     * @param credenziali Le credenziali associate al configuratore
     */
    public Configuratore(Credenziali credenziali) {
        super(TIPOUTENTE, credenziali);
    }
    
    /**
     * Costruttore della classe Configuratore.
     * Crea un nuovo configuratore con le credenziali predefinite.
     */
    public Configuratore() {
        super(TIPOUTENTE, Credenziali.credenzialiPredefinite());
    }
}
