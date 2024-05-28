package it.unibs.ids.progetto.news;

import java.util.Map.Entry;

import it.unibs.ids.progetto.Albero;
import it.unibs.ids.progetto.Comprensorio;
import it.unibs.ids.progetto.FattoriDiConversione;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Leaf;
import it.unibs.ids.progetto.Nodo;
import it.unibs.ids.progetto.NotLeaf;

public class PrintManager {
	
	
	  /**
     * Restituisce una rappresentazione testuale dell'albero.
     * 
     * @return Una stringa che rappresenta l'albero
     */
    public static String toStringAlbero(Albero albero) {
        StringBuffer bf = new StringBuffer();
        iterative(bf, albero.getRadice(), 1);
        
        return bf.toString();
    }

    /**
     * Metodo ausiliario ricorsivo per generare una rappresentazione testuale dell'albero.
     * 
     * @param bf Il buffer in cui aggiungere la rappresentazione
     * @param nodo Il nodo corrente
     * @param depth La profondità del nodo nella gerarchia
     */
    private static void iterative(StringBuffer bf, Nodo nodo, int depth) {
        String asterischi = "*".repeat(depth); // Genera una stringa di asterischi in base al grado di "figlio di"
        String blank = " ".repeat(depth + 1);
        if (nodo.isLeaf()) {
            bf.append(asterischi + " " + nodo.getNome());
        } else {
            bf.append(asterischi + NotLeafPrintManager.toString(blank,(NotLeaf)nodo));
            for (Nodo nodoChild : ((NotLeaf)nodo).getChildren()) {
                bf.append("\n");
                iterative(bf, nodoChild, depth + 1);
            }
        }
    }
    
    /**
     * Restituisce una rappresentazione testuale del comprensorio
     * con elencati i nomi dei comuni presenti.
     * 
     * @return String La rappresentazione testuale del comprensorio
     */
    public static String toStringComprensorio(Comprensorio comprensorio) {
        StringBuffer bf = new StringBuffer();
        bf.append(comprensorio.getNome() + "\n");
        for (String comune : comprensorio.getComprensorio()) {
            bf.append(" - " + comune);
            bf.append("\n");
        }
        
        return bf.toString();
    }



	/**
	 * Genera una stringa delle relazioni di una foglia con le altre foglie.
	 * 
	 * @return Una stringa rappresentante le relazioni con le foglie.
	 */
	public static String toString(String nome, FattoriDiConversione fattori) {
		StringBuffer bf = new StringBuffer();
		bf.append("\n");
		for (Entry<Leaf, Double> fatt : fattori.getFattori()) {
			Leaf key = fatt.getKey();
			Double val = fatt.getValue();
			if (!nome.equals(key.getNome())) {
				// Formatta il double con un massimo di tre decimali
				String formattedVal = String.format("%.3f", val);
				bf.append(nome + " - " + key.getNome() + " - " + formattedVal);
				bf.append("\n");
			}
		}
		return bf.toString();
	}
    
	
	/**
	 * Restituisce una rappresentazione testuale della geografia, includendo la lista dei comprensori.
	 * 
	 * @return Una stringa che rappresenta la geografia
	 */
	public static String toString(Geografia geografia) {
		StringBuffer bf = new StringBuffer();
		for (Comprensorio comprensorio : geografia.getComprensori()) {
			bf.append(toStringComprensorio(comprensorio));
			bf.append("\n");
		}
		
		return bf.toString();
	}
	
	  
    /**
     * Restituisce una rappresentazione testuale della 
     * gerarchia (di tutti i suoi alberi).
     * 
     * @return Una stringa che rappresenta la gerarchia
     */
    public static String toString(Gerarchia gerarchia) {
    	StringBuffer builder = new StringBuffer();
        
        for (Nodo nodo : gerarchia.getRadici())  {
            builder.append("\n\n");
            try {
                builder.append(toStringAlbero(new Albero((NotLeaf) nodo)));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        builder.append("\n\n");
        return builder.toString();     
    }
    
    /**
     * Restituisce una rappresentazione testuale della 
     * gerarchia (di tutti i suoi alberi).
     * 
     * @param alberi La lista degli alberi di nodi
     * @return Una stringa che rappresenta la gerarchia
     */
    public static String toStringRadici(Gerarchia gerarchia) {
        StringBuffer bf = new StringBuffer();
        
        for (Nodo nodo : gerarchia.getRadici())  {
            bf.append("\n* " + nodo.getNome());

        }
        return bf.toString();
            
    }
    
    
    public static String visualizzaProposteChiuse(Commercio commercio){
    	
    	StringBuffer str = new StringBuffer();
    	
    	for (InsiemeChiuso insiemeChiuso : commercio.getInsiemiChiusi()) {
			for (PropostaChiusa propostaChiusa : insiemeChiuso.getProposteChiuse()) {
				
				if (propostaChiusa.getFruitore().getID().equals(commercio.getFruitoreDiSessione().getID())) {
					
					if (insiemeChiuso.getProposteChiuse().get(0)
							.equals(propostaChiusa)) 
						str.append("{");
					
					str.append("\n" + toStringProposta(propostaChiusa));
					
					if (insiemeChiuso.getProposteChiuse().get(insiemeChiuso.getProposteChiuse().size()-1)
							.equals(propostaChiusa)) 
						str.append("}\n\n");
					
				}
			}
		}
    	
    	return str.toString();
  
    }
    
    public static String visualizzaProposteRitirate(Commercio commercio){
    	
    	StringBuffer str = new StringBuffer();
    	
		str.append("{");
    	for (PropostaRitirata propostaRitirata : commercio.getInsiemeRitirato().getProposteRitirate()) {
    		if (propostaRitirata.getFruitore().getID().equals(commercio.getFruitoreDiSessione().getID())) {
				str.append(toStringProposta(propostaRitirata)+ "\n");
			}
		}
    	str.append("}\n\n");
    	
    	return str.toString();
  
    }
    
    
    public static String visualizzaProposteAperte(Commercio commercio){
    	
    	StringBuffer str = new StringBuffer();
    	
		str.append("{");
    	for (PropostaAperta propostaAperta : commercio.getInsiemeApertoDiSessione().getProposteAperte()) {
			if (propostaAperta.getFruitore().getID().equals(commercio.getFruitoreDiSessione().getID())) {
				str.append(toStringProposta(propostaAperta)+ "\n");
			}
		}
    	
    	str.append("}\n\n");
    	return str.toString();
  
    	
    }
    

    public static String visualizzaProposteChiuse(Nodo nodo,Commercio commercio){
    	
    	StringBuffer str = new StringBuffer();
		str.append("{");

    	for (InsiemeChiuso insiemeChiuso : commercio.getInsiemiChiusi()) {
			for (PropostaChiusa propostaChiusa : insiemeChiuso.getProposteChiuse()) {
				if 		(propostaChiusa.getOfferta().getFoglia().root().equals(nodo.root()) 
						&& propostaChiusa.getOfferta().getFoglia().getNome().equals(nodo.getNome()) 
						||
						propostaChiusa.getOfferta().getFoglia().root().equals(nodo.root()) 
						&& propostaChiusa.getRichiesta().getFoglia().getNome().equals(nodo.getNome())) {
	    			str.append(toStringProposta(propostaChiusa)+ "\n");
	    			
	    		}
			}
		}
    	str.append("}\n\n");
    	return str.toString();
  
    }
    
    public static String visualizzaProposteRitirate(Nodo nodo,Commercio commercio){
    	
    	StringBuffer str = new StringBuffer();
		str.append("{");

    	
    	for (PropostaRitirata propostaRitirata : commercio.getInsiemeRitirato().getProposteRitirate()) {
    		if (propostaRitirata.getOfferta().getFoglia().root().equals(nodo.root()) 
					&& propostaRitirata.getOfferta().getFoglia().getNome().equals(nodo.getNome()) 
					||
					propostaRitirata.getOfferta().getFoglia().root().equals(nodo.root()) 
					&& propostaRitirata.getRichiesta().getFoglia().getNome().equals(nodo.getNome())) {
    			str.append(toStringProposta(propostaRitirata)+ "\n");
    			
    		}
		}
    	
    	
    	
    	str.append("}\n\n");
    	return str.toString();
  
    }
    
    public static String visualizzaProposteAperte(Nodo nodo,Commercio commercio){
    	
    	StringBuffer str = new StringBuffer();
		str.append("{");

    	for (InsiemeAperto insiemeAperto : commercio.getInsiemiAperti()) {
			for (PropostaAperta propostaAperta : insiemeAperto.getProposteAperte()) {
				if (propostaAperta.getOfferta().getFoglia().root().equals(nodo.root()) 
						&& propostaAperta.getOfferta().getFoglia().getNome().equals(nodo.getNome()) 
						||
						propostaAperta.getOfferta().getFoglia().root().equals(nodo.root()) 
						&& propostaAperta.getRichiesta().getFoglia().getNome().equals(nodo.getNome())) {
					str.append(toStringProposta(propostaAperta) + "\n");
					
				}
			}
		}
    	str.append("}\n\n");
    	return str.toString();
  
    	
    }
    
    // Insieme aperto - Proposta aperta
    
    public static String toStringAperta(InsiemeAperto insiemeAperto) {
    	StringBuffer str = new StringBuffer();
    	
    	for (PropostaAperta propostaAperta : insiemeAperto.getProposteAperte()) {
			str.append(toStringProposta(propostaAperta)+"\n");
		}
    	
		return str.toString();    	
    }
    
    
    // Insieme chiuso
    public static String toStringChiuso(InsiemeChiuso insiemeChiuso) {
    	StringBuffer str = new StringBuffer();
    	
    	for (PropostaChiusa propostachiusa : insiemeChiuso.getProposteChiuse()) {
			str.append(toStringProposta(propostachiusa)+"\n");
		}
    	
		return str.toString();    	
    }
    
    
    // Iniseme ritirato
    
    public static String toStringRitirato(InsiemeRitirato insiemeRitirato) {
    	StringBuffer str = new StringBuffer();
    	
    	for (PropostaRitirata propostaRitirata : insiemeRitirato.getProposteRitirate()) {
			str.append(toStringProposta(propostaRitirata)+"\n");
		}
    	
		return str.toString();    	
    }
    
    
    // Classe proposta
    
    public static String toStringProposta(Proposta proposta) {
    	StringBuffer str = new StringBuffer();
    	
    	str.append("Richiesta " + toStringPrestazione(proposta.getRichiesta()));
    	str.append("\nOfferta " + toStringPrestazione(proposta.getOfferta()));
    	str.append("\nID: " + proposta.getID() + "\n");
    	
		return str.toString();    	
    }
    
    
    public static String toStringPrestazione(PrestazioneOpera prestazione) {
    	StringBuffer str = new StringBuffer();
    	
    	str.append(" [" + prestazione.getFoglia().getNome()
    			+ " (" + prestazione.getFoglia().root() + "), " 
    			+ prestazione.getDurata() + "]");
    	
		return str.toString();    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
