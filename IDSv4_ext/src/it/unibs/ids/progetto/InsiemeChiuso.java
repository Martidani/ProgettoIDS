package it.unibs.ids.progetto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InsiemeChiuso implements Serializable{
	
	
	private List<PropostaChiusa> proposteChiuse;


	
	
	public InsiemeChiuso() {
		this.proposteChiuse=new ArrayList<>();
		
	}


	public List<PropostaChiusa> getProposteChiuse() {
		return proposteChiuse;
	}


	public void addProposteChiuse(PropostaChiusa propostaChiusa) {
		this.proposteChiuse.add(propostaChiusa);
	}
	
	
    

}
