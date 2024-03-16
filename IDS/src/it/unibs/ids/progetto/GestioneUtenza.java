package it.unibs.ids.progetto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Classe GestioneUtenza
 * @author marti
 *
 */
public  class GestioneUtenza {
	
	//Static
	
	private static final String IDCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final String PSSWCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!?@^&%$£";
	private static final int IDLENTGH = 6;
	private static final int PSSWLENTGH = 8;
	/**
	 * Attributi
	 * 
	 * 
	 * ArrayList<comprensorio> 
	 *
	 */

	private ArrayList<Utente> utenti;
	private ArrayList<Compresiorio> geografia;

	/**
	 * Metodi
	 * 
	 * addComprensorio in Arraylist
	 * 
	 */
	

	
	public GestioneUtenza() {
		super();
	}
	
	
	public ArrayList<Utente> getUtenti() {
		return utenti;
	}

	public void addUtente(Utente utente) {
		this.utenti.add(utente);
	}

	public void addComprensorio(Compresiorio compresiorio) {
		this.geografia.add(compresiorio);
	}
	

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
	
	public boolean verificaConfiguratore(String ID, String Password) {
		for (Utente utente : utenti) {
			if (utente.getCredenziali().getID().equals(ID)) {
				if (utente.getCredenziali().getPassword().equals(Password)) {
					if (utente.getTipoUtente() == Configuratore.TIPOUTENTE ){
						return true;
					}
				}
			}
		}
		return false;
		
	}
	
	
	public boolean verificaEsistenzaUtente(String ID,String Password) {
		
		for (Utente utente : utenti) {
			if (utente.getCredenziali().getID().equals(ID)) {
				if (utente.getCredenziali().getPassword().equals(Password)) {
					return true;
				}
			}
		}
		return false;
	}
	

}
