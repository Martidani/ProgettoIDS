package it.unibs.ids.progetto;

import java.io.Serializable;

/**
 * Classe configuratore
 * @author marti
 *
 */

public class Configuratore extends Utente implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final char TIPOUTENTE = 'c';
	
	
	public Configuratore(Credenziali credenziali) {
		super(TIPOUTENTE,credenziali);

	}
	
	
	public Configuratore() {
		super(TIPOUTENTE,GestioneUtenza.credenzialiPredefinite());

	}
	
	


}
