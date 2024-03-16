package it.unibs.ids.progetto;

/**
 * Classe configuratore
 * @author marti
 *
 */

public class Configuratore extends Utente {

	public static final char TIPOUTENTE = 'c';
	
	
	public Configuratore(Credenziali credenziali) {
		super(TIPOUTENTE,credenziali);

	}
	
	
	public Configuratore() {
		super(TIPOUTENTE,GestioneUtenza.credenzialiPredefinite());

	}
	
	
	/**
	 * 
	 * 
	 * Metodi
	 *
	 *
	 *
	 * CostruisciComprensorio(Arraylist<String>) richiamo il costruttore di comprensorio
	 * 
	 * VisualizzaComprensorio
	 * 
	 * 
	 *
	 */

}
