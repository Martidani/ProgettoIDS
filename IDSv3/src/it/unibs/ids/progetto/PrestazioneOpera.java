package it.unibs.ids.progetto;

public class PrestazioneOpera {

	private Nodo foglia;
	private int durata;
	
	
	
	
	public PrestazioneOpera(Nodo foglia, int durata) throws NodeNotLeafException {
		if (foglia.isLeaf()) {
			this.foglia = foglia;
			this.durata = durata;
		}else throw new NodeNotLeafException();
		
	}
	public PrestazioneOpera(Nodo foglia) throws NodeNotLeafException {
		if (foglia.isLeaf()) {
			this.foglia = foglia;
		}else throw new NodeNotLeafException();
		
	}
	
	public Nodo getFoglia() {
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
