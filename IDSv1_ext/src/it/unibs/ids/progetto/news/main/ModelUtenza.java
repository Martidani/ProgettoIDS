package it.unibs.ids.progetto.news.main;


import java.io.Serializable;

import it.unibs.ids.progetto.Configuratore;
import it.unibs.ids.progetto.DefaultInitializer;

import it.unibs.ids.progetto.FileManager;

import it.unibs.ids.progetto.Utente;
import it.unibs.ids.progetto.Utenza;


public class ModelUtenza implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private Utenza utenza;
    
	public ModelUtenza() {
		
        // Caricamento da file
        this.utenza = FileManager.caricaUtenza();

        
        if ( this.utenza == null) {
            // Inizializzazione predefinita degli oggetti solo se non sono stati caricati da file
        	 this.utenza = DefaultInitializer.getDefaultInitializer().getUtenza();

        } else {
            System.out.println("Lettura da file: " + FileManager.getUtenzaFile());
        }
	}
	
	public Utenza getUtenza() {
		return utenza;
	}


	public void addUtente(Configuratore configuratore) {
		utenza.addUtente(configuratore);
	}
	public Utente autenticazioneConfiguratore(String iD, String pSSW) {
		return utenza.autenticazioneConfiguratore(iD, pSSW);
	}
	public boolean verificaEsistenzaID(String iD) {
		return utenza.verificaEsistenzaID(iD);
	}


	public void save() {

        FileManager.salvaSuFile(utenza);
	}  
	
}
