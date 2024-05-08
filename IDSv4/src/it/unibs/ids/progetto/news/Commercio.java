package it.unibs.ids.progetto.news;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import it.unibs.ids.progetto.Comprensorio;
import it.unibs.ids.progetto.FattoriDiConversione;
import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Nodo;

public class Commercio implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int numeroProposte;
	private List<InsiemeAperto> insiemiAperti;
	private List<InsiemeChiuso> insiemiChiusi;
	private InsiemeRitirato insiemeRitirato;
	
	
	public Commercio() {
		super();
		this.numeroProposte = 0;
		this.insiemiAperti = new ArrayList<>();
		this.insiemiChiusi = new ArrayList<>();
		this.insiemeRitirato = new InsiemeRitirato();
	}
	
	
	public int numProposte() {
		setNumProposte();
		return numeroProposte;
	}
	
	public void setNumProposte() {
		this.numeroProposte++;
	}
	public void decrementaNumProposte() {
		this.numeroProposte--;
	}
	
	public List<InsiemeAperto> getInsiemiAperti() {
		return insiemiAperti;
	}
	// Dato il comprensorio mi ritorna l'insieme aperto di appertenenza
	public InsiemeAperto getInsiemeAperto(Comprensorio comprensorio) {
		for (InsiemeAperto insiemeAperto : insiemiAperti) {
			if (insiemeAperto.getComprensorio().getNome()
					.equals(comprensorio.getNome())) 
				return insiemeAperto;
		}
		InsiemeAperto insiemeAperto = new InsiemeAperto(comprensorio);
		insiemiAperti.add(insiemeAperto);
		return insiemeAperto;
		
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
	
	// Data una proposta mi ritorna l'insieme aperto di appartenenza
	public InsiemeAperto esistePropostaAperta(PropostaAperta propostaApertaInput) {
		
		for (InsiemeAperto insiemeAperto : insiemiAperti) {
			if (insiemeAperto.esistePropostaAperta(propostaApertaInput)) {
				return insiemeAperto;
			}
		}
		
		return null;
	}
	
	public void ritira(PropostaAperta propostaAperta, Fruitore fruitore) {
		InsiemeAperto insiemeAperto = this.getInsiemeAperto(fruitore.getComprensorioAppartenenza());
		for (PropostaAperta propostaAperta2: insiemeAperto.getProposteAperte()) {
			if (propostaAperta2.getFruitore().equals(fruitore)) {
				if (propostaAperta.getID() == propostaAperta2.getID()) {
					PropostaRitirata propostaRitirata = new PropostaRitirata(propostaAperta.getRichiesta(), propostaAperta.getOfferta(),
							propostaAperta.getID(), propostaAperta.getFruitore());
							insiemeAperto.eliminaPropostaAperta(propostaAperta);
							this.insiemeRitirato.addProposteRitirate(propostaRitirata);
				}
				
			}
			
			
		}
	}
	
	// Chiude una lista di proposte aperte
	private void chiudi(InsiemeAperto insiemeAperto,List<PropostaAperta> proposteAperte) {
		
		InsiemeChiuso insiemeChiuso = new InsiemeChiuso();
		
		for (PropostaAperta propostaAperta : proposteAperte) {
			PropostaChiusa propostaChiusa = new PropostaChiusa(propostaAperta.getRichiesta(), propostaAperta.getOfferta(), 
					propostaAperta.getID(),propostaAperta.getFruitore());
			
			insiemeChiuso.addProposteChiuse(propostaChiusa);
			insiemeAperto.eliminaPropostaAperta(propostaAperta);
		}
		
		
		insiemiChiusi.add(insiemeChiuso);

		
		
	}
	
	// L'algoritmo ritorna la lista di proposte chiudibili
	public void metodo(InsiemeAperto insiemeAperto) {
		
	     List<PropostaAperta> listaChiudibili = algoritmo(insiemeAperto);
	     if (listaChiudibili != null)
	         chiudi(insiemeAperto, listaChiudibili);
	    
	}

	// Chiama proposte chiudibili per ogni proposta aperta appartenente all'insieme aperto
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

	/**
	 * 
	 * @param proposteAperte Lista proposte aperte appartenenti allo stesso comprensorio
	 * @param propostaAperta1 i-esima proposta aperta della lista
	 * @return
	 */
//	public List<PropostaAperta> proposteChiudibili(List<PropostaAperta> proposteAperte, PropostaAperta propostaAperta1) {
//	    List<PropostaAperta> proposteChiudibili = new ArrayList<>();
//	    for (PropostaAperta propostaAperta2 : proposteAperte) {
//	        if (!propostaAperta2.equals(propostaAperta1)) {
//	            if (soddisfacimentoTotale(propostaAperta1, propostaAperta2)) {
//	                proposteChiudibili.add(propostaAperta2);
//	                proposteChiudibili.add(propostaAperta1);
//	            } else if (soddisfacimento1(propostaAperta1, propostaAperta2)) {
//	                proposteChiudibili.add(propostaAperta2);
//	            } else if (soddisfacimento2(propostaAperta1, propostaAperta2)) {
//	                proposteChiudibili.add(propostaAperta2);
//	            }
//	        }
//	    }
//	    if (!proposteChiudibili.isEmpty()) {
//	        return proposteChiudibili;
//	    }
//	    return null;
//	}
   
    // Metodo per generare le proposte chiudibili dato un insieme di proposte aperte
    public static List<PropostaAperta> proposteChiudibili(List<PropostaAperta> proposteAperte, PropostaAperta propostaAperta1) {
       
    	List<PropostaAperta> proposteChiudibili = new ArrayList<>(); 

       
    	for (PropostaAperta propostaAperta2 : proposteAperte) {
    		List<PropostaAperta> copia = new ArrayList<>();
    		copia.addAll(proposteAperte);
    		if (!propostaAperta2.equals(propostaAperta1)) {
    			
        		if (soddisfacimentoTotale(propostaAperta1, propostaAperta2)) {
        			proposteChiudibili.add(propostaAperta2);
        			proposteChiudibili.add(propostaAperta1);
        			return proposteChiudibili;
        		} else if (soddisfacimento1(propostaAperta1, propostaAperta2)) {
        			 
        			 proposteChiudibili = proposteSoddisfacimento1(copia,propostaAperta1,
        					 propostaAperta2, new ArrayList<PropostaAperta>());
        			 
        		} else if (soddisfacimento2(propostaAperta1, propostaAperta2)) {
        			 
        			 proposteChiudibili = proposteSoddisfacimento2(copia, propostaAperta1,
        					 propostaAperta2, new ArrayList<PropostaAperta>());
        		}
        		
        		if(proposteChiudibili != null) {
            		if (!proposteChiudibili.isEmpty()) {
            			proposteChiudibili.add(propostaAperta2);
            			return proposteChiudibili;
            		}
        		}
    		}
    	}
		return null;
    }

	private static boolean soddisfacimento1(Proposta propostaA, Proposta propostaB) {
	    return propostaA.getOfferta().getFoglia().getNome().
	    		equals
	    		(propostaB.getRichiesta().getFoglia().getNome()) 
	    		&& 
	    		propostaA.getOfferta().getDurata() 
	    		== 
	    		(propostaB.getRichiesta()).getDurata() ;
	}

	private static boolean soddisfacimento2(Proposta propostaA, Proposta propostaB) {
	    return propostaA.getRichiesta().getFoglia().getNome().
	    		equals
	    		(propostaB.getOfferta().getFoglia().getNome()) 
	    		&& 
	    		propostaA.getRichiesta().getDurata() 
	    		== 
	    		(propostaB.getOfferta()).getDurata() ;
	}

	private static boolean soddisfacimentoTotale(Proposta propostaA, Proposta propostaB) {
	    return soddisfacimento1(propostaA, propostaB) && soddisfacimento2(propostaA, propostaB);
	}
  
    // Metodo per generare le proposte chiudibili dato un insieme di proposte aperte
    public static List<PropostaAperta> proposteSoddisfacimento1(
    		List<PropostaAperta> proposteAperte, PropostaAperta propostaOriginale, 
    		PropostaAperta propostaAperta1, List<PropostaAperta> proposteChiudibili) {

    	proposteAperte.remove(propostaAperta1);
		
		if(proposteAperte != null) {
    		if (!proposteAperte.isEmpty()) {
		    	for (PropostaAperta propostaAperta2 : proposteAperte) {
		        	if (!propostaAperta2.equals(propostaAperta1)) {
		        		
		        		if (soddisfacimento1(propostaAperta1, propostaAperta2)) {
		        			proposteChiudibili.add(propostaAperta2);
		        			
		        			if (propostaAperta2.equals(propostaOriginale)) 
		        				return proposteChiudibili;
		        			else {
		        		    	List<PropostaAperta> proposteAperteCopia = new ArrayList<PropostaAperta>();
		        		    	proposteAperteCopia.addAll(proposteAperte);
		        		    	return proposteChiudibili = proposteSoddisfacimento1(proposteAperteCopia, propostaOriginale,  
		        						propostaAperta2, proposteChiudibili);
		        			}		
		        		}
		        	}	
				}
    		}
    	}
		return null;   
}

  
    // Metodo per generare le proposte chiudibili dato un insieme di proposte aperte
    public static List<PropostaAperta> proposteSoddisfacimento2(
    		List<PropostaAperta> proposteAperte, PropostaAperta propostaOriginale, 
    		PropostaAperta propostaAperta1, List<PropostaAperta> proposteChiudibili) {

    	proposteAperte.remove(propostaAperta1);
		
		if(proposteAperte != null) {
    		if (!proposteAperte.isEmpty()) {
		    	for (PropostaAperta propostaAperta2 : proposteAperte) {
		        	if (!propostaAperta2.equals(propostaAperta1)) {
		        		
		        		if (soddisfacimento2(propostaAperta1, propostaAperta2)) {
		        			proposteChiudibili.add(propostaAperta2);
		        			
		        			if (propostaAperta2.equals(propostaOriginale)) 
		        				return proposteChiudibili;
		        			else {
		        		    	List<PropostaAperta> proposteAperteCopia = new ArrayList<PropostaAperta>();
		        		    	proposteAperteCopia.addAll(proposteAperte);
		        		    	return proposteChiudibili = proposteSoddisfacimento2(proposteAperteCopia, propostaOriginale,  
		        						propostaAperta2, proposteChiudibili);
		        			}		
		        		}
		        	}	
				}
    		}
    	}
		return null;   
}
    
    
    public String visualizzaProposteChiuse(Fruitore fruitore){
    	
    	StringBuffer str = new StringBuffer();
    	
    	for (InsiemeChiuso insiemeChiuso : insiemiChiusi) {
			for (PropostaChiusa propostaChiusa : insiemeChiuso.getProposteChiuse()) {
				if (propostaChiusa.getFruitore().equals(fruitore)) {
					str.append(propostaChiusa.toString());
				}
			}
		}
    	
    	return str.toString();
  
    }
    
    public String visualizzaProposteRitirate(Fruitore fruitore){
    	
    	StringBuffer str = new StringBuffer();
    	
    	
    	for (PropostaRitirata propostaRitirata : insiemeRitirato.getProposteRitirate()) {
    		if (propostaRitirata.getFruitore().equals(fruitore)) {
				str.append(propostaRitirata.toString());
			}
		}
    	
    	
    	return str.toString();
  
    }
    
    
    public String visualizzaProposteAperte(Fruitore fruitore){
    	
    	StringBuffer str = new StringBuffer();
    	
    	InsiemeAperto insiemeAperto = this.getInsiemeAperto(fruitore.getComprensorioAppartenenza());
    	
    	for (PropostaAperta propostaAperta : insiemeAperto.getProposteAperte()) {
			if (propostaAperta.getFruitore().equals(fruitore)) {
				str.append(propostaAperta.toString());
			}
		}
    	
    	
    	return str.toString();
  
    	
    }
    

    public String visualizzaProposteChiuse(Nodo nodo){
    	
    	StringBuffer str = new StringBuffer();
    	
    	for (InsiemeChiuso insiemeChiuso : insiemiChiusi) {
			for (PropostaChiusa propostaChiusa : insiemeChiuso.getProposteChiuse()) {
				if (propostaChiusa.getOfferta().getFoglia().equals(nodo) ||
	    				propostaChiusa.getRichiesta().getFoglia().equals(nodo)) {
	    			str.append(propostaChiusa.toString());
	    			
	    		}
			}
		}
    	
    	return str.toString();
  
    }
    
    public String visualizzaProposteRitirate(Nodo nodo){
    	
    	StringBuffer str = new StringBuffer();
    	
    	
    	for (PropostaRitirata propostaRitirata : insiemeRitirato.getProposteRitirate()) {
    		if (propostaRitirata.getOfferta().getFoglia().equals(nodo) ||
    				propostaRitirata.getRichiesta().getFoglia().equals(nodo)) {
    			str.append(propostaRitirata.toString());
    			
    		}
		}
    	
    	
    	
    	
    	return str.toString();
  
    }
    
    public String visualizzaProposteAperte(Nodo nodo){
    	
    	StringBuffer str = new StringBuffer();
    	
    	for (InsiemeAperto insiemeAperto : insiemiAperti) {
			for (PropostaAperta propostaAperta : insiemeAperto.getProposteAperte()) {
				if (propostaAperta.getOfferta().getFoglia().equals(nodo) ||
						propostaAperta.getRichiesta().getFoglia().equals(nodo)) {
					str.append(propostaAperta.toString());
					
				}
			}
		}
    	
    	return str.toString();
  
    	
    }
	    
}
