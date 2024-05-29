package it.unibs.ids.progetto.main.controller;

import it.unibs.ids.progetto.RootTreeException;
import it.unibs.ids.progetto.main.model.Model;
import it.unibs.ids.progetto.printer.PrintManager;

public class ControllerConf implements Controller {

	private ControllerConfAccess controllerConfAccess;
    private ControllerConfWork controllerConfWork;
    
	public ControllerConf(Model model) {
		super();
	    this.controllerConfAccess = new ControllerConfAccess(model);
	    this.controllerConfWork = new  ControllerConfWork(model);
	}

	public void creaComprensorio() {
		controllerConfWork.creaComprensorio();
	}

	public void creaGerarchia() throws RootTreeException {
		controllerConfWork.creaGerarchia();
	}

	public String stampaGeografia() {
		return controllerConfWork.stampaGeografia().toString();
	}

	public String stampaGerarchia() {
		return controllerConfWork.stampaGerarchia().toString();
	}

	public String stampaFattori() {
		return controllerConfWork.stampaFattori().toString();
	}

	public void registrazione() {
		controllerConfAccess.registrazione();
	}

	public int login() {
		return controllerConfAccess.login();
	}

	public String visualizzaProposteFoglia() {
		return controllerConfWork.visualizzaProposteFoglia();
	}
	
}
