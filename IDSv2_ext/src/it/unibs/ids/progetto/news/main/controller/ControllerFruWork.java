package it.unibs.ids.progetto.news.main.controller;

import it.unibs.fp.mylib.InputDati;
import it.unibs.ids.progetto.Nodo;
import it.unibs.ids.progetto.NotLeaf;
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
}
