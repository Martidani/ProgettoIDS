package it.unibs.ids.progetto.main.view;

import it.unibs.ids.progetto.eccezioni.RootTreeException;
import it.unibs.ids.progetto.printer.PrintMenu;

public interface View {

    public abstract void run() throws RootTreeException;
    
    public static int modalitàFunzionamento() {
    	return PrintMenu.menuSistema().scegli();
	}
}
