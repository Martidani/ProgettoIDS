package it.unibs.ids.progetto.news.main.view;

import it.unibs.fp.mylib.MyMenu;
import it.unibs.ids.progetto.RootTreeException;

public interface View {

    public abstract void run() throws RootTreeException;
	
    final static String[] vociSistema =  {"Configuratore","Fruitore"};
    final static MyMenu menuSistema = new MyMenu("Menu sistema", vociSistema);
    
    public static int modalitàFunzionamento() {
    	return menuSistema.scegli();
	}
}
