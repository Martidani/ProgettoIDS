package it.unibs.ids.progetto;
import java.io.Serializable;

public class Richiesta extends PrestazioneOpera implements Serializable{

	public Richiesta(Leaf foglia, int durata) {
		this.foglia = foglia;
		this.durata = durata;
		
	}
}
