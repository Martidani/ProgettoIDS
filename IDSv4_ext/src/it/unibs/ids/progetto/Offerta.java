package it.unibs.ids.progetto;
import java.io.Serializable;

public class Offerta extends PrestazioneOpera implements Serializable{

	public Offerta(Leaf foglia) {
			this.foglia = foglia;
	}
}
