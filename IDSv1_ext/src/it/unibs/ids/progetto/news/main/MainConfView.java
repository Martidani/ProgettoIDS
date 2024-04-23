package it.unibs.ids.progetto.news.main;

import it.unibs.fp.mylib.MyMenu;
import it.unibs.ids.progetto.RootTreeException;

/**
 * Questa classe gestisce i menu dell'applicazione e le relative azioni.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class MainConfView {

	private AccessConfController accessConfController;
	private WorkConfController workConfController;
	
    public MainConfView(AccessConfController accessConfController, WorkConfController workConfController) {
    	this.accessConfController = accessConfController;
    	this.workConfController = workConfController;
	}

    private final static String[] vociAccesso = 
        {"Registrazione","Login"};
    private final static String[] voci = 
        {"Introdurre comprensorio geografico", "Introdurre albero", "Visualizza comprensorio", 
            "Visualizza gerarchia", "Visualizza fattori di conversione"};
    
    
    private static MyMenu menuConfiguratore = new MyMenu("Menu principale", voci);
    private static MyMenu menuAccesso = new MyMenu("Menu accesso", vociAccesso);
	
    
    /**
     * Metodo che gestisce il menu di configurazione.
     * 
     * @param gerarchia L'oggetto Gerarchia su cui effettuare le operazioni.
     * @param geografia L'oggetto Geografia su cui effettuare le operazioni.
     * @return La scelta effettuata dall'utente.
     * @throws RootTreeException Eccezione sollevata in caso di errore nella manipolazione dell'albero.
     * @throws LeafHasChildrenException 
     */
    private int menuConfiguratore() throws RootTreeException {
        int scelta;
        scelta = menuConfiguratore.scegli();
        switch (scelta) {

            case 1:
            	workConfController.creaComprensorio();
                break;

            case 2:
            	workConfController.creaGerarchia();
                break;

            case 3:
            	workConfController.stampaGeografia();
                break;

            case 4:
            	workConfController.stampaGerarchia();
                break;

            case 5:
            	workConfController.stampaFattori();
                break;

            default:
                break;
        }
        return scelta;
    }

    /**
     * Metodo che gestisce il menu di accesso.
     * 
     * @param utenza L'utente che sta effettuando l'accesso.
     * @return La scelta effettuata dall'utente.
     */
    private int menuAccesso() {
        int accesso;
        accesso = menuAccesso.scegli();
        switch (accesso) {
            case 1:
            	accessConfController.registrazione();
                break;

            case 2:
                accesso = accessConfController.login();
                break;

            default:
                break;
        }
        return accesso;
    }


    public void run() throws RootTreeException {
	    //accensione del sistema
        int scelta;
        do {
        	scelta = menuAccesso();
        } while (scelta == 1);

        
	    //modalità configuratore
        if (scelta != 0) {
            do {
                scelta = menuConfiguratore();
            } while (scelta != 0);
        }
    }
}

