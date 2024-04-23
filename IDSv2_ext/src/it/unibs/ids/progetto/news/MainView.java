package it.unibs.ids.progetto.news;

import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.RootTreeException;
import it.unibs.ids.progetto.Utenza;


/**
 * Questa classe gestisce i menu dell'applicazione e le relative azioni.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public interface MainView {

    public abstract int menuAccesso(Utenza utenza, Geografia geografia);
	public abstract int menu(Gerarchia gerarchia, Geografia geografia) throws RootTreeException;
  
}
