package it.unibs.ids.progetto.main.model;

import java.io.Serializable;


import it.unibs.ids.progetto.Comprensorio;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.servizi.DefaultInitializer;
import it.unibs.ids.progetto.servizi.FileManager;




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



	public void save() {
        FileManager.salvaSuFile(geografia);
	}


	public Comprensorio cercaComprensorio(String c) {
		return geografia.cercaComprensorio(c);
	}  
	
}
