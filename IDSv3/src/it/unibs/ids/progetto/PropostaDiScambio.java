package it.unibs.ids.progetto;

public class PropostaDiScambio {
	
	private boolean status; // false proposta aperta, true proposta chiusa
	private PrestazioneOpera richiesta;
	private PrestazioneOpera offerta;
	
	
	
	public PropostaDiScambio(PrestazioneOpera richiesta, PrestazioneOpera offerta) {
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
		Nodo foglia1 = this.richiesta.getFoglia();
		Nodo foglia2 = this.offerta.getFoglia();
		
		double fattore = foglia1.fattoreFoglia(foglia2);
		
		durata =  (int) (fattore * durata);
		
		this.offerta.setDurata(durata);
	}
	
	
	
	
	
	
	

}
