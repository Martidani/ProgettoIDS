package it.unibs.ids.progetto.news.main;

import it.unibs.fp.mylib.MyMenu;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Utenza;
import it.unibs.ids.progetto.news.Geografia;
import it.unibs.ids.progetto.news.ecccezioni.RootTreeException;

/**
 * Questa classe gestisce i menu dell'applicazione e le relative azioni.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class MenuController {

	
    public final static String[] voci = 
        {"Introdurre comprensorio geografico", "Introdurre albero", "Visualizza comprensorio", 
            "Visualizza gerarchia", "Visualizza fattori di conversione"};
    
    static MyMenu menuConfiguratore = new MyMenu("Menu principale", voci);
	
    /**
     * Metodo che gestisce il menu di configurazione.
     * 
     * @param gerarchia L'oggetto Gerarchia su cui effettuare le operazioni.
     * @param geografia L'oggetto Geografia su cui effettuare le operazioni.
     * @return La scelta effettuata dall'utente.
     * @throws RootTreeException Eccezione sollevata in caso di errore nella manipolazione dell'albero.
     */
    public static int menuConfiguratore(Gerarchia gerarchia, Geografia geografia) throws RootTreeException {
        int scelta;
        scelta = menuConfiguratore.scegli();
        switch (scelta) {

            case 1:
                WorkConfController.creaComprensorio(geografia);
                break;

            case 2:
                WorkConfController.creaGerarchia(gerarchia);
                break;

            case 3:
                WorkConfController.stampaGeografia(geografia);
                break;

            case 4:
                WorkConfController.stampaGerarchia(gerarchia);
                break;

            case 5:
                WorkConfController.stampaFattori(gerarchia);
                break;

            default:
                break;
        }
        return scelta;
    }

    
    public final static String[] vociAccesso = 
        {"Registrazione","Login"};
    
    static MyMenu menuAccesso = new MyMenu("Menu accesso", vociAccesso);
    
    /**
     * Metodo che gestisce il menu di accesso.
     * 
     * @param utenza L'utente che sta effettuando l'accesso.
     * @return La scelta effettuata dall'utente.
     */
    public static int menuAccesso(Utenza utenza) {
        int accesso;
        accesso = menuAccesso.scegli();
        switch (accesso) {
            case 1:
                AccessConfController.registrazione(utenza);
                break;

            case 2:
                accesso = AccessConfController.login(utenza);
                break;

            default:
                break;
        }
        return accesso;
    }
}
