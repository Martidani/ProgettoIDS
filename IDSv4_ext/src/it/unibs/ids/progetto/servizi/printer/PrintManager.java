package it.unibs.ids.progetto.servizi.printer;

import it.unibs.ids.progetto.Nodo;

import it.unibs.ids.progetto.main.model.Model;

public class PrintManager {
	
	PrinterGeografia geografiaPrinter;
	PrinterGerarchia gerarchiaPrinter;
	PrinterCommercio commercioPrinter;
	PrinterUtenza utenzaPrinter;

	public PrintManager(Model model) {
		this.geografiaPrinter = new PrinterGeografia(model.getGeografia());
		this.gerarchiaPrinter = new PrinterGerarchia(model.getGerarchia());;
		this.commercioPrinter = new PrinterCommercio(model.getCommercio());;
		this.utenzaPrinter = new PrinterUtenza(model.getUtenza());
		
	}
	
	

	public String toStringGeografia() {
		return geografiaPrinter.toStringGeografia();
	}

	public String toStringGerarchia() {
		return gerarchiaPrinter.toStringGerarchia();
	}

	public String toStringRadici() {
		return gerarchiaPrinter.toStringRadici();
	}
	
	public String toStringProposteAperte() {
		return commercioPrinter.visualizzaProposteAperte();
	}

	public String toStringProposteAperte(Nodo foglia) {
		return commercioPrinter.visualizzaProposteAperte(foglia);
	}

	public String toStringProposteChiuse() {
		return commercioPrinter.visualizzaProposteChiuse();
	}

	public String toStringProposteChiuse(Nodo foglia) {
		return commercioPrinter.visualizzaProposteChiuse(foglia);
	}

	public String toStringProposteRitirate() {
		return commercioPrinter.visualizzaProposteRitirate();
	}

	public String toStringProposteRitirate(Nodo foglia) {
		return commercioPrinter.visualizzaProposteRitirate(foglia);
	}



	public String visualizzaProposteChiuseCommercio() {
		return commercioPrinter.visualizzaProposteChiuseCommercio();
	}
	 
    
}
	   

