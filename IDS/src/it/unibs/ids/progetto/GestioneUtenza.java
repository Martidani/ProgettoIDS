package it.unibs.ids.progetto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * La classe GestioneUtenza gestisce gli utenti e i comprensori nel sistema.
 * 
 * Autore: Daniele Martinelli e Federico Sabbadini
 */
public  class GestioneUtenza implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	// Attributi statici per la generazione casuale di ID e password
	private static final String IDCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final String PSSWCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!?@^&%$£";
	private static final int IDLENTGH = 6;
	private static final int PSSWLENTGH = 8;
	
	// Lista degli utenti nel sistema
	private ArrayList<Utente> utenti;
	// Lista dei comprensori geografici nel sistema
	private ArrayList<Comprensorio> geografia;

	/**
	 * Costruttore della classe GestioneUtenza.
	 * Inizializza la lista degli utenti con un configuratore predefinito.
	 */
	public GestioneUtenza() {
		this.utenti = new ArrayList<>();
		// Configuratore predefinito
		Configuratore configuratoreDefault = new Configuratore();
		addUtente(configuratoreDefault);
		this.geografia = new ArrayList<>();
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
	 * Aggiunge un comprensorio alla lista dei comprensori geografici nel sistema.
	 * 
	 * @param comprensorio Il comprensorio da aggiungere
	 */
	public void addComprensorio(Comprensorio comprensorio) {
		this.geografia.add(comprensorio);
	}
	
	/**
	 * Restituisce la lista dei comprensori geografici nel sistema.
	 * 
	 * @return La lista dei comprensori geografici
	 */
	public ArrayList<Comprensorio> getGeografia() {
		return geografia;
	}

	/**
	 * Genera e restituisce delle credenziali predefinite per un utente.
	 * 
	 * @return Le credenziali predefinite
	 */
	public static Credenziali credenzialiPredefinite() {
		StringBuilder userID = new StringBuilder();
		Random rand = new Random();
		
		for (int i = 0; i < IDLENTGH; i++) {
			userID.append(IDCHAR.charAt(rand.nextInt(IDCHAR.length())));
		}
		
		StringBuilder userPSSW = new StringBuilder();
		Random rand2 = new Random();
		
		for (int i = 0; i < PSSWLENTGH; i++) {
			userPSSW.append(PSSWCHAR.charAt(rand2.nextInt(PSSWCHAR.length())));
		}
		
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
	
	/**
	 * Restituisce una rappresentazione testuale della geografia, includendo la lista dei comprensori.
	 * 
	 * @return Una stringa che rappresenta la geografia
	 */
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer();
		int num = 0;
		for (Comprensorio comprensorio : geografia) {
			bf.append("C" + num + "\n");
			bf.append(comprensorio.toString());
			bf.append("\n");
			num++;
		}
		
		return bf.toString();
	}

	
}