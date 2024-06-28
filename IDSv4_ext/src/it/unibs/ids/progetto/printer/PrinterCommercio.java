package it.unibs.ids.progetto.printer;

import it.unibs.ids.progetto.Commercio;
import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.InsiemeAperto;
import it.unibs.ids.progetto.InsiemeChiuso;
import it.unibs.ids.progetto.Nodo;
import it.unibs.ids.progetto.PrestazioneOpera;
import it.unibs.ids.progetto.Proposta;
import it.unibs.ids.progetto.PropostaAperta;
import it.unibs.ids.progetto.PropostaChiusa;
import it.unibs.ids.progetto.PropostaRitirata;

public class PrinterCommercio {
 
	
	private Commercio commercio ;
    
	public PrinterCommercio(Commercio commercio) {
        this.commercio = commercio;     
	}
	
	private static String toStringProposta(Proposta proposta) {
    	StringBuffer str = new StringBuffer();
    	
    	str.append("\nID: " + proposta.getID());
    	str.append("\n  Richiesta " + toStringPrestazione(proposta.getRichiesta()));
    	str.append("\n  Offerta " + toStringPrestazione(proposta.getOfferta()));
    	
		return str.toString();    	
    }
 
    
    private static String toStringPrestazione(PrestazioneOpera prestazione) {
    	StringBuffer str = new StringBuffer();
    	
    	str.append(" [" + prestazione.getFoglia().getNome()
    			+ " (" + prestazione.getFoglia().root() + "), " 
    			+ prestazione.getDurata() + "]");
    	
		return str.toString();    	
    }
    
    public String visualizzaProposteChiuse(){
    	
    	StringBuffer str = new StringBuffer();
    	
    	for (InsiemeChiuso insiemeChiuso : commercio.getInsiemiChiusi()) {
			for (PropostaChiusa propostaChiusa : insiemeChiuso.getProposteChiuse()) {
				
				if (propostaChiusa.getFruitore().getID().equals(commercio.getFruitoreDiSessione().getID())) {
					
					str.append(toStringProposta(propostaChiusa) + "\n");
					
					
					
				}
			}
		}
    	
    	return str.toString();
  
    }
    
    public String visualizzaProposteRitirate(){
    	
    	StringBuffer str = new StringBuffer();
    	
    	for (PropostaRitirata propostaRitirata : commercio.getInsiemeRitirato().getProposteRitirate()) {
    		if (propostaRitirata.getFruitore().getID().equals(commercio.getFruitoreDiSessione().getID())) {
				str.append(toStringProposta(propostaRitirata)+ "\n");
			}
		}

    	
    	return str.toString();
  
    }
    
    
    public String visualizzaProposteAperte(){
    	
    	StringBuffer str = new StringBuffer();
    	

    	for (PropostaAperta propostaAperta : commercio.getInsiemeApertoDiSessione().getProposteAperte()) {
			if (propostaAperta.getFruitore().getID().equals(commercio.getFruitoreDiSessione().getID())) {
				str.append(toStringProposta(propostaAperta)+ "\n");
			}
		}
    	

    	return str.toString();
  
    	
    }
    

    public String visualizzaProposteChiuse(Nodo nodo){
    	
    	StringBuffer str = new StringBuffer();


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

    	return str.toString();
  
    }
    
    public String visualizzaProposteRitirate(Nodo nodo){
    	
    	StringBuffer str = new StringBuffer();


    	
    	for (PropostaRitirata propostaRitirata : commercio.getInsiemeRitirato().getProposteRitirate()) {
    		if (propostaRitirata.getOfferta().getFoglia().root().equals(nodo.root()) 
					&& propostaRitirata.getOfferta().getFoglia().getNome().equals(nodo.getNome()) 
					||
					propostaRitirata.getOfferta().getFoglia().root().equals(nodo.root()) 
					&& propostaRitirata.getRichiesta().getFoglia().getNome().equals(nodo.getNome())) {
    			str.append(toStringProposta(propostaRitirata)+ "\n");
    			
    		}
		}
    	
    	
    	

    	return str.toString();
  
    }
    
    public String visualizzaProposteAperte(Nodo nodo){
    	
    	StringBuffer str = new StringBuffer();


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
  
    	return str.toString();
  
    	
    }

	public String visualizzaProposteChiuseCommercio() {
    	StringBuffer str = new StringBuffer();


    	for (InsiemeChiuso insiemeChiuso : this.commercio.getInsiemiChiusi()) {
    		str.append("\n{");
			for (PropostaChiusa propostaChiusa : insiemeChiuso.getProposteChiuse()) {
				str.append(toStringProposta(propostaChiusa));
				Fruitore fruitore = propostaChiusa.getFruitore();
				str.append(PrinterUtenza.toStringFruitore(fruitore));
			}
    		str.append("}\n");
		}

    	return str.toString();
  
    }
	
}
