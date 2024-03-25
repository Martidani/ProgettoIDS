package it.unibs.ids.progetto;
import java.io.Serializable;

/**
 * La classe Credenziali rappresenta le credenziali di accesso di un utente.
 * Ogni credenziale è composta da un ID (identificativo dell'utente) e una password.
 * Inoltre, tiene traccia se le credenziali sono definitive o meno.
 * Se un utente si è appena registrato avrà delle credenziali 
 * non definitive fino alla loro modifica
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Credenziali implements Serializable {

	
    private static final long serialVersionUID = 1L;
    private String ID;
    private String password;
    private boolean definitive;
    
    /**
     * Costruttore della classe Credenziali.
     * 
     * @param ID L'identificativo dell'utente
     * @param password La password associata all'identificativo
     */
    public Credenziali(String ID, String password) {
        this.ID = ID;
        this.password = password;
    }

    
    /**
     * Restituisce l'ID associato alle credenziali.
     * 
     * @return L'ID dell'utente
     */
    public String getID() {
        return ID;
    }

    /**
     * Imposta l'ID associato alle credenziali.
     * 
     * @param ID L'ID dell'utente da impostare
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Restituisce la password associata alle credenziali.
     * 
     * @return La password dell'utente
     */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta la password associata alle credenziali.
     * 
     * @param password La password dell'utente da impostare
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Verifica se le credenziali sono definitive o meno.
     * 
     * @return true se le credenziali sono definitive, false altrimenti
     */
    public boolean isDefinitive() {
        return definitive;
    }

    /**
     * Imposta lo stato di definitività delle credenziali.
     * 
     * @param definitive true se le credenziali sono definitive, false altrimenti
     */
    public void setDefinitive(boolean definitive) {
        this.definitive = definitive;
    }


}