package it.unibs.ids.progetto.news;

import java.util.ArrayList;
import java.util.List;

public class InsiemeRitirato {


	private List<PropostaRitirata> proposteRitirate;


	
	
	public InsiemeRitirato() {
		this.proposteRitirate=new ArrayList<>();
		
	}


	public List<PropostaRitirata> getProposteRitirate() {
		return proposteRitirate;
	}


	public void addProposteRitirate(PropostaRitirata propostaRitirata) {
		this.proposteRitirate.add(propostaRitirata);
	}
	
	
    public String toString() {
    	StringBuffer str = new StringBuffer();
    	
    	for (PropostaRitirata propostaRitirata : proposteRitirate) {
			str.append(propostaRitirata.toString()+"\n");
		}
    	
		return str.toString();    	
    }
}
