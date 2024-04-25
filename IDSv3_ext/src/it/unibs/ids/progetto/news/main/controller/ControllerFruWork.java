package it.unibs.ids.progetto.news.main.controller;

import it.unibs.fp.mylib.InputDati;
import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.Leaf;
import it.unibs.ids.progetto.Nodo;
import it.unibs.ids.progetto.NotLeaf;
import it.unibs.ids.progetto.news.Offerta;
import it.unibs.ids.progetto.news.PropostaDiScambio;
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
		System.out.println(radice.toNavigationString() + "\n");
		Nodo child;
				
		do {
			int valoreDominio = InputDati.leggiIntero("Scegli l'opzione -> ");	
			child = radice.getChildren().get(valoreDominio-1);

			System.out.println(child.toNavigationString()+ "\n");
		} while (!child.isLeaf());
	}

	public String stampaGeografia() {
		return model.toStringGeografia().toString();
	}
	
	public void proponiScambio() {
    	
    	Leaf fogliaRichiesta = inserimentoPrestazioneOpera();
    	int durata = InputDati.leggiInteroPositivo("Inserisci durata -> ");
    	
    	
    	Leaf fogliaOfferta  = inserimentoPrestazioneOpera();
    	
    	Offerta offerta = new Offerta(fogliaOfferta);
    	Richiesta richiesta = new Richiesta(fogliaRichiesta, durata);
    	PropostaDiScambio proposta = new PropostaDiScambio(richiesta, offerta);
    	

		System.out.println("\nOfferta: ");
		System.out.println("[" + offerta.getNome() + ", "+ offerta.getDurata() + " ore]");
	
		
		if (InputDati.yesOrNo("Confermi l'offerta?")) {
			Fruitore fruitore = (Fruitore) model.getUtenteDiSessione();
			fruitore.addProposte(proposta);
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
}
