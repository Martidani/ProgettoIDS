package it.unibs.ids.progetto.news.main;

import it.unibs.ids.progetto.Configuratore;
import it.unibs.ids.progetto.Credenziali;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Utente;
import it.unibs.ids.progetto.Utenza;


/**
 * Questa classe gestisce le operazioni di login e registrazione degli utenti.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class AccessConfController extends AccessController {
	



    

    public AccessConfController() {
		super();

	}

	@Override
    /**
     * Metodo per registrare un nuovo utente.
     * 
     * @param utenza   L'oggetto Utenza utilizzato per registrare il nuovo utente.
     */
    public void registrazione(Utenza utenza, Geografia geografia) {
        Configuratore configuratore = new Configuratore();
        String id = configuratore.getID();
        String psswd = configuratore.getPassword();
        System.out.println("ID di default: " + id);
        System.out.println("Password di default: " + psswd);

        Credenziali credenziali = new Credenziali(id, psswd);
        configuratore.setCredenziali(credenziali);
        configuratore.setIsDefinitivo(false);
        utenza.addUtente(configuratore);
    }
	
	/**
	 * Metodo per effettuare il login.
	 * 
	 * @param utenza 	      L'oggetto Utenza utilizzato per verificare le credenziali.
	 * @param ID              L'ID inserito.
	 * @param PSSW            La password inserita.
	 * @return                Il risultato del login.
	 */
	public int autenticazione(Utenza utenza, String ID, String PSSW) {
		Utente utente = utenza.autenticazioneConfiguratore(ID, PSSW);
		if (utente == null) {
	    	System.out.println(" ! Non esiste alcun configuratore con queste credenziali !");
	        return 1;
	    } else if (!utente.IsDefinitivo()) {
	    	System.out.println("Scegli nuove credenziali: ");
	        Credenziali credenzialiRegistrazione = primoAccesso(utenza);
	        utente.setCredenziali(credenzialiRegistrazione);
	        utente.setIsDefinitivo(true);
	        return 2;
	    } else {
	        System.out.println("-> Utente riconosciuto");
	        return 2;
	    }
	}

}
