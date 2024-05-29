package it.unibs.ids.progetto.main;

import it.unibs.ids.progetto.RootTreeException;
import it.unibs.ids.progetto.main.controller.Controller;
import it.unibs.ids.progetto.main.controller.ControllerConf;
import it.unibs.ids.progetto.main.controller.ControllerFru;
import it.unibs.ids.progetto.main.model.Model;
import it.unibs.ids.progetto.main.view.View;
import it.unibs.ids.progetto.main.view.ViewConf;
import it.unibs.ids.progetto.main.view.ViewFru;


/**
 * Classe che contiene il metodo main per l'esecuzione dell'applicazione.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class Main {

    public static void main(String[] args) throws RootTreeException {

    	Model model = new Model();
    	
        Controller controller;
        View view;
        
        int mode = View.modalitàFunzionamento();
        if (mode == 1) {
        	controller = new ControllerConf(model);
        	view = new ViewConf(controller);
        	view.run();
        }
        else if (mode == 2){
        	controller = new ControllerFru(model);
        	view = new ViewFru(controller);
        	view.run();
        }

    	model.save();
    }
}