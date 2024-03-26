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
        super(TIPOUTENTE, Credenziali.credenzialiPredefinite());
    }

    /**
     * Imposta la definitività delle credenziali.
     * Per un configuratore appena creato è false.
     * 
     * @param x La definitività da impostare.
     */
    public void setIsDefinitivo(boolean x) {
        this.credenziali.setDefinitive(x);
    }

    /**
     * Restituisce lo stato di definitività delle credenziali.
     * 
     * @return boolean True se le credenziali sono definitive, false altrimenti
     */
    public boolean getIsDefinitivo() {
        return this.credenziali.isDefinitive();
    }
}