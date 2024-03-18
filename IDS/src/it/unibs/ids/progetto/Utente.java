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
	public String getID() {
		return this.credenziali.getID();
	}
	public String getPSSW() {
		return this.credenziali.getPassword();
	}
	public void setCredenziali(Credenziali credenziali) {
		this.credenziali.setID(credenziali.getID());
		this.credenziali.setPassword(credenziali.getPassword());
	}
	public void setCredenziali(String ID, String password) {
		this.credenziali.setID(ID);
		this.credenziali.setPassword(password);
	}
	public char getTipoUtente() {
		return tipoUtente;
	}
	
	
	
	
}
