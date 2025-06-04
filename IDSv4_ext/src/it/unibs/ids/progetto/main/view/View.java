package it.unibs.ids.progetto.main.view;

import it.unibs.ids.progetto.eccezioni.RootTreeException;
import it.unibs.ids.progetto.servizi.printer.PrintMenu;

/**
 * Interfaccia View
 * Definisce il metodo run() che deve essere implementato dalle classi che la implementano.
 */
public interface View {

    /**
     * Metodo che avvia l'esecuzione della View.
     * Può lanciare un'eccezione RootTreeException in caso di problemi con la radice dell'albero.
     * @throws RootTreeException Eccezione che indica problemi con la radice dell'albero
     */
    public abstract void run() throws RootTreeException;
    
    /**
     * Metodo statico che restituisce la modalità di funzionamento del sistema.
     * Utilizza PrintMenu per visualizzare un menu e restituisce la scelta effettuata dall'utente.
     * @return Intero che rappresenta la scelta effettuata dall'utente nel menu
     */
    public static int modalitàFunzionamento() {
        return PrintMenu.menuSistema().scegli();
    }
}
