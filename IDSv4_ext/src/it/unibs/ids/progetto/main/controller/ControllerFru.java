package it.unibs.ids.progetto.main.controller;

import it.unibs.ids.progetto.main.model.Model;
import it.unibs.ids.progetto.printer.PrintManager;

public class ControllerFru implements Controller {

	private ControllerFruAccess controllerFruAccess;
    private ControllerFruWork controllerFruWork;
    
	public ControllerFru(Model model) {
		super();
	    this.controllerFruAccess = new ControllerFruAccess(model);
	    this.controllerFruWork = new  ControllerFruWork(model);
	}

	public String stampaGeografia() {
		return controllerFruWork.stampaGeografia().toString();
	}

	public void registrazione() {
		controllerFruAccess.registrazione();
		
	}

	public int login() {
		return controllerFruAccess.login();
		
	}

	public void navigaGerarchia() {
		controllerFruWork.navigaGerarchia();
		
	}

	public void proponiScambio() {
		controllerFruWork.proponiScambio();
	}

	public void visualizzaProposte() {
		controllerFruWork.visualizzaProposte();
		
	}

	public void ritiraProposte() {
		controllerFruWork.ritiraProposte();
		
	}
}
