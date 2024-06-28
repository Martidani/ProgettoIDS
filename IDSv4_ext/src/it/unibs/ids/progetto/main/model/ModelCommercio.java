package it.unibs.ids.progetto.main.model;

import java.io.Serializable;

import it.unibs.ids.progetto.Commercio;
import it.unibs.ids.progetto.DefaultInitializer;
import it.unibs.ids.progetto.FileManager;
import it.unibs.ids.progetto.Fruitore;
import it.unibs.ids.progetto.InsiemeAperto;
import it.unibs.ids.progetto.PropostaAperta;
import it.unibs.ids.progetto.Utente;


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
	

	public void save() {

		FileManager.salvaSuFile(commercio);
		
	}


	public void ritira(PropostaAperta proposta) {
		commercio.ritira(proposta);
		
	}

	public void metodo(InsiemeAperto insiemeAperto) {
		commercio.cercaProposteChiudibili(insiemeAperto);
		
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
		else
			commercio.setFruitoreDiSessione(null);
		
	}

}
