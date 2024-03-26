package it.unibs.ids.progetto;
import java.io.Serializable;

/**
 * La classe Configuratore rappresenta un utente con privilegi di configurazione.
 * Estende la classe astratta Utente.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Configuratore extends Utente implements Serializable {

	
    private static final long serialVersionUID = 1L;
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
        super(TIPOUTENTE, Utenza.credenzialiPredefinite());
    }


}