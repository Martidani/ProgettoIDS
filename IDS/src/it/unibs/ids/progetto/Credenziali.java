package it.unibs.ids.progetto;


public class Credenziali {


	
	private String ID;
	private String Password;
	
	
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