package it.unibs.ids.progetto.news;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.unibs.ids.progetto.FattoriDiConversione;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Nodo;

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
	
	public InsiemeChiuso chiudi(InsiemeAperto insiemeAperto,ArrayList<PropostaAperta> proposteAperte) {
		
		InsiemeChiuso insiemeChiuso = new InsiemeChiuso();
		
		for (PropostaAperta propostaAperta : proposteAperte) {
			PropostaChiusa propostaChiusa = new PropostaChiusa(propostaAperta.getRichiesta(), propostaAperta.getOfferta(), propostaAperta.getID());
			insiemeChiuso.addProposteChiuse(propostaChiusa);
			insiemeAperto.eliminaPropostaAperta(propostaAperta);
		}
		
		if (insiemeAperto == null) 
			insiemiAperti.remove(insiemeAperto);
		
		
		insiemiChiusi.add(insiemeChiuso);
		return insiemeChiuso;
		
		
	}
	
	public void metodo() {
		for (InsiemeAperto insiemeAperto : insiemiAperti) {
			ArrayList<PropostaAperta> listaChiudibili =  algoritmo(insiemeAperto);
			chiudi(insiemeAperto, listaChiudibili);
		}
	}
	
	
	
	public ArrayList<PropostaAperta> algoritmo(InsiemeAperto insiemeAperto){
		
		
		
		return null;
		
	}

	
	
	  public ArrayList<PropostaAperta> addTransitivoFattoreConversione(InsiemeAperto insiemeAperto) {
	        
		  
	        for (PropostaAperta propostaAperta1 : insiemeAperto.getProposteAperte()) {
	            for (PropostaAperta propostaAperta2 : insiemeAperto.getProposteAperte()) {
	            	   
	      		  ArrayList<PropostaAperta> cammino = new ArrayList<>();
	      		  ArrayList<PropostaAperta> visitati = new ArrayList<>();
	      		  
	                if (!propostaAperta1.equals(propostaAperta2)) {
	                	visitati.add(propostaAperta1);
	                	visitati.add(propostaAperta2);
	                	cammino = calcTransitivo(insiemeAperto,propostaAperta1,propostaAperta2, visitati,cammino);
	                    if (cammino != null) {
	                    	cammino.add(propostaAperta1);
	                    	return cammino;
	                    }
	                }
	            }
	        }
	    }

	    /**
	     * Metodo per calcolare il fattore di conversione transitivo tra due nodi.
	     * 
	     * @param nodo1 Il primo nodo
	     * @param nodo2 Il secondo nodo
	     * @param visitati Lista dei nodi visitati durante il calcolo
	     * @return Il fattore di conversione transitivo tra i due nodi, null se non è possibile calcolarlo
	     */
	    private ArrayList<PropostaAperta> calcTransitivo(InsiemeAperto insiemeAperto, PropostaAperta propostaAperta1, PropostaAperta propostaAperta2, 
	    		ArrayList<PropostaAperta> visitati,ArrayList<PropostaAperta> cammino) {
	    	
	    	
	    	
	        if (propostaAperta1.getRichiesta().equals(propostaAperta2.getOfferta())) {
	        	   if (propostaAperta2.getRichiesta().equals(propostaAperta1.getOfferta())) {
	        		   if (propostaAperta1.getRichiesta().getDurata() == propostaAperta2.getRichiesta().getDurata()) {
	        			   cammino.add(propostaAperta2);
	        			   return cammino;
	        		   }
	        	   }
	           
	        } else {
	            for (PropostaAperta propostaAperta3 : insiemeAperto.getProposteAperte() ) {
	 
	                if (!visitati.contains(propostaAperta3)) {
	                    visitati.add(propostaAperta3);
	                    cammino.add(propostaAperta3);
	                    if (calcTransitivo(insiemeAperto,propostaAperta2, propostaAperta3, visitati,cammino) == null) {
	                        return null;
	                    }
	                }
	            }
	        }
	       
	    }
}
