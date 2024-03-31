package it.unibs.ids.progetto.news;

import it.unibs.fp.mylib.InputDati;
import it.unibs.ids.progetto.DefaultInitializer;
import it.unibs.ids.progetto.FileManager;
import it.unibs.ids.progetto.Geografia;
import it.unibs.ids.progetto.Gerarchia;
import it.unibs.ids.progetto.Utenza;


/**
 * Classe che contiene il metodo main per l'esecuzione dell'applicazione.
 * 
 * @author Daniele Martinelli
 * @author Federico Sabbadini
 */
public class Main {


    public static void main(String[] args) throws Exception {

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

        ///////////////sistema menu inizio modalità
        boolean tipoFunzionamento = InputDati.yesOrNo("Vuoi essere un fruitore? ");
       
        MenuController menuController;
        if (tipoFunzionamento)
        	menuController = new MenuFruitorController();
        menuController = new MenuConfController();
       
        int accesso, scelta;
        do {
            accesso = menuController.menuAccesso(utenza, geografia);
        } while (accesso == 1);

        
	    //modalità configuratore        
        if (accesso != 0) {
            do {
                scelta = menuController.menu(gerarchia, geografia);
            } while (scelta != 0);
        }
        
        
	    //modalità fruitore
	    if (accesso != 0) {
	        do {
	            scelta = menuController.menu(gerarchia, geografia);
	        } while (scelta != 0);
	    }

        FileManager.salvaSuFile(gerarchia);
        FileManager.salvaSuFile(utenza);
        FileManager.salvaSuFile(geografia);
    }


}
