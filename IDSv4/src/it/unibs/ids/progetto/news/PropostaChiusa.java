package it.unibs.ids.progetto.news;

import it.unibs.ids.progetto.PrestazioneOpera;

public class PropostaChiusa extends Proposta {

	public PropostaChiusa(PrestazioneOpera richiesta, PrestazioneOpera offerta,int ID) {
        
		 super(offerta, offerta, ID);
    }


	@Override
	public void setStatus() {
		this.status = "Chiusa";
		
	}

}
