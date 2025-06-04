package it.unibs.ids.progetto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * La classe Utenza gestisce gli utenti nel sistema.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Utenza implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	// Lista degli utenti nel sistema
	private ArrayList<Utente> utenti;
	// Utente di sessione, impostato sulla base di 
	// chi effettua l'accesso al sistema
	private Utente utenteDiSessione;



	/**
	 * Costruttore della classe Utenza.
	 */
	public Utenza() {
		this.utenti = new ArrayList<>();
	}
	
	
	/**
	 * Restituisce la lista degli utenti nel sistema.
	 * 
	 * @return La lista degli utenti
	 */
	public ArrayList<Utente> getUtenti() {
		return utenti;
	}

	/**
	 * Aggiunge un utente alla lista degli utenti nel sistema.
	 * 
	 * @param utente L'utente da aggiungere
	 */
	public void addUtente(Utente utente) {
		this.utenti.add(utente);
	}

	/**
	 * Restituisce l'utente di sessione.
	 * 
	 * @return L'utente di sessione
	 */
	public Utente getUtenteDiSessione() {
		return utenteDiSessione;
	}

	/**
	 * Imposta l'utente di sessione.
	 * 
	 * @param utente L'utente da impostare
	 */
	public void setUtenteDiSessione(Utente utenteDiSessione) {
		this.utenteDiSessione = utenteDiSessione;
	}
	
    /**
     * Verifica l'esistenza di un utente con le credenziali specificate.
     * 
     * @param ID L'ID dell'utente
     * @param Password La password dell'utente
     * @param predicate Il predicato per filtrare l'utente desiderato
     * @return L'utente corrispondente alle credenziali, null se non trovato
     */
    private Utente autenticazione(String ID, String Password, Predicate<Utente> predicate) {
        return utenti.stream()
                .filter(utente -> utente.getID().equals(ID))
                .filter(utente -> utente.getPassword().equals(Password))
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }
    
    public Utente autenticazioneConfiguratore(String ID, String Password) {
        return autenticazione(ID, Password, utente -> utente.getTipoUtente() == Configuratore.TIPOUTENTE);
    }

    public Utente autenticazioneFruitore(String ID, String Password) {
        return autenticazione(ID, Password, utente -> utente.getTipoUtente() == Fruitore.TIPOUTENTE);
    }
	/**
	 * Verifica l'esistenza di un ID utente nel sistema.
	 * 
	 * @param id L'ID da verificare
	 * @return true se l'ID esiste, false altrimenti
	 */
	public boolean verificaEsistenzaID(String id) {
		for (Utente utente : utenti) 
			if (utente.getID().equals(id)) 
				return true;
		return false;
	}

	
}