package it.unibs.ids.progetto.main.view;

import it.unibs.ids.progetto.eccezioni.RootTreeException;
import it.unibs.ids.progetto.main.controller.Controller;
import it.unibs.ids.progetto.main.controller.ControllerFru;
import it.unibs.ids.progetto.servizi.printer.PrintMenu;


public class ViewFru implements View {

	private ControllerFru controllerFru;
	private int scelta;
    
	private static PrintMenu menuFruitore = PrintMenu.menuFruitore();
	private static PrintMenu menuAccesso = PrintMenu.menuAccessoF();
    
    public ViewFru(Controller controller) {
        this.controllerFru = (ControllerFru) controller;
        this.scelta=0;
	}
    
    private void menu() {
		scelta = menuFruitore.scegli();
		switch (scelta) {

		    case 1:
		    	controllerFru.navigaGerarchia();
		    	break;
		        
		    case 2:
		    	controllerFru.proponiScambio();
		    	break;
		    	
		    case 3:
		    	controllerFru.visualizzaProposte();
		    	break;
		    	
		    case 4:
		    	controllerFru.ritiraProposte();
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
             System.out.print(controllerFru.stampaGeografia());
             controllerFru.registrazione();

            break;

        case 2:
        	scelta = controllerFru.login();
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

	    //modalità fruitore
        if (scelta != 0) {
            do {
                menu();
            } while (scelta != 0);
        }
    }
    
}