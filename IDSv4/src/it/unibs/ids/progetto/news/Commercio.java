package it.unibs.ids.progetto.news;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Commercio implements Serializable {
	
	private int numeroProposte;
	private List<InsiemeAperto> insiemiAperti;
	private List<InsiemeChiuso> insiemiChiusi;
	
	
	public Commercio() {
		super();
		this.numeroProposte = 0;
		this.insiemiAperti = new ArrayList<>();
		this.insiemiChiusi = new ArrayList<>();
	}
	
	
	public int numProposte() {
		setNumProposte();
		return numeroProposte;
	}
	
	public void setNumProposte() {
		this.numeroProposte=this.numeroProposte + 1;
	}
	
	public List<InsiemeAperto> getInsiemiAperti() {
		return insiemiAperti;
	}
	public void addInsiemiAperti(InsiemeAperto insiemeAperto) {
		this.insiemiAperti.add(insiemeAperto);
	}
	public List<InsiemeChiuso> getInsiemiChiusi() {
		return insiemiChiusi;
	}
	public void addInsiemiChiusi(InsiemeChiuso insiemeChiuso) {
		this.insiemiChiusi.add(insiemeChiuso);
	}
	
	public InsiemeAperto esistePropostaAperta(PropostaAperta propostaApertaInput) {
		
		for (InsiemeAperto insiemeAperto : insiemiAperti) {
			if (insiemeAperto.esistePropostaAperta(propostaApertaInput)) {
				return insiemeAperto;
			}
		}
		
		return null;
	}
	
	public InsiemeChiuso chiudi(InsiemeAperto insiemeAperto) {
		
		InsiemeChiuso insiemeChiuso = new InsiemeChiuso();
		
		for (PropostaAperta propostaAperta : insiemeAperto.getProposteAperte()) {
			PropostaChiusa propostaChiusa = new PropostaChiusa(propostaAperta.getRichiesta(), propostaAperta.getOfferta(), propostaAperta.getID());
			insiemeChiuso.addProposteChiuse(propostaChiusa);
			insiemeAperto.eliminaPropostaAperta(propostaAperta);
		}
		
		if (insiemeAperto == null) 
			insiemiAperti.remove(insiemeAperto);
		
		
		insiemiChiusi.add(insiemeChiuso);
		return insiemeChiuso;
		
		
	}
	

}
