package it.unibs.ids.progetto.news.main.controller;

import it.unibs.ids.progetto.news.main.model.Model;

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
}
