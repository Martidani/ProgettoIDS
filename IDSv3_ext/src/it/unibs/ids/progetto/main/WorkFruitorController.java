package it.unibs.ids.progetto.main;

import it.unibd.ids.progetto.news.Offerta;
import it.unibd.ids.progetto.news.PropostaDiScambio;
import it.unibd.ids.progetto.news.Richiesta;
import it.unibs.fp.mylib.InputDati;
import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Leaf;
import it.unibs.ids.progetto.Nodo;
import it.unibs.ids.progetto.NotLeaf;
import it.unibs.ids.progetto.Utenza;

public class WorkFruitorController implements WorkController {

    
	public WorkFruitorController() {
		super();
	}

	public void navigaGerarchia(Gerarchia gerarchia) {
		String ger = gerarchia.toStringRadici();
		System.out.println(ger + "\n");
      
		String nomeRadice = InputDati.leggiStringaNonVuota("Scegli radice -> ");
		NotLeaf radice = gerarchia.visualizzaRadice(nomeRadice);
		System.out.println(radice.toNavigationString() + "\n");
		Nodo child;
				
		do {
			int valoreDominio = InputDati.leggiIntero("Scegli l'opzione -> ");	
			child = radice.getChildren().get(valoreDominio-1);

			System.out.println(child.toNavigationString()+ "\n");
		} while (!child.isLeaf());
	}
	
	public void proponiScambio( Gerarchia gerarchia, Utenza utenza) {
    	
    	Leaf fogliaRichiesta = inserimentoPrestazioneOpera(gerarchia);
    	int durata = InputDati.leggiInteroPositivo("Inserisci durata -> ");
    	
    	
    	Leaf fogliaOfferta  = inserimentoPrestazioneOpera(gerarchia);
    	
    	Offerta offerta = new Offerta(fogliaOfferta);
    	Richiesta richiesta = new Richiesta(fogliaRichiesta, durata);
    	PropostaDiScambio proposta = new PropostaDiScambio(richiesta, offerta);
    	

		System.out.println("\nOfferta: ");
		System.out.println("[" + offerta.getNome() + ", "+ offerta.getDurata() + " ore]");
	
		
		if (InputDati.yesOrNo("Confermi l'offerta?")) {
			Fruitore fruitore = (Fruitore) utenza.getUtenteDiSessione();
			fruitore.addProposte(proposta);
		}
    	
	}

	private Leaf inserimentoPrestazioneOpera(Gerarchia gerarchia) {
		String nomePrestazione;
		String nomeRadicePrestazione;
		Leaf foglia;
    	do {
    		nomePrestazione = InputDati.leggiStringaNonVuota("Inserisci richiesta [foglia di appartenenza] -> ");
        	nomeRadicePrestazione = InputDati.leggiStringaNonVuota("Inserisci radice -> ");	
        	foglia = gerarchia.visualizzaFoglia(nomePrestazione, nomeRadicePrestazione);
		} while (foglia == null);
		return foglia;
	}
}
