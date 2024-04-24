package it.unibs.ids.progetto.news;

import it.unibs.ids.progetto.PrestazioneOpera;

public class PropostaAperta extends Proposta {
	
	
	public PropostaAperta(PrestazioneOpera richiesta, PrestazioneOpera offerta,int ID) {
	    super(offerta, offerta, ID);
	  }


	@Override
	public void setStatus() {
		this.status = "Aperta";
		
	}
	
	

}
