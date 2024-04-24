package it.unibs.ids.progetto.news.main;

import it.unibs.fp.mylib.MyMenu;
import it.unibs.ids.progetto.RootTreeException;

/**
 * Questa classe gestisce i menu dell'applicazione e le relative azioni.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class View {

	private Controller controller;
	
    public View(Controller controller) {
    	this.controller = controller;

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
            	controller.creaComprensorio();
                break;

            case 2:
            	controller.creaGerarchia();
                break;

            case 3:
            	System.out.println(controller.stampaGeografia().toString());;
                break;

            case 4:
            	System.out.println(controller.stampaGerarchia().toString());;
                break;

            case 5:
            	System.out.println(controller.stampaFattori().toString());;
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
            	controller.registrazione();
                break;

            case 2:
                accesso = controller.login();
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

