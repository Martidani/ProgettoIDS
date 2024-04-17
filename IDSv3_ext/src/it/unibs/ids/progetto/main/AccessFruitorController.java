package it.unibs.ids.progetto.main;

import it.unibs.fp.mylib.InputDati;
import it.unibs.ids.progetto.Comprensorio;
import it.unibs.ids.progetto.Credenziali;
import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Utente;
import it.unibs.ids.progetto.Utenza;

public class AccessFruitorController extends AccessController {


	public AccessFruitorController() {
		super();
	}

	@Override
	/**
	 * Metodo per registrare un nuovo utente.
	 * 
	 * @param utenza   L'oggetto Utenza utilizzato per registrare il nuovo utente.
	 */
	public void registrazione(Utenza utenza, Geografia geografia) {
		
		String c;
		do {
			c = InputDati.leggiStringaNonVuota("  Comprensorio: ");
		} while (!geografia.verificaEsistenzaComprensorio(c));
		
	    Credenziali credenziali = primoAccesso(utenza);
	    String indirizzo = InputDati.leggiStringaNonVuota("  Indirizzo e-mail: ");
	    Comprensorio comprensorio = geografia.cercaComprensorio(c);

	    Fruitore fruitore = new Fruitore(comprensorio, credenziali, indirizzo);
	    utenza.addUtente(fruitore);
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
		Utente utenteDiSessione = utenza.autenticazioneFruitore(ID, PSSW);
		utenza.setUtenteDiSessione(utenteDiSessione);
		
	    if (utenteDiSessione == null) {
	    		System.out.println(" ! Non esiste alcun fruitore con queste credenziali !");
	        return 1;
	    }  else {
	        System.out.println("-> Utente riconosciuto");
	        return 2;
	    }
	}
	
}
