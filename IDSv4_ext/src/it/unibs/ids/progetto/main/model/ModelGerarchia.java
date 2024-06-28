package it.unibs.ids.progetto.main.model;

import java.io.Serializable;

import it.unibs.ids.progetto.Albero;

import it.unibs.ids.progetto.DefaultInitializer;
import it.unibs.ids.progetto.FattoriDiConversione;
import it.unibs.ids.progetto.FileManager;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Leaf;
import it.unibs.ids.progetto.NotLeaf;


public class ModelGerarchia implements Serializable {


    private static final long serialVersionUID = 1L;
	private Gerarchia gerarchia;
    
	public ModelGerarchia() {
		
        // Caricamento da file
        this.gerarchia = FileManager.caricaGerarchia();
        
        if ( gerarchia == null ) {
            // Inizializzazione predefinita degli oggetti solo se non sono stati caricati da file
            gerarchia = DefaultInitializer.getDefaultInitializer().getGerarchia();
        } else {
            System.out.println("Lettura da file: " + FileManager.getGerarchiaFile());
        }
	}
	

	public Gerarchia getGerarchia() {
		return gerarchia;
	}

	public void addAlbero(Albero albero) {
		gerarchia.addAlbero(albero);
	}


	public boolean verificaEsistenzaNomeRadice(String radice) {
		return gerarchia.verificaEsistenzaNomeRadice(radice);
	}
	public void addTransitivoFattoreConversione() {
        FattoriDiConversione.addTransitivoFattoreConversione(gerarchia);		
	}
	public Leaf visualizzaFoglia(String foglia, String radice) {
		return gerarchia.visualizzaFoglia(foglia, radice);
	}

	public void save() {
        FileManager.salvaSuFile(gerarchia);

	}


	public NotLeaf visualizzaRadice(String nomeRadice) {
		return gerarchia.visualizzaRadice(nomeRadice);
	}  
}
