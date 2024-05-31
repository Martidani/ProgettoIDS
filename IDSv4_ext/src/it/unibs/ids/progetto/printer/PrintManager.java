package it.unibs.ids.progetto.printer;

import java.util.Map.Entry;

import it.unibs.ids.progetto.Albero;
import it.unibs.ids.progetto.Commercio;
import it.unibs.ids.progetto.Comprensorio;
import it.unibs.ids.progetto.FattoriDiConversione;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.InsiemeAperto;
import it.unibs.ids.progetto.InsiemeChiuso;
import it.unibs.ids.progetto.InsiemeRitirato;
import it.unibs.ids.progetto.Leaf;
import it.unibs.ids.progetto.Nodo;
import it.unibs.ids.progetto.NotLeaf;
import it.unibs.ids.progetto.PrestazioneOpera;
import it.unibs.ids.progetto.Proposta;
import it.unibs.ids.progetto.PropostaAperta;
import it.unibs.ids.progetto.PropostaChiusa;
import it.unibs.ids.progetto.PropostaRitirata;
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
	   

