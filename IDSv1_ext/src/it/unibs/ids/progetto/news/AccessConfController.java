package it.unibs.ids.progetto.news;

import it.unibs.fp.mylib.InputDati;
import it.unibs.ids.progetto.ConfLoginFailException;
import it.unibs.ids.progetto.Configuratore;
import it.unibs.ids.progetto.Credenziali;
import it.unibs.ids.progetto.Utenza;

/**
 * Questa classe gestisce le operazioni di login e registrazione degli utenti.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class AccessConfController {
	
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
    public static int eseguiLogin(Utenza utenza) {
        int accesso = 1;
        
        for (int i = 0; i < NUM_MAX_TENTATIVI; i++) {
            System.out.println("Inserisci dati di login: ");
            String ID = InputDati.leggiStringaNonVuota("  ID: ");
            String PSSW = InputDati.leggiStringaNonVuota("  Password: ");
            try {
                accesso = autenticazione(utenza, ID, PSSW);
            } catch (ConfLoginFailException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
            if (accesso != 1) 
                break;
        }
        return accesso;
    }

    /**
     * Metodo per registrare un nuovo utente.
     * 
     * @param utenza   L'oggetto Utenza utilizzato per registrare il nuovo utente.
     */
    public static void registrazione(Utenza utenza) {
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
     * Metodo per il login.
     * 
     * @param utenza   L'oggetto Utenza utilizzato per gestire l'accesso.
     * @return         L'accesso aggiornato.
     */
    public static int login(Utenza utenza) {
        int accesso = 1;
        for (int i = 0; i < NUM_MAX_TENTATIVI; i++) {
            System.out.println("Inserisci dati di login: ");
            String ID = InputDati.leggiStringaNonVuota("  ID: ");
            String PSSW = InputDati.leggiStringaNonVuota("  Password: ");
            try {
                accesso = autenticazione(utenza, ID, PSSW);
            } catch (ConfLoginFailException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
            if (accesso != 1) 
                break;
        }
        return accesso;
    }

    /**
     * Metodo per effettuare il login.
     * 
     * @param utenza   L'oggetto Utenza utilizzato per verificare le credenziali.
     * @param ID       L'ID inserito.
     * @param PSSW     La password inserita.
     * @return         Il risultato del login.
     * @throws ConfLoginFailException Eccezione sollevata in caso di fallimento dell'autenticazione.
     */
    private static int autenticazione(Utenza utenza, String ID, String PSSW) throws ConfLoginFailException {
        Configuratore conf = utenza.autenticazioneConfiguratore(ID, PSSW);
        if (conf == null) {
            throw new ConfLoginFailException();
        } else if (!conf.getCredenziali().isDefinitive()) {
            System.out.println("Scegli nuove credenziali: ");
            Credenziali credenzialiRegistrazione = primoAccesso(utenza);
            conf.setCredenziali(credenzialiRegistrazione);
            conf.setIsDefinitivo(true);
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
    private static Credenziali primoAccesso(Utenza utenza) {
        String ID;
        do {
            ID = InputDati.leggiStringaNonVuota("  ID: ");
            if (utenza.verificaEsistenzaID(ID)) System.out.println(" ! ID già utilizzato ! ");
        } while (utenza.verificaEsistenzaID(ID));

        String PSSW = InputDati.leggiStringaNonVuota("  Password: ");
        return new Credenziali(ID, PSSW);
    }
}
