package it.unibs.ids.progetto.news;
import java.io.Serializable;

import it.unibs.ids.progetto.Leaf;

public class Offerta extends PrestazioneOpera implements Serializable{

	public Offerta(Leaf foglia) {
			this.foglia = foglia;
	}
}
