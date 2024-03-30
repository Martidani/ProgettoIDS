package it.unibs.ids.progetto.news.main;

import it.unibs.fp.mylib.InputDati;
import it.unibs.ids.progetto.Credenziali;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Utenza;

public abstract class AccessController {

    /**
     * Numero massimo di tentativi di login consentiti.
     */
    private static final int NUM_MAX_TENTATIVI = 3;


    



	public abstract void registrazione(Utenza utenza, Geografia geografia);
	
	
	public AccessController() {
		super();
	}


	/**
	 * Metodo per inserire le credenziali di registrazione.
	 * 
	 * @param utenza          L'oggetto Utenza utilizzato per verificare l'esistenza dell'ID.
	 * @return                Le credenziali inserite dall'utente.
	 */
	public static Credenziali primoAccesso(Utenza utenza) {
	    String ID;
	    do {
	        ID = InputDati.leggiStringaNonVuota("  ID: ");
	        if (utenza.verificaEsistenzaID(ID)) System.out.println(" ! ID già utilizzato ! ");
	    } while (utenza.verificaEsistenzaID(ID));

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
	public int login(Utenza utenza) {
		int accesso = 2;
	    for (int i = 0; i < NUM_MAX_TENTATIVI; i++) {
	        System.out.println("Inserisci dati di login: ");
	        String ID = InputDati.leggiStringaNonVuota("  ID: ");
	        String PSSW = InputDati.leggiStringaNonVuota("  Password: ");
	        accesso = autenticazione(utenza, ID, PSSW);
	        if (accesso != 1) {
	            break;
	        }
	    }
	    return accesso;
	}
	/**
	 * Metodo per effettuare il login.
	 * 
	 * @param utenza 	      L'oggetto Utenza utilizzato per verificare le credenziali.
	 * @param ID              L'ID inserito.
	 * @param PSSW            La password inserita.
	 * @return                Il risultato del login.
	 */
	public abstract int autenticazione(Utenza utenza, String ID, String PSSW);



	
}
