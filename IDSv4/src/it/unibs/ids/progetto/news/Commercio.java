package it.unibs.ids.progetto.news;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import it.unibs.ids.progetto.Comprensorio;
import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.Nodo;

/**
 * La classe Commercio gestisce le operazioni di commercio all'interno del sistema.
 * È responsabile della gestione delle proposte aperte, chiuse e ritirate.
 */
public class Commercio implements Serializable {
    
    private int numeroProposte; // Numero totale delle proposte presenti
    private List<InsiemeAperto> insiemiAperti; // Lista degli insiemi aperti
    private Fruitore utenteDiSessione; // Utente di sessione attuale
    
    private List<InsiemeChiuso> insiemiChiusi; // Lista degli insiemi chiusi
    private InsiemeRitirato insiemeRitirato; // Insieme delle proposte ritirate
    
    /**
     * Costruttore predefinito che inizializza le liste di proposte aperte e chiuse.
     */
    public Commercio() {
        super();
        this.numeroProposte = 0;
        this.insiemiAperti = new ArrayList<>();
        this.insiemiChiusi = new ArrayList<>();
        this.insiemeRitirato = new InsiemeRitirato();
    }
    
    /**
     * Imposta l'utente di sessione attuale.
     * @param utenteDiSessione L'utente di sessione da impostare.
     */
    public void setUtenteDiSessione(Fruitore utenteDiSessione) {
        this.utenteDiSessione = utenteDiSessione;
    }
    
    /**
     * Restituisce l'insieme aperto di sessione corrente.
     * @return L'insieme aperto di sessione corrente.
     */
    public InsiemeAperto getInsiemeApertoDiSessione() {
        return getInsiemeAperto(utenteDiSessione.getComprensorioAppartenenza());
    }

    /**
     * Restituisce il numero totale di proposte presenti.
     * @return Il numero totale di proposte.
     */
    public int numProposte() {
        setNumProposte();
        return numeroProposte;
    }
    
    /**
     * Incrementa il numero totale di proposte.
     */
    public void setNumProposte() {
        this.numeroProposte++;
    }
    
    /**
     * Decrementa il numero totale di proposte.
     */
    public void decrementaNumProposte() {
        this.numeroProposte--;
    }
    
    /**
     * Restituisce la lista degli insiemi aperti.
     * @return La lista degli insiemi aperti.
     */
    public List<InsiemeAperto> getInsiemiAperti() {
        return insiemiAperti;
    }
    
    /**
     * Restituisce l'insieme aperto di un determinato comprensorio, se esiste.
     * Se l'insieme aperto non esiste, ne crea uno nuovo e lo restituisce.
     * @param comprensorio Il comprensorio di cui si vuole ottenere l'insieme aperto.
     * @return L'insieme aperto del comprensorio specificato.
     */
    public InsiemeAperto getInsiemeAperto(Comprensorio comprensorio) {
        for (InsiemeAperto insiemeAperto : insiemiAperti) {
            if (insiemeAperto.getComprensorio().getNome().equals(comprensorio.getNome())) {
                return insiemeAperto;
            }
        }
        InsiemeAperto nuovoInsiemeAperto = new InsiemeAperto(comprensorio);
        insiemiAperti.add(nuovoInsiemeAperto);
        return nuovoInsiemeAperto;
    }

    /**
     * Aggiunge un insieme aperto alla lista degli insiemi aperti.
     * @param insiemeAperto L'insieme aperto da aggiungere.
     */
    public void addInsiemiAperti(InsiemeAperto insiemeAperto) {
        this.insiemiAperti.add(insiemeAperto);
    }
    
    /**
     * Restituisce la lista degli insiemi chiusi.
     * @return La lista degli insiemi chiusi.
     */
    public List<InsiemeChiuso> getInsiemiChiusi() {
        return insiemiChiusi;
    }
    
    /**
     * Aggiunge un insieme chiuso alla lista degli insiemi chiusi.
     * @param insiemeChiuso L'insieme chiuso da aggiungere.
     */
    public void addInsiemiChiusi(InsiemeChiuso insiemeChiuso) {
        this.insiemiChiusi.add(insiemeChiuso);
    }
    


	
    /**
     * Cerca un insieme aperto di appartenenza per una data proposta aperta.
     * @param propostaApertaInput La proposta aperta di cui cercare l'insieme aperto di appartenenza.
     * @return L'insieme aperto di appartenenza della proposta aperta, o null se non trovato.
     */
    public InsiemeAperto esistePropostaAperta(PropostaAperta propostaApertaInput) {
		
		for (InsiemeAperto insiemeAperto : insiemiAperti) {
			if (insiemeAperto.esistePropostaAperta(propostaApertaInput)) {
				return insiemeAperto;
			}
		}
		
		return null;
	}
	
    /**
     * Ritira una proposta aperta.
     * @param propostaAperta La proposta aperta da ritirare.
     */
	public void ritira(PropostaAperta propostaAperta) {
		InsiemeAperto insiemeAperto = this.getInsiemeAperto(utenteDiSessione.getComprensorioAppartenenza());
		InsiemeAperto insiemeApertoCopia = new InsiemeAperto(utenteDiSessione.getComprensorioAppartenenza());
		insiemeApertoCopia.addProposteAperte(insiemeAperto.getProposteAperte());
		
		for (PropostaAperta propostaAperta2: insiemeApertoCopia.getProposteAperte()) {
			if (propostaAperta2.getFruitore().getID().equals(utenteDiSessione.getID())) {
				if (propostaAperta.getID() == propostaAperta2.getID()) {
					PropostaRitirata propostaRitirata = 
							new PropostaRitirata(propostaAperta.getRichiesta(), propostaAperta.getOfferta(),
							propostaAperta.getID(), propostaAperta.getFruitore());
							insiemeAperto.eliminaPropostaAperta(propostaAperta);
							this.insiemeRitirato.addPropostaRitirata(propostaRitirata);
				}
				
			}
			
			
		}
	}
	
	/**
	 * Cerca una proposta aperta dato il suo ID.
	 * @param ID L'ID della proposta aperta da cercare.
	 * @return La proposta aperta corrispondente all'ID, o null se non trovata.
	 */
	public PropostaAperta cercaProposta (int ID) {
		for (PropostaAperta propostaAperta : getInsiemeApertoDiSessione().getProposteAperte()) {
			if (propostaAperta.getID() == ID)
				return propostaAperta;		
		}
		return null;
	}
	
	
	/**
	 * Chiude una lista di proposte aperte e le trasferisce agli insiemi chiusi.
	 * @param insiemeAperto L'insieme aperto di appartenenza delle proposte da chiudere.
	 * @param proposteAperte La lista delle proposte aperte da chiudere.
	 */	
	private void chiudi(InsiemeAperto insiemeAperto,List<PropostaAperta> proposteAperte) {
		
		InsiemeChiuso insiemeChiuso = new InsiemeChiuso();
		
		for (PropostaAperta propostaAperta : proposteAperte) {
			PropostaChiusa propostaChiusa = new PropostaChiusa(propostaAperta.getRichiesta(), propostaAperta.getOfferta(), 
					propostaAperta.getID(),propostaAperta.getFruitore());
			
			insiemeChiuso.addPropostaChiusa(propostaChiusa);
			insiemeAperto.eliminaPropostaAperta(propostaAperta);
		}
		
		
		insiemiChiusi.add(insiemeChiuso);

		
		
	}
	
	/**
	 * Cerca e chiude le proposte aperte chiudibili in un insieme aperto.
	 * @param insiemeAperto L'insieme aperto in cui cercare le proposte chiudibili.
	 */
	public void cercaProposteChiudibili(InsiemeAperto insiemeAperto) {
		
	     List<PropostaAperta> listaChiudibili = algoritmo(insiemeAperto);
	     if (listaChiudibili != null)
	         chiudi(insiemeAperto, listaChiudibili);
	    
	}

	/**
	 * Algoritmo per trovare le proposte chiudibili in un insieme aperto.
	 * @param insiemeAperto L'insieme aperto in cui cercare le proposte chiudibili.
	 * @return La lista delle proposte chiudibili, o null se non trovate.
	 */
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
	 * Genera le proposte chiudibili dato un insieme di proposte aperte e una proposta aperta.
	 * @param proposteAperte La lista delle proposte aperte.
	 * @param propostaAperta1 La proposta aperta di riferimento.
	 * @return La lista delle proposte chiudibili, o null se non trovate.
	 */
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

	/**
	 * Verifica se una proposta A soddisfa la richiesta di una proposta B (soddisfacimento1).
	 * @param propostaA La proposta A.
	 * @param propostaB La proposta B.
	 * @return true se la proposta A soddisfa la richiesta della proposta B, altrimenti false.
	 */
	private static boolean soddisfacimento1(Proposta propostaA, Proposta propostaB) {
	    return propostaA.getOfferta().getFoglia().getNome().
	    		equals
	    		(propostaB.getRichiesta().getFoglia().getNome()) 
	    		&& 
	    		propostaA.getOfferta().getDurata() 
	    		== 
	    		(propostaB.getRichiesta()).getDurata() ;
	}

	/**
	 * Verifica se una proposta A soddisfa l'offerta di una proposta B (soddisfacimento2).
	 * @param propostaA La proposta A.
	 * @param propostaB La proposta B.
	 * @return true se la proposta A soddisfa l'offerta della proposta B, altrimenti false.
	 */
	private static boolean soddisfacimento2(Proposta propostaA, Proposta propostaB) {
	    return propostaA.getRichiesta().getFoglia().getNome().
	    		equals
	    		(propostaB.getOfferta().getFoglia().getNome()) 
	    		&& 
	    		propostaA.getRichiesta().getDurata() 
	    		== 
	    		(propostaB.getOfferta()).getDurata() ;
	}

	/**
	 * Verifica se una proposta A soddisfa completamente una proposta B (soddisfacimentoTotale).
	 * @param propostaA La proposta A.
	 * @param propostaB La proposta B.
	 * @return true se la proposta A soddisfa completamente la proposta B, altrimenti false.
	 */
	private static boolean soddisfacimentoTotale(Proposta propostaA, Proposta propostaB) {
	    return soddisfacimento1(propostaA, propostaB) && soddisfacimento2(propostaA, propostaB);
	}
  

	/**
	 * Genera le proposte chiudibili soddisfacendo la condizione 1.
	 * @param proposteAperte La lista delle proposte aperte.
	 * @param propostaOriginale La proposta aperta originale.
	 * @param propostaAperta1 La proposta aperta di riferimento.
	 * @param proposteChiudibili La lista delle proposte chiudibili.
	 * @return La lista delle proposte chiudibili, o null se non trovate.
	 */
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

  
	/**
	 * Genera le proposte chiudibili soddisfacendo la condizione 2.
	 * @param proposteAperte La lista delle proposte aperte.
	 * @param propostaOriginale La proposta aperta originale.
	 * @param propostaAperta1 La proposta aperta di riferimento.
	 * @param proposteChiudibili La lista delle proposte chiudibili.
	 * @return La lista delle proposte chiudibili, o null se non trovate.
	 */
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
    
    
	/**
	 * Visualizza le proposte chiuse dell'utente di sessione.
	 * @return Una stringa contenente le proposte chiuse dell'utente di sessione.
	 */
    public String visualizzaProposteChiuse(){
    	
    	StringBuffer str = new StringBuffer();
    	
    	for (InsiemeChiuso insiemeChiuso : insiemiChiusi) {
			for (PropostaChiusa propostaChiusa : insiemeChiuso.getProposteChiuse()) {
				
				if (propostaChiusa.getFruitore().getID().equals(utenteDiSessione.getID())) {
					
					if (insiemeChiuso.getProposteChiuse().get(0)
							.equals(propostaChiusa)) 
						str.append("{");
					
					str.append("\n" + propostaChiusa.toString());
					
					if (insiemeChiuso.getProposteChiuse().get(insiemeChiuso.getProposteChiuse().size()-1)
							.equals(propostaChiusa)) 
						str.append("}\n\n");
					
				}
			}
		}
    	
    	return str.toString();
  
    }
    
    /**
     * Visualizza le proposte ritirate dell'utente di sessione.
     * @return Una stringa contenente le proposte ritirate dell'utente di sessione.
     */
    public String visualizzaProposteRitirate(){
    	
    	StringBuffer str = new StringBuffer();
    	
    	
    	for (PropostaRitirata propostaRitirata : insiemeRitirato.getProposteRitirate()) {
    		if (propostaRitirata.getFruitore().getID().equals(utenteDiSessione.getID())) {
				str.append(propostaRitirata.toString()+ "\n");
			}
		}
    	
    	
    	return str.toString();
  
    }
    
    
    /**
     * Visualizza le proposte aperte dell'utente di sessione.
     * @return Una stringa contenente le proposte aperte dell'utente di sessione.
     */
    public String visualizzaProposteAperte(){
    	
    	StringBuffer str = new StringBuffer();
    	
    	
    	for (PropostaAperta propostaAperta : getInsiemeApertoDiSessione().getProposteAperte()) {
			if (propostaAperta.getFruitore().getID().equals(utenteDiSessione.getID())) {
				str.append(propostaAperta.toString()+ "\n");
			}
		}
    	
    	
    	return str.toString();
  
    	
    }
    
    /**
     * Visualizza le proposte chiuse relative a un nodo specificato.
     * @param nodo Il nodo di riferimento.
     * @return Una stringa contenente le proposte chiuse relative al nodo specificato.
     */
    public String visualizzaProposteChiuse(Nodo nodo){
    	
    	StringBuffer str = new StringBuffer();
    	
    	for (InsiemeChiuso insiemeChiuso : insiemiChiusi) {
			for (PropostaChiusa propostaChiusa : insiemeChiuso.getProposteChiuse()) {
				if 		(propostaChiusa.getOfferta().getFoglia().root().equals(nodo.root()) 
						&& propostaChiusa.getOfferta().getFoglia().getNome().equals(nodo.getNome()) 
						||
						propostaChiusa.getOfferta().getFoglia().root().equals(nodo.root()) 
						&& propostaChiusa.getRichiesta().getFoglia().getNome().equals(nodo.getNome())) {
	    			str.append(propostaChiusa.toString()+ "\n");
	    			
	    		}
			}
		}
    	
    	return str.toString();
  
    }
    
    /**
     * Visualizza le proposte ritirate relative a un nodo specificato.
     * @param nodo Il nodo di riferimento.
     * @return Una stringa contenente le proposte ritirate relative al nodo specificato.
     */
    public String visualizzaProposteRitirate(Nodo nodo){
    	
    	StringBuffer str = new StringBuffer();
    	
    	
    	for (PropostaRitirata propostaRitirata : insiemeRitirato.getProposteRitirate()) {
    		if (propostaRitirata.getOfferta().getFoglia().root().equals(nodo.root()) 
					&& propostaRitirata.getOfferta().getFoglia().getNome().equals(nodo.getNome()) 
					||
					propostaRitirata.getOfferta().getFoglia().root().equals(nodo.root()) 
					&& propostaRitirata.getRichiesta().getFoglia().getNome().equals(nodo.getNome())) {
    			str.append(propostaRitirata.toString()+ "\n");
    			
    		}
		}
    	
    	
    	
    	
    	return str.toString();
  
    }
    
    /**
     * Visualizza le proposte aperte relative a un nodo specificato.
     * @param nodo Il nodo di riferimento.
     * @return Una stringa contenente le proposte aperte relative al nodo specificato.
     */
    public String visualizzaProposteAperte(Nodo nodo){
    	
    	StringBuffer str = new StringBuffer();
    	
    	for (InsiemeAperto insiemeAperto : insiemiAperti) {
			for (PropostaAperta propostaAperta : insiemeAperto.getProposteAperte()) {
				if (propostaAperta.getOfferta().getFoglia().root().equals(nodo.root()) 
						&& propostaAperta.getOfferta().getFoglia().getNome().equals(nodo.getNome()) 
						||
						propostaAperta.getOfferta().getFoglia().root().equals(nodo.root()) 
						&& propostaAperta.getRichiesta().getFoglia().getNome().equals(nodo.getNome())) {
					str.append(propostaAperta.toString()+ "\n");
					
				}
			}
		}
    	
    	return str.toString();
  
    	
    }
	    
}
