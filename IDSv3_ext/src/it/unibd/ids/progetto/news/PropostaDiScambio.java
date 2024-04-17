package it.unibd.ids.progetto.news;
import it.unibs.ids.progetto.Leaf;


public class PropostaDiScambio {
	
	// false = proposta aperta
	// true = proposta chiusa
	private boolean status; 
	private Richiesta richiesta;
	private Offerta offerta;
	
	public PropostaDiScambio(Richiesta richiesta, Offerta offerta) {
		this.status = false;
		this.richiesta = richiesta;
		this.offerta = offerta;

		setOfferta();
	}
	
	public boolean getStatus() {
		return status;
	}
	public void changeStatus() {
		this.status = true;
	}
	public PrestazioneOpera getRichiesta() {
		return richiesta;
	}
	public PrestazioneOpera getOfferta() {
		return offerta;
	}
	public void setOfferta() {
		
		int durata = this.richiesta.getDurata();
		Leaf foglia1 = this.richiesta.getFoglia();
		Leaf foglia2 = this.offerta.getFoglia();
	
		double fattore = foglia1.fattoreFoglia(foglia2);
		durata =  (int) (fattore * durata);
		
		offerta.setDurata(durata);
	}

}
