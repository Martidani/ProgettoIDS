package it.unibs.ids.progetto.news;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InsiemeChiuso implements Serializable{
	
	
	private List<PropostaChiusa> proposteChiuse;


	
	
	public InsiemeChiuso() {
		this.proposteChiuse=new ArrayList<>();
		
	}


	public List<PropostaChiusa> getPropostaChiusa() {
		return proposteChiuse;
	}


	public void addProposteChiuse(PropostaChiusa propostaChiusa) {
		this.proposteChiuse.add(propostaChiusa);
	}
	
	
    public String toString() {
    	StringBuffer str = new StringBuffer();
    	
    	for (PropostaChiusa propostachiusa : proposteChiuse) {
			str.append(propostachiusa.toString()+"\n");
		}
    	
		return str.toString();    	
    }

}
