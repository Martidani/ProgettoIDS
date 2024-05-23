package it.unibs.ids.progetto.news;

import java.io.Serializable;

import it.unibs.ids.progetto.Fruitore;


public class PropostaAperta extends Proposta implements Serializable{
	
	
	private static final long serialVersionUID = 1L;


	public PropostaAperta(Richiesta richiesta, Offerta offerta,int ID,Fruitore fruitore) {
	    super(richiesta, offerta, ID,fruitore);
	  }


	@Override
	public void setStatus() {
		this.status = "Aperta";
		
	}
	
	

}
