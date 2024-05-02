package it.unibs.ids.progetto.news;

import java.io.Serializable;

import it.unibs.ids.progetto.PrestazioneOpera;

public class PropostaRitirata extends Proposta implements Serializable{

private static final long serialVersionUID = 1L;


public PropostaRitirata(PrestazioneOpera richiesta, PrestazioneOpera offerta,int ID) {
        
	 super(richiesta, offerta, ID);
    }


	@Override
	public void setStatus() {
		this.status = "Ritirata";
		
	}
}
