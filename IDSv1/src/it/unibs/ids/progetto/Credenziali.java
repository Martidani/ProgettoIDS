package it.unibs.ids.progetto;
import java.io.Serializable;
import it.unibs.fp.mylib.Estrattore;

/**
 * La classe Credenziali rappresenta le credenziali di accesso di un utente.
 * Ogni credenziale è composta da un ID (identificativo dell'utente) e una password.
 * Inoltre, tiene traccia se le credenziali sono definitive o meno.
 * Se un utente si è appena registrato avrà delle credenziali 
 * non definitive fino alla loro modifica.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class Credenziali implements Serializable {

    // Numero di versione per la serializzazione
    private static final long serialVersionUID = 1L;
    
    // Lunghezza predefinita per l'ID e la password
    private static final int IDLENTGH = 6;
    private static final int PSSWLENTGH = 8;
    
    // Caratteri utilizzati per la generazione di ID e password
    private static final String IDCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String PSSWCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!?@^&%$£";
    
    private String ID; // Identificativo dell'utente
    private String password; // Password associata all'identificativo
    private boolean definitive; // Indica se le credenziali sono definitive o meno
    
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

    /**
     * Genera e restituisce delle credenziali predefinite per 
     * un utente (da utilizzarsi nella registrazione).
     * 
     * @return Le credenziali predefinite
     */
    public static Credenziali credenzialiPredefinite() {
        // Genera un ID e una password casuali
        String userID = Estrattore.estraiString(IDCHAR, IDLENTGH);
        String userPSSW = Estrattore.estraiString(PSSWCHAR, PSSWLENTGH);
        return new Credenziali(userID.toString(), userPSSW.toString());
    }
}
