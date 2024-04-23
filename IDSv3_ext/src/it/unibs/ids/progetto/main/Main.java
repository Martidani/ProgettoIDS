package it.unibs.ids.progetto.main;

import it.unibs.fp.mylib.InputDati;
import it.unibs.ids.progetto.DefaultInitializer;
import it.unibs.ids.progetto.FileManager;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.RootTreeException;
import it.unibs.ids.progetto.Utenza;



/**
 * Classe che contiene il metodo main per l'esecuzione dell'applicazione.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class Main {


    public static void main(String[] args) throws RootTreeException  {

        // Caricamento da file
        Utenza utenza = FileManager.caricaUtenza();
        Gerarchia gerarchia = FileManager.caricaGerarchia();
        Geografia geografia = FileManager.caricaGeografia();

        if (utenza == null || gerarchia == null || geografia == null) {
            // Inizializzazione predefinita degli oggetti solo se non sono stati caricati da file
            DefaultInitializer defaultInitializer = DefaultInitializer.getDefaultInitializer();
            gerarchia = defaultInitializer.getGerarchia();
            utenza = defaultInitializer.getUtenza();
            geografia = defaultInitializer.getGeografia();
        } else {
            System.out.println("Lettura da file: " + FileManager.getUtenzaFile() 
            + ", " + FileManager.getGerarchiaFile() + ", " + FileManager.getGeografiaFile());
        }
        
        
	    //accensione del sistema
        MainView menuController;
        int num = InputDati.leggiInteroRange(" \n Modalità di funzionamento:\n 1 - Configuratore\n 2- Fruitore\n --> ", 1, 2);
        if (num==1)
        	menuController = new MainConfView();
        else
        	menuController = new MainFruitorView();
        
        
	    //modalità fruitore/configuratore
        int scelta;
        do {
        	scelta = menuController.menuAccesso(utenza, geografia);
        } while (scelta == 1);

        
	    if (scelta != 0) {
	        do {
	            scelta = menuController.menu(gerarchia, geografia, utenza);
	        } while (scelta != 0);
	    }

	    //salva su file
        FileManager.salvaSuFile(gerarchia);
        FileManager.salvaSuFile(utenza);
        FileManager.salvaSuFile(geografia);
    }


}
