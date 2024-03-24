package it.unibs.ids.progetto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Classe GestioneUtenza
 * @author marti
 *
 */
public  class GestioneUtenza implements Serializable {
	
	//Static
	
	private static final String IDCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final String PSSWCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!?@^&%$£";
	private static final int IDLENTGH = 6;
	private static final int PSSWLENTGH = 8;
	
	

	private ArrayList<Utente> utenti;
	private ArrayList<Comprensorio> geografia;

	
	

	
	public GestioneUtenza() {
		
		this.utenti = new ArrayList<>();
		Configuratore configuratoreDefault = new Configuratore();
		addUtente(configuratoreDefault);
		
		this.geografia = new ArrayList<>();
	}
	
	
	public ArrayList<Utente> getUtenti() {
		return utenti;
	}

	public void addUtente(Utente utente) {
		this.utenti.add(utente);
	}

	public void addComprensorio(Comprensorio comprensorio) {
		this.geografia.add(comprensorio);
	}
	

	public ArrayList<Comprensorio> getGeografia() {
		return geografia;
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
	
	public boolean verificaEsistenzaID(String id) {
		for (Utente utente : utenti) {
			if (utente.getID().equals(id)) {
				return true;
			}
		}
		return false;
		
	}
	
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
