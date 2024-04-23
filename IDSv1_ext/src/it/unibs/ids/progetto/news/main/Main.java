package it.unibs.ids.progetto.news.main;

import it.unibs.ids.progetto.RootTreeException;

/**
 * Classe che contiene il metodo main per l'esecuzione dell'applicazione.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class Main {


    public static void main(String[] args) throws RootTreeException {

    	Model model = new Model();
    	AccessConfController accessConfController = new AccessConfController(model);
    	WorkConfController workConfController = new WorkConfController(model);
    	MainConfView mainConfView = new MainConfView(accessConfController, workConfController);

    	mainConfView.run();

    	model.save();
    }
}
