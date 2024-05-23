package it.unibs.ids.progetto.news;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.unibs.ids.progetto.Comprensorio;

public class InsiemeAperto implements Serializable{
	
	
	private Comprensorio comprensorio;
	private List<PropostaAperta> proposteAperte;
	
	
	public InsiemeAperto(Comprensorio comprensorio) {
		
		this.comprensorio = comprensorio;
		this.proposteAperte = new ArrayList<>();
	}


	public Comprensorio getComprensorio() {
		return comprensorio;
	}


	public List<PropostaAperta> getProposteAperte() {
		return proposteAperte;
	}

	public void addPropostaAperta(PropostaAperta proposta) {
		this.proposteAperte.add(proposta);
	}
	
	public void addProposteAperte(List<PropostaAperta> proposteAperte) {
		for (PropostaAperta propostaAperta : proposteAperte) {
			this.addPropostaAperta(propostaAperta);
		}
	}
	
	
	
	public void eliminaPropostaAperta(PropostaAperta propostaDaEliminare) {
		if (proposteAperte != null) 
			this.proposteAperte.remove(propostaDaEliminare);
		
	}
	
	public boolean contains(PropostaAperta proposta) {
		return proposteAperte.contains(proposta);
	}
	
	public boolean esistePropostaAperta(PropostaAperta propostaApertaInput) {
		
		for (PropostaAperta propostaAperta : proposteAperte) {
			if (propostaAperta.getID() == propostaApertaInput.getID()) {
				return true;
			}
		}
		
		return false;
	}

    public String toString() {
    	StringBuffer str = new StringBuffer();
    	
    	for (PropostaAperta propostaAperta : proposteAperte) {
			str.append(propostaAperta.toString()+"\n");
		}
    	
		return str.toString();    	
    }

}
