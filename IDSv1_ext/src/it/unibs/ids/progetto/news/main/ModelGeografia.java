package it.unibs.ids.progetto.news.main;

import java.io.Serializable;


import it.unibs.ids.progetto.Comprensorio;

import it.unibs.ids.progetto.DefaultInitializer;

import it.unibs.ids.progetto.FileManager;
import it.unibs.ids.progetto.Geografia;



public class ModelGeografia implements Serializable {

    private static final long serialVersionUID = 1L;
	private Geografia geografia ;
    
	public ModelGeografia() {
		
        // Caricamento da file

        this.geografia = FileManager.caricaGeografia();
        
        if (geografia == null) {
            // Inizializzazione predefinita degli oggetti solo se non sono stati caricati da file
            geografia = DefaultInitializer.getDefaultInitializer().getGeografia();
        } else {
            System.out.println("Lettura da file: " + FileManager.getGeografiaFile());
        }
	}
	

	public Geografia getGeografia() {
		return geografia;
	}


	public boolean verificaEsistenzaComprensorio(String nome) {
		return geografia.verificaEsistenzaComprensorio(nome);
	}

	public void addComprensorio(Comprensorio comprensorio) {
		geografia.addComprensorio(comprensorio);
	}

	public String toStringGeografia() {
		return geografia.toString();

	}


	public void save() {
        FileManager.salvaSuFile(geografia);
	}  
	
}
