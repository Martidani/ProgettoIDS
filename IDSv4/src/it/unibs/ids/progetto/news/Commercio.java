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
	
	public InsiemeChiuso chiudi(InsiemeAperto insiemeAperto,List<PropostaAperta> proposteAperte) {
		
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
			List<PropostaAperta> listaChiudibili =  algoritmo(insiemeAperto);
			chiudi(insiemeAperto, listaChiudibili);
		}
	}
	
	
	
	public List<PropostaAperta> algoritmo(InsiemeAperto insiemeAperto){
		
		InsiemeAperto insiemeApertoN = insiemeAperto;
		List<PropostaAperta> lista;
		
		for (PropostaAperta propostaAperta : insiemeApertoN.getProposteAperte()) {
			lista = proposteChiudibili(insiemeAperto.getProposteAperte(), propostaAperta);
			
			if (lista != null) {
				return lista;
			}
			for (PropostaAperta proposta : lista) {
				if (insiemeApertoN.contains(proposta))
					insiemeApertoN.eliminaPropostaAperta(proposta);
			}
			
		}
		
		return null;
		
	}

	
	// Metodo per verificare se una proposta può soddisfare un'altra
    private static boolean soddisfacimento1(Proposta propostaA, Proposta propostaB) {
        return ((propostaA.getOfferta().equals(propostaB.getRichiesta())
        		&& (propostaA.getOfferta().getDurata()) == (propostaB.getRichiesta().getDurata())))
        		;
    }
    
	// Metodo per verificare se una proposta può soddisfare un'altra
    private static boolean soddisfacimento2(Proposta propostaA, Proposta propostaB) {
        return ( ((propostaA.getRichiesta().equals(propostaB.getOfferta())))
        		&& (propostaA.getRichiesta().getDurata()) == (propostaB.getOfferta().getDurata()))
        		;
    }
    
	// Metodo per verificare se una proposta può soddisfare un'altra
    private static boolean soddisfacimentoTotale(Proposta propostaA, Proposta propostaB) {
        return (propostaA.getOfferta().equals(propostaB.getRichiesta())
        		&& (propostaA.getRichiesta().equals(propostaB.getOfferta()))
        		&& (propostaA.getRichiesta().getDurata()) == (propostaB.getOfferta().getDurata()));
    }

    
    // Metodo per generare le proposte chiudibili dato un insieme di proposte aperte
    public static List<PropostaAperta> proposteChiudibili(List<PropostaAperta> proposteAperte, PropostaAperta propostaAperta1) {
       
    	List<PropostaAperta> proposteChiudibili = new ArrayList<>(); 

       
    	for (PropostaAperta propostaAperta2 : proposteAperte) {
    		if (!propostaAperta2.equals(propostaAperta1)) {
    			
        		if (soddisfacimentoTotale(propostaAperta1, propostaAperta1)) {
        			proposteChiudibili.add(propostaAperta2);
        			proposteChiudibili.add(propostaAperta1);
        			return proposteChiudibili;
        		} else if (soddisfacimento1(propostaAperta1, propostaAperta2)) {
        			 proposteAperte.remove(propostaAperta2);
        			 proposteChiudibili = proposteSoddisfacimento1(proposteAperte,propostaAperta1,  propostaAperta2, new ArrayList<PropostaAperta>());
        		} else if (soddisfacimento2(propostaAperta1, propostaAperta2)) {
        			 proposteAperte.remove(propostaAperta2);
        			 proposteChiudibili = proposteSoddisfacimento2(proposteAperte, propostaAperta1, propostaAperta2, new ArrayList<PropostaAperta>());
        		}
        		
        		if (proposteChiudibili != null) {
        			proposteChiudibili.add(propostaAperta2);
        			return proposteChiudibili;
        		}
    		}

        	

		}
    	
    	
        return null;
        
}

    
    // Metodo per generare le proposte chiudibili dato un insieme di proposte aperte
    public static List<PropostaAperta> proposteSoddisfacimento1(List<PropostaAperta> proposteAperte, 
    		PropostaAperta propostaOriginale, PropostaAperta propostaAperta1, List<PropostaAperta> proposteChiudibili) {
       
    	for (PropostaAperta propostaAperta2 : proposteAperte) {
        	if (!propostaAperta2.equals(propostaAperta1)) {
        		
        		if (soddisfacimento1(propostaAperta1, propostaAperta2)) {
        			proposteChiudibili.add(propostaAperta2);
        			proposteAperte.remove(propostaAperta2);
        			
        			if (propostaAperta2.equals(propostaOriginale)) 
        				return proposteChiudibili;
        			else
        				proposteChiudibili = proposteSoddisfacimento1(proposteAperte, propostaOriginale,  propostaAperta2, proposteChiudibili);
        		}
	
        	}	
		}
		return null;
      
}
	
    // Metodo per generare le proposte chiudibili dato un insieme di proposte aperte
    public static List<PropostaAperta> proposteSoddisfacimento2(List<PropostaAperta> proposteAperte, 
    		PropostaAperta propostaOriginale, PropostaAperta propostaAperta1, List<PropostaAperta> proposteChiudibili) {
       
    	for (PropostaAperta propostaAperta2 : proposteAperte) {
        	if (!propostaAperta2.equals(propostaAperta1)) {
        		
        		if (soddisfacimento2(propostaAperta1, propostaAperta2)) {
        			proposteChiudibili.add(propostaAperta2);
        			proposteAperte.remove(propostaAperta2);
        			
        			if (propostaAperta2.equals(propostaOriginale)) 
        				return proposteChiudibili;
        			else
        				proposteChiudibili = proposteSoddisfacimento1(proposteAperte, propostaOriginale,  propostaAperta2, proposteChiudibili);
        		}
	
        	}	
		}
		return null;
      
}
	
	
	
	
    
	    
	    
}
