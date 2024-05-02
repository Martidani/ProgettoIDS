package it.unibs.ids.progetto.news;

import java.io.Serializable;

import it.unibs.ids.progetto.PrestazioneOpera;

public class PropostaAperta extends Proposta implements Serializable{
	
	
	private static final long serialVersionUID = 1L;


	public PropostaAperta(PrestazioneOpera richiesta, PrestazioneOpera offerta,int ID) {
	    super(richiesta, offerta, ID);
	  }


	@Override
	public void setStatus() {
		this.status = "Aperta";
		
	}
	
	

}
