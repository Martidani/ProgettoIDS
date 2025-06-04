package it.unibs.ids.progetto;
import java.io.Serializable;

/**
 * La classe astratta Utente rappresenta un utente generico del sistema.
 * Ogni utente ha un tipo (definito da un carattere) e delle credenziali
 * (identificativo e password).
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public abstract class Utente implements Serializable {

	
    private static final long serialVersionUID = 1L;
    private char tipoUtente;
    protected Credenziali credenziali;
    
    /**
     * Costruttore della classe Utente.
     * 
     * @param tipoUtente Il tipo di utente (carattere)
     * @param credenziali Le credenziali associate all'utente
     */
    public Utente(char tipoUtente, Credenziali credenziali) {
        this.tipoUtente = tipoUtente;
        this.credenziali = credenziali;
    }
  

    /**
     * Restituisce le credenziali dell'utente.
     * 
     * @return Credenziali Le credenziali dell'utente
     */
    public Credenziali getCredenziali() {
        return credenziali;
    }

    /**
     * Restituisce l'ID dell'utente.
     * 
     * @return String L'ID dell'utente
     */
    public String getID() {
        return this.credenziali.getID();
    }

    /**
     * Restituisce la password dell'utente.
     * 
     * @return String La password dell'utente
     */
    public String getPassword() {
        return this.credenziali.getPassword();
    }

    /**
     * Imposta le credenziali dell'utente.
     * 
     * @param credenziali  Le nuove credenziali da impostare
     */
    public void setCredenziali(Credenziali credenziali) {
        this.credenziali.setID(credenziali.getID());
        this.credenziali.setPassword(credenziali.getPassword());
    }

    /**
     * Restituisce il tipo di utente.
     * 
     * @return char Il tipo di utente (es. 'A' per amministratore, 'U' per utente normale)
     */
    public char getTipoUtente() {
        return tipoUtente;
    }

	public abstract void setIsDefinitivo(boolean b);

    /**
     * Restituisce lo stato di definitività delle credenziali.
     * 
     * @return boolean True se le credenziali sono definitive, false altrimenti
     */
    public boolean IsDefinitivo() {
        return this.credenziali.isDefinitive();
    }
}