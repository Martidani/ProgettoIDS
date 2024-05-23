package it.unibs.ids.progetto.news.main.model;

import java.io.Serializable;

import it.unibs.ids.progetto.DefaultInitializer;
import it.unibs.ids.progetto.FileManager;
import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.Nodo;
import it.unibs.ids.progetto.Utente;
import it.unibs.ids.progetto.news.Commercio;
import it.unibs.ids.progetto.news.InsiemeAperto;
import it.unibs.ids.progetto.news.PropostaAperta;

public class ModelCommercio implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Commercio commercio ;
    
	public ModelCommercio() {
		
        // Caricamento da file

        this.commercio = FileManager.caricaCommercio();
        
        if (commercio == null) {
            // Inizializzazione predefinita degli oggetti solo se non sono stati caricati da file
        	commercio = DefaultInitializer.getDefaultInitializer().getCommercio();
        } else {
            System.out.println("Lettura da file: " + FileManager.getCommercioFile());
        }
	}
	

	public Commercio getCommercio() {
		return commercio;
	}
	
	
	public String visualizzaProposteAperte(Nodo foglia) {
		if (foglia == null) 
			return commercio.visualizzaProposteAperte();
		else
			return commercio.visualizzaProposteAperte(foglia);
		
	}


	public String visualizzaProposteChiuse(Nodo foglia) {
		if (foglia == null) 
			return commercio.visualizzaProposteChiuse();
		else
			return commercio.visualizzaProposteChiuse(foglia);
	}

	public String visualizzaProposteRitirate(Nodo foglia) {
		if (foglia == null) 
			return commercio.visualizzaProposteRitirate();
		else
			return commercio.visualizzaProposteRitirate(foglia);
	}

	public void save() {

		FileManager.salvaSuFile(commercio);
		
	}


	public void ritira(PropostaAperta proposta) {
		commercio.ritira(proposta);
		
	}

	public void metodo(InsiemeAperto insiemeAperto) {
		commercio.metodo(insiemeAperto);
		
	}

	public int numProposte() {
		return commercio.numProposte();
	}

	public InsiemeAperto getInsiemeApertoDiSessione() {
		return commercio.getInsiemeApertoDiSessione();
	}

	public void decrementaNumProposte() {
		commercio.decrementaNumProposte();
		
	}

	public PropostaAperta cercaProposta(int s1) {
		return commercio.cercaProposta(s1);
	}


	public void setUtenteDiSessione(Utente utenteDiSessione) {
		if (utenteDiSessione.getTipoUtente() == Fruitore.TIPOUTENTE)
			commercio.setFruitoreDiSessione((Fruitore) utenteDiSessione);
		
	}

}
