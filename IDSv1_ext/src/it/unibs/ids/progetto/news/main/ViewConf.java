package it.unibs.ids.progetto.news.main;

import it.unibs.fp.mylib.MyMenu;
import it.unibs.ids.progetto.RootTreeException;

/**
 * Questa classe gestisce i menu dell'applicazione e le relative azioni.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class ViewConf {

	private ControllerConf controller;
	private int scelta;
	
    public ViewConf(ControllerConf controller) {
    	this.controller = controller;
    	this.scelta = 0;

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
    private void menuConfiguratore() throws RootTreeException {
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
    }

    /**
     * Metodo che gestisce il menu di accesso.
     * 
     * @param utenza L'utente che sta effettuando l'accesso.
     * @return La scelta effettuata dall'utente.
     */
    private void menuAccesso() {
    	scelta = menuAccesso.scegli();
        switch (scelta) {
            case 1:
            	controller.registrazione();
                break;

            case 2:
            	scelta = controller.login();
                break;

            default:
                break;
        }

    }


    public void run() throws RootTreeException {
	    //accensione del sistema
        do {
        	menuAccesso();
        } while (scelta == 1);

        
	    //modalità configuratore
        if (scelta != 0) {
            do {
                menuConfiguratore();
            } while (scelta != 0);
        }
    }
}

