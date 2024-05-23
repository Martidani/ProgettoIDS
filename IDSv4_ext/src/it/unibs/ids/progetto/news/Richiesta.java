package it.unibs.ids.progetto.news;
import java.io.Serializable;

import it.unibs.ids.progetto.Leaf;

public class Richiesta extends PrestazioneOpera implements Serializable{

	public Richiesta(Leaf foglia, int durata) {
		this.foglia = foglia;
		this.durata = durata;
		
	}
}
