package it.unibs.ids.progetto.news;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import it.unibs.ids.progetto.Comprensorio;
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
	public void decrementaNumProposte() {
		this.numeroProposte--;
	}
	
	public List<InsiemeAperto> getInsiemiAperti() {
		return insiemiAperti;
	}
	// Dato il comprensorio mi ritorna l'insieme aperto di appertenenza
	public InsiemeAperto getInsiemeAperto(Comprensorio comprensorio) {
		for (InsiemeAperto insiemeAperto : insiemiAperti) {
			if (insiemeAperto.getComprensorio().equals(comprensorio)) 
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
	
	// Chiude una lista di proposte aperte
	private void chiudi(InsiemeAperto insiemeAperto,List<PropostaAperta> proposteAperte) {
		
		InsiemeChiuso insiemeChiuso = new InsiemeChiuso();
		
		for (PropostaAperta propostaAperta : proposteAperte) {
			PropostaChiusa propostaChiusa = new PropostaChiusa(propostaAperta.getRichiesta(), propostaAperta.getOfferta(), propostaAperta.getID());
			insiemeChiuso.addProposteChiuse(propostaChiusa);
			insiemeAperto.eliminaPropostaAperta(propostaAperta);
		}
		
		if (insiemeAperto.getProposteAperte().isEmpty()) 
				insiemiAperti.remove(insiemeAperto);
		
		
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
            			proposteChiudibili.add(propostaAperta1);
            			proposteChiudibili.add(propostaAperta2);
            			return proposteChiudibili;
            		}
        		}
    		}
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
  
    // Metodo per generare le proposte chiudibili dato un insieme di proposte aperte
    public static List<PropostaAperta> proposteSoddisfacimento1(List<PropostaAperta> proposteAperteRimaste, 
    		PropostaAperta propostaOriginale, PropostaAperta propostaAperta1, List<PropostaAperta> proposteChiudibili) {
       
 
    	proposteAperteRimaste.remove(propostaAperta1);
    	Iterator<PropostaAperta> iterator = proposteAperteRimaste.iterator();
    	
    	while(iterator.hasNext()) {
    		PropostaAperta propostaAperta2 = iterator.next();
    		
    		if (!propostaAperta2.equals(propostaAperta1)) {
        		
        		if (soddisfacimento1(propostaAperta1, propostaAperta2)) {
        			
        			proposteChiudibili.add(propostaAperta2);
        			
        			if (propostaAperta2.equals(propostaOriginale)) {
        				return proposteChiudibili;
        			}
        			else
        				proposteChiudibili = proposteSoddisfacimento1(proposteAperteRimaste, propostaOriginale,  propostaAperta2, proposteChiudibili);
        		}
    		}
 
    	}
		return null;
      
}
    public static List<PropostaAperta> proposteSoddisfacimento2(List<PropostaAperta> proposteAperteRimaste, 
    		PropostaAperta propostaOriginale, PropostaAperta propostaAperta1, List<PropostaAperta> proposteChiudibili) {
       
 
    	proposteAperteRimaste.remove(propostaAperta1);
    	Iterator<PropostaAperta> iterator = proposteAperteRimaste.iterator();
    	
    	while(iterator.hasNext()) {
    		PropostaAperta propostaAperta2 = iterator.next();
    		
    		if (!propostaAperta2.equals(propostaAperta1)) {
        		
        		if (soddisfacimento2(propostaAperta1, propostaAperta2)) {
        			
        			proposteChiudibili.add(propostaAperta2);
        			
        			if (propostaAperta2.equals(propostaOriginale)) {
        				return proposteChiudibili;
        			}
        			else
        				proposteChiudibili = proposteSoddisfacimento2(proposteAperteRimaste, propostaOriginale,  propostaAperta2, proposteChiudibili);
        		}
	
    		}
 
    	}
		return null;
      
}
    // Metodo per generare le proposte chiudibili dato un insieme di proposte aperte
//    public static List<PropostaAperta> proposteSoddisfacimento2(List<PropostaAperta> proposteAperteRimaste, 
//    		PropostaAperta propostaOriginale, PropostaAperta propostaAperta1, List<PropostaAperta> proposteChiudibili) {
//       
//    	proposteAperteRimaste.remove(propostaAperta1);
//    	
////    	List<PropostaAperta> daVisitare = new ArrayList<>();
////    	for (PropostaAperta propostaAperta : proposteAperte) {
////			for (PropostaAperta propostaAperta2 : proposteChiudibili) {
////				if (!propostaAperta.equals(propostaAperta2)) {
////					if (!daVisitare.contains(propostaAperta)) {
////						daVisitare.add(propostaAperta);
////					}
////				}
////			}
////		}
//    	
//    	
//    	if(proposteAperteRimaste != null) {
//    		if (!proposteAperteRimaste.isEmpty()) {
//		    	for (PropostaAperta propostaAperta2 : proposteAperteRimaste) {
//		        	if (!propostaAperta2.equals(propostaAperta1)) {
//		        		
//		        		if (soddisfacimento2(propostaAperta1, propostaAperta2)) {
//		        			proposteChiudibili.add(propostaAperta2);
//		        			
//		        			if (propostaAperta2.equals(propostaOriginale)) 
//		        				return proposteChiudibili;
//		        			else
//		        				proposteChiudibili = proposteSoddisfacimento2(proposteAperteRimaste, propostaOriginale,  propostaAperta2, proposteChiudibili);
//		        		}
//			
//		        	}	
//				}
//    		}
//    	}
//		return null;
//      
//}

  
	    
}
