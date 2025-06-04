package it.unibs.ids.progetto.main.view;

import it.unibs.ids.progetto.eccezioni.RootTreeException;
import it.unibs.ids.progetto.main.controller.Controller;
import it.unibs.ids.progetto.main.controller.ControllerFru;
import it.unibs.ids.progetto.servizi.printer.PrintMenu;

/**
 * La classe ViewFru implementa l'interfaccia View e gestisce le interazioni tra l'utente
 * di tipo fruitore e il sistema.
 */
public class ViewFru implements View {

    private ControllerFru controllerFru; // Controller specifico per i fruitori
    private int scelta; // Variabile per memorizzare la scelta dell'utente

    private static PrintMenu menuFruitore = PrintMenu.menuFruitore(); // Menu specifico per i fruitori
    private static PrintMenu menuAccesso = PrintMenu.menuAccessoF(); // Menu di accesso
    
    /**
     * Costruttore della classe ViewFru.
     * @param controller Il controller da associare alla vista, convertito a ControllerFru
     */
    public ViewFru(Controller controller) {
        this.controllerFru = (ControllerFru) controller;
        this.scelta = 0;
    }
    
    /**
     * Metodo privato che gestisce il menu delle operazioni disponibili per il fruitore.
     */
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
     * Metodo privato che gestisce il menu di accesso iniziale per il fruitore.
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
    
    /**
     * Implementazione del metodo run() dell'interfaccia View.
     * Gestisce il flusso di esecuzione dell'applicazione per il fruitore.
     */
    @Override
    public void run() throws RootTreeException {
        // Gestione del menu di accesso
        do {
            menuAccesso();
        } while (scelta == 1);

        // Modalità fruitore
        if (scelta != 0) {
            do {
                menu();
            } while (scelta != 0);
        }
    }
    
}
