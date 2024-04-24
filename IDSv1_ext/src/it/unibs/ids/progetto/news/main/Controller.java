package it.unibs.ids.progetto.news.main;

import it.unibs.ids.progetto.Credenziali;
import it.unibs.ids.progetto.RootTreeException;


public class Controller {

	private ControllerConfAccess controllerConfAccess;
    private ControllerConfWork controllerConfWork;
    
	public Controller(Model model) {
		super();
        this.controllerConfAccess = new ControllerConfAccess(model);
        this.controllerConfWork = new  ControllerConfWork(model);
	}
	

    public void registrazione() {
    	controllerConfAccess.registrazione();
    }
	

	public int autenticazione(String ID, String PSSW) {
		return controllerConfAccess.autenticazione(ID, PSSW);
	}
	
	public Credenziali primoAccesso() {
		return controllerConfAccess.primoAccesso();
	}
	

	public int login() {
		return controllerConfAccess.login();
	}
	

    public void creaComprensorio() {
    	controllerConfWork.creaComprensorio();
    }


    public  void creaGerarchia( ) throws RootTreeException {
    	controllerConfWork.creaGerarchia();
    }


    public  String stampaGeografia( ) {
    	return controllerConfWork.stampaGeografia().toString();
    }


    public  String stampaGerarchia( ) {
    	return controllerConfWork.stampaGerarchia().toString();
    }
 
    public  String stampaFattori( )  {
    	return controllerConfWork.stampaFattori().toString();
    }


    public void navigaGerarchia( ) {
    	controllerConfWork.navigaGerarchia();
    }
	
}
