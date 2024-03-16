package it.unibs.ids.progetto;

import java.util.Random;

/**
 * Classe per generico utente
 * @author marti
 *
 */
public abstract class Utente {

	
	private char tipoUtente;
	private Credenziali credenziali;
	
	
	
	public Utente(char tipoUtente, Credenziali credenziali) {

		this.tipoUtente = tipoUtente;
		this.credenziali = credenziali;
		
	}
	
	
	public Credenziali getCredenziali() {
		return credenziali;
	}
	public void setCredenziali(String ID, String password) {
		this.credenziali.setID(ID);
		this.credenziali.setPassword(password);
	}
	public char getTipoUtente() {
		return tipoUtente;
	}
	public void setTipoUtente(char tipoUtente) {
		this.tipoUtente = tipoUtente;
	}
	
	
	
	
}
