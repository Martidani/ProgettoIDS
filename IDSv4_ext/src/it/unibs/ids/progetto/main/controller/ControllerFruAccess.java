package it.unibs.ids.progetto.main.controller;

import it.unibs.fp.mylib.InputDati;
import it.unibs.ids.progetto.Comprensorio;
import it.unibs.ids.progetto.Credenziali;
import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.MailAddress;
import it.unibs.ids.progetto.Utente;
import it.unibs.ids.progetto.main.model.Model;

public class ControllerFruAccess  {

	
	/**
     * Numero massimo di tentativi di login consentiti.
     */
    private static final int NUM_MAX_TENTATIVI = 3;
	protected Model model;
	
	public ControllerFruAccess(Model model) {
		this.model = model;
	}


	/**
	 * Metodo per registrare un nuovo utente.
	 * 
	 * @param utenza   L'oggetto Utenza utilizzato per registrare il nuovo utente.
	 */
	public void registrazione() {
		
		String c;
		do {
			c = InputDati.leggiStringaNonVuota("  Comprensorio: ");
		} while (!model.verificaEsistenzaComprensorio(c));
		
	    Credenziali credenziali = primoAccesso();
	    String indirizzo;
	    do {
	    	indirizzo = InputDati.leggiStringaNonVuota("  Indirizzo e-mail: ");
		} while (!MailAddress.isValidEmail(indirizzo));
	    
	    Comprensorio comprensorio = model.cercaComprensorio(c);

	    Fruitore fruitore = new Fruitore(comprensorio, credenziali, new MailAddress(indirizzo));
	    model.addUtente(fruitore);
	}
	
	/**
	 * Metodo per effettuare il login.
	 * 
	 * @param utenza 	      L'oggetto Utenza utilizzato per verificare le credenziali.
	 * @param ID              L'ID inserito.
	 * @param PSSW            La password inserita.
	 * @return                Il risultato del login.
	 */
	public int autenticazione(String ID, String PSSW) {
		Utente utente = model.autenticazioneFruitore(ID, PSSW);
		
	    if (utente == null) {
	    		System.out.println(" ! Non esiste alcun fruitore con queste credenziali !");
	        return 1;
	    }  else {
	        System.out.println("-> Utente riconosciuto");
			model.setUtenteDiSessione(utente);
	        return 2;
	    }
	}
	


	/**
	 * Metodo per inserire le credenziali di registrazione.
	 * 
	 * @param utenza          L'oggetto Utenza utilizzato per verificare l'esistenza dell'ID.
	 * @return                Le credenziali inserite dall'utente.
	 */
	public Credenziali primoAccesso() {
	    String ID;
	    do {
	        ID = InputDati.leggiStringaNonVuota("  ID: ");
	        if (model.verificaEsistenzaID(ID)) System.out.println(" ! ID già utilizzato ! ");
	    } while (model.verificaEsistenzaID(ID));

	    String PSSW = InputDati.leggiStringaNonVuota("  Password: ");
	    return new Credenziali(ID, PSSW);
	}
	
	/**
	 * Metodo per il login.
	 * 
	 * @param utenza  		  L'oggetto Utenza utilizzato per gestire l'accesso.
	 * @param accesso         L'accesso corrente.
	 * @return                L'accesso aggiornato.
	 */
	public int login() {
		int accesso = 2;
	    for (int i = 0; i < NUM_MAX_TENTATIVI; i++) {
	        System.out.println("Inserisci dati di login: ");
	        String ID = InputDati.leggiStringaNonVuota("  ID: ");
	        String PSSW = InputDati.leggiStringaNonVuota("  Password: ");
	        accesso = autenticazione(ID, PSSW);
	        if (accesso != 1) {
	            break;
	        }
	    }
	    return accesso;
	}
}
