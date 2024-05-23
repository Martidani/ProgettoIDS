package it.unibs.ids.progetto.news;
import java.io.Serializable;

import it.unibs.ids.progetto.Leaf;

public abstract class PrestazioneOpera  implements Serializable{

	protected Leaf foglia;
	protected int durata;
	
	public Leaf getFoglia() {
		return foglia;
	}
	
	public int getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata = durata;
	}
	
	public String getNome() {
		return foglia.getNome();
	}
	
}
