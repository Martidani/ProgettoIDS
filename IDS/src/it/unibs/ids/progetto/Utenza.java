package it.unibs.ids.progetto;
import java.io.Serializable;
import java.util.ArrayList;
import it.unibs.fp.mylib.Estrattore;

/**
 * La classe Utenza gestisce gli utenti nel sistema.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public class Utenza implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	// Attributi per la generazione di ID e password
	private static final int IDLENTGH = 6;
	private static final int PSSWLENTGH = 8;
	private static final String IDCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final String PSSWCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!?@^&%$£";
	
	// Lista degli utenti nel sistema
	private ArrayList<Utente> utenti;


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
	 * Genera e restituisce delle credenziali predefinite per 
	 * un utente (da utilizzarsi nella registrazione).
	 * 
	 * @return Le credenziali predefinite
	 */
	public static Credenziali credenzialiPredefinite() {
		String userID = Estrattore.estraiString(IDCHAR, IDLENTGH);
		String userPSSW = Estrattore.estraiString(PSSWCHAR, PSSWLENTGH);
		return new Credenziali(userID.toString(), userPSSW.toString());
	}
	
	/**
	 * Verifica l'esistenza di un configuratore con le credenziali specificate.
	 * 
	 * @param ID L'ID dell'utente
	 * @param Password La password dell'utente
	 * @return Il configuratore corrispondente alle credenziali, null se non trovato
	 */
	public Configuratore verificaEsistenzaConfiguratore(String ID, String Password) {
		for (Utente utente : utenti) {
			if (utente.getID().equals(ID)) {
				if (utente.getPSSW().equals(Password)) {
					if (utente.getTipoUtente() == Configuratore.TIPOUTENTE ){
						return (Configuratore) utente;
					}
				}
			}
		}
		return null;
		
	}
	
	/**
	 * Verifica l'esistenza di un utente con le credenziali specificate.
	 * 
	 * @param ID L'ID dell'utente
	 * @param Password La password dell'utente
	 * @return true se l'utente esiste, false altrimenti
	 */
	public boolean verificaEsistenzaUtente(String ID,String Password) {
		for (Utente utente : utenti) {
			if (utente.getID().equals(ID)) {
				if (utente.getPSSW().equals(Password)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Verifica l'esistenza di un ID utente nel sistema.
	 * 
	 * @param id L'ID da verificare
	 * @return true se l'ID esiste, false altrimenti
	 */
	public boolean verificaEsistenzaID(String id) {
		for (Utente utente : utenti) {
			if (utente.getID().equals(id)) {
				return true;
			}
		}
		return false;
	}

	
}