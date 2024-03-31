package it.unibs.ids.progetto.news;

import it.unibs.fp.mylib.InputDati;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Nodo;

public class WorkFruitorController implements WorkController {

    
	public WorkFruitorController() {
		super();
	}

	public void navigaGerarchia(Gerarchia gerarchia) {
		String ger = gerarchia.toStringRadici();
		System.out.println(ger + "\n");
      
		String nomeRadice = InputDati.leggiStringaNonVuota("Scegli radice -> ");
		Nodo radice = gerarchia.visualizzaRadice(nomeRadice);
		System.out.println(radice.toNavigationString() + "\n");
		Nodo child;
				
		do {
			int valoreDominio = InputDati.leggiIntero("Scegli l'opzione -> ");	
			child = radice.getChildren().get(valoreDominio-1);
			radice = child;
			System.out.println(child.toNavigationString()+ "\n");
		} while (!child.isLeaf());
	}
	
}
