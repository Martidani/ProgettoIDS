package it.unibs.ids.progetto.main.view;

import it.unibs.ids.progetto.RootTreeException;
import it.unibs.ids.progetto.main.controller.Controller;
import it.unibs.ids.progetto.main.controller.ControllerConf;
import it.unibs.ids.progetto.printer.PrintMenu;

/**
 * Questa classe gestisce i menu dell'applicazione e le relative azioni.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class ViewConf implements View {

	private ControllerConf controllerConf;
	private int scelta;
 
    private static PrintMenu menuConfiguratore = PrintMenu.menuConfiguratore();
    private static PrintMenu menuAccesso = PrintMenu.menuAccessoC();
	
    public ViewConf(Controller controller) {
        this.controllerConf = (ControllerConf) controller;
        this.scelta=0;
	}
    
    /**
     * Metodo che gestisce il menu di configurazione.
     * 
     * @param gerarchia L'oggetto Gerarchia su cui effettuare le operazioni.
     * @param geografia L'oggetto Geografia su cui effettuare le operazioni.
     * @return La scelta effettuata dall'utente.
     * @throws RootTreeException Eccezione sollevata in caso di errore nella manipolazione dell'albero.
     * @throws LeafHasChildrenException 
     */
    private void menu() throws RootTreeException {
        scelta = menuConfiguratore.scegli();
        switch (scelta) {

            case 1:
            	controllerConf.creaComprensorio();
                break;

            case 2:
            	controllerConf.creaGerarchia();
                break;

            case 3:
            	System.out.print(controllerConf.stampaGeografia());;
                break;

            case 4:
            	System.out.print(controllerConf.stampaGerarchia());;
                break;

            case 5:
            	System.out.println(controllerConf.stampaFattori());;
                break;
                
            case 6:
            	System.out.println(controllerConf.visualizzaProposteFoglia());
                break;
                
            case 7:
            	System.out.println(controllerConf.visualizzaInsiemiChiusi());
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
            	controllerConf.registrazione();
                break;

            case 2:
            	scelta = controllerConf.login();
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
                menu();
            } while (scelta != 0);
        }
    }
}

