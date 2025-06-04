package it.unibs.ids.progetto.servizi.printer;

import it.unibs.ids.progetto.Nodo;
import it.unibs.ids.progetto.main.model.Model;

/**
 * La classe PrintManager gestisce la stampa delle informazioni relative al modello dell'applicazione.
 */
public class PrintManager {
	
	// Stampanti per le diverse entità del sistema
	PrinterGeografia geografiaPrinter;
	PrinterGerarchia gerarchiaPrinter;
	PrinterCommercio commercioPrinter;
	PrinterUtenza utenzaPrinter;

	/**
	 * Costruttore della classe PrintManager.
	 * @param model Modello dell'applicazione da cui ottenere le informazioni da stampare.
	 */
	public PrintManager(Model model) {
		this.geografiaPrinter = new PrinterGeografia(model.getGeografia());
		this.gerarchiaPrinter = new PrinterGerarchia(model.getGerarchia());
		this.commercioPrinter = new PrinterCommercio(model.getCommercio());
		this.utenzaPrinter = new PrinterUtenza(model.getUtenza());
	}

	/**
	 * Metodo per ottenere una rappresentazione in formato stringa della geografia.
	 * @return Stringa rappresentante la geografia.
	 */
	public String toStringGeografia() {
		return geografiaPrinter.toStringGeografia();
	}

	/**
	 * Metodo per ottenere una rappresentazione in formato stringa della gerarchia.
	 * @return Stringa rappresentante la gerarchia.
	 */
	public String toStringGerarchia() {
		return gerarchiaPrinter.toStringGerarchia();
	}

	/**
	 * Metodo per ottenere una rappresentazione in formato stringa delle radici della gerarchia.
	 * @return Stringa rappresentante le radici della gerarchia.
	 */
	public String toStringRadici() {
		return gerarchiaPrinter.toStringRadici();
	}
	
	/**
	 * Metodo per ottenere una rappresentazione in formato stringa delle proposte aperte nel commercio.
	 * @return Stringa rappresentante le proposte aperte nel commercio.
	 */
	public String toStringProposteAperte() {
		return commercioPrinter.visualizzaProposteAperte();
	}

	/**
	 * Metodo per ottenere una rappresentazione in formato stringa delle proposte aperte nel commercio relative a un nodo specifico.
	 * @param foglia Nodo di interesse.
	 * @return Stringa rappresentante le proposte aperte nel commercio relative al nodo specificato.
	 */
	public String toStringProposteAperte(Nodo foglia) {
		return commercioPrinter.visualizzaProposteAperte(foglia);
	}

	/**
	 * Metodo per ottenere una rappresentazione in formato stringa delle proposte chiuse nel commercio.
	 * @return Stringa rappresentante le proposte chiuse nel commercio.
	 */
	public String toStringProposteChiuse() {
		return commercioPrinter.visualizzaProposteChiuse();
	}

	/**
	 * Metodo per ottenere una rappresentazione in formato stringa delle proposte chiuse nel commercio relative a un nodo specifico.
	 * @param foglia Nodo di interesse.
	 * @return Stringa rappresentante le proposte chiuse nel commercio relative al nodo specificato.
	 */
	public String toStringProposteChiuse(Nodo foglia) {
		return commercioPrinter.visualizzaProposteChiuse(foglia);
	}

	/**
	 * Metodo per ottenere una rappresentazione in formato stringa delle proposte ritirate nel commercio.
	 * @return Stringa rappresentante le proposte ritirate nel commercio.
	 */
	public String toStringProposteRitirate() {
		return commercioPrinter.visualizzaProposteRitirate();
	}

	/**
	 * Metodo per ottenere una rappresentazione in formato stringa delle proposte ritirate nel commercio relative a un nodo specifico.
	 * @param foglia Nodo di interesse.
	 * @return Stringa rappresentante le proposte ritirate nel commercio relative al nodo specificato.
	 */
	public String toStringProposteRitirate(Nodo foglia) {
		return commercioPrinter.visualizzaProposteRitirate(foglia);
	}

	/**
	 * Metodo per ottenere una rappresentazione in formato stringa delle proposte chiuse nel commercio.
	 * @return Stringa rappresentante le proposte chiuse nel commercio.
	 */
	public String visualizzaProposteChiuseCommercio() {
		return commercioPrinter.visualizzaProposteChiuseCommercio();
	}
	 
    
}

