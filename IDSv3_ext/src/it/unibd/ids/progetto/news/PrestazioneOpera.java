package it.unibd.ids.progetto.news;
import it.unibs.ids.progetto.Leaf;

public abstract class PrestazioneOpera {

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
	
	
}
