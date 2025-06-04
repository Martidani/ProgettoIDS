package it.unibs.ids.progetto.news.main;

import it.unibs.fp.mylib.InputDati;
import it.unibs.ids.progetto.Configuratore;
import it.unibs.ids.progetto.Credenziali;
import it.unibs.ids.progetto.Utente;


/**
 * Questa classe gestisce le operazioni di login e registrazione degli utenti.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class ControllerConfAccess {
	
	private Model model;
    public ControllerConfAccess(Model model) {
		super();
		this.model = model;
	}
	
    /**
     * Numero massimo di tentativi di login consentiti.
     */
    private static final int NUM_MAX_TENTATIVI = 3;
    
    /**
     * Metodo per eseguire il login.
     * 
     * @param utenza   L'oggetto Utenza utilizzato per gestire il login.
     * @return         Il risultato dell'operazione di login.
     */
    public void registrazione() {
        Configuratore configuratore = new Configuratore();
        String id = configuratore.getID();
        String psswd = configuratore.getPassword();
        System.out.println("ID di default: " + id);
        System.out.println("Password di default: " + psswd);

        Credenziali credenziali = new Credenziali(id, psswd);
        configuratore.setCredenziali(credenziali);
        configuratore.setIsDefinitivo(false);
        model.addUtente(configuratore);
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
		Utente utente = model.autenticazioneConfiguratore(ID, PSSW);
		if (utente == null) {
	    	System.out.println(" ! Non esiste alcun configuratore con queste credenziali !");
	        return 1;
	    } else if (!utente.IsDefinitivo()) {
	    	System.out.println("Scegli nuove credenziali: ");
	        Credenziali credenzialiRegistrazione = primoAccesso();
	        utente.setCredenziali(credenzialiRegistrazione);
	        utente.setIsDefinitivo(true);
	        return 2;
	    } else {
	        System.out.println("-> Utente riconosciuto");
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
