package it.unibs.ids.progetto.news;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.unibs.ids.progetto.FattoriDiConversione;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Nodo;

public class Commercio implements Serializable {
	
	private static final long serialVersionUID = 1L;
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
		this.numeroProposte++;
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
	
	private InsiemeChiuso chiudi(InsiemeAperto insiemeAperto,List<PropostaAperta> proposteAperte) {
		
		InsiemeChiuso insiemeChiuso = new InsiemeChiuso();
		
		for (PropostaAperta propostaAperta : proposteAperte) {
			PropostaChiusa propostaChiusa = new PropostaChiusa(propostaAperta.getRichiesta(), propostaAperta.getOfferta(), propostaAperta.getID());
			insiemeChiuso.addProposteChiuse(propostaChiusa);
			insiemeAperto.eliminaPropostaAperta(propostaAperta);
		}
		
		if (insiemeAperto.getProposteAperte().isEmpty()) 
				insiemiAperti.remove(insiemeAperto);
		
		
		insiemiChiusi.add(insiemeChiuso);
		return insiemeChiuso;
		
		
	}
	
	public void metodo() {
	    for (InsiemeAperto insiemeAperto : insiemiAperti) {
	        List<PropostaAperta> listaChiudibili = algoritmo(insiemeAperto);
	        if (listaChiudibili != null)
	            chiudi(insiemeAperto, listaChiudibili);
	    }
	}

	public List<PropostaAperta> algoritmo(InsiemeAperto insiemeAperto) {
	    List<PropostaAperta> lista;
	    for (PropostaAperta propostaAperta : insiemeAperto.getProposteAperte()) {
	        lista = proposteChiudibili(insiemeAperto.getProposteAperte(), propostaAperta);
	        if (lista != null && !lista.isEmpty()) {
	            return lista;
	        }
	    }
	    return null;
	}

	public List<PropostaAperta> proposteChiudibili(List<PropostaAperta> proposteAperte, PropostaAperta propostaAperta1) {
	    List<PropostaAperta> proposteChiudibili = new ArrayList<>();
	    for (PropostaAperta propostaAperta2 : proposteAperte) {
	        if (!propostaAperta2.equals(propostaAperta1)) {
	            if (soddisfacimentoTotale(propostaAperta1, propostaAperta2)) {
	                proposteChiudibili.add(propostaAperta2);
	                proposteChiudibili.add(propostaAperta1);
	            } else if (soddisfacimento1(propostaAperta1, propostaAperta2)) {
	                proposteChiudibili.add(propostaAperta2);
	            } else if (soddisfacimento2(propostaAperta1, propostaAperta2)) {
	                proposteChiudibili.add(propostaAperta2);
	            }
	        }
	    }
	    if (!proposteChiudibili.isEmpty()) {
	        return proposteChiudibili;
	    }
	    return null;
	}


	private static boolean soddisfacimento1(Proposta propostaA, Proposta propostaB) {
	    return propostaA.getOfferta().getFoglia().equals(propostaB.getRichiesta().getFoglia()) 
	    		&& propostaA.getOfferta().getDurata() == (propostaB.getRichiesta()).getDurata() ;
	}

	private static boolean soddisfacimento2(Proposta propostaA, Proposta propostaB) {
	    return propostaA.getRichiesta().getFoglia().equals(propostaB.getOfferta().getFoglia()) 
	    		&& propostaA.getRichiesta().getDurata() == (propostaB.getOfferta()).getDurata() ;
	}

	private static boolean soddisfacimentoTotale(Proposta propostaA, Proposta propostaB) {
	    return soddisfacimento1(propostaA, propostaB) && soddisfacimento2(propostaA, propostaB);
	}



	
	
	
	
    
	    
	    
}
