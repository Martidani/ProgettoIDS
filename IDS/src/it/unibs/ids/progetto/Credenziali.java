package it.unibs.ids.progetto;

import java.io.Serializable;

public class Credenziali implements Serializable {


	
	private String ID;
	private String Password;
	private boolean definitive;
	
	
	public boolean isDefinitive() {
		return definitive;
	}

	public void setDefinitive(boolean definitive) {
		this.definitive = definitive;
	}

	public Credenziali(String ID, String password) {
		this.ID = ID;
		this.Password = password;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
	
	
	
}
