package it.unibs.ids.progetto.main.controller;

import it.unibs.fp.mylib.InputDati;
import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.InsiemeAperto;
import it.unibs.ids.progetto.Leaf;
import it.unibs.ids.progetto.Nodo;
import it.unibs.ids.progetto.NotLeaf;
import it.unibs.ids.progetto.Offerta;
import it.unibs.ids.progetto.PropostaAperta;
import it.unibs.ids.progetto.Richiesta;
import it.unibs.ids.progetto.main.model.Model;
import it.unibs.ids.progetto.printer.PrinterLeaf;
import it.unibs.ids.progetto.printer.PrinterNotLeaf;
import it.unibs.ids.progetto.printer.PrintManager;

public class ControllerFruWork  {

	
	protected Model model;
    private PrintManager printManager;
	
	public ControllerFruWork (Model model) {
		this.model = model;
	    this.printManager = new PrintManager(model);
	}

	public void navigaGerarchia() {
		String ger = printManager.toStringRadici();
		System.out.println(ger + "\n");
      
		String nomeRadice = InputDati.leggiStringaNonVuota("Scegli radice -> ");
		NotLeaf radice = model.visualizzaRadice(nomeRadice);
		System.out.println(PrinterNotLeaf.toNavigationString(radice) + "\n");
		Nodo child;
				
		do {
			int valoreDominio = InputDati.leggiIntero("Scegli l'opzione -> ");	
			child = radice.getChildren().get(valoreDominio-1);

			if (child.isLeaf() )
					System.out.println(PrinterLeaf.toNavigationString(child)+ "\n");
			else
				System.out.println(PrinterNotLeaf.toNavigationString(child)+ "\n");

				
		} while (!child.isLeaf());
	}

	public String stampaGeografia() {
		return printManager.toStringGeografia();
	}
	
	public void proponiScambio() {
        InsiemeAperto insiemeAperto = model.getInsiemeApertoDiSessione();
        
	       
	       Leaf fogliaRichiesta = inserimentoPrestazioneOpera(true);
	       int durata = InputDati.leggiInteroPositivo("Inserisci durata -> ");
	       Leaf fogliaOfferta = inserimentoPrestazioneOpera(false);

	        Offerta offerta = new Offerta(fogliaOfferta);
	        Richiesta richiesta = new Richiesta(fogliaRichiesta, durata);
	        Fruitore fruitore = (Fruitore) model.getUtenteDiSessione();
	        PropostaAperta proposta = new PropostaAperta(richiesta, offerta, model.numProposte(),fruitore);
	       
	        System.out.println("\nOfferta: ");
	        System.out.println("[" + offerta.getNome() + ", "+ offerta.getDurata() + " ore]");
	        
	        if (InputDati.yesOrNo("Confermi l'offerta?")) {
	            model.addProposte(proposta);
	            insiemeAperto.addPropostaAperta(proposta);
	            model.metodo(insiemeAperto);            
	        } else {
				model.decrementaNumProposte();
			}
    	
	}

	private Leaf inserimentoPrestazioneOpera(boolean modeFun) {
		String nomePrestazione;
		String nomeRadicePrestazione;
		Leaf foglia;
    	do {
    		if (modeFun)
    			nomePrestazione = InputDati.leggiStringaNonVuota("Inserisci richiesta [foglia di appartenenza] -> ");
    		else
    			nomePrestazione = InputDati.leggiStringaNonVuota("Inserisci offerta [foglia di appartenenza] -> ");

        	nomeRadicePrestazione = InputDati.leggiStringaNonVuota("Inserisci radice -> ");	
        	foglia = model.visualizzaFoglia(nomePrestazione, nomeRadicePrestazione);
		} while (foglia == null);
		return foglia;
	}

	public void visualizzaProposte() {
		System.out.println();
		String proposteA =printManager.toStringProposteAperte();
		String proposteC =printManager.toStringProposteChiuse();
		String proposteR =printManager.toStringProposteRitirate();
		if (!proposteA.isBlank()) {
			System.out.println("Proposte Aperte: \n{" + proposteA + "}\n");
		}
		else
			System.out.println("\n{\nNon ci sono Proposte Aperte!\n}\n");
		if (!proposteC.isBlank()) {
			System.out.println("\nProposte Chiuse: \n{" + proposteC + "}\n");
		}
		else
			System.out.println("\n{\nNon ci sono Proposte Chiuse!\n}\n");
		if (!proposteR.isBlank()) {
			System.out.println("\nProposte Ritirate: \n{" + proposteR + "}");
		}
		else
			System.out.println("\n{\nNon ci sono Proposte Ritirate!\n}\n");
		
	}

	public void ritiraProposte() {
		String proposte = printManager.toStringProposteAperte();
		

		int s1;
		PropostaAperta proposta;
		System.out.println("Proposte da ritirare: \n\n" + proposte);
		
		if (!proposte.isBlank() && InputDati.yesOrNo("\nVuoi ritirare una proposta? \n") ) {
			do {
				 s1 = InputDati.leggiInteroNonNegativo("\nInserisci ID proposta: ");
				 
			} while ((proposta = model.cercaProposta(s1)) == null);
			
			model.ritira(proposta);
		}
		else
			System.out.println("Non ci sono proposte (aperte) ritirabili");

	}
		
	}

