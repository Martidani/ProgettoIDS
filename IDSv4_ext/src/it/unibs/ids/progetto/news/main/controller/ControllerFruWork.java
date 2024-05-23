package it.unibs.ids.progetto.news.main.controller;

import it.unibs.fp.mylib.InputDati;
import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.Leaf;
import it.unibs.ids.progetto.Nodo;
import it.unibs.ids.progetto.NotLeaf;
import it.unibs.ids.progetto.news.InsiemeAperto;
import it.unibs.ids.progetto.news.LeafPrintManager;
import it.unibs.ids.progetto.news.NotLeafPrintManager;
import it.unibs.ids.progetto.news.Offerta;
import it.unibs.ids.progetto.news.PropostaAperta;
import it.unibs.ids.progetto.news.Richiesta;
import it.unibs.ids.progetto.news.main.model.Model;

public class ControllerFruWork  {

	
	protected Model model;
	
	public ControllerFruWork (Model model) {
		this.model = model;
	}

	public void navigaGerarchia() {
		String ger = model.toStringRadici();
		System.out.println(ger + "\n");
      
		String nomeRadice = InputDati.leggiStringaNonVuota("Scegli radice -> ");
		NotLeaf radice = model.visualizzaRadice(nomeRadice);
		System.out.println(NotLeafPrintManager.toNavigationString(radice) + "\n");
		Nodo child;
				
		do {
			int valoreDominio = InputDati.leggiIntero("Scegli l'opzione -> ");	
			child = radice.getChildren().get(valoreDominio-1);

			if (child.isLeaf() )
					System.out.println(LeafPrintManager.toNavigationString(child)+ "\n");
			else
				System.out.println(NotLeafPrintManager.toNavigationString(child)+ "\n");

				
		} while (!child.isLeaf());
	}

	public String stampaGeografia() {
		return model.toStringGeografia().toString();
	}
	
	public void proponiScambio() {
        InsiemeAperto insiemeAperto = model.getInsiemeApertoDiSessione();
        
	       
	       Leaf fogliaRichiesta = inserimentoPrestazioneOpera();
	       int durata = InputDati.leggiInteroPositivo("Inserisci durata -> ");
	       Leaf fogliaOfferta = inserimentoPrestazioneOpera();

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

	private Leaf inserimentoPrestazioneOpera() {
		String nomePrestazione;
		String nomeRadicePrestazione;
		Leaf foglia;
    	do {
    		nomePrestazione = InputDati.leggiStringaNonVuota("Inserisci richiesta [foglia di appartenenza] -> ");
        	nomeRadicePrestazione = InputDati.leggiStringaNonVuota("Inserisci radice -> ");	
        	foglia = model.visualizzaFoglia(nomePrestazione, nomeRadicePrestazione);
		} while (foglia == null);
		return foglia;
	}

	public void visualizzaProposte() {
		System.out.println();
		String proposteA =model.visualizzaProposteAperte(null);
		String proposteC =model.visualizzaProposteChiuse(null);
		String proposteR =model.visualizzaProposteRitirate(null);
		if (!proposteA.isBlank()) {
			System.out.println(" Proposte Aperte: \n" + proposteA);
		}
		if (!proposteC.isBlank()) {
			System.out.println(" Proposte Chiuse: \n" + proposteC);
		}
		if (!proposteR.isBlank()) {
			System.out.println(" Proposte Ritirate: \n" + proposteR);
		}
		
	}

	public void ritiraProposte() {
		String proposte = model.visualizzaProposteAperte(null);
		

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

