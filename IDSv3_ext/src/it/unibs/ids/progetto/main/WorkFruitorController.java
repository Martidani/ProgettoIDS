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
		
    	String nomePrestazione;
    	String nomeRadicePrestazione;
    	Leaf fogliaRichiesta;
    	do {
    		nomePrestazione = InputDati.leggiStringaNonVuota("Inserisci richiesta [foglia di appartenenza] -> ");
        	nomeRadicePrestazione = InputDati.leggiStringaNonVuota("Inserisci radice -> ");	
        	fogliaRichiesta = gerarchia.visualizzaFoglia(nomePrestazione, nomeRadicePrestazione);
		} while (fogliaRichiesta == null);
    	
    	int durata = InputDati.leggiInteroPositivo("Inserisci durata -> ");
    	
    	Leaf fogliaOfferta;
    	do {
    		nomePrestazione = InputDati.leggiStringaNonVuota("Inserisci offerta [foglia di appartenenza] -> ");
        	nomeRadicePrestazione = InputDati.leggiStringaNonVuota("Inserisci radice -> ");	
        	fogliaOfferta = gerarchia.visualizzaFoglia(nomePrestazione, nomeRadicePrestazione);
		} while (fogliaOfferta == null);
    	
    	
    	Offerta offerta = new Offerta(fogliaOfferta);
    	Richiesta richiesta = new Richiesta(fogliaRichiesta, durata);
    	PropostaDiScambio proposta = new PropostaDiScambio(richiesta, offerta);
    	
    	Fruitore fruitore = (Fruitore) utenza.getUtenteDiSessione();
    	fruitore.addProposte(proposta);
    	
	}
}
